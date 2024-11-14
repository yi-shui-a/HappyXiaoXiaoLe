package happyXiaoXiaoLe;

import happyXiaoXiaoLe.person.Person;
import happyXiaoXiaoLe.scene.*;
import happyXiaoXiaoLe.sound.Music;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */


public class Director {

    public static final double WIDTH = 810, HEIGHT = 635;
    private static Director instance = new Director();
    private Person user;//游戏用户
    private Stage stage;//游戏窗口
    private Director(){}
    public GameEndlessScene gameEndlessScene;//无尽模式
    public GameAdventureScene gameAdventureScene;//冒险模式
    public GameGemScene gameGemScene;//宝石模式
    public static Director getInstance(){
        return instance;
    }

    /**
     * 游戏窗口初始化函数
     * @param stage
     */
    public void init(Stage stage){
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("开心消消乐");
        stage.getIcons().add(new Image("/image/background/logo.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        this.stage = stage;
        toLogin();
        Music.getInstance().play1();
        stage.show();
        Platform.setImplicitExit(false);
        stage.setOnCloseRequest(event -> {
            event.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("温馨提示");
            alert.setHeaderText(null);
            alert.initOwner(Director.getInstance().getStage());
            alert.setContentText("您确定要退出游戏吗？");
            Optional<ButtonType> result =alert.showAndWait();
            SoundEffect.play1();
            if(result.get() == ButtonType.OK) {
                System.exit(0);
            }
        });
    }

    /**
     * 转到登陆页面
     */
    public void toLogin() {
        Login.load(stage);
    }

    /**
     * 转到模式选择页面
     */
    public void toModelSelect() {
        ModelSelect.load(stage);
    }

    /**
     * 转到模式选择页面
     */
    public void toHelpSelect() {
        HelpSelect.load(stage);
    }

    /**
     * 转到主页
     */
    public void toIndex() {
        Index.load(stage);
    }

    /**
     * 转到关于我们页面
     */
    public void toAboutUs(){
        AboutUs.load(stage);
    }

    /**
     * 转到设置页面
     */
    public void toSetting() {
        Setting.load(stage);
    }

    /**
     * 转到个人信息页面
     */
    public void toPersonInf() {
        PersonInf.load(stage);
    }

    /**
     * 转到游戏帮助界面
     */
    public void toHelp(){
        Help.load(stage);
    }

    /**
     * 转到注册页面
     */
    public void toLogon(){
        Logon.load(stage);
    }

    /**
     * 转到冒险模式选关页面
     */
    public void toAdventureStage(){
        AdventureStage.load(stage);
    }

    /**
     * 转到宝石模式选关页面
     */
    public void toGemStage(){
        GemStage.load(stage);
    }

    /**
     * 获取用户信息。
     * @return 用户信息
     */
    public Person getUser() {
        return user;
    }

    /**
     * 设置用户信息
     * @param user
     */
    public void setUser(Person user) {
        this.user = user;
    }

    /**
     * 获取游戏主窗口
     * @return
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 无尽模式启动函数。
     */
    public void endlessStart(){
        gameEndlessScene = new GameEndlessScene();
        gameEndlessScene.init(stage);
    }

    /**
     * 无尽模式结束处理函数。
     */
    public void endLessOver(){
        stage.getScene().setOnMouseClicked(null);
        gameEndlessScene =null;
    }

    /**
     * 冒险模式启动函数。
     */
    public void adventureStart(int level){
        gameAdventureScene = new GameAdventureScene();
        gameAdventureScene.init(stage,level);
    }

    /**
     * 冒险模式结束处理函数。
     */
    public void adventureOver(){
        stage.getScene().setOnMouseClicked(null);
        gameEndlessScene =null;
    }

    /**
     * 宝石模式启动函数。
     */
    public void jewelStart(int level){
        gameGemScene = new GameGemScene();
        gameGemScene.init(stage,level);
    }

    /**
     * 宝石模式结束处理函数。
     */
    public void jewelOver(){
        stage.getScene().setOnMouseClicked(null);
        gameGemScene =null;
    }

    /**
     * 生成警告弹窗的函数。
     * @param title 标题文本
     * @param text 弹窗文本
     */
    public void WarningAlertCreat(String title, String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.initOwner(Director.getInstance().getStage());
        alert.setContentText(text);
        alert.showAndWait();
        SoundEffect.play1();
    }

    /**
     * 生成信息弹窗的函数。
     * @param title 标题文本
     * @param text 弹窗文本
     */
    public void InformationAlertCreat(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.initOwner(Director.getInstance().getStage());
        alert.setContentText(text);
        alert.showAndWait();
        SoundEffect.play1();
    }

}
