package happyXiaoXiaoLe.modelList;

/**
 *
 * @author yishui
 * @version 1.8.0_301
 * @see Model
 * @see ModelAdventureSpecial
 */

public class ModelAdventure implements Model{

    /**
     * 创建一个ModelAdventureSpecial的属性，方便调用相关的函数
     */
    private ModelAdventureSpecial modelAdventureSpecial;

    /**
     * 构造函数
     *@param
     *@return
     */
    public ModelAdventure(){

        modelAdventureSpecial=new ModelAdventureSpecial();
    }

    /**
     * 设置等级
     * @param level
     */
    public void setLevel(int level){
        modelAdventureSpecial.setLevel(level);
    }


    /**
     * 返回当前地图
     *@param
     *@return int[][]
     */
    public int[][] getMapNow(){

        return modelAdventureSpecial.getMap();
    }

    /**
     * 生成初始地图
     * @return int[][]
     */
    public int[][] getMapZero(){
        modelAdventureSpecial.generateMap();
        return modelAdventureSpecial.getMap();
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

        modelAdventureSpecial.swapBlock(a1, a2, b1, b2);
        return modelAdventureSpecial.getMap();
    }

    /**
     * 返回交换检查后的数值，详见“数据结构.md”
     * 返回消去检查后的地图,请直接getMapNow
     * @param
     * @return int
     */
    public int getMapJudge(){

        int temp = 0;
        temp = modelAdventureSpecial.checkMap();
        return temp;
    }

    /**
     * 返回确定下落几格后的地图
     *@param
     *@return int[][]
     */
    public int[][] getMapDropChecked(){

        modelAdventureSpecial.setDropBlock();
        return modelAdventureSpecial.getMap();
    }

    /**
     * 返回下落后的地图
     *@param
     *@return int[][]
     */
    public int[][] getMapAfter(){

        modelAdventureSpecial.dropBlock();
        return modelAdventureSpecial.getMap();
    }

    /**
     * 判读是否可以消去，
     * 如果可以消去，直接消去
     * 如果消去不了，返回-1
     *
     *  *@param
     *  *@return int
     */
    public int getMapSwappedResult(){

        return modelAdventureSpecial.checkMap();
    }

    /**
     *@param
     *@return int
     */
    public int PassOrFail(){

        return modelAdventureSpecial.PassOrFail();
    }


    /**
     * 获得当前的分数
     *@param
     *@return int
     */
    public int getScore(){

        return modelAdventureSpecial.getScore();
    }

    /**
     * 获取剩余步数
     *@param
     *@return int
     */
    public int getStepRemain(){

        return modelAdventureSpecial.getStepRemain();
    }

    /**
     * 获取每种方块仍需要消除的方块个数
     *@param
     *@return int[]
     */
    public int[] getBlockRemainNum(){

        return modelAdventureSpecial.getBlockRemainNum();
    }

    /**
     * 获得第一种方块所要消除的方块种类
     *@param
     *@return int[]
     */
    public int[] getRemoveBlock1(){

        return modelAdventureSpecial.getRemoveBlock1();
    }

    /**
     * 获得第二次种方块所要消除的方块种类
     *@param
     *@return int[]
     */
    public int[] getRemoveBlock2(){

        return modelAdventureSpecial.getRemoveBlock2();
    }

    /**
     * 将游戏结果转换为字符串
     *@param result
     *@return java.lang.String
     */
    public String toString(int result){

        return modelAdventureSpecial.toString(result);
    }

    /**
     * 判断游戏是否胜利
     *@param
     *@return int
     */
    public int passOrNot(){

        return modelAdventureSpecial.PassOrFail();
    }

    /**
     * 在每次移动后减少当前的剩余步数
     *@param
     *@return int
     */
    public int decreaseStepRemain(){

        int temp=modelAdventureSpecial.getStepRemain();
        int[][] map=getMapNow();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(map[i][j]==0){
                    modelAdventureSpecial.setStepRemain(temp-1);
                    return modelAdventureSpecial.getStepRemain();
                }
            }
        }
        return modelAdventureSpecial.getStepRemain();
    }

    /**
     * 获取需要消除的方块的种类
     *@param
     *@return java.lang.String[]
     */
    public String[] getRemoveSort(){

        return modelAdventureSpecial.getRemoveSort();
    }

}
