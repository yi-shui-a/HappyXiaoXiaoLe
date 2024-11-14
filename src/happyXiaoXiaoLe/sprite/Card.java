package happyXiaoXiaoLe.sprite;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.scene.GameGemScene;
import happyXiaoXiaoLe.scene.Setting;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.swing.*;

/**
 * @author yishui,Mxkun
 * @version 1.8.0_301
 * @see Card
 * @see Sprite
 *
 */

public class Card extends Sprite {

    /**
     * 每个方框的边框长度
     */
    public final int SIDE = 46;

    /**
     * 方块的编号信息
     */
    private int num;
    private int fall = 0;//下落状态,下落的格数
    private double ox;//预计移动后的x坐标
    private double oy;//预计移动后的y坐标
    private double speed = 2.875;//移动速度

    private int imageNum;//图片序号

    /**
     * 为每个编号建立对应的图片信息
     * @param x
     * @param y
     * @param n
     */
    public Card(double x, double y, int n) {
        super(null, x, y, 46, 46);
        ox = x;
        oy = y;
        num = n;
        if (n > 0) {
            int temp = 0;
            temp = n % 1000000;
            imageNum = n / 10000000;
            if (temp >= 100000)
                imageNum = imageNum * 10 + 2;
            if (temp < 100000 && temp >= 10000)
                imageNum = imageNum * 10 + 1;
        } else {
            imageNum = n;
        }
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
                    image = new Image("/image/card/动物/狗头冰块.png");
                    break;
                case 21:
                    image = new Image("/image/card/动物/粉猪冰块.png");
                    break;
                case 31:
                    image = new Image("/image/card/动物/灰狐冰块.png");
                    break;
                case 41:
                    image = new Image("/image/card/动物/菜狗冰块.png");
                    break;
                case 12:
                    image = new Image("/image/card/动物/狗头宝石.png");
                    break;
                case 22:
                    image = new Image("/image/card/动物/粉猪宝石.png");
                    break;
                case 32:
                    image = new Image("/image/card/动物/灰狐宝石.png");
                    break;
                case 42:
                    image = new Image("/image/card/动物/菜狗宝石.png");
                    break;
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
                    image = new Image("/image/card/水果/草莓冰块.png");
                    break;
                case 21:
                    image = new Image("/image/card/水果/桃子冰块.png");
                    break;
                case 31:
                    image = new Image("/image/card/水果/猕猴桃冰块.png");
                    break;
                case 41:
                    image = new Image("/image/card/水果/山竹冰块.png");
                    break;
                case 12:
                    image = new Image("/image/card/水果/草莓宝石.png");
                    break;
                case 22:
                    image = new Image("/image/card/水果/桃子宝石.png");
                    break;
                case 32:
                    image = new Image("/image/card/水果/猕猴桃宝石.png");
                    break;
                case 42:
                    image = new Image("/image/card/水果/山竹宝石.png");
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
                    image = new Image("/image/card/程序员/eclipse冰块.png");
                    break;
                case 21:
                    image = new Image("/image/card/程序员/c++冰块.png");
                    break;
                case 31:
                    image = new Image("/image/card/程序员/pc冰块.png");
                    break;
                case 41:
                    image = new Image("/image/card/程序员/java冰块.png");
                    break;
                case 12:
                    image = new Image("/image/card/程序员/eclipse宝石.png");
                    break;
                case 22:
                    image = new Image("/image/card/程序员/c++宝石.png");
                    break;
                case 32:
                    image = new Image("/image/card/程序员/pc宝石.png");
                    break;
                case 42:
                    image = new Image("/image/card/程序员/java宝石.png");
                    break;
            }
        }
    }

    /**
     * 获得方块的下落状态
     *@param
     *@return int
     */
    public int getFall() {

        return fall;
    }

    /**
     * 重新设置方块的下落状态
     *@param fall
     *@return void
     */
    public void setFall(int fall) {

        this.fall = fall;
    }

    /**
     * 获取方块的目标x轴位置
     *@param
     *@return double
     */
    public double getOx() {

        return ox;
    }

    /**
     * 重新设置目标x轴的位置
     *@param ox
     *@return void
     */
    public void setOx(double ox) {

        this.ox = ox;
    }

    /**
     * 获取方块的目标y轴位置
     *@param
     *@return double
     */
    public double getOy() {
        return oy;
    }

    /**
     * 重新设置目标y轴的位置
     *@param oy
     *@return void
     */
    public void setOy(double oy) {
        this.oy = oy;
    }

    /**
     * 获取方块的下落速度
     *@param
     *@return double
     */
    public double getSpeed() {

        return speed;
    }
    /**
     * 重新设置方块的下落速度
     *@param speed
     *@return void
     */
    public void setSpeed(double speed) {

        this.speed = speed;
    }

    /**
     * 获得图片编号
     *@param
     *@return int
     */
    public int getImageNum() {

        return imageNum;
    }

    /**
     * 重新设置图片编号
     *@param imageNum
     *@return void
     */
    public void setImageNum(int imageNum) {

        this.imageNum = imageNum;
    }

    /**
     * 获取方块的编号
     *@param
     *@return int
     */
    public int getNum() {

        return num;
    }

    /**
     * 判断是否被选中
     *@param x, y
     *@return boolean
     */
    public boolean isPoint(double x, double y) {//判断是否被选中

        if (imageNum == -1) {
            return false;
        }
        //大于左上角，小于右下角的坐标则肯定在范围内
        if (x > this.x && y > this.y
                && x < this.x + this.width && y < this.y + this.height) {
            //System.out.println(this.dx + "," + this.dy);
            return true;
        }
        return false;
    }


    /**
     * 检查方块，确定目标下落距离
     */
    public void fall() {

        int temp = num % 10000000;
        //System.out.println(temp);
        temp = (int) (temp / 1000000);
        fall = temp;
        num = num - temp * 1000000;
        if (temp != 0) {
            oy = y + SIDE * fall;
        }
    }
}