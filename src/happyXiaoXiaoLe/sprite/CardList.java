package happyXiaoXiaoLe.sprite;

import happyXiaoXiaoLe.modelList.ModelEndless;
import happyXiaoXiaoLe.modelList.ModelAdventure;
import happyXiaoXiaoLe.modelList.ModelJewel;
import happyXiaoXiaoLe.sound.SoundEffect;

/**
 * @author yishui,Mxkun
 * @version 1.8.0_301
 * @see Card
 * @see Sprite
 * @see ModelJewel
 * @see ModelAdventure
 * @see ModelEndless
 *
 */

public class CardList {
    public final int SIDE = 46;//每个卡片的尺寸
    public Card[][] cards = new Card[10][10];//定义消除的卡片
    public Choice[][] choices = new Choice[10][10];//定义选择框
    public Number[] step = new Number[40];//定义显示步数的卡片
    public Number[] levelImage = new Number[20];//定义显示关卡数的卡片
    private ModelEndless modelEndless = new ModelEndless();
    private ModelAdventure modelAdventure = new ModelAdventure();
    private ModelJewel modelJewel = new ModelJewel();
    public Goal[] goals;//定义显示目标的图片
    public Goal goal;
    public int[][] map;//定义游戏地图

    public int selected = 0;//是否选中，1为选中，0为未选
    public int secondOrNot = 0;//接下来点的是第一个点还是第二个点
    public int x1 = -1, y1 = -1, x2 = -1, y2 = -1;//定义两次选中的卡片的坐标，默认为-1
    private final double SPEED = 2.875;//定义卡片下落的速度

    private int flag = 0;//判断是否需要继续左右，上下移动的
    private int flagSwap = 0;

    private int flagSwapResult = 100;//交换检查的结果，未检查时设为100，使用时值为地图检查后的返回值
    private boolean result = false;//swap2如果交换完毕返回true，未完成返回false

    /**
     * 获取一个无尽模式的对象
     *@param
     *@return happyXiaoXiaoLe.modelList.ModelEndless
     */
    public ModelEndless getModelEndless() {

        return modelEndless;
    }

    /**
     * 获取一个冒险模式的对象
     *@param
     *@return happyXiaoXiaoLe.modelList.ModelAdventure
     */
    public ModelAdventure getModelAdventure() {

        return modelAdventure;
    }

    /**
     * 获取一个宝石模式的对象
     *@param
     *@return happyXiaoXiaoLe.modelList.ModelJewel
     */
    public ModelJewel getModelJewel(){
        return modelJewel;
    }

    /**
     * 重新设置无尽模式的对象
     *@param modelEndless
     *@return void
     */
    public void setModelEndless(ModelEndless modelEndless) {

        this.modelEndless = modelEndless;
    }

    /**
     * 重新设置冒险模式的对象
     *@param modelAdventure
     *@return void
     */
    public void setModelAdventure(ModelAdventure modelAdventure) {

        this.modelAdventure = modelAdventure;
    }

    /**
     * 重新设置宝石模式的对象
     *@param modelJewel
     *@return void
     */
    public void setModelJewel(ModelJewel modelJewel) {

        this.modelJewel = modelJewel;
    }

    /**
     * 重新设置地图
     *@param map
     *@return void
     */
    public void setMap(int[][] map) {

        this.map = map;

    }

    //交换两张卡的关键信息
    public void swapCardData(int x1, int y1, int x2, int y2) {

        Card card = cards[x1][y1];
        cards[x1][y1] = cards[x2][y2];
        cards[x2][y2] = card;

        int temp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = temp;
    }

    /**
     * 生成无尽模式的地图
     *@param
     *@return void
     */
    public void initEndlessMap() {
        map = modelEndless.getMapZero();
    }

    /**
     * 生成冒险模式的地图
     *@param
     *@return void
     */
    public void initAdventureMap() {

        map = modelAdventure.getMapZero();
        goals = new Goal[getModelAdventure().getRemoveSort().length];
    }

    /**
     * 生成宝石模式的地图
     *@param
     *@return void
     */
    public void initJewelMap(){
        map=modelJewel.getMapZero();
    }

    //生成初始卡片
    public void initCard() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //Math.random()*5
                Card card = new Card(290 + j * 46, 70 + i * 46, map[i][j]);
                cards[i][j] = card;
            }
        }
    }

    //生成选择框
    public void initChoice() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Choice choice = new Choice(290 + j * 46, 70 + i * 46, i, j);
                choices[i][j] = choice;

            }
        }
    }

    /**
     * 生成步数对象
     *@param
     *@return void
     */
    public void initStep() {

        for (int i = 0; i < 40; i++) {
            step[i] = new Number(i + 1, 1);
        }
    }

    /**
     * 生成等级图片对象
     *@param
     *@return void
     */
    public void initLevelImage() {

        for (int i = 0; i < 20; i++) {
            levelImage[i] = new Number(i + 1, 2);
        }
    }

    /**
     * 交换两个卡片，
     * 交换完成返回true，
     * 未完成返回false
     * @param moveRight,moveDown
     * @return boolean
     */
    public boolean swap(int moveRight, int moveDown) {

        if (moveRight == 1 && moveDown == 0) {//向右
            if (flag == 0) {
                flag = 1;
                cards[x1][y1].setOx(cards[x1][y1].x + SIDE);
            }
            cards[x1][y1].x += SPEED;
            cards[x2][y2].x -= SPEED;
            if (cards[x1][y1].x - cards[x1][y1].getOx() >= 0) {//运动到既定位置，停止
                cards[x1][y1].x = cards[x1][y1].getOx();
                cards[x2][y2].x = cards[x1][y1].getOx() - SIDE;
                cards[x2][y2].setOx(cards[x2][y2].x);
                flag = 0;
                //swapCardData(x1, y1, x2, y2);
                return true;
            }
        } else if (moveRight == -1 && moveDown == 0) {
            if (flag == 0) {
                flag = 1;
                cards[x1][y1].setOx(cards[x1][y1].x - SIDE);
            }
            cards[x1][y1].x -= SPEED;
            cards[x2][y2].x += SPEED;
            if (cards[x1][y1].getOx() - cards[x1][y1].x >= 0) {//运动到既定位置，停止
                cards[x1][y1].x = cards[x1][y1].getOx();
                cards[x2][y2].x = cards[x1][y1].getOx() + SIDE;
                cards[x2][y2].setOx(cards[x2][y2].x);
                flag = 0;
                //swapCardData(x1, y1, x2, y2);
                return true;
            }
        } else if (moveRight == 0 && moveDown == 1) {//向下
            if (flag == 0) {
                flag = 1;
                cards[x1][y1].setOy(cards[x1][y1].y + SIDE);
            }
            cards[x1][y1].y += SPEED;
            cards[x2][y2].y -= SPEED;
            if (cards[x1][y1].y - cards[x1][y1].getOy() >= 0) {//运动到既定位置，停止
                cards[x1][y1].y = cards[x1][y1].getOy();
                cards[x2][y2].y = cards[x1][y1].getOy() - SIDE;
                cards[x2][y2].setOy(cards[x2][y2].y);
                flag = 0;
                //swapCardData(x1, y1, x2, y2);
                return true;
            }
        } else if (moveRight == 0 && moveDown == -1) {
            if (flag == 0) {
                flag = 1;
                cards[x1][y1].setOy(cards[x1][y1].y - SIDE);
            }
            cards[x1][y1].y -= SPEED;
            cards[x2][y2].y += SPEED;
            if (cards[x1][y1].getOy() - cards[x1][y1].y >= 0) {//运动到既定位置，停止
                cards[x1][y1].y = cards[x1][y1].getOy();
                cards[x2][y2].y = cards[x1][y1].getOy() + SIDE;
                cards[x2][y2].setOy(cards[x2][y2].y);
                flag = 0;
                //swapCardData(x1, y1, x2, y2);
                return true;
            }
        }
        return false;
    }

    /**
     * 无尽模式
     * 交换两个卡片，
     *@param moveRight, moveDown
     *@return int
     */
    public int swap2Endless(int moveRight, int moveDown) {

        swapCardData(x1, y1, x2, y2);
        if (flagSwapResult == 100) {
            flagSwapResult = modelEndless.getMapSwappedResult();
        }

        int temp = flagSwapResult;//暂存flagSwapResult值，用于最终返回
        int a = 0, b = 0;
        if (flagSwapResult == -1 && result == false) {
            SoundEffect.play3();

            if (moveRight == -1) {
                a = 1;
                result = swap(a, b);
            }
            if (moveRight == 1) {
                a = -1;
                result = swap(a, b);
            }
            if (moveDown == -1) {
                b = 1;
                result = swap(a, b);
            }
            if (moveDown == 1) {
                b = -1;
                result = swap(a, b);
            }
        }

        if (result == true) {
            initCard();
            x1 = -1;
            y1 = -1;
            x2 = -1;
            y2 = -1;
            flagSwapResult = 100;
            result = false;
            return temp;
        }

        if (flagSwapResult != -1) {
            initCard();
            SoundEffect.play2();
            x1 = -1;
            y1 = -1;
            x2 = -1;
            y2 = -1;
            flagSwapResult = 100;
            result = false;
            return temp;
        }

        return -2;
    }

    /**
     * 冒险模式
     * 交换两个卡片，
     *@param moveRight, moveDown
     *@return int
     */
    public int swap2Adventure(int moveRight, int moveDown) {
        swapCardData(x1, y1, x2, y2);
        if (flagSwapResult == 100) {
            flagSwapResult = modelAdventure.getMapSwappedResult();
        }

        int temp = flagSwapResult;//暂存flagSwapResult值，用于最终返回
        int a = 0, b = 0;
        if (flagSwapResult == -1 && result == false) {
            SoundEffect.play2();

            if (moveRight == -1) {
                a = 1;
                result = swap(a, b);
            }
            if (moveRight == 1) {
                a = -1;
                result = swap(a, b);
            }
            if (moveDown == -1) {
                b = 1;
                result = swap(a, b);
            }
            if (moveDown == 1) {
                b = -1;
                result = swap(a, b);
            }
        }

        if (result == true) {
            initCard();
            x1 = -1;
            y1 = -1;
            x2 = -1;
            y2 = -1;
            flagSwapResult = 100;
            result = false;
            return temp;
        }

        if (flagSwapResult != -1) {
            initCard();
            SoundEffect.play2();
            x1 = -1;
            y1 = -1;
            x2 = -1;
            y2 = -1;
            flagSwapResult = 100;
            result = false;
            return temp;
        }

        return -2;
    }

    /**
     * 宝石模式
     * 交换两个卡片，
     *@param moveRight, moveDown
     *@return int
     */
    public int swap2Jewel(int moveRight, int moveDown) {
        swapCardData(x1, y1, x2, y2);
        if (flagSwapResult == 100) {
            flagSwapResult = modelJewel.getMapSwappedResult();
        }

        int temp = flagSwapResult;//暂存flagSwapResult值，用于最终返回
        int a = 0, b = 0;
        if (flagSwapResult == -1 && result == false) {
            SoundEffect.play3();

            if (moveRight == -1) {
                a = 1;
                result = swap(a, b);
            }
            if (moveRight == 1) {
                a = -1;
                result = swap(a, b);
            }
            if (moveDown == -1) {
                b = 1;
                result = swap(a, b);
            }
            if (moveDown == 1) {
                b = -1;
                result = swap(a, b);
            }
        }

        if (result == true) {
            initCard();
            x1 = -1;
            y1 = -1;
            x2 = -1;
            y2 = -1;
            flagSwapResult = 100;
            result = false;
            return temp;
        }

        if (flagSwapResult != -1) {
            initCard();
            SoundEffect.play2();
            x1 = -1;
            y1 = -1;
            x2 = -1;
            y2 = -1;
            flagSwapResult = 100;
            result = false;
            return temp;
        }

        return -2;
    }

    /**
     * 鼠标处理事件，
     * 判断能不能移动，位置是不是合适，不判断消除，返回两个值，一个左右移动方向，一个上下移动方向
     * move[1]:1为向下，-1为向上；
     * move[0]：1为向右，-1为向左
     *@param x, y
     *@return int[]
     */
    public int[] dealMouse(double x, double y) {

        int[] move = new int[2];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (secondOrNot == 0 && cards[i][j].isPoint(x, y)) {
                    SoundEffect.play1();
                    x1 = i;
                    y1 = j;
                    selected = 1;
                    System.out.println("第一次记点");
                    System.out.println(x1 + "  " + y1);
                    secondOrNot = 1;
                    break;
                } else if (secondOrNot == 1 && cards[i][j].isPoint(x, y)) {
                    SoundEffect.play1();
                    x2 = i;
                    y2 = j;
                    selected = 0;
                    if (y1 == y2) {
                        if (x1 + 1 == x2) {
                            move[1] = 1;//向下
                            System.out.println("向下");
                        } else if (x1 - 1 == x2) {
                            move[1] = -1;//向上
                            System.out.println("向上");
                        } else if (x1 == x2) {
                            x1 = 10;
                            x2 = 10;
                            y1 = 10;
                            y2 = 10;
                        } else {
                            x1 = 10;
                            x2 = 10;
                            y1 = 10;
                            y2 = 10;
                        }
                    } else if (x1 == x2) {
                        if (y1 + 1 == y2) {
                            move[0] = 1;//向右
                            System.out.println("向右");
                        } else if (y1 - 1 == y2) {
                            move[0] = -1;//向左
                            System.out.println("向左");
                        } else {
                            x1 = 10;
                            x2 = 10;
                            y1 = 10;
                            y2 = 10;
                        }
                    } else {
                        x1 = 10;
                        x2 = 10;
                        y1 = 10;
                        y2 = 10;
                    }
                    System.out.println("第二次记点");
                    System.out.println(x2 + "  " + y2);
                    secondOrNot = 0;
                    break;
                }
            }
        }
        return move;
    }

    /**
     * 返回交换检查后的数值，详见“数据结构.md”
     *@param
     *@return int
     */
    public int getMapJudge() {

        return modelEndless.getMapJudge();
    }

    /**
     * 确定每个方块的目标下落目标高度Oy
     *@param
     *@return void
     */
    public void cardDropJudge() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cards[i][j].fall();
            }
        }
    }

    /**
     * 方块下落函数
     * 每次调用时为止下落一定高度
     * 目标下落位置到达后停止
     *@param
     *@return boolean
     */
    public boolean cardDrop() {

        int n = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (cards[i][j].getOy() > cards[i][j].y) {
                    cards[i][j].y += SPEED;
                } else {
                    n++;
                    continue;
                }
            }
        }
        if (n > 99)
            return true;
        else return false;
    }


}

