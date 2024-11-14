package happyXiaoXiaoLe.sprite;

import javafx.scene.image.Image;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class Set extends Sprite{

    /**
     * 局内设置按键类
     */
    public Set(){
        super(new Image("/image/button/gameSetting.png"),710,5,60,60);
    }

    /**+
     * 判断是否被选中。
     * @param x 点的横坐标。
     * @param y 点的纵坐标。
     * @return
     */
    public boolean isPoint(double x, double y) {
        //大于左上角，小于右下角的坐标则肯定在范围内
        if (x > this.x && y > this.y
                && x < this.x + this.width && y < this.y + this.height) {
            return true;
        }
        return false;
    }

}
