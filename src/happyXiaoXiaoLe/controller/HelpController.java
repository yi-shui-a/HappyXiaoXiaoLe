package happyXiaoXiaoLe.controller;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.scene.Setting;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */


public class HelpController {

    /**
     * 初始化方法，不同模式对应不同背景
     */
    @FXML
    private void initialize() {
        switch (Setting.getInstance().help) {
            case 1:background.setImage(new Image("image/background/help/adventure.png"));break ;
            case 2:background.setImage(new Image("image/background/help/endless.png"));break;
            case 3:background.setImage(new Image("image/background/help/jewel.png"));break;
        }
    }

    @FXML
    private ImageView leave;

    @FXML
    private ImageView background;

    /**
     * 点击离开键返回主页
     */
    @FXML
    void mouseClickedLeave() {
        Director.getInstance().toHelpSelect();//返回帮助选择模式界面
        SoundEffect.play1();
    }

    /**
     * 从此往下均为鼠标进出按钮，设置透明度效果的函数
     */
    @FXML
    void mouseEnteredLeave() {
        leave.setOpacity(0.6);
    }

    @FXML
    void mouseExitedLeave() {
        leave.setOpacity(1);
    }
}
