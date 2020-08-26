import GLOOP.*;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;

public class PONG
{
    GLLicht light;
    GLKamera camera, vrCamera;

    GLTastatur keyboard;

    Music musicPlayer1, musicPlayer2, touchEffect1, touchEffect2, point;

    GLQuader border01, border02, border03, border04, border05, border06, border07, border08, border09, border10, border11, border12;

    GLQuader item;

    GLQuader player1, player2;
    GLKugel ball1;

    String log = "", newestLogLine;

    Number player1Number1, player2Number1, player1Number2, player2Number2;

    int screenXY;

    int player1Points = 0, player2Points = 0;

    double directionX = 3*1.5, directionY = 0.6*1.5, directionZ = 0;

    double random;

    int random2;

    boolean paused;

    public PONG() {
        light = new GLLicht();
        camera = new GLEntwicklerkamera(screenSize("x"), screenSize("y"));
        camera.setzeBlickpunkt(0, 75, 0);
        camera.setzePosition(0, 75, 500);

        keyboard = new GLTastatur();

        musicPlayer1 = new Music("/Music/Playing/488500.wav");
        musicPlayer1.startMusic(10000);         
        musicPlayer2 = new Music("/Music/Paused/805774.wav");

        player1Number1 = new Number();
        player1Number1.zero();
        player1Number1.move(-50, 200, 0);
        player2Number1 = new Number();
        player2Number1.zero();
        player2Number1.move(50, 200, 0);

        player1Number2 = new Number();
        player1Number2.zero();
        player1Number2.move(-65, 380, 0);
        player1Number2.visibility(false);
        player2Number2 = new Number();
        player2Number2.zero();
        player2Number2.move(35, 380, 0);
        player2Number2.visibility(false);

        border01 = new GLQuader(0, -0.5, 25.5, 240, 1, 1);
        border02 = new GLQuader(0, -0.5, -25.5, 240, 1, 1);
        border03 = new GLQuader(0, 150.5, 25.5, 240, 1, 1);
        border04 = new GLQuader(0, 150.5, -25.5, 240, 1, 1);

        border05 = new GLQuader(-119.5, 75, 25.5, 1, 150, 1);
        border06 = new GLQuader(-119.5, 75, -25.5, 1, 150, 1);
        border07 = new GLQuader(119.5, 75, 25.5, 1, 150, 1);
        border08 = new GLQuader(119.5, 75, -25.5, 1, 150, 1);

        border09 = new GLQuader(119.5, -0.5, 0, 1, 1, 50);
        border10 = new GLQuader(-119.5, -0.5, 0, 1, 1, 50);
        border11 = new GLQuader(119.5, 150.5, 0, 1, 1, 50);
        border12 = new GLQuader(-119.5, 150.5, 0, 1, 1, 50);

        item = new GLQuader(0, 75, 0, 1, 150, 50);
        item.setzeSichtbarkeit(false);

        player1 = new GLQuader(-105, 75, 0, 20, 20, 20);
        player2 = new GLQuader(105, 75, 0, 20, 20, 20);

        ball1 = new GLKugel(0, 75, 0, 5);
        ball1.setzeQualitaet(25);
        ball1.setzeSelbstleuchten(0, 1, 1);

        moveBalls();
    }

    public void moveBalls() {
        while (player1Points < 100 && player2Points < 100 && !paused) {
            ball1.verschiebe(directionX, directionY, directionZ);

            Sys.warte(33);

            checkKeyboard();

            ballTouchesWall();

            rotateCamera();

            moveNumbers();

            if (directionX > 0 && directionX < 1.5){
                directionX = 1.5;
            }
            if (directionX < 0 && directionX > -1.5){
                directionX = -1.5;
            }
            if (directionX > 0 && directionX > 6.5){
                directionX = 6.5;
            }
            if (directionX < 0 && directionX < -6.5){
                directionX = -6.5;
            }
            if (directionY > 0 && directionY < 1.5){
                directionY = 1.5;
            }
            if (directionY < 0 && directionY > -1.5){
                directionY = -1.5;
            }
            if (directionY > 0 && directionY > 6.5){
                directionY = 6.5;
            }
            if (directionY < 0 && directionY < -6.5){
                directionY = -6.5;
            }

            writeLog();
        }
        while (paused) {
            checkKeyboard();
        }
    }

    public void setNumbers(int n1, int n2) {
        if(n1 == 0) {
            player1Number1.zero();
        } else if(n1 == 1) {
            player1Number1.one();
        } else if(n1 == 2) {
            player1Number1.two();
        } else if(n1 == 3) {
            player1Number1.three();
        } else if(n1 == 4) {
            player1Number1.four();
        } else if(n1 == 5) {
            player1Number1.five();
        } else if(n1 == 6) {
            player1Number1.six();
        } else if(n1 == 7) {
            player1Number1.seven();
        } else if(n1 == 8) {
            player1Number1.eight();
        } else if(n1 == 9) {
            player1Number1.nine();
        } else if(n1 == 10) {
            player1Number1.zero();
            player1Number2.one();
        } else if(n1 == 11) {
            player1Number1.one();
            player1Number2.one();
        } else if(n1 == 12) {
            player1Number1.two();
            player1Number2.one();
        } else if(n1 == 13) {
            player1Number1.three();
            player1Number2.one();
        } else if(n1 == 14) {
            player1Number1.four();
            player1Number2.one();
        } else if(n1 == 15) {
            player1Number1.five();
            player1Number2.one();
        } else if(n1 == 16) {
            player1Number1.six();
            player1Number2.one();
        } else if(n1 == 17) {
            player1Number1.seven();
            player1Number2.one();
        } else if(n1 == 18) {
            player1Number1.eight();
            player1Number2.one();
        } else if(n1 == 19) {
            player1Number1.nine();
            player1Number2.one();
        } else if(n1 == 20) {
            player1Number1.zero();
            player1Number2.two();
        } else if(n1 == 21) {
            player1Number1.one();
            player1Number2.two();
        } else if(n1 == 22) {
            player1Number1.two();
            player1Number2.two();
        } else if(n1 == 23) {
            player1Number1.three();
            player1Number2.two();
        } else if(n1 == 24) {
            player1Number1.four();
            player1Number2.two();
        } else if(n1 == 25) {
            player1Number1.five();
            player1Number2.two();
        } else if(n1 == 26) {
            player1Number1.six();
            player1Number2.two();
        } else if(n1 == 27) {
            player1Number1.seven();
            player1Number2.two();
        } else if(n1 == 28) {
            player1Number1.eight();
            player1Number2.two();
        } else if(n1 == 29) {
            player1Number1.nine();
            player1Number2.two();
        } else if(n1 == 30) {
            player1Number1.zero();
            player1Number2.three();
        } else if(n1 == 31) {
            player1Number1.one();
            player1Number2.three();
        } else if(n1 == 32) {
            player1Number1.two();
            player1Number2.three();
        } else if(n1 == 33) {
            player1Number1.three();
            player1Number2.three();
        } else if(n1 == 34) {
            player1Number1.four();
            player1Number2.three();
        } else if(n1 == 35) {
            player1Number1.five();
            player1Number2.three();
        } else if(n1 == 36) {
            player1Number1.six();
            player1Number2.three();
        } else if(n1 == 37) {
            player1Number1.seven();
            player1Number2.three();
        } else if(n1 == 38) {
            player1Number1.eight();
            player1Number2.three();
        } else if(n1 == 39) {
            player1Number1.nine();
            player1Number2.three();
        } else if(n1 == 40) {
            player1Number1.zero();
            player1Number2.four();
        } else if(n1 == 41) {
            player1Number1.one();
            player1Number2.four();
        } else if(n1 == 42) {
            player1Number1.two();
            player1Number2.four();
        } else if(n1 == 43) {
            player1Number1.three();
            player1Number2.four();
        } else if(n1 == 44) {
            player1Number1.four();
            player1Number2.four();
        } else if(n1 == 45) {
            player1Number1.five();
            player1Number2.four();
        } else if(n1 == 46) {
            player1Number1.six();
            player1Number2.four();
        } else if(n1 == 47) {
            player1Number1.seven();
            player1Number2.four();
        } else if(n1 == 48) {
            player1Number1.eight();
            player1Number2.four();
        } else if(n1 == 49) {
            player1Number1.nine();
            player1Number2.four();
        } else if(n1 == 50) {
            player1Number1.zero();
            player1Number2.five();
        } else if(n1 == 51) {
            player1Number1.one();
            player1Number2.five();
        } else if(n1 == 52) {
            player1Number1.two();
            player1Number2.five();
        } else if(n1 == 53) {
            player1Number1.three();
            player1Number2.five();
        } else if(n1 == 54) {
            player1Number1.four();
            player1Number2.five();
        } else if(n1 == 55) {
            player1Number1.five();
            player1Number2.five();
        } else if(n1 == 56) {
            player1Number1.six();
            player1Number2.five();
        } else if(n1 == 57) {
            player1Number1.seven();
            player1Number2.five();
        } else if(n1 == 58) {
            player1Number1.eight();
            player1Number2.five();
        } else if(n1 == 59) {
            player1Number1.nine();
            player1Number2.five();
        } else if(n1 == 60) {
            player1Number1.zero();
            player1Number2.six();
        } else if(n1 == 61) {
            player1Number1.one();
            player1Number2.six();
        } else if(n1 == 62) {
            player1Number1.two();
            player1Number2.six();
        } else if(n1 == 63) {
            player1Number1.three();
            player1Number2.six();
        } else if(n1 == 64) {
            player1Number1.four();
            player1Number2.six();
        } else if(n1 == 65) {
            player1Number1.five();
            player1Number2.six();
        } else if(n1 == 66) {
            player1Number1.six();
            player1Number2.six();
        } else if(n1 == 67) {
            player1Number1.seven();
            player1Number2.six();
        } else if(n1 == 68) {
            player1Number1.eight();
            player1Number2.six();
        } else if(n1 == 69) {
            player1Number1.nine();
            player1Number2.six();
        } else if(n1 == 70) {
            player1Number1.zero();
            player1Number2.seven();
        } else if(n1 == 71) {
            player1Number1.one();
            player1Number2.seven();
        } else if(n1 == 72) {
            player1Number1.two();
            player1Number2.seven();
        } else if(n1 == 73) {
            player1Number1.three();
            player1Number2.seven();
        } else if(n1 == 74) {
            player1Number1.four();
            player1Number2.seven();
        } else if(n1 == 75) {
            player1Number1.five();
            player1Number2.seven();
        } else if(n1 == 76) {
            player1Number1.six();
            player1Number2.seven();
        } else if(n1 == 77) {
            player1Number1.seven();
            player1Number2.seven();
        } else if(n1 == 78) {
            player1Number1.eight();
            player1Number2.seven();
        } else if(n1 == 79) {
            player1Number1.nine();
            player1Number2.seven();
        } else if(n1 == 80) {
            player1Number1.zero();
            player1Number2.eight();
        } else if(n1 == 81) {
            player1Number1.one();
            player1Number2.eight();
        } else if(n1 == 82) {
            player1Number1.two();
            player1Number2.eight();
        } else if(n1 == 83) {
            player1Number1.three();
            player1Number2.eight();
        } else if(n1 == 84) {
            player1Number1.four();
            player1Number2.eight();
        } else if(n1 == 85) {
            player1Number1.five();
            player1Number2.eight();
        } else if(n1 == 86) {
            player1Number1.six();
            player1Number2.eight();
        } else if(n1 == 87) {
            player1Number1.seven();
            player1Number2.eight();
        } else if(n1 == 88) {
            player1Number1.eight();
            player1Number2.eight();
        } else if(n1 == 89) {
            player1Number1.nine();
            player1Number2.eight();
        } else if(n1 == 90) {
            player1Number1.zero();
            player1Number2.nine();
        } else if(n1 == 91) {
            player1Number1.one();
            player1Number2.nine();
        } else if(n1 == 92) {
            player1Number1.two();
            player1Number2.nine();
        } else if(n1 == 93) {
            player1Number1.three();
            player1Number2.nine();
        } else if(n1 == 94) {
            player1Number1.four();
            player1Number2.nine();
        } else if(n1 == 95) {
            player1Number1.five();
            player1Number2.nine();
        } else if(n1 == 96) {
            player1Number1.six();
            player1Number2.nine();
        } else if(n1 == 97) {
            player1Number1.seven();
            player1Number2.nine();
        } else if(n1 == 98) {
            player1Number1.eight();
            player1Number2.nine();
        } else if(n1 == 99) {
            player1Number1.nine();
            player1Number2.nine();
        }

        if(n2 == 0) {
            player2Number1.zero();
        } else if(n2 == 1) {
            player2Number1.one();
        } else if(n2 == 2) {
            player2Number1.two();
        } else if(n2 == 3) {
            player2Number1.three();
        } else if(n2 == 4) {
            player2Number1.four();
        } else if(n2 == 5) {
            player2Number1.five();
        } else if(n2 == 6) {
            player2Number1.six();
        } else if(n2 == 7) {
            player2Number1.seven();
        } else if(n2 == 8) {
            player2Number1.eight();
        } else if(n2 == 9) {
            player2Number1.nine();
        } else if(n2 == 10) {
            player2Number1.zero();
            player2Number2.one();
        } else if(n2 == 11) {
            player2Number1.one();
            player2Number2.one();
        } else if(n2 == 12) {
            player2Number1.two();
            player2Number2.one();
        } else if(n2 == 13) {
            player2Number1.three();
            player2Number2.one();
        } else if(n2 == 14) {
            player2Number1.four();
            player2Number2.one();
        } else if(n2 == 15) {
            player2Number1.five();
            player2Number2.one();
        } else if(n2 == 16) {
            player2Number1.six();
            player2Number2.one();
        } else if(n2 == 17) {
            player2Number1.seven();
            player2Number2.one();
        } else if(n2 == 18) {
            player2Number1.eight();
            player2Number2.one();
        } else if(n2 == 19) {
            player2Number1.nine();
            player2Number2.one();
        } else if(n2 == 20) {
            player2Number1.zero();
            player2Number2.two();
        } else if(n2 == 21) {
            player2Number1.one();
            player2Number2.two();
        } else if(n2 == 22) {
            player2Number1.two();
            player2Number2.two();
        } else if(n2 == 23) {
            player2Number1.three();
            player2Number2.two();
        } else if(n2 == 24) {
            player2Number1.four();
            player2Number2.two();
        } else if(n2 == 25) {
            player2Number1.five();
            player2Number2.two();
        } else if(n2 == 26) {
            player2Number1.six();
            player2Number2.two();
        } else if(n2 == 27) {
            player2Number1.seven();
            player2Number2.two();
        } else if(n2 == 28) {
            player2Number1.eight();
            player2Number2.two();
        } else if(n2 == 29) {
            player2Number1.nine();
            player2Number2.two();
        } else if(n2 == 30) {
            player2Number1.zero();
            player2Number2.three();
        } else if(n2 == 31) {
            player2Number1.one();
            player2Number2.three();
        } else if(n2 == 32) {
            player2Number1.two();
            player2Number2.three();
        } else if(n2 == 33) {
            player2Number1.three();
            player2Number2.three();
        } else if(n2 == 34) {
            player2Number1.four();
            player2Number2.three();
        } else if(n2 == 35) {
            player2Number1.five();
            player2Number2.three();
        } else if(n2 == 36) {
            player2Number1.six();
            player2Number2.three();
        } else if(n2 == 37) {
            player2Number1.seven();
            player2Number2.three();
        } else if(n2 == 38) {
            player2Number1.eight();
            player2Number2.three();
        } else if(n2 == 39) {
            player2Number1.nine();
            player2Number2.three();
        } else if(n2 == 40) {
            player2Number1.zero();
            player2Number2.four();
        } else if(n2 == 41) {
            player2Number1.one();
            player2Number2.four();
        } else if(n2 == 42) {
            player2Number1.two();
            player2Number2.four();
        } else if(n2 == 43) {
            player2Number1.three();
            player2Number2.four();
        } else if(n2 == 44) {
            player2Number1.four();
            player2Number2.four();
        } else if(n2 == 45) {
            player2Number1.five();
            player2Number2.four();
        } else if(n2 == 46) {
            player2Number1.six();
            player2Number2.four();
        } else if(n2 == 47) {
            player2Number1.seven();
            player2Number2.four();
        } else if(n2 == 48) {
            player2Number1.eight();
            player2Number2.four();
        } else if(n2 == 49) {
            player2Number1.nine();
            player2Number2.four();
        } else if(n2 == 50) {
            player2Number1.zero();
            player2Number2.five();
        } else if(n2 == 51) {
            player2Number1.one();
            player2Number2.five();
        } else if(n2 == 52) {
            player2Number1.two();
            player2Number2.five();
        } else if(n2 == 53) {
            player2Number1.three();
            player2Number2.five();
        } else if(n2 == 54) {
            player2Number1.four();
            player2Number2.five();
        } else if(n2 == 55) {
            player2Number1.five();
            player2Number2.five();
        } else if(n2 == 56) {
            player2Number1.six();
            player2Number2.five();
        } else if(n2 == 57) {
            player2Number1.seven();
            player2Number2.five();
        } else if(n2 == 58) {
            player2Number1.eight();
            player2Number2.five();
        } else if(n2 == 59) {
            player2Number1.nine();
            player2Number2.five();
        } else if(n2 == 60) {
            player2Number1.zero();
            player2Number2.six();
        } else if(n2 == 61) {
            player2Number1.one();
            player2Number2.six();
        } else if(n2 == 62) {
            player2Number1.two();
            player2Number2.six();
        } else if(n2 == 63) {
            player2Number1.three();
            player2Number2.six();
        } else if(n2 == 64) {
            player2Number1.four();
            player2Number2.six();
        } else if(n2 == 65) {
            player2Number1.five();
            player2Number2.six();
        } else if(n2 == 66) {
            player2Number1.six();
            player2Number2.six();
        } else if(n2 == 67) {
            player2Number1.seven();
            player2Number2.six();
        } else if(n2 == 68) {
            player2Number1.eight();
            player2Number2.six();
        } else if(n2 == 69) {
            player2Number1.nine();
            player2Number2.six();
        } else if(n2 == 70) {
            player2Number1.zero();
            player2Number2.seven();
        } else if(n2 == 71) {
            player2Number1.one();
            player2Number2.seven();
        } else if(n2 == 72) {
            player2Number1.two();
            player2Number2.seven();
        } else if(n2 == 73) {
            player2Number1.three();
            player2Number2.seven();
        } else if(n2 == 74) {
            player2Number1.four();
            player2Number2.seven();
        } else if(n2 == 75) {
            player2Number1.five();
            player2Number2.seven();
        } else if(n2 == 76) {
            player2Number1.six();
            player2Number2.seven();
        } else if(n2 == 77) {
            player2Number1.seven();
            player2Number2.seven();
        } else if(n2 == 78) {
            player2Number1.eight();
            player2Number2.seven();
        } else if(n2 == 79) {
            player2Number1.nine();
            player2Number2.seven();
        } else if(n2 == 80) {
            player2Number1.zero();
            player2Number2.eight();
        } else if(n2 == 81) {
            player2Number1.one();
            player2Number2.eight();
        } else if(n2 == 82) {
            player2Number1.two();
            player2Number2.eight();
        } else if(n2 == 83) {
            player2Number1.three();
            player2Number2.eight();
        } else if(n2 == 84) {
            player2Number1.four();
            player2Number2.eight();
        } else if(n2 == 85) {
            player2Number1.five();
            player2Number2.eight();
        } else if(n2 == 86) {
            player2Number1.six();
            player2Number2.eight();
        } else if(n2 == 87) {
            player2Number1.seven();
            player2Number2.eight();
        } else if(n2 == 88) {
            player2Number1.eight();
            player2Number2.eight();
        } else if(n2 == 89) {
            player2Number1.nine();
            player2Number2.eight();
        } else if(n2 == 90) {
            player2Number1.zero();
            player2Number2.nine();
        } else if(n2 == 91) {
            player2Number1.one();
            player2Number2.nine();
        } else if(n2 == 92) {
            player2Number1.two();
            player2Number2.nine();
        } else if(n2 == 93) {
            player2Number1.three();
            player2Number2.nine();
        } else if(n2 == 94) {
            player2Number1.four();
            player2Number2.nine();
        } else if(n2 == 95) {
            player2Number1.five();
            player2Number2.nine();
        } else if(n2 == 96) {
            player2Number1.six();
            player2Number2.nine();
        } else if(n2 == 97) {
            player2Number1.seven();
            player2Number2.nine();
        } else if(n2 == 98) {
            player2Number1.eight();
            player2Number2.nine();
        } else if(n2 == 99) {
            player2Number1.nine();
            player2Number2.nine();
        }
    }

    public void moveNumbers() {
        if(player1Points > 9) {
            if(player1Number1.getX() < -35) {player1Number1.move(1,0,0);}
            player1Number2.visibility(true);
            if(player1Number2.getY() > 200) {player1Number2.move(0,-3,0);}
        }
        if(player2Points > 9) {
            if(player2Number1.getX() < 65) {player2Number1.move(1,0,0);}
            player2Number2.visibility(true);            
            if(player2Number2.getY() > 200) {player2Number2.move(0,-3,0);}
        }
    }

    public void rotateCamera() {
        random = new Random().nextDouble();
        random = random * 5;
        camera.rotiere(0.75, 0, random, 0, 0, 75, 0);
    }

    public void ballTouchesWall() {
        if(ball1.gibX() > 89) {
            if(ball1.gibY() > player2.gibY() - 10 && ball1.gibY() < player2.gibY() + 10) {
                touchEffect1 = new Music("/Music/Effects/touch.wav");
                touchEffect1.startMusic(0);
                directionX = directionX * -1;
                randomBallSpeed();
            } else {
                point = new Music("/Music/Effects/point.wav");
                point.startMusic(0);
                player1Points++;
                resetBall();
                directionX = directionX * -1;
                randomBallSpeed();
            }
        }
        if(ball1.gibX() < -89) {
            if(ball1.gibY() > player1.gibY() - 10 && ball1.gibY() < player1.gibY() + 10) {
                touchEffect1 = new Music("/Music/Effects/touch.wav");
                touchEffect1.startMusic(0);
                directionX = directionX * -1;
                randomBallSpeed();
            } else {
                point = new Music("/Music/Effects/point.wav");
                point.startMusic(0);
                player2Points++;
                resetBall();
                directionX = directionX * -1;
                randomBallSpeed();
            }
        }
        if(ball1.gibY() > 144) {
            touchEffect2 = new Music("/Music/Effects/touch.wav");
            touchEffect2.startMusic(0);
            directionY = directionY * -1;
            randomBallSpeed();
        }
        if(ball1.gibY() < 6) {
            touchEffect2 = new Music("/Music/Effects/touch.wav");
            touchEffect2.startMusic(0);
            directionY = directionY * -1;
            randomBallSpeed();
        }          
    }

    public void randomBallSpeed() {
        if(new Random().nextInt(10) == 1) {    
            random = new Random().nextDouble();
            random = (random - 0.5) * 3;
            directionX += random;
            directionY += random * random / 2;
        }   
    }
    
    public void resetBall() {
        ball1.verschiebe(ball1.gibX() * -1, (ball1.gibY() * -1) + 75, ball1.gibZ() * -1);
        player1.setzePosition(-105, 75, 0);
        player2.setzePosition(105, 75, 0);
        writeLog();
        setNumbers(player1Points, player2Points);
        int i = 0;
        while (i <= 100) {
            checkKeyboard();
            moveNumbers();
            Sys.warte(33);
            i++;
        }
    }

    
    public void checkKeyboard() {
        if(keyboard.strg() && player1.gibY() > 10 && !paused) {
            player1.verschiebe(0, -3*1.5, 0);
        }
        if(keyboard.tab() && player1.gibY() < 140 && !paused) {
            player1.verschiebe(0, 3*1.5, 0);
        }
        if(keyboard.unten() && player2.gibY() > 10 && !paused) {
            player2.verschiebe(0, -3*1.5, 0);
        }
        if(keyboard.oben() && player2.gibY() < 140 && !paused) {
            player2.verschiebe(0, 3*1.5, 0);
        }
        if(keyboard.esc()){
            if(!paused) {
                musicPlayer1.stopMusic();
                musicPlayer2.startMusic(10000);
                paused = true;
                Sys.warte(333);
            } else {
                musicPlayer2.stopMusic();
                musicPlayer1.startMusic(10000);
                paused = false;
                Sys.warte(333);
                moveBalls();
            }
        }
    }

    public void writeLog() {
        newestLogLine = "Player 1: X" + Float.toString(player1.gibX()) + ", Y" + Float.toString(player1.gibY()) + ", Z" + Float.toString(player1.gibZ()) + "; Ball: X" + Float.toString(ball1.gibX()) + ", Y" + Float.toString(ball1.gibY()) + ", Z" + Float.toString(ball1.gibZ()) + "; Player 2: X" + Float.toString(player2.gibX()) + ", Y" + Float.toString(player2.gibY()) + ", Z" + Float.toString(player2.gibZ());
        System.out.println(newestLogLine);
        log = log + newestLogLine + "\n";
    }

    public int screenSize(String dimension) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();

        if(dimension == "x") {
            screenXY = d.width / 2;
        } else if(dimension == "y") {
            screenXY = d.height;
        }

        return screenXY;
    }
}


