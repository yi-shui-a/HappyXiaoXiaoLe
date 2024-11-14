package happyXiaoXiaoLe.controller;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class AboutUsController {

    /**
     * 离开键
     */
    @FXML
    private ImageView leave;

    /**
     * 点击离开键返回主页
     */
    @FXML
    void mouseClickedLeave() {
        Director.getInstance().toIndex();//返回主页
        SoundEffect.play1();

    }

    /**
     * 从此往下均为鼠标进出按钮，设置透明度效果的函数
     */
    @FXML
    void mouseEnteredLeave() {
        leave.setOpacity(0.8);
    }

    @FXML
    void mouseExitedLeave() {
        leave.setOpacity(1);
    }

}
