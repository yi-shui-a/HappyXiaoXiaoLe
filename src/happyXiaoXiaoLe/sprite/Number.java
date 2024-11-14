package happyXiaoXiaoLe.sprite;

import javafx.scene.image.Image;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class Number extends Sprite{

    /**
     * 游戏场景里的数字类
     * @param ImageNum 图片标号
     * @param n n为1表示剩余步数，n为2表示关卡数
     */
    public Number(int ImageNum,int n) {
        super(new Image("image/imageView/stepNumber/1.png"),0,0,0,0);
        if(n == 1){
            x = 90;
            y = 385;
            width = 80;
            height = 60;
            switch (ImageNum) {
                case 1:
                    image = new Image("image/imageView/stepNumber/1.png");
                    break;
                case 2:
                    image = new Image("image/imageView/stepNumber/2.png");
                    break;
                case 3:
                    image = new Image("image/imageView/stepNumber/3.png");
                    break;
                case 4:
                    image = new Image("image/imageView/stepNumber/4.png");
                    break;
                case 5:
                    image = new Image("image/imageView/stepNumber/5.png");
                    break;
                case 6:
                    image = new Image("image/imageView/stepNumber/6.png");
                    break;
                case 7:
                    image = new Image("image/imageView/stepNumber/7.png");
                    break;
                case 8:
                    image = new Image("image/imageView/stepNumber/8.png");
                    break;
                case 9:
                    image = new Image("image/imageView/stepNumber/9.png");
                    break;
                case 10:
                    image = new Image("image/imageView/stepNumber/10.png");
                    break;
                case 11:
                    image = new Image("image/imageView/stepNumber/11.png");
                    break;
                case 12:
                    image = new Image("image/imageView/stepNumber/12.png");
                    break;
                case 13:
                    image = new Image("image/imageView/stepNumber/13.png");
                    break;
                case 14:
                    image = new Image("image/imageView/stepNumber/14.png");
                    break;
                case 15:
                    image = new Image("image/imageView/stepNumber/15.png");
                    break;
                case 16:
                    image = new Image("image/imageView/stepNumber/16.png");
                    break;
                case 17:
                    image = new Image("image/imageView/stepNumber/17.png");
                    break;
                case 18:
                    image = new Image("image/imageView/stepNumber/18.png");
                    break;
                case 19:
                    image = new Image("image/imageView/stepNumber/19.png");
                    break;
                case 20:
                    image = new Image("image/imageView/stepNumber/20.png");
                    break;
                case 21:
                    image = new Image("image/imageView/stepNumber/21.png");
                    break;
                case 22:
                    image = new Image("image/imageView/stepNumber/22.png");
                    break;
                case 23:
                    image = new Image("image/imageView/stepNumber/23.png");
                    break;
                case 24:
                    image = new Image("image/imageView/stepNumber/24.png");
                    break;
                case 25:
                    image = new Image("image/imageView/stepNumber/25.png");
                    break;
                case 26:
                    image = new Image("image/imageView/stepNumber/26.png");
                    break;
                case 27:
                    image = new Image("image/imageView/stepNumber/27.png");
                    break;
                case 28:
                    image = new Image("image/imageView/stepNumber/28.png");
                    break;
                case 29:
                    image = new Image("image/imageView/stepNumber/29.png");
                    break;
                case 30:
                    image = new Image("image/imageView/stepNumber/30.png");
                    break;
                case 31:
                    image = new Image("image/imageView/stepNumber/31.png");
                    break;
                case 32:
                    image = new Image("image/imageView/stepNumber/32.png");
                    break;
                case 33:
                    image = new Image("image/imageView/stepNumber/33.png");
                    break;
                case 34:
                    image = new Image("image/imageView/stepNumber/34.png");
                    break;
                case 35:
                    image = new Image("image/imageView/stepNumber/35.png");
                    break;
                case 36:
                    image = new Image("image/imageView/stepNumber/36.png");
                    break;
                case 37:
                    image = new Image("image/imageView/stepNumber/37.png");
                    break;
                case 38:
                    image = new Image("image/imageView/stepNumber/38.png");
                    break;
                case 39:
                    image = new Image("image/imageView/stepNumber/39.png");
                    break;
                case 40:
                    image = new Image("image/imageView/stepNumber/40.png");
                    break;
            }
        }else if(n == 2){
            x = 402;
            y = 14;
            width = 35;
            height = 35;
            switch (ImageNum) {
                case 1:
                    image = new Image("image/imageView/stageNumber/1.png");
                    break;
                case 2:
                    image = new Image("image/imageView/stageNumber/2.png");
                    break;
                case 3:
                    image = new Image("image/imageView/stageNumber/3.png");
                    break;
                case 4:
                    image = new Image("image/imageView/stageNumber/4.png");
                    break;
                case 5:
                    image = new Image("image/imageView/stageNumber/5.png");
                    break;
                case 6:
                    image = new Image("image/imageView/stageNumber/6.png");
                    break;
                case 7:
                    image = new Image("image/imageView/stageNumber/7.png");
                    break;
                case 8:
                    image = new Image("image/imageView/stageNumber/8.png");
                    break;
                case 9:
                    image = new Image("image/imageView/stageNumber/9.png");
                    break;
                case 10:
                    image = new Image("image/imageView/stageNumber/10.png");
                    break;
                case 11:
                    image = new Image("image/imageView/stageNumber/11.png");
                    break;
                case 12:
                    image = new Image("image/imageView/stageNumber/12.png");
                    break;
                case 13:
                    image = new Image("image/imageView/stageNumber/13.png");
                    break;
                case 14:
                    image = new Image("image/imageView/stageNumber/14.png");
                    break;
                case 15:
                    image = new Image("image/imageView/stageNumber/15.png");
                    break;
                case 16:
                    image = new Image("image/imageView/stageNumber/16.png");
                    break;
                case 17:
                    image = new Image("image/imageView/stageNumber/17.png");
                    break;
                case 18:
                    image = new Image("image/imageView/stageNumber/18.png");
                    break;
                case 19:
                    image = new Image("image/imageView/stageNumber/19.png");
                    break;
                case 20:
                    image = new Image("image/imageView/stageNumber/20.png");
                    break;
            }
        }
    }
}
