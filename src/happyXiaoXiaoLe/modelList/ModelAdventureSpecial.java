package happyXiaoXiaoLe.modelList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author yishui
 * @version 1.8.0_301
 *
 */


public class ModelAdventureSpecial {
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
     * 记录游戏的当前剩余步数
     */
    private int stepRemain = 0;//剩余步数

    private int[] blockRemainNum;//还需要消除的特定方块数量
    private int[] removeBlock1;//需要消除的方块种类,8位数字
    private int[] removeBlock2;

    /**
     * 记录游戏需要消除道德方块种类
     * 冰块、方块
     */
    private String[] removeSort;

    /**
     * 记录获取各个星级所需要的分数
     * 通过读取文件获得
     */
    private int passScore3 = 0;//三星所需分数
    private int passScore2 = 0;
    private int passScore1 = 0;//一星所需分数

    /**
     * 构造函数
     */
    public ModelAdventureSpecial() {
        map = new int[LEFT][RIGHT];
        this.level = 0;
    }

    /**
     * 获取当前的剩余步数
     * @param
     * @return int
     */
    public int getStepRemain() {

        return stepRemain;
    }

    /**
     *重新设置当前的剩余步数
     *@param stepRemain
     *@return void
     */
    public void setStepRemain(int stepRemain) {

        this.stepRemain = stepRemain;
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
     * 完成一次点击后减少剩余步数
     * 返回剩余步数
     * @param
     * @return int
     */
    public int reduceStepRemain() {

        stepRemain = stepRemain - 1;
        return stepRemain;
    }

    /**
     * 从地图中交换两个方块的值
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
     * 读取文件生成一个10*10的地图
     * *@param []
     * *@return void
     */
    public void generateMap() throws RuntimeException {

        File file = new File("data/GameData/AdventureMap/" + level + ".txt");
        StringBuilder result = new StringBuilder();
        try {
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String s = null;
            String str = null;
            if ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                StringTokenizer st = new StringTokenizer(s, "-");
                stepRemain = Integer.parseInt(st.nextToken());
                passScore3 = Integer.parseInt(st.nextToken());
                passScore2 = Integer.parseInt(st.nextToken());
                passScore1 = Integer.parseInt(st.nextToken());
            }
            int num = 0;
            ArrayList<String> arrayRemoveSort = new ArrayList<>();
            ArrayList<Integer> arrayBlockRemainNum = new ArrayList<>();
            ArrayList<Integer> arrayRemoveBlock1 = new ArrayList<>();
            ArrayList<Integer> arrayRemoveBlock2 = new ArrayList<>();


            while (!((s = br.readLine()).contains("<"))) {
                StringTokenizer st = new StringTokenizer(s, "-");
                arrayRemoveSort.add(st.nextToken());
                arrayBlockRemainNum.add(Integer.parseInt(st.nextToken()));

                if (num == 0) {
                    int temp1=st.countTokens();
                    for (int i = 0; i < temp1; i++) {
                        str = st.nextToken();
                        arrayRemoveBlock1.add(Integer.parseInt(str));
                    }
                }

                if (num == 1) {
                    int temp2= st.countTokens();
                    for (int i = 0; i < temp2; i++) {
                        str = st.nextToken();
                        arrayRemoveBlock2.add(Integer.parseInt(str));
                    }
                }
                num++;
            }

            removeSort = new String[arrayRemoveSort.size()];
            for (int i = 0; i < arrayRemoveSort.size(); i++) {
                removeSort[i] = arrayRemoveSort.get(i);
            }
            blockRemainNum = new int[arrayBlockRemainNum.size()];
            for (int i = 0; i < arrayBlockRemainNum.size(); i++) {
                blockRemainNum[i] = arrayBlockRemainNum.get(i);
            }
            removeBlock1 = new int[arrayRemoveBlock1.size()];
            for (int i = 0; i < arrayRemoveBlock1.size(); i++) {
                removeBlock1[i] = arrayRemoveBlock1.get(i);
            }
            removeBlock2 = new int[arrayRemoveBlock2.size()];
            for (int i = 0; i < arrayRemoveBlock2.size(); i++) {
                removeBlock2[i] = arrayRemoveBlock2.get(i);
            }

            int i = 0;
            ArrayList<String> strTemp = new ArrayList<>();

            while ((s = br.readLine()) != null) {
                strTemp.add(s);
            }
            for (String st : strTemp) {
                StringTokenizer stringTokenizer = new StringTokenizer(st, "_");
                for (int j = 0; j < RIGHT; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                }
                i++;
                if (i >= 10) {
                    break;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<removeBlock1.length;i++){
            System.out.println(removeBlock1[i]);
        }
    }

    /**
     * 随机生成一个方块,返回其编码
     * @param
     * @return int
     */
    private int randomBlock() {
        int temp = 0;
        temp = ((int) (Math.random() * 4) + 1) * 10000000;
        while (temp == 0)
            temp = ((int) (Math.random() * 4) + 1) * 10000000;
        return temp;
    }

    /**
     * 数组检查是否可以消去
     * 如果可以消去，为方块重新赋值，并
     * @param
     * @return int
     */
    public int checkMap() {

        //T连检查
        for (int i = 0; i < LEFT-2; i++) {
            for (int j = 0; j < RIGHT - 4; j++) {
                //判断是否有障碍物
                if (map[i][j] <= 0 || map[i][j + 1] <= 0 || map[i][j + 2] <= 0 ||
                        map[i][j + 3] <= 0 || map[i][j + 4] <= 0) {
                    continue;
                }
                if (map[i][j] / 10000000 == map[i][j + 1] / 10000000 && map[i][j] / 10000000 == map[i][j + 2] / 10000000 &&
                        map[i][j] / 10000000 == map[i][j + 3] / 10000000 && map[i][j] / 10000000 == map[i][j + 4] / 10000000) {
                    if (i <= 1) {
                        if (map[i + 1][j + 2] <= 0 || map[i + 2][j + 2] <= 0)
                            continue;
                        if (map[i][j + 2] / 10000000 == map[i + 1][j + 2] / 10000000 &&
                                map[i][j + 2] / 10000000 == map[i + 2][j + 2] / 10000000) {
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
                            continue;
                        if (map[i][j + 2] / 10000000 == map[i + 1][j + 2] / 10000000 &&
                                map[i][j + 2] / 10000000 == map[i + 2][j + 2] / 10000000) {
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
                            continue;
                        if (map[i][j + 2] / 10000000 == map[i - 1][j + 2] / 10000000 &&
                                map[i][j + 2] / 10000000 == map[i - 2][j + 2] / 10000000) {
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
                            continue;
                        if (map[i][j + 2] / 10000000 == map[i - 1][j + 2] / 10000000 &&
                                map[i][j + 2] / 10000000 == map[i - 2][j + 2] / 10000000) {
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

        for (int j = 0; j < RIGHT-2; j++) {
            for (int i = 0; i < LEFT - 4; i++) {
                if (map[i][j] <= 0 || map[i + 1][j] <= 0 || map[i + 2][j] <= 0 ||
                        map[i + 3][j] <= 0 || map[i + 4][j] <= 0)
                    continue;
                if (map[i][j] / 10000000 == map[i + 1][j] / 10000000 && map[i][j] / 10000000 == map[i + 2][j] / 10000000 &&
                        map[i][j] / 10000000 == map[i + 3][j] / 10000000 && map[i][j] / 10000000 == map[i + 4][j] / 10000000) {
                    if (j <= 1) {
                        if (map[i + 2][j + 1] <= 0 || map[i + 2][j + 2] <= 0)
                            continue;
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
                    if (i >= 2 && i < RIGHT - 2) {
                        if (map[i + 2][j + 1] <= 0 || map[i + 2][j + 2] <= 0)
                            continue;
                        if (map[i + 2][j] / 10000000 == map[i + 2][j + 1] / 10000000 &&
                                map[i + 2][j] / 10000000 == map[i + 2][j + 2] / 10000000) {
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
                            continue;
                        if (map[i + 2][j] / 10000000 == map[i + 2][j - 1] / 10000000 &&
                                map[i + 2][j] / 10000000 == map[i + 2][j - 2] / 10000000) {
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
                            continue;
                        if (map[i + 2][j] / 10000000 == map[i + 2][j - 1] / 10000000 &&
                                map[i + 2][j] / 10000000 == map[i + 2][j - 2] / 10000000) {
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
                if (map[i][j] <= 0 || map[i][j + 1] <= 0 || map[i][j + 2] <= 0)
                    continue;
                if (map[i][j] / 10000000 == map[i][j + 1] / 10000000 &&
                        map[i][j] / 10000000 == map[i][j + 2] / 10000000) {
                    if (i <= 1) {
                        if (map[i + 1][j] <= 0 || map[i + 2][j] <= 0)
                            continue;
                        if (map[i][j] / 10000000 == map[i + 1][j] / 10000000 &&
                                map[i][j] / 10000000 == map[i + 2][j] / 10000000) {
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
                            continue;
                        if (map[i][j] / 10000000 == map[i + 1][j] / 10000000 && map[i][j] / 10000000 == map[i + 2][j] / 10000000) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j + 1] = judge(map[i][j + 1]);
                            map[i][j + 2] = judge(map[i][j + 2]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                        if (map[i - 1][j] <= 0 || map[i - 2][j] <= 0)
                            continue;
                        if (map[i][j] / 10000000 == map[i - 1][j] / 10000000 && map[i][j] / 10000000 == map[i - 2][j] / 10000000) {
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
                            continue;
                        if (map[i][j] / 10000000 == map[i - 1][j] / 10000000 && map[i][j] / 10000000 == map[i - 2][j] / 10000000) {
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
                    continue;
                if (map[i][j] / 10000000 == map[i][j - 1] / 10000000 &&
                        map[i][j] / 10000000 == map[i][j - 2] / 10000000) {
                    if (i <= 1) {
                        if (map[i + 1][j] <= 0 || map[i + 2][j] <= 0)
                            continue;
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
                            continue;
                        if (map[i][j] / 10000000 == map[i + 1][j] / 10000000 &&
                                map[i][j] / 10000000 == map[i + 2][j] / 10000000) {
                            map[i][j] = judge(map[i][j]);
                            map[i][j - 1] = judge(map[i][j - 1]);
                            map[i][j - 2] = judge(map[i][j - 2]);
                            map[i + 1][j] = judge(map[i + 1][j]);
                            map[i + 2][j] = judge(map[i + 2][j]);
                            score = score + 1000;
                            return 2;//成功
                        }
                        if (map[i - 1][j] <= 0 || map[i - 2][j] <= 0)
                            continue;
                        if (map[i][j] / 10000000 == map[i - 1][j] / 10000000 &&
                                map[i][j] / 10000000 == map[i - 2][j] / 10000000) {
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
                            continue;
                        if (map[i][j] / 10000000 == map[i - 1][j] / 10000000 &&
                                map[i][j] / 10000000 == map[i - 2][j] / 10000000) {
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
                    continue;
                if (map[i][j] / 10000000 == map[i][j + 1] / 10000000 &&
                        map[i][j] / 10000000 == map[i][j + 2] / 10000000 &&
                        map[i][j] / 10000000 == map[i][j + 3] / 10000000 && map[i][j] / 10000000 == map[i][j + 4] / 10000000) {
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
                    continue;
                if (map[i][j] / 10000000 == map[i + 1][j] / 10000000 && map[i][j] / 10000000 == map[i + 2][j] / 10000000 &&
                        map[i][j] / 10000000 == map[i + 3][j] / 10000000 && map[i][j] / 10000000 == map[i + 4][j] / 10000000) {
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
                    continue;
                if (map[i][j] / 10000000 == map[i][j + 1] / 10000000 && map[i][j] / 10000000 == map[i][j + 2] / 10000000 &&
                        map[i][j] / 10000000 == map[i][j + 3] / 10000000) {
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
                    continue;
                if (map[i][j] / 10000000 == map[i + 1][j] / 10000000 && map[i][j] / 10000000 == map[i + 2][j] / 10000000 &&
                        map[i][j] / 10000000 == map[i + 3][j] / 10000000) {
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
                    continue;
                }
                if (map[i][j] / 10000000 == map[i][j + 1] / 10000000 &&
                        map[i][j + 1] / 10000000 == map[i][j + 2] / 10000000) {
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
                    continue;
                }
                if (map[i][j] / 10000000 == map[i + 1][j] / 10000000 &&
                        map[i][j] / 10000000 == map[i + 2][j] / 10000000) {
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
                        if (map[i2][j] < 0)
                            break;
                        if (map[i2][j] == 0)
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

        boolean rl = true;
        for (int i = LEFT - 1; i >= 0; i--) {
            for (int j = RIGHT - 1; j >= 0; j--) {
                if (map[i][j] < 0)
                    continue;
                while (map[i][j] == 0) {
                    for (int i2 = i; i2 > 0; i2--) {
                        if (map[i2 - 1][j] < 0) {
                            map[i2][j] = randomBlock();
                            rl = false;
                            break;
                        }
                        map[i2][j] = map[i2 - 1][j];
                    }
                    if (rl) {
                        map[0][j] = randomBlock();
                        rl = true;
                    }
                }
            }
        }

        /*
        将所有方块第七位的数字改为0
         */
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int temp;
                while ((int) ((map[i][j] % 10000000) / 1000000) != 0)
                    map[i][j] -= 1000000;
            }
        }
    }

    /**
     * 判断方块是消减某种东西还是消除
     */
    public int judge(int block) {
        int temp = block % 100000;
        temp = (int) (temp / 10000);
        for (int num = 0; num < removeSort.length; num++) {
            if (removeSort[num].equals("方块") && temp == 0 && num == 0) {
                for (int i = 0; i < removeBlock1.length; i++) {
                    if ((int) (block / 10000000) == (int) (removeBlock1[i] / 10000000)) {
                        blockRemainNum[num] = blockRemainNum[num] - 1;
                        return 0;//消除态
                    }
                }
            }

            if (removeSort[num].equals("冰块") && temp > 0 && num == 0) {
                score = score + 100;
                for (int i = 0; i < removeBlock1.length; i++) {
                    if (block == removeBlock1[i]) {
                        blockRemainNum[num] = blockRemainNum[num] - 1;
                        System.out.println("消除成功");
                        return block - 10000;
                    }
                }
            }

            if (removeSort[num].equals("方块") && temp == 0 && num == 1) {
                for (int i = 0; i < removeBlock2.length; i++) {
                    if ((int) (block / 10000000) == (int) (removeBlock2[i] / 10000000)) {
                        blockRemainNum[num] = blockRemainNum[num] - 1;
                        return 0;//消除态
                    }
                }
            }

            if (removeSort[num].equals("冰块") && temp > 0 && num == 1) {
                score = score + 100;
                for (int i = 0; i < removeBlock2.length; i++) {
                    if (block == removeBlock2[i]) {
                        blockRemainNum[num] = blockRemainNum[num] - 1;
                        return block - 10000;
                    }
                }
            }
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

    /**
     * 通关条件判定
     */
    public int PassOrFail() {

        if (blockRemainNum.length == 2) {
            if (blockRemainNum[1] <= 0 && blockRemainNum[0] <= 0) {
                score = score + stepRemain * 1000;
                if (score >= passScore3) {
                    return 3;
                }
                if (score >= passScore2) {
                    return 2;
                }
                if (score >= passScore1) {
                    return 1;
                }
            }
            return 0;//游戏继续
        }
        if (blockRemainNum.length == 1) {
            if (blockRemainNum[0] <= 0) {
                score = score + stepRemain * 1000;
                if (score >= passScore3) {
                    return 3;
                }
                if (score >= passScore2) {
                    return 2;
                }
                if (score >= passScore1) {
                    return 1;
                }
            }
            return 0;//游戏继续
        }
        return 0;
    }

    /**
     * 将游戏结果以字符串形式储存
     * @param result
     * @return  java.lang.String
     */
    public String toString(int result) {

        return level + "-" + score + "-" + result;
    }

    /**
     * 获得剩余需要消除的方块数量
     *@param
     *@return int[]
     */
    public int[] getBlockRemainNum() {

        return blockRemainNum;
    }

    /**
     * 重新设置需要消除的方块数量
     *@param blockRemainNum
     *@return void
     */
    public void setBlockRemainNum(int[] blockRemainNum) {

        this.blockRemainNum = blockRemainNum;
    }

    /**
     * 获取第一种目标所需要的方块种类
     *@param
     *@return int[]
     */
    public int[] getRemoveBlock1() {

        return removeBlock1;
    }

    /**
     * 重新设置第一种目标所需要的方块种类
     *@param removeBlock1
     *@return void
     */
    public void setRemoveBlock1(int[] removeBlock1) {

        this.removeBlock1 = removeBlock1;
    }

    /**
     * 获取第二种目标所需要的方块种类
     *@param
     *@return int[]
     */
    public int[] getRemoveBlock2() {
        return removeBlock2;
    }

    /**
     * 重新设置第二种目标所需要的方块种类
     *@param removeBlock2
     *@return void
     */
    public void setRemoveBlock2(int[] removeBlock2) {
        this.removeBlock2 = removeBlock2;
    }


    /**
     * 获取三星通关所需要的分数
     *@param
     *@return int
     */
    public int getPassScore3() {

        return passScore3;
    }

    /**
     * 获取二星通关所需要的分数
     *@param
     *@return int
     */
    public int getPassScore2() {

        return passScore2;
    }

    /**
     * 获取一星通关所需要的分数
     *@param
     *@return int
     */
    public int getPassScore1() {
        return passScore1;
    }

    /**
     * 获取通关所需要的方块类型
     *@param
     *@return java.lang.String[]
     */
    public String[] getRemoveSort() {

        return removeSort;
    }
}