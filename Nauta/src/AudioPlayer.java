import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class AudioPlayer {

    private static AudioPlayer instance;
    private HashMap<String, Clip> audios;
    private float musicVol;
    private float effectVol;
    private String currentMusic;
    private AudioInputStream inputStream;

    private AudioPlayer(){

        musicVol = 0.7f;
        effectVol = 0.7f;

        audios = new HashMap<String, Clip>();

    }

    public static AudioPlayer get(){
        if (instance == null){
            instance = new AudioPlayer();
        }
        return instance;
    }

    public void playBackMusic(String audioName){
        Clip musicClip = getSoundClip(audioName);
        musicClip.setFramePosition(0);
        setVolume(musicClip, musicVol);
        musicClip.start();
        currentMusic = audioName;
    }

    public void stopBackMusic(){
        if (currentMusic != null){
            Clip currentClip = getSoundClip(currentMusic);
            currentClip.stop();
            currentMusic = null;
        }
    }

    public void playEffectSound(String audioName){
        Clip soundClip = getSoundClip(audioName);
        soundClip.setFramePosition(0);
        setVolume(soundClip, effectVol);
        soundClip.start();
    }

    public void setMusicVol(float vol){
        if (vol >= 0 && vol <= 1){
            musicVol = vol;
            if (currentMusic != null){
                Clip soundClip = getSoundClip(currentMusic);
                setVolume(soundClip, musicVol);
            }
        }
    }

    public float getMusicVol(){
        return musicVol;
    }

    public void setEffectVol(float vol){
        if (vol >= 0 && vol <= 1) {
            effectVol = vol;
            if (currentMusic != null){
                Clip soundClip = getSoundClip(currentMusic);
                setVolume(soundClip, effectVol);
            }
        }
    }

    public float getEffectVol(){
        return effectVol;
    }

    private Clip getSoundClip(String clipName){

        Clip clip = audios.get(clipName);

        if (clip != null){

            return clip;

        } else {

            try {
                inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(clipName + ".wav"));
                clip = AudioSystem.getClip();
                clip.open(inputStream);
                audios.put(clipName, clip);

            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return clip;
    }

    private void setVolume(Clip soundClip, float volume){
        FloatControl gainControl = (FloatControl)soundClip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = range * volume + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}
