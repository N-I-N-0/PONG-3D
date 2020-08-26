import GLOOP.*;

public class Number
{
    GLQuader one, two, three, four, five, six, seven;
    boolean visible = true;
    int number;
    
    public Number()
    {
        one = new GLQuader(0, 20, 0, 20, 5, 5);
        two = new GLQuader(0, 0, 0, 20, 5, 5);
        three = new GLQuader(0, -20, 0, 20, 5, 5);
        four = new GLQuader(-10, 10, 0, 5, 20, 5);
        five = new GLQuader(10, 10, 0, 5, 20, 5);
        six = new GLQuader(-10, -10, 0, 5, 20, 5);
        seven = new GLQuader(10, -10, 0, 5, 20, 5);
       
    }
    
    public void zero() {
        one.setzeSichtbarkeit(true);
        two.setzeSichtbarkeit(false);
        three.setzeSichtbarkeit(true);
        four.setzeSichtbarkeit(true);
        five.setzeSichtbarkeit(true);
        six.setzeSichtbarkeit(true);
        seven.setzeSichtbarkeit(true);
        number = 0;
    }
    
     public void one() {
        one.setzeSichtbarkeit(false);
        two.setzeSichtbarkeit(false);
        three.setzeSichtbarkeit(false);
        four.setzeSichtbarkeit(false);
        five.setzeSichtbarkeit(true);
        six.setzeSichtbarkeit(false);
        seven.setzeSichtbarkeit(true);
        number = 1;        
    }
    
     public void two() {
        one.setzeSichtbarkeit(true);
        two.setzeSichtbarkeit(true);
        three.setzeSichtbarkeit(true);
        four.setzeSichtbarkeit(false);
        five.setzeSichtbarkeit(true);
        six.setzeSichtbarkeit(true);
        seven.setzeSichtbarkeit(false);
        number = 2;
    }
    
     public void three() {
        one.setzeSichtbarkeit(true);
        two.setzeSichtbarkeit(true);
        three.setzeSichtbarkeit(true);
        four.setzeSichtbarkeit(false);
        five.setzeSichtbarkeit(true);
        six.setzeSichtbarkeit(false);
        seven.setzeSichtbarkeit(true);
        number = 3;
    }
    
     public void four() {
        one.setzeSichtbarkeit(false);
        two.setzeSichtbarkeit(true);
        three.setzeSichtbarkeit(false);
        four.setzeSichtbarkeit(true);
        five.setzeSichtbarkeit(true);
        six.setzeSichtbarkeit(false);
        seven.setzeSichtbarkeit(true);
        number = 4;
    }
    
     public void five() {
        one.setzeSichtbarkeit(true);
        two.setzeSichtbarkeit(true);
        three.setzeSichtbarkeit(true);
        four.setzeSichtbarkeit(true);
        five.setzeSichtbarkeit(false);
        six.setzeSichtbarkeit(false);
        seven.setzeSichtbarkeit(true);
        number = 5;
    }
    
     public void six() {
        one.setzeSichtbarkeit(true);
        two.setzeSichtbarkeit(true);
        three.setzeSichtbarkeit(true);
        four.setzeSichtbarkeit(true);
        five.setzeSichtbarkeit(false);
        six.setzeSichtbarkeit(true);
        seven.setzeSichtbarkeit(true);
        number = 6;
    }
    
     public void seven() {
        one.setzeSichtbarkeit(true);
        two.setzeSichtbarkeit(false);
        three.setzeSichtbarkeit(false);
        four.setzeSichtbarkeit(false);
        five.setzeSichtbarkeit(true);
        six.setzeSichtbarkeit(false);
        seven.setzeSichtbarkeit(true);
        number = 7;
    }
    
     public void eight() {
        one.setzeSichtbarkeit(true);
        two.setzeSichtbarkeit(true);
        three.setzeSichtbarkeit(true);
        four.setzeSichtbarkeit(true);
        five.setzeSichtbarkeit(true);
        six.setzeSichtbarkeit(true);
        seven.setzeSichtbarkeit(true);
        number = 8;
    }
    
     public void nine() {
        one.setzeSichtbarkeit(true);
        two.setzeSichtbarkeit(true);
        three.setzeSichtbarkeit(true);
        four.setzeSichtbarkeit(true);
        five.setzeSichtbarkeit(true);
        six.setzeSichtbarkeit(false);
        seven.setzeSichtbarkeit(true);
        number = 9;
    }
    
    public double getX() {
        return two.gibX();
    }
    
    public double getY() {
        return two.gibY();
    }
    
    public double getZ() {
        return two.gibZ();
    }
    
    public void visibility(boolean v) {
        if(v) {
            if(number == 0) {
                zero();
            } else if(number == 1) {
                one();
            } else if(number == 2) {
                two();
            } else if(number == 3) {
                three();
            } else if(number == 4) {
                four();
            } else if(number == 5) {
                five();
            } else if(number == 6) {
                six();
            } else if(number == 7) {
                seven();
            } else if(number == 8) {
                eight();
            } else if(number == 9) {
                nine();
            }
        } else { 
            one.setzeSichtbarkeit(false);
            two.setzeSichtbarkeit(false);
            three.setzeSichtbarkeit(false);
            four.setzeSichtbarkeit(false);
            five.setzeSichtbarkeit(false);
            six.setzeSichtbarkeit(false);
            seven.setzeSichtbarkeit(false);
        }
    }
    
    public boolean isVisible() {
        return true;
    }
    
    public void move(double x, double y, double z) {
        one.verschiebe(x, y, z);
        two.verschiebe(x, y, z);
        three.verschiebe(x, y, z);
        four.verschiebe(x, y, z);
        five.verschiebe(x, y, z);
        six.verschiebe(x, y, z);
        seven.verschiebe(x, y, z);
    }
    
    public void rotate(double a, double x1, double y1, double z1, double x2, double y2, double z2) {
        one.rotiere(a, x1, y1, z1, x2, y2, z2);
        two.rotiere(a, x1, y1, z1, x2, y2, z2);
        three.rotiere(a, x1, y1, z1, x2, y2, z2);
        four.rotiere(a, x1, y1, z1, x2, y2, z2);
        five.rotiere(a, x1, y1, z1, x2, y2, z2);
        six.rotiere(a, x1, y1, z1, x2, y2, z2);
        seven.rotiere(a, x1, y1, z1, x2, y2, z2);
    }
}
