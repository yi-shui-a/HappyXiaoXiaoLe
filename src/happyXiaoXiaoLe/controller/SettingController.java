package happyXiaoXiaoLe.controller;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.scene.Setting;
import happyXiaoXiaoLe.sound.Music;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class SettingController {

    /**
     * 初始化方法，确定哪一个按钮被选中，以及音乐打开状态
     */
    @FXML
    private void initialize() {
        themeSelected(Setting.getInstance().theme);
        soundSelect(Setting.getInstance().sound);
    }

    @FXML
    private ImageView leave;

    @FXML
    private ImageView music;

    @FXML
    private RadioButton button1;

    @FXML
    private RadioButton button2;

    @FXML
    private RadioButton button3;

    /**
     * 离开键的实现
     */
    @FXML
    void mouseClickedLeave() {
        Director.getInstance().toIndex();
        SoundEffect.play1();
    }

    /**
     * 音量键实现音乐的打开和关闭
     */
    @FXML
    void mouseClickedMusic() {
        if(Setting.getInstance().sound == 1){
            music.setImage(new Image("/image/button/mute.png"));
            Music.getInstance().mediaPlayer.stop();
            Setting.getInstance().sound = 0;
        } else if(Setting.getInstance().sound == 0){
            music.setImage(new Image("/image/button/play.png"));
            Setting.getInstance().sound = 1;
            SoundEffect.play1();
            Music.getInstance().play1();
        }
    }

    /**
     * 更换主题一
     */
    @FXML
    void mouseClickedButton1() {
        button1.setSelected(true);
        button2.setSelected(false);
        button3.setSelected(false);
        Setting.getInstance().theme = 1;
        Music.getInstance().mediaPlayer.stop();
        Music.getInstance().play1();
        SoundEffect.play1();
    }

    /**
     * 更换主题二
     */
    @FXML
    void mouseClickedButton2() {
        button1.setSelected(false);
        button2.setSelected(true);
        button3.setSelected(false);
        Setting.getInstance().theme = 2;
        Music.getInstance().mediaPlayer.stop();
        Music.getInstance().play1();
        SoundEffect.play1();
    }

    /**
     * 更换主题三
     */
    @FXML
    void mouseClickedButton3() {
        button1.setSelected(false);
        button2.setSelected(false);
        button3.setSelected(true);
        Setting.getInstance().theme = 3;
        Music.getInstance().mediaPlayer.stop();
        Music.getInstance().play1();
        SoundEffect.play1();
    }

    /**
     * 根据theme标志确定哪一个按钮应该被选中
     * @param theme 主题标志
     */
    public void themeSelected(int theme){
        if(theme == 1){
            button1.setSelected(true);
            button2.setSelected(false);
            button3.setSelected(false);
        }else if(theme ==2){
            button1.setSelected(false);
            button2.setSelected(true);
            button3.setSelected(false);
        }else if(theme == 3){
            button1.setSelected(false);
            button2.setSelected(false);
            button3.setSelected(true);
        }
    }

    /**
     * 根据sound标志确定音量键图片
     * @param sound 音量标识
     */
    public void soundSelect(int sound){
        if(sound == 0){
            music.setImage(new Image("/image/button/mute.png"));
        } else if(sound == 1){
            music.setImage(new Image("/image/button/play.png"));
        }
    }

    /**
     * 从此往下均为鼠标进出按钮，设置透明度效果的函数
     */
    @FXML
    void mouseEnteredLeave() {
        leave.setOpacity(0.8);
    }

    @FXML
    void mouseEnteredMusic() {
        music.setOpacity(0.8);
    }

    @FXML
    void mouseExitedLeave() {
        leave.setOpacity(1);
    }

    @FXML
    void mouseExitedMusic() {
        music.setOpacity(1);
    }


}
