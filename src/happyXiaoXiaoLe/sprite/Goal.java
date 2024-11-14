package happyXiaoXiaoLe.sprite;

import happyXiaoXiaoLe.scene.Setting;
import javafx.scene.image.Image;

/**
 * @author yishui,Mxkun
 * @version 1.8.0_301
 * @see Sprite
 *
 */

public class Goal extends Sprite{

    private int imageNum;//图片序号

    /**
     * 构造函数，
     *@param x, y, n
     *@return
     */
    public Goal(double x, double y, int n) {

        super(null, x, y, 80, 80);
        int temp = 0;
        temp = n % 1000000;
        imageNum = n / 10000000;
        if (temp >= 100000)
            imageNum = imageNum*10 + 2;
        if (temp < 100000 && temp >= 10000)
            imageNum = imageNum *10+ 1;

        if (Setting.getInstance().theme == 1) {
            switch (imageNum) {
                case 1:
                    image = new Image("/image/card/动物/狗头001.png");
                    break;
                case 2:
                    image = new Image("/image/card/动物/粉猪001.png");
                    break;
                case 3:
                    image = new Image("/image/card/动物/灰狐001.png");
                    break;
                case 4:
                    image = new Image("/image/card/动物/菜狗001.png");
                    break;
                case 11:
                    image = new Image("/image/card/冰块001.png");
                    break;
                case 12:
                    image = new Image("/image/card/动物/宝石.png");
            }
        } else if (Setting.getInstance().theme == 2) {
            switch (imageNum) {
                case 1:
                    image = new Image("/image/card/水果/草莓02.png");
                    break;
                case 2:
                    image = new Image("/image/card/水果/桃子02.png");
                    break;
                case 3:
                    image = new Image("/image/card/水果/猕猴桃02.png");
                    break;
                case 4:
                    image = new Image("/image/card/水果/山竹02.png");
                    break;
                case 11:
                    image = new Image("/image/card/冰块.png");
                    break;
                case 12:
                    image = new Image("/image/card/水果/宝石.png");
                    break;
            }
        } else if (Setting.getInstance().theme == 3) {
            switch (imageNum) {
                case 1:
                    image = new Image("/image/card/程序员/eclipse001.png");
                    break;
                case 2:
                    image = new Image("/image/card/程序员/c++001.png");
                    break;
                case 3:
                    image = new Image("/image/card/程序员/pc001.png");
                    break;
                case 4:
                    image = new Image("/image/card/程序员/java02.png");
                    break;
                case 11:
                    image = new Image("/image/card/冰块.png");
                    break;
                case 12:
                    image = new Image("/image/card/程序员/宝石.png");
                    break;
            }
        }
    }
}
