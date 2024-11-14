package happyXiaoXiaoLe.modelList;

/**
 *
 * @author yishui
 * @version 1.8.0_301
 * @see Model
 *
 */

public class ModelJewel implements Model {

    /**
     * 定义一个ModelJewelSpecial类
     */
    private ModelJewelSpecial modelJewelSpecial;

    /**
     * 构造函数，
     * 创建一个ModelJewelSpecial对象
     */
    public ModelJewel(){
        modelJewelSpecial=new ModelJewelSpecial();
    }

    /**
     * 设置等级
     * @param level
     */
    public void setLevel(int level){
        modelJewelSpecial.setLevel(level);
    }

    /**
     * 返回当前地图
     * @return int[][]
     */
    public int[][] getMapNow(){
        return modelJewelSpecial.getMap();
    }

    /**
     * 生成初始地图
     * @return int[][]
     */
    public int[][] getMapZero(){
        modelJewelSpecial.generateMap();
        return modelJewelSpecial.getMap();
    }

    /**
     * 返回交换后的地图
     * @param a1
     * @param a2
     * @param b1
     * @param b2
     * @return int[][]
     */
    public int[][] getMapSwapped(int a1, int a2, int b1, int b2){

        modelJewelSpecial.swapBlock(a1, a2, b1, b2);
        return modelJewelSpecial.getMap();
    }

    /**
     * 返回交换检查后的数值，详见“数据结构.md”
     * 返回消去检查后的地图,请直接getMapNow
     * @return int
     */
    public int getMapJudge(){

        int temp = 0;
        temp = modelJewelSpecial.checkMap();
        return temp;
    }

    /**
     * 返回确定下落几格后的地图
     * @return int[][]
     */
    public int[][] getMapDropChecked(){

        modelJewelSpecial.setDropBlock();
        return modelJewelSpecial.getMap();
    }

    /**
     * 返回下落后的地图
     * @return int[][]
     */
    public int[][] getMapAfter(){
        modelJewelSpecial.dropBlock();
        return modelJewelSpecial.getMap();
    }

    /**
     * 判读是否可以消去，
     * 如果可以消去，直接消去
     * 如果消去不了，返回-1
     * @see int
     */
    public int getMapSwappedResult(){

        return modelJewelSpecial.checkMap();
    }

    /**
     * 判断当前游戏是否胜利
     * 胜利返回星级数据
     * @param
     * @return int
     */
    public int PassOrFail(){
        return modelJewelSpecial.PassOrFail();
    }


    /**
     * 获取本关卡的当前得分
     *@param
     *@return int
     */
    public int getScore(){

        return modelJewelSpecial.getScore();
    }

    /**
     * 获取本关卡当前的剩余步数
     *@param
     *@return int
     */
    public int getStepRemain(){

        return modelJewelSpecial.getStepRemain();
    }

    /**
     * 获取当前还未消除的方块数量
     *@param
     *@return int
     */
    public int getBlockRemainNum(){

        return modelJewelSpecial.getBlockRemainNum();
    }

    /**
     * 获取所需要消除的方块种类
     *@param
     *@return int[]
     */
    public int[] getRemoveBlock(){

        return modelJewelSpecial.getRemoveBlock();
    }

    /**
     * 将游戏结果数据转换为字符串
     *@param result
     *@return java.lang.String
     */
    public String toString(int result){

        return modelJewelSpecial.toString(result);
    }

    /**
     * 判断当前游戏是否结束，
     * 如果结束，返回对应的星级信息
     *@param
     *@return int
     */
    public int passOrNot(){

        return modelJewelSpecial.PassOrFail();
    }

    /**
     * 在每次交换完成后，判断此时是否需要减少剩余步数
     * 如果需要减少步数，返回减少后的剩余步数
     * 如果不需要减少步数，返回当前的剩余步数
     *@param
     *@return int
     */
    public int decreaseStepRemain(){

        int temp=modelJewelSpecial.getStepRemain();
        int[][] map=getMapNow();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(map[i][j]==0){
                    modelJewelSpecial.setStepRemain(temp-1);
                    return modelJewelSpecial.getStepRemain();
                }
            }
        }
        return modelJewelSpecial.getStepRemain();
    }

    /**
     * 获得需要消除的方块种类
     *@param
     *@return java.lang.String
     */
    public String getRemoveSort(){

        return modelJewelSpecial.getRemoveSort();
    }

}
