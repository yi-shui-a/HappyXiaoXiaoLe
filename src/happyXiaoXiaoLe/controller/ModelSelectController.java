package happyXiaoXiaoLe.controller;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.sound.Music;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class ModelSelectController {

    @FXML
    private ImageView adventure;

    @FXML
    private ImageView endless;

    @FXML
    private ImageView gem;

    @FXML
    private ImageView leave;

    /**
     * 点击进入冒险模式的选关界面
     */
    @FXML
    void clickedAdventure() {
        SoundEffect.play1();
        Director.getInstance().toAdventureStage();
    }

    /**
     * 点击直接进入无尽模式，换音乐
     */
    @FXML
    void clickedEndless() {
        Music.getInstance().mediaPlayer.stop();
        Music.getInstance().play2();
        SoundEffect.play1();
        Director.getInstance().endlessStart();
    }

    /**
     * 点击进入宝石模式的选关界面
     */
    @FXML
    void clickedGem() {
        Director.getInstance().toGemStage();
        SoundEffect.play1();
    }

    /**
     * 点击离开键返回主页
     */
    @FXML
    void clickedLeave() {
        Director.getInstance().toIndex();
        SoundEffect.play1();
    }

    /**
     * 从此往下均为鼠标进出按钮，设置透明度效果的函数
     */
    @FXML
    void enteredAdventure() {
        adventure.setOpacity(0.8);
    }

    @FXML
    void enteredEndless() {
        endless.setOpacity(0.8);
    }

    @FXML
    void enteredGem() {
        gem.setOpacity(0.8);
    }

    @FXML
    void exitedAdventure() {
        adventure.setOpacity(1);
    }

    @FXML
    void exitedEndless() {
        endless.setOpacity(1);
    }

    @FXML
    void exitedGem() {
        gem.setOpacity(1);
    }

    @FXML
    void enteredLeave() {
        leave.setOpacity(0.6);
    }

    @FXML
    void exitedLeave() {
        leave.setOpacity(1);
    }

}
