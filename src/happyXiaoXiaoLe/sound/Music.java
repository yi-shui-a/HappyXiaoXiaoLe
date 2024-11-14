package happyXiaoXiaoLe.sound;

import happyXiaoXiaoLe.scene.Setting;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class Music {

    private static final Music instance = new Music();

    private Music(){}

    public static Music getInstance(){
        return instance;
    }

    private final File file1 = new File("resources/sound/WorldSceneBGM.mp3");
    private final File file2 = new File("resources/sound/SeaTreasureMatch.mp3");
    private final File file3 = new File("resources/sound/水果模式世界音乐.wav");
    //private final File file4 = new File("resources/sound/水果模式游戏音乐.mp3");
    private final File file5 = new File("resources/sound/程序员背景音乐.wav");
    //private final File file6 = new File("resources/sound/程序员模式游戏音乐.wav");

    public Media media = null;
    public MediaPlayer mediaPlayer = null;

    /**
     * （主页界面背景音乐）通过sound标志判断是否播放音乐，通过theme标志确定播放哪一首音乐
     */
    public void play1(){
        if(Setting.getInstance().theme ==1) {
            media = new Media((file1.toURI().toString()));
            mediaPlayer = new MediaPlayer(media);
        }else if(Setting.getInstance().theme ==2) {
            media = new Media((file3.toURI().toString()));
            mediaPlayer = new MediaPlayer(media);
        } else if(Setting.getInstance().theme ==3) {
            media = new Media((file5.toURI().toString()));
            mediaPlayer = new MediaPlayer(media);
        }
        if(Setting.getInstance().sound ==1){
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
    }

    /**
     * （游戏界面背景音乐）通过sound标志判断是否播放音乐，通过theme标志确定播放哪一首音乐
     */
    public void play2(){
        if(Setting.getInstance().theme ==1) {
            media = new Media((file2.toURI().toString()));
            mediaPlayer = new MediaPlayer(media);
        }else if(Setting.getInstance().theme ==2) {
            media = new Media((file3.toURI().toString()));
            mediaPlayer = new MediaPlayer(media);
        } else if(Setting.getInstance().theme ==3) {
            media = new Media((file5.toURI().toString()));
            mediaPlayer = new MediaPlayer(media);
        }
        if(Setting.getInstance().sound ==1){
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }
    }
}
