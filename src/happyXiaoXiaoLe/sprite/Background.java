package happyXiaoXiaoLe.sprite;

import happyXiaoXiaoLe.scene.Setting;
import javafx.scene.image.Image;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class Background extends Sprite {
    /**
     * 背景类
     * @param model 模式标志
     */
    public Background(int model) {
        super(null, 0, 0, 800, 600);
        switch (model) {
            case 2:image = new Image("/image/background/endlessGameStage.png");break;
            default:if(Setting.getInstance().shape == 0){
                image = new Image("/image/background/cardioid.png");
            }else image = new Image("/image/background/square.png");
        }
    }
}
