package happyXiaoXiaoLe.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public abstract class Sprite {

    Image image;
    public double x, y, width, height;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Sprite(Image image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(GraphicsContext graphicsContext) { //绘制可视化对象
        graphicsContext.drawImage(image, x, y, width, height);
    }
}