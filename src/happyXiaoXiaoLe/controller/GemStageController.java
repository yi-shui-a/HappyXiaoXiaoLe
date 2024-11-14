package happyXiaoXiaoLe.controller;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.scene.Setting;
import happyXiaoXiaoLe.sound.Music;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class GemStageController {

    private int stage;

    /**
     * 初始化方法。
     */
    @FXML
    private void initialize() {
        stage = Director.getInstance().getUser().getJewelLevel();
        stageInitialize(stage);
    }


    /**
     * 页面初始化方法。
     * @param stage 游戏主窗口
     */
    private void stageInitialize(int stage) {
        //从符合条件的最大关卡开始，每一关依次设置为可见
        switch (Math.min(stage, 10)) {
            case 10:stage10.setOpacity(1);
            case 9:stage9.setOpacity(1);
            case 8:stage8.setOpacity(1);
            case 7:stage7.setOpacity(1);
            case 6:stage6.setOpacity(1);
            case 5:stage5.setOpacity(1);
            case 4:stage4.setOpacity(1);
            case 3:stage3.setOpacity(1);
            case 2:stage2.setOpacity(1);
            case 1:stage1.setOpacity(1);
        }
    }

    @FXML
    private ImageView stage1;

    @FXML
    private ImageView stage2;

    @FXML
    private ImageView stage3;

    @FXML
    private ImageView stage4;

    @FXML
    private ImageView stage5;

    @FXML
    private ImageView stage6;

    @FXML
    private ImageView stage7;

    @FXML
    private ImageView stage8;

    @FXML
    private ImageView stage9;

    @FXML
    private ImageView stage10;

    @FXML
    private ImageView leave;


    /**
     * 进入关卡设置函数（换音乐，换页面）
     * @param level 进入的是第几关
     */
    void enterGame(int level) {
        Music.getInstance().mediaPlayer.stop();
        Music.getInstance().play2();
        SoundEffect.play1();
        Director.getInstance().jewelStart(level);
    }

    /**
     * 从此往下十个函数均为进入每一关的函数（分当前是第一页和第二页两种情况）。
     */
    @FXML
    void mouseClickedStage1() {
        Setting.getInstance().shape = 1;
        enterGame(1);
    }

    @FXML
    void mouseClickedStage10() {
        Setting.getInstance().shape = 1;
        enterGame(10);
    }

    @FXML
    void mouseClickedStage2() {
        Setting.getInstance().shape = 1;
        enterGame(2);
    }

    @FXML
    void mouseClickedStage3() {
        Setting.getInstance().shape = 1;
        enterGame(3);
    }

    @FXML
    void mouseClickedStage4() {
        Setting.getInstance().shape = 1;
        enterGame(4);
    }

    @FXML
    void mouseClickedStage5() {
        Setting.getInstance().shape = 1;
        enterGame(5);
    }

    @FXML
    void mouseClickedStage6() {
        Setting.getInstance().shape = 1;
        enterGame(6);
    }

    @FXML
    void mouseClickedStage7() {
        Setting.getInstance().shape = 1;
        enterGame(7);
    }

    @FXML
    void mouseClickedStage8() {
        Setting.getInstance().shape = 1;
        enterGame(8);
    }

    @FXML
    void mouseClickedStage9() {
        Setting.getInstance().shape = 1;
        enterGame(9);
    }
    /**
     * 点击离开键返回主页
     */
    @FXML
    void mouseClickedLeave() {
        Director.getInstance().toModelSelect();
        SoundEffect.play1();
    }

    /**
     * 从此往下均为鼠标进出按钮，设置透明度效果的函数
     */
    @FXML
    void mouseEnteredStage1() {
        if (stage>=1)
            stage1.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage10() {
        if (stage>=10)
            stage10.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage2() {
        if (stage>=2)
            stage2.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage3() {
        if (stage>=3)
            stage3.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage4() {
        if (stage>=4)
            stage4.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage5() {
        if (stage>=5)
            stage5.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage6() {
        if (stage>=6)
            stage6.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage7() {
        if (stage>=7)
            stage7.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage8() {
        if (stage>=8)
            stage8.setOpacity(0.6);
    }

    @FXML
    void mouseEnteredStage9() {
        if (stage>=9)
            stage9.setOpacity(0.6);
    }

    @FXML
    void mouseExitedStage1() {
        if (stage>=1)
            stage1.setOpacity(1);
    }

    @FXML
    void mouseExitedStage10() {
        if (stage>=10)
            stage10.setOpacity(1);
    }

    @FXML
    void mouseExitedStage2() {
        if (stage>=2)
            stage2.setOpacity(1);
    }

    @FXML
    void mouseExitedStage3() {
        if (stage>=3)
            stage3.setOpacity(1);
    }

    @FXML
    void mouseExitedStage4() {
        if (stage>=4)
            stage4.setOpacity(1);
    }

    @FXML
    void mouseExitedStage5() {
        if (stage>=5)
            stage5.setOpacity(1);
    }

    @FXML
    void mouseExitedStage6() {
        if (stage>=6)
            stage6.setOpacity(1);
    }

    @FXML
    void mouseExitedStage7() {
        if (stage>=7)
            stage7.setOpacity(1);
    }

    @FXML
    void mouseExitedStage8() {
        if (stage>=8)
            stage8.setOpacity(1);
    }
    @FXML
    void mouseExitedStage9() {
        if (stage>=9)
            stage9.setOpacity(1);
    }

    @FXML
    void mouseEnteredLeave() {
        leave.setOpacity(0.6);
    }

    @FXML
    void mouseExitedLeave() {
        leave.setOpacity(1);
    }
}
