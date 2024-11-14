package happyXiaoXiaoLe.sprite;

import javafx.scene.image.Image;

/**
 * @author Mxkun
 * @version 1.8.0_301
 * @see Sprite
 *
 */

public class Choice extends Sprite{
    int dx;
    int dy;

    public Choice(double x, double y,int i,int j) {
        super(new Image("/image/button/choice.png"), x, y, 46, 46);
        dx = i;
        dy = j;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }
}
