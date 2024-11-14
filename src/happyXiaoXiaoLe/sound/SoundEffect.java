package happyXiaoXiaoLe.sound;

import happyXiaoXiaoLe.scene.Setting;
import javafx.scene.media.AudioClip;

import java.util.Objects;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class SoundEffect {

    /**
     * 点击音效播放类（分三种主题）。
     */

    public static void play1(){
        if(Setting.getInstance().sound == 1) {
            if(Setting.getInstance().theme == 1 || Setting.getInstance().theme == 2) {
                AudioClip audioClip = new AudioClip(Objects.requireNonNull(SoundEffect.class.getResource("/sound/Button32.wav")).toString());
                audioClip.play();
            }else if(Setting.getInstance().theme == 3){
                AudioClip audioClip = new AudioClip(Objects.requireNonNull(SoundEffect.class.getResource("/sound/程序员按键.wav")).toString());
                audioClip.play();
            }
        }
    }

    /**
     * 消除音效播放类（分三种主题）。
     */
    public static void play2(){
        if(Setting.getInstance().sound == 1) {
                AudioClip audioClip = new AudioClip(Objects.requireNonNull(SoundEffect.class.getResource("/sound/消除音效.mp3")).toString());
                audioClip.play();
        }
    }

    /**
     * 不能消除音效播放类（分三种主题）。
     */
    public static void play3(){
        if(Setting.getInstance().sound == 1) {
            AudioClip audioClip = new AudioClip(Objects.requireNonNull(SoundEffect.class.getResource("/sound/不能消除.mp3")).toString());
            audioClip.play();
        }
    }
}
