package happyXiaoXiaoLe.person;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author yishui,Mxkun
 * @version 1.8.0_301
 */


public class Person {
    private String name;//昵称
    private String id;//账号
    private String password;//密码
    private String email;//邮箱
    private String phone;//电话号码
    private int adventureLevel;//冒险模式闯关进度
    private int endlessScore;//无尽模式最高分数
    private int jewelLevel;//宝石模式闯关进度
    private int achievement;//成就

    /**
     * 冒险模式游戏数据
     */
    private int[][] adventureData = new int[20][2];

    /**
     * 宝石模式游戏数据
     */
    private int[][] jewelData = new int[20][2];

    /**
     * 获取宝石模式游戏数据
     *
     * @param
     * @return int[][]
     */
    public int[][] getJewelData() {

        return jewelData;
    }

    /**
     * 重新设置宝石模式游戏数据
     *
     * @param level, score, result
     * @return void
     */
    public void setJewelData(int level, int score, int result) {

        jewelData[level][0] = score;
        jewelData[level][1] = result;
    }

    /**
     * 获取冒险模式游戏数据
     * @param
     * @return int[][]
     */
    public int[][] getAdventureData() {

        return adventureData;
    }

    /**
     * 重新设置冒险模式游戏数据
     *@param level, score, result
     *@return void
     */
    public void setAdventureData(int level, int score, int result) {

        adventureData[level][0] = score;
        adventureData[level][1] = result;
    }

    /**
     * 重新设置冒险模式游戏数据
     *@param
     *@return void
     */
    public void initAdventureData() {

        for (int i = 0; i < 20; i++) {
            adventureData[i][0] = 0;
            adventureData[i][1] = 0;
        }
    }

    /**
     * 重新设置宝石模式游戏数据
     *@param
     *@return void
     */
    public void initJewelData() {
        for (int i = 0; i < 20; i++) {
            adventureData[i][0] = 0;
            adventureData[i][1] = 0;
        }
    }

    /**
     * 注册时的构造函数，随机生成账号
     *
     * @param name
     * @param password
     * @param email
     * @param phone
     */
    public Person(String name, String password, String email, String phone) {
        this.name = name;
        this.id = randomId();
        this.password = password;
        this.email = email;
        this.phone = phone;
        initAdventureData();
        initJewelData();
    }

    /**
     * 登陆时的构造函数
     *
     * @param name
     * @param id
     * @param password
     * @param email
     * @param phone
     */
    public Person(String name, String id, String password, String email, String phone) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    /**
     * 将用户数据以字符串形式输出
     * @return
     */
    @Override
    public String toString() {
        return name + '-' + id + '-' + password + '-' + email + '-' + phone;
    }

    /**
     * 重新设计用户属性
     *@param name, password, email, phone
     *@return void
     */
    public void setPerson(String name, String password, String email, String phone) {

        setName(name);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
    }

    /**
     * 获取用户姓名信息
     *@param
     *@return java.lang.String
     */
    public String getName() {

        return name;
    }

    /**
     * 重新设置用户姓名信息
     *@param name
     *@return void
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * 获取用户账号信息
     *@param
     *@return java.lang.String
     */
    public String getId() {
        return id;
    }

    /**
     * 重新设置用户账号信息
     *@param id
     *@return void
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户密码信息
     *@param
     *@return java.lang.String
     */
    public String getPassword() {
        return password;
    }

    /**
     * 重新设置用户密码信息
     *@param password
     *@return void
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户邮箱信息
     *@param
     *@return java.lang.String
     */
    public String getEmail() {
        return email;
    }

    /**
     * 重新设置用户邮箱信息
     *@param email
     *@return void
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户电话信息
     *@param
     *@return java.lang.String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 重新设置用户电话信息
     *@param phone
     *@return void
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户成就信息
     *@param
     *@return int
     */
    public int getAchievement() {
        return achievement;
    }

    /**
     * 获取用户冒险模式等级信息
     *@param
     *@return int
     */
    public int getAdventureLevel() {
        return adventureLevel;
    }

    /**
     * 获取用户宝石模式等级信息
     *@param
     *@return int
     */
    public int getJewelLevel() {
        return jewelLevel;
    }

    /**
     * 获取用户无限模式最高分信息
     *@param
     *@return int
     */
    public int getEndlessScore() {
        return endlessScore;
    }

    /**
     * 重新设置用户成就信息
     *@param achievement
     *@return void
     */
    public void setAchievement(int achievement) {

        this.achievement = achievement;
    }

    /**
     * 重新设置用户冒险模式等级信息
     *@param adventureLevel
     *@return void
     */
    public void setAdventureLevel(int adventureLevel) {
        this.adventureLevel = adventureLevel;
    }

    /**
     * 重新设置用户无尽模式最高分信息
     *@param endlessScore
     *@return void
     */
    public void setEndlessScore(int endlessScore) {
        this.endlessScore = endlessScore;
    }

    /**
     * 重新设置用户宝石模式等级信息
     *@param jewelLevel
     *@return void
     */
    public void setJewelLevel(int jewelLevel) {
        this.jewelLevel = jewelLevel;
    }

    /**
     * 为注册用户随机生成一个不重复的新账号
     *
     * @return 新账号
     */
    public String randomId() {
        while (true) {
            int temp = 0;
            for (int i = 0; i < 9; i++)
                temp = temp * 10 + (int) (Math.random() * 9);
            //查重防止账号重复
            for (int i = 0; i < PersonDirectory.getInstance().getNumberOfPerson(); i++) {
                if (String.valueOf(temp).equals(PersonDirectory.getInstance().getPerson(i))) ;
            }
            return String.valueOf(temp);
        }
    }

    /**
     * 重新设置无尽模式最高分
     *@param
     *@return void
     */
    public void setEndlessScore() {

        ArrayList<Person> people = new ArrayList<>();
        //1.声明一个字符输入流
        FileReader reader = null;
        //2.声明一个字符输入缓冲流
        BufferedReader readerBuf = null;

        try {
            //3.通过BufferedReader包装字符输入流
            readerBuf = new BufferedReader(new FileReader(
                    "data/PersonData/Person/" + id + "/Endless.txt"));
            //4.创建一个集合，用来存放读取的文件的数据
            ArrayList<String> strList = new ArrayList<>();
            //5.用来存放一行的数据
            String lineStr;
            //6.逐行读取txt文件中的内容
            while ((lineStr = readerBuf.readLine()) != null) {
                //7.把读取的行添加到list中
                strList.add(lineStr);
            }
            //8.循环遍历集合，将集合中的数据放入数组中
            for (String str : strList) {
                if (Integer.parseInt(str) > endlessScore) {
                    endlessScore = Integer.parseInt(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //9.关闭字符输入流
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //10.关闭字符输入缓冲流
            try {
                if (readerBuf != null)
                    readerBuf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向passData.txt文件写入数据
     * 1、2、3、4分别代表Adeventure、Endless、Jewel、Achievement的数据
     * 这四种数据每个占一行的空间
     * @param
     * @return boolean
     */
    public boolean writePassData() {
        setEndlessScore();
        File file = new File("data/PersonData/Person/" + id + "/passData.txt");
        FileWriter writeFile = null;
        try {
            writeFile = new FileWriter(file);
            writeFile.write(adventureLevel + "\n");
            writeFile.write(endlessScore + "\n");
            writeFile.write(jewelLevel + "\n");
            writeFile.write(achievement + "");
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
