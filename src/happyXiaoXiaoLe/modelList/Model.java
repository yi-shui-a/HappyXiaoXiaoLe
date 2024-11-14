package happyXiaoXiaoLe.modelList;

/**
 *
 * @author yishui
 * @version 1.8.0_301
 *
 */

public interface Model {

    /**
     * 设置等级
     * @param level
     */
    public void setLevel(int level);

    /**
     * 返回当前地图
     * @return
     */
    public int[][] getMapNow();

    /**
     * 生成初始地图
     * @return
     */
    public int[][] getMapZero();

    /**
     * 返回交换后的地图
     * @param a1
     * @param a2
     * @param b1
     * @param b2
     * @return
     */
    public int[][] getMapSwapped(int a1, int a2, int b1, int b2);

    /**
     * 返回交换检查后的数值，详见“数据结构.md”
     * 返回消去检查后的地图,请直接getMapNow
     * @return
     */
    public int getMapJudge();

    /**
     * 返回确定下落几格后的地图
     * @return
     */
    public int[][] getMapDropChecked();

    /**
     * 返回下落后的地图
     * @return
     */
    public int[][] getMapAfter();

    /**
     * 判读是否可以消去，如果消去不了，返回原状
     * @return 返回地图数组
     */
    public int getMapSwappedResult();
}
