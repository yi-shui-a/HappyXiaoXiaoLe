package happyXiaoXiaoLe.modelList;

/**
 *
 * @author yishui
 * @version 1.8.0_301
 * @see Model
 *
 */

public class ModelEndless implements Model {

    /**
     * 新建一个ModelNormal类
     */
    private ModelNormal modelNormal;

    /**
     * 设定游戏时间
     * 单位为毫秒
     */
    private long time = 180000;

    /**
     * 设定初始开始时间为0
     */
    private long start = 0;

    /**
     * 设定初始结束时间为0
     */
    private long end = 0;

    /**
     * 设置时间函数是否运行的标志
     * 开始为正常运行
     */
    private boolean is = true;

    /**
     * 构造函数
     */
    public ModelEndless() {
        modelNormal = new ModelNormal();
    }

    /**
     * 设置等级
     * @param level
     */
    public void setLevel(int level) {
        modelNormal.setLevel(level);

    }

    /**
     * 返回当前地图
     *
     * @return
     */
    public int[][] getMapNow() {
        return modelNormal.getMap();
    }

    /**
     * 生成初始地图
     *
     * @return
     */
    public int[][] getMapZero() {
        modelNormal.generateMap();
        int i = 99999;
        int pass = 0;
        while (true) {
            i = modelNormal.checkMap();
            if (i == -1) {
                modelNormal.setScore(0);
                return modelNormal.getMap();
            } else {
                modelNormal.dropBlock();
            }
        }
    }

    /**
     * 返回交换后的地图
     *
     * @param a1
     * @param a2
     * @param b1
     * @param b2
     * @return int[][]
     */
    public int[][] getMapSwapped(int a1, int a2, int b1, int b2) {
        modelNormal.swapBlock(a1, a2, b1, b2);
        return modelNormal.getMap();
    }

    /**
     * 判读是否可以消去，
     * 如果可以消去，直接消去
     * 如果消去不了，返回-1
     *
     * @return 返回地图数组
     */
    public int getMapSwappedResult() {

        return modelNormal.checkMap();
    }

    /**
     * 返回交换检查后的数值，详见“数据结构.md”
     * 如果返回消去检查后的地图,请直接getMapNow
     *
     * @return int
     */
    public int getMapJudge() {

        int temp = 0;
        temp = modelNormal.checkMap();
        return temp;
    }

    /**
     * 返回确定下落几格后的地图
     * @return int[][]
     */
    public int[][] getMapDropChecked() {

        modelNormal.setDropBlock();
        return modelNormal.getMap();
    }

    /**
     * 返回下落后的地图
     * @return int[][]
     */
    public int[][] getMapAfter() {

        modelNormal.dropBlock();
        return modelNormal.getMap();
    }

    /**
     * 获取当前关卡的关卡数
     *@param
     *@return int
     */
    public int getLevel() {

        return modelNormal.getLevel();
    }

    /**
     * 获取需要消去的方块个数
     *@param
     *@return int
     */
    public int getRemoveNum() {

        return modelNormal.getRemoveNum();
    }

    /**
     * 获取移动的方块种类
     *@param
     *@return int
     */
    public int getRemoveBlock() {

        return modelNormal.getRemoveBlock();
    }

    /**
     * 获取本关卡的当前得分
     *@param
     *@return int
     */
    public int getScore() {

        return modelNormal.getScore();
    }

    /**
     * 获取本关卡当前的剩余步数
     *@param
     *@return int
     */
    public int getStepNum() {

        return modelNormal.getStepNum();
    }

    /**
     * 获取本关卡的剩余时间
     *@param
     *@return int
     */
    public int getRemainTime() {

        if (is) {
            is = false;
            start = System.currentTimeMillis();
            end = System.currentTimeMillis();
        }
        final int timeInterval = 1000;// 一秒钟运行一次
        try {
            end = System.currentTimeMillis();//获取结束时间
            System.out.println(end - start);
            time = time - (end - start);
            start = end;
        } catch (Exception e) {
            System.out.println("Got an exception!");
        }

        return (int) (time / 1000);
    }

    /**
     * 判断当前游戏是否结束
     * 如果时间为0，则游戏结束
     *@param
     *@return boolean
     */
    public boolean GameEnd() {

        if (time <= 0)
            return true;
        return false;
    }

    /**
     * 获取游戏开始时间
     *@param
     *@return long
     */
    public long getStart() {

        return start;
    }

    /**
     * 重新设置游戏开始时间
     *@param
     *@return long
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * 获取游戏结束时间
     *@param
     *@return long
     */
    public long getEnd() {
        return end;
    }

    /**
     * 重新设置游戏结束时间
     *@param
     *@return long
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * 获取当前的时间函数工作状态
     * 如果正在运行返回true
     *@param
     *@return boolean
     */
    public boolean isIs() {

        return is;
    }

    /**
     * 重新设置当前的时间函数工作状态
     *@param is
     *@return void
     */
    public void setIs(boolean is) {

        this.is = is;
    }
}
