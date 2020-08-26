import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music
{
    Clip clip;
    
    public Music(String wav) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(wav));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(1);
            clip.stop();
        } catch(Exception ex) {
        }
    }
    
    public void startMusic(int loopCount) {
        clip.start();
        clip.loop(loopCount);
    }
    
    public void stopMusic() {
        clip.stop();
    }
}
