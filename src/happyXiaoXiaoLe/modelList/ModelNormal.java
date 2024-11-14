package happyXiaoXiaoLe.modelList;


/**
 *
 * @author yishui
 * @version 1.8.0_301
 *
 */

public class ModelNormal {

    /**
     * 创建一个游戏地图数组
     */
    private int[][] map;

    /**
     * 判断当前关卡的等级
     */
    private int level;

    /**
     * 定义关卡的地图大小比例
     */
    private final int LEFT = 10;
    private final int RIGHT = 10;

    /**
     * 记录关卡的当前得分
     */
    private int score = 0;

    /**
     * 记录游戏的当前消耗步数
     */
    private int stepNum = 0;

    /**
     * 记录游戏的当前剩余步数
     */
    private int stepRemain = 0;

    private int removeNum = 0;//已经消除的特定方块数量
    private int removeBlock = 1;//需要消除的方块种类

    /**
     * 构造函数
     * 创建一个新的地图对象
     * 将当前等级初始化为0
     */
    public ModelNormal() {

        map = new int[LEFT][RIGHT];
        this.level = 0;
    }

    /**
     * 获得当前地图
     * @param
     * @return int[][]
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * 输入当前关卡等级
     * 为当前关卡的步数限制赋值
     *
     * @param level
     * @return void
     */
    public void setLevel(int level) {

        this.level = level;
        if (level <= 5)
            stepRemain = 15 + 7 * level;
        if (level >= 6 && level <= 10)
            stepRemain = 13 + 6 * level;
        if (level >= 11 && level <= 15)
            stepRemain = 20 + 6 * level;

    }

    /**
     * 获取当前的关卡等级
     *@param
     *@return int
     */
    public int getLevel() {
        return level;
    }

    /**
     * 获取当前需要消除的方块数量
     *@param
     *@return int
     */
    public int getRemoveNum() {

        return removeNum;
    }

    /**
     * 获取需要消除的方块种类
     *@param
     *@return int
     */
    public int getRemoveBlock() {

        return removeBlock;
    }

    /**
     * 获取当前的游戏分数
     *@param
     *@return int
     */
    public int getScore() {
        return score;
    }

    /**
     * 重新设置当前的游戏分数
     *@param score
     *@return void
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 获取当前已经走过的步数
     *@param
     *@return int
     */
    public int getStepNum() {

        return stepNum;
    }

    /**
     * 完成一次点击后增加步数
     * @param
     * @return int
     */
    public int increaseStepNum() {
        stepNum++;
        return stepNum;
    }

    /**
     * 完成一次点击后减少剩余步数
     * 并返回减少后的步数
     * @param
     * @return int
     */
    public int reduceStepRemain() {
        stepRemain = stepRemain - 1;
        return stepRemain;
    }


    /**
     * 随机生成所要消除的方块种类
     * @param
     * @return void
     */
    private void selectBlock() {

        removeBlock = (int) (Math.random() * 4) + 1;
    }

    /**
     * 交换两个方块的值
     * @param
     * @return void
     */
    public void swapBlock(int a1, int a2, int b1, int b2) {

        if (map[a1][a2] < 0 || map[b1][b2] < 0)
            return;
        int temp = map[a1][a2];
        map[a1][a2] = map[b1][b2];
        map[b1][b1] = temp;
    }

    /**
     * 随机生成一个10*10的地图
     * *@param []
     * *@return void
     */
    public void generateMap() {

        for (int i = 0; i < LEFT; i++) {
            for (int j = 0; j < RIGHT; j++) {
                map[i][j] = ((int) (Math.random() * 4) + 1) * 10000000;
                while (map[i][j] == 0)
                    map[i][j] = ((int) (Math.random() * 4) + 1) * 10000000;
            }
        }

        /*
        心形地图
           0123456789
            **    **
           ****  ****
           **********
           **********
            ********
            ********
             ******
             ******
              ****
               **
         */
        if (level >= 11 && level <= 15) {
            map[0][1] = -1;
            map[0][2] = -1;
            map[0][7] = -1;
            map[0][8] = -1;

            int i = 1;
            for (int j = 0; j < RIGHT; j++) {
                if (j != 4 && j != 5)
                    map[i][j] = -1;
            }
            for (i = 2; i < 4; i++) {
                for (int j = 0; j < RIGHT; j++) {
                    map[i][j] = -1;
                }
            }
            for (i = 4; i < 6; i++) {
                for (int j = 1; j < RIGHT - 1; j++) {
                    map[i][j] = -1;
                }
            }
            for (i = 6; i < 8; i++) {
                for (int j = 2; j < RIGHT - 2; j++) {
                    map[i][j] = -1;
                }
            }
            i = 8;
            for (int j = 3; j < RIGHT - 3; j++) {
                map[i][j] = -1;
            }
            i = 9;
            map[i][4] = 0;
            map[i][5] = 0;
        }


        if (level > 5 && level <= 10) {
            int specialNum = 2 * (level - 4);
            int x = 0, y = 0;

            for (int i = 1; i <= specialNum; i++) {
                x = (int) (Math.random() * 10);
                y = (int) (Math.random() * 10);
                map[x][y] = map[x][y] + 10000;
            }
        }

    }

    /**
     * 随机生成一个方块,返回其编码
     * @param
     * @return int
     */
    public int randomBlock() {
        int temp = 0;
        temp = ((int) (Math.random() * 4) + 1) * 10000000;
        while (temp == 0)
            temp = ((int) (Math.random() * 4) + 1) * 10000000;
        return temp;
    }

    /**
     * 数组检查是否可以消去
     * @param
     * @return int
     */
    public int checkMap() {

        //T连检查
        for (int i = 0; i < LEFT; i++) {
            for (int j = 0; j < RIGHT - 4; j++) {
                if (map[i][j] <= 0 || map[i][j + 1] <= 0 || map[i][j + 2] <= 0 ||
                        map[i][j + 3] <= 0 || map[i][j + 4] <= 0) {
                    break;
                }
                if (map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2] &&
                        map[i][j] == map[i][j + 3] && map[i][j] == map[i][j + 4]) {
                    if (i <= 1) {
                        if (map[i + 1][j + 2] <= 0 || map[i + 2][j + 2] <= 0)
                            break;
                        if (map[i][j + 2] == map[i + 1][j + 2] && map[i][j + 2] == map[i + 2][j + 2]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i][j + 3] = judge(map[i][j + 3]);
                            map[i][j + 4] = judge(map[i][j + 4]);
                            map[i + 1][j + 2] = judge(map[i + 1][j + 2]);
                            map[i + 2][j + 2] = judge(map[i + 2][j + 2]);
                            score = score + 1500;
                            return 1;//T连成功
                        }
                    }
                    if (i >= 2 && i < LEFT - 2) {
                        if (map[i + 1][j + 2] <= 0 || map[i + 2][j + 2] <= 0)
                            break;
                        if (map[i][j + 2] == map[i + 1][j + 2] && map[i][j + 2] == map[i + 2][j + 2]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i][j + 3] = judge(map[i][j + 3]);
                            map[i][j + 4] = judge(map[i][j + 4]);
                            map[i + 1][j + 2] = judge(map[i + 1][j + 2]);
                            map[i + 2][j + 2] = judge(map[i + 2][j + 2]);
                            score = score + 1500;
                            return 1;//T连成功
                        }
                        if (map[i - 1][j + 2] <= 0 || map[i - 2][j + 2] <= 0)
                            break;
                        if (map[i][j + 2] == map[i - 1][j + 2] && map[i][j + 2] == map[i - 2][j + 2]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i][j + 3] = judge(map[i][j + 3]);
                            map[i][j + 4] = judge(map[i][j + 4]);
                            map[i - 1][j + 2] = judge(map[i - 1][j + 2]);
                            map[i - 2][j + 2] = judge(map[i - 2][j + 2]);
                            score = score + 1500;
                            return 1;//T连成功
                        }
                    }
                    if (i >= LEFT - 2) {
                        if (map[i - 1][j + 2] <= 0 || map[i - 2][j + 2] <= 0)
                            break;
                        if (map[i][j + 2] == map[i - 1][j + 2] && map[i][j + 2] == map[i - 2][j + 2]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i][j + 3] = judge(map[i][j + 3]);
                            map[i][j + 4] = judge(map[i][j + 4]);
                            map[i - 1][j + 2] = judge(map[i - 1][j + 2]);
                            map[i - 2][j + 2] = judge(map[i - 2][j + 2]);
                            score = score + 1500;
                            return 1;//T连成功
                        }
                    }
                }
            }
        }

        for (int j = 0; j < RIGHT; j++) {
            for (int i = 0; i < LEFT - 4; i++) {
                if (map[i][j] <= 0 || map[i + 1][j] <= 0 || map[i + 2][j] <= 0 ||
                        map[i + 3][j] <= 0 || map[i + 4][j] <= 0)
                    break;
                if (map[i][j] == map[i + 1][j] && map[i][j] == map[i + 2][j] &&
                        map[i][j] == map[i + 3][j] && map[i][j] == map[i + 4][j]) {
                    if (j <= 1) {
                        if (map[i + 2][j + 1] <= 0 || map[i + 2][j + 2] <= 0)
                            break;
                        if (map[i + 2][j] == map[i + 2][j + 1] && map[i + 2][j] == map[i + 2][j + 2]) {
                            map[i][j] = judge(map[i][j]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            map[i + 3][j] = judge(map[i + 3][j]);
                            map[i + 4][j] = judge(map[i + 4][j]);
                            map[i + 2][j + 1] = judge(map[i + 2][j + 1]);
                            map[i + 2][j + 2] = judge(map[i + 2][j + 2]);
                            score = score + 1500;
                            return 1;//T连成功
                        }
                    }
                    if (i >= 2 && i < RIGHT - 2 ) {
                        if (map[i + 2][j + 1] <= 0 || map[i + 2][j + 2] <= 0)
                            break;
                        if (map[i + 2][j] == map[i + 2][j + 1] && map[i + 2][j] == map[i + 2][j + 2]) {
                            map[i][j] = judge(map[i][j]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            map[i + 3][j] = judge(map[i + 3][j]);
                            map[i + 4][j] = judge(map[i + 4][j]);
                            map[i + 2][j + 1] = judge(map[i + 2][j + 1]);
                            map[i + 2][j + 2] = judge(map[i + 2][j + 2]);
                            score = score + 1500;
                            return 1;//T连成功
                        }
                        if (map[i + 2][j - 1] <= 0 || map[i + 2][j - 2] <= 0)
                            break;
                        if (map[i + 2][j] == map[i + 2][j - 1] && map[i + 2][j] == map[i + 2][j - 2]) {
                            map[i][j] = judge(map[i][j]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            map[i + 3][j] = judge(map[i + 3][j]);
                            map[i + 4][j] = judge(map[i + 4][j]);
                            map[i + 2][j - 1] = judge(map[i + 2][j - 1]);
                            map[i + 2][j - 2] = judge(map[i + 2][j - 2]);
                            score = score + 1500;
                            return 1;//T连成功
                        }
                    }
                    if (i >= RIGHT - 2) {
                        if (map[i + 2][j - 1] <= 0 || map[i + 2][j - 2] <= 0)
                            break;
                        if (map[i + 2][j] == map[i + 2][j - 1] && map[i + 2][j] == map[i + 2][j - 2]) {
                            map[i][j] = judge(map[i][j]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            map[i + 3][j] = judge(map[i + 3][j]);
                            map[i + 4][j] = judge(map[i + 4][j]);
                            map[i + 2][j - 1] = judge(map[i + 2][j - 1]);
                            map[i + 2][j - 2] = judge(map[i + 2][j - 2]);
                            score = score + 1500;
                            return 1;//T连成功
                        }
                    }
                }
            }
        }

        //L连判断
        for (int i = 0; i < LEFT; i++) {
            for (int j = 0; j < RIGHT - 2; j++) {
                if (map[i][j] <=0 || map[i][j + 1] <= 0 || map[i][j + 2] <= 0)
                    break;
                if (map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2]) {
                    if (i <= 1) {
                        if (map[i + 1][j] <= 0 || map[i + 2][j] <= 0)
                            break;
                        if (map[i][j] == map[i + 1][j] && map[i][j] == map[i + 2][j]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                    }
                    if (i >= 2 && i < LEFT - 2) {
                        if (map[i + 1][j] <= 0 || map[i + 2][j] <= 0)
                            break;
                        if (map[i][j] == map[i + 1][j] && map[i][j] == map[i + 2][j]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                        if (map[i - 1][j] <= 0 || map[i - 2][j] <= 0)
                            break;
                        if (map[i][j] == map[i - 1][j] && map[i][j] == map[i - 2][j]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i - 1][j] = judge(map[i - 1][j]);
                            map[i - 2][j] = judge(map[i - 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                    }
                    if (i >= LEFT - 2) {
                        if (map[i - 1][j] <= 0 || map[i - 2][j] <= 0)
                            break;
                        if (map[i][j] == map[i - 1][j] && map[i][j] == map[i - 2][j]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i - 1][j] = judge(map[i - 1][j]);
                            map[i - 2][j] = judge(map[i - 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                    }
                }
            }
        }

        for (int i = 0; i < LEFT; i++) {
            for (int j = 2; j < RIGHT; j++) {
                if (map[i][j] <= 0 || map[i][j - 1] <= 0 || map[i][j - 1] <= 0)
                    break;
                if (map[i][j] == map[i][j - 1] && map[i][j] == map[i][j - 2]) {
                    if (i <= 1) {
                        if (map[i + 1][j] <= 0 || map[i + 2][j] <= 0)
                            break;
                        if (map[i][j] == map[i + 1][j] && map[i][j] == map[i + 2][j]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j - 1] = judge(map[i][j - 1]);
                            map[i][j - 2] = judge(map[i][j - 2]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                    }
                    if (i >= 2 && i < LEFT - 2) {
                        if (map[i + 1][j] <= 0 || map[i + 2][j] <= 0)
                            break;
                        if (map[i][j] == map[i + 1][j] && map[i][j] == map[i + 2][j]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j - 1] = judge(map[i][j - 1]);
                            map[i][j - 2] = judge(map[i][j - 2]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                        if (map[i - 1][j] <= 0 || map[i - 2][j] <= 0)
                            break;
                        if (map[i][j] == map[i - 1][j] && map[i][j] == map[i - 2][j]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j - 1] = judge(map[i][j - 1]);
                            map[i][j - 2] = judge(map[i][j - 2]);
                            map[i - 1][j] = judge(map[i - 1][j]);
                            map[i - 2][j] = judge(map[i - 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                    }
                    if (i >= LEFT - 2) {
                        if (map[i - 1][j] <= 0 || map[i - 2][j] <= 0)
                            break;
                        if (map[i][j] == map[i - 1][j] && map[i][j] == map[i - 2][j]) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j - 1] = judge(map[i][j - 1]);
                            map[i][j - 2] = judge(map[i][j - 2]);
                            map[i - 1][j] = judge(map[i - 1][j]);
                            map[i - 2][j] = judge(map[i - 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                    }
                }
            }
        }

        //五连
        for (int i = 0; i < LEFT; i++) {
            for (int j = 0; j < RIGHT - 4; j++) {
                if (map[i][j] <= 0 || map[i][j + 1] <= 0 || map[i][j + 2] <= 0 ||
                        map[i][j + 3] <= 0 || map[i][j + 4] <= 0)
                    break;
                if (map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2] && map[i][j] == map[i][j + 3] &&
                        map[i][j] == map[i][j + 4]) {
                    map[i][j] = judge(map[i][j]);
                    map[i][j + 1] = judge(map[i][j + 1]);
                    map[i][j + 2] = judge(map[i][j + 2]);
                    map[i][j + 3] = judge(map[i][j + 3]);
                    map[i][j + 4] = judge(map[i][j + 4]);
                    score = score + 1000;
                    return 3;
                }
            }
        }

        for (int j = 0; j < RIGHT; j++) {
            for (int i = 0; i < LEFT - 4; i++) {
                if (map[i][j] <= 0 || map[i + 1][j] <= 0 || map[i + 2][j] <= 0 ||
                        map[i + 3][j] <= 0 || map[i + 4][j] <= 0)
                    break;
                if (map[i][j] == map[i + 1][j] && map[i][j] == map[i + 2][j] && map[i][j] == map[i + 3][j] &&
                        map[i][j] == map[i + 4][j]) {
                    map[i][j] = judge(map[i][j]);
                    map[i + 1][j] = judge(map[i + 1][j]);
                    map[i + 2][j] = judge(map[i + 2][j]);
                    map[i + 3][j] = judge(map[i + 3][j]);
                    map[i + 4][j] = judge(map[i + 4][j]);
                    score = score + 1000;
                    return 3;
                }
            }
        }

        //四连
        for (int i = 0; i < LEFT; i++) {
            for (int j = 0; j < RIGHT - 3; j++) {
                if (map[i][j] <= 0 || map[i][j + 1] <= 0 || map[i][j + 2] <= 0 ||
                        map[i][j + 3] <= 0)
                    break;
                if (map[i][j] == map[i][j + 1] && map[i][j] == map[i][j + 2] && map[i][j] == map[i][j + 3]) {
                    map[i][j] = judge(map[i][j]);
                    map[i][j + 1] = judge(map[i][j + 1]);
                    map[i][j + 2] = judge(map[i][j + 2]);
                    map[i][j + 3] = judge(map[i][j + 3]);
                    score = score + 500;
                    return 4;
                }
            }
        }

        for (int j = 0; j < RIGHT; j++) {
            for (int i = 0; i < LEFT - 3; i++) {
                if (map[i][j] <= 0 || map[i + 1][j] <= 0 || map[i + 2][j] <= 0 ||
                        map[i + 3][j] <= 0)
                    break;
                if (map[i][j] == map[i + 1][j] && map[i][j] == map[i + 2][j] && map[i][j] == map[i + 3][j]) {
                    map[i][j] = judge(map[i][j]);
                    map[i + 1][j] = judge(map[i + 1][j]);
                    map[i + 2][j] = judge(map[i + 2][j]);
                    map[i + 3][j] = judge(map[i + 3][j]);
                    score = score + 500;
                    return 4;
                }
            }
        }

        //三连
        for (int i = 0; i < LEFT; i++) {
            for (int j = 0; j < RIGHT - 2; j++) {
                if (map[i][j] <= 0 || map[i][j + 1] <= 0 || map[i][j + 2] <= 0) {
                    break;
                }
                if (map[i][j] == map[i][j + 1] && map[i][j+1] == map[i][j + 2]) {
                    map[i][j] = judge(map[i][j]);
                    map[i][j + 1] = judge(map[i][j + 1]);
                    map[i][j + 2] = judge(map[i][j + 2]);
                    score = score + 300;
                    return 5;
                }
            }
        }

        for (int j = 0; j < RIGHT; j++) {
            for (int i = 0; i < LEFT - 2; i++) {
                if (map[i][j] <= 0 || map[i + 1][j] <= 0 || map[i + 2][j] <= 0) {
                    break;
                }
                if (map[i][j] == map[i + 1][j] && map[i][j] == map[i + 2][j]) {
                    map[i][j] = judge(map[i][j]);
                    map[i + 1][j] = judge(map[i + 1][j]);
                    map[i + 2][j] = judge(map[i + 2][j]);
                    score = score + 300;
                    return 5;
                }
            }
        }

        return -1;//如果没有可以消除的方块，返回-1
    }

    /**
     * 确认方块需要下落几格,并添加标记
     * @param
     * @retrun void
     */

    public void setDropBlock() {

        for (int i = LEFT - 1; i >= 0; i--) {
            for (int j = RIGHT - 1; j >= 0; j--) {
                if (map[i][j] < 0)
                    continue;
                if (map[i][j] == 0) {
                    for (int i2 = i; i2 >= 0; i2--) {
                        if (map[i2][j] < 0 || map[i2][j] == 0)
                            continue;
                        map[i2][j] = map[i2][j] + 1000000;
                    }
                }
            }
        }
    }

    /**
     * 方块随机下落,并且直接修改地图为下落后的状态
     */
    public void dropBlock() {

        for (int i = LEFT - 1; i >= 0; i--) {
            for (int j = RIGHT - 1; j >= 0; j--) {
                if (map[i][j] < 0)
                    continue;
                while (map[i][j] == 0) {
                    for (int i2 = i; i2 > 0; i2--) {
                        if (map[i2][j] < 0)
                            continue;
                        map[i2][j] = map[i2 - 1][j];
                    }
                    map[0][j] = randomBlock();
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int temp;
                while ((int)((map[i][j]%10000000)/1000000)!=0)
                    map[i][j]-=1000000;
            }
        }
    }

    /**
     * 判断方块是消减某种东西还是消除
     */
    public int judge(int block) {
        int temp = block % 100000;
        temp = (int) (temp / 10000);
        if (temp == 0) {
            if ((int) (block / 10000000) == removeBlock)
                removeNum = removeNum + 1;
            return 0;//消除态
        }
        if (temp > 0) {
            score = score + 100;
            return block - 10000;
        }
        return 0;
    }

    /**
     * 冰块判断,是否消除干净
     */
    private boolean iceRemoveAllJudge() {

        int temp = 0;
        for (int i = 0; i < LEFT; i++) {
            for (int j = 0; j < RIGHT; j++) {
                if (map[i][j] <= 0)
                    continue;
                temp = map[i][j] % 100000;
                temp = (int) (temp / 10000);
                if (temp > 0)
                    return false;
            }
        }
        return true;
    }

}
