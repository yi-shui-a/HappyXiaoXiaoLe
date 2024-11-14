package happyXiaoXiaoLe.controller;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.scene.Setting;
import happyXiaoXiaoLe.sound.Music;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class AdventureStageController {

    private int stage ;
    private int page = 1;

    /**
     * 第一页初始化方法
     */
    @FXML
    private void initialize() {
        stage = Director.getInstance().getUser().getAdventureLevel();
        StageInitialize(stage);
    }

    /**
     * 第二页初始化方法
     */
    private void page2Initialize(){
        StageInitialize(stage-10);
    }

    /**
     * 页面初始化方法
     */
    private void StageInitialize(int stage) {
        //从符合条件的最大关卡开始，每一关依次设置为可见
        switch(Math.min(stage, 10)){
            case 10:stage10.setOpacity(1);
            case 9:stage9.setOpacity(1);
            case 8:stage8.setOpacity(1);
            case 7:stage7.setOpacity(1);
            case 6:stage6.setOpacity(1);
            case 5:stage5.setOpacity(1);
            case 4:stage4.setOpacity(1);
            case 3:stage3.setOpacity(1);
            case 2:stage2.setOpacity(1);
            case 1:stage1.setOpacity(1);
        }
    }

    @FXML
    private ImageView stage1;

    @FXML
    private ImageView stage2;

    @FXML
    private ImageView stage3;

    @FXML
    private ImageView stage4;

    @FXML
    private ImageView stage5;

    @FXML
    private ImageView stage6;

    @FXML
    private ImageView stage7;

    @FXML
    private ImageView stage8;

    @FXML
    private ImageView stage9;

    @FXML
    private ImageView stage10;

    @FXML
    private RadioButton page1;

    @FXML
    private RadioButton page2;

    @FXML
    private ImageView leave;

    /**
     * 从第一页换到第二页
     */
    @FXML
    void mouseClickedPage1() {
        SoundEffect.play1();
        page1.setSelected(true);
        page2.setSelected(false);
        page = 1;
        swapPage(page);
        stageOpacity();
        initialize();
    }

    /**
     * 从第二页换到第一页
     */
    @FXML
    void mouseClickedPage2() {
        SoundEffect.play1();
        page1.setSelected(false);
        page2.setSelected(true);
        page = 2;
        swapPage(page);
        stageOpacity();
        page2Initialize();
    }

    /**
     * 进入关卡设置函数（换音乐，换页面）
     * @param level 进入的是第几关
     */
    void enterGame(int level){
        Music.getInstance().mediaPlayer.stop();
        Music.getInstance().play2();
        SoundEffect.play1();
        Director.getInstance().adventureStart(level);
    }

    /**
     * 换页之后更改关卡按钮图标函数
     */
    private void swapPage(int page){
        if(page == 1){
            stage1.setImage(new Image("/image/button/adventureStage/1.png"));
            stage2.setImage(new Image("/image/button/adventureStage/2.png"));
            stage3.setImage(new Image("/image/button/adventureStage/3.png"));
            stage4.setImage(new Image("/image/button/adventureStage/4.png"));
            stage5.setImage(new Image("/image/button/adventureStage/5.png"));
            stage6.setImage(new Image("/image/button/adventureStage/6.png"));
            stage7.setImage(new Image("/image/button/adventureStage/7.png"));
            stage8.setImage(new Image("/image/button/adventureStage/8.png"));
            stage9.setImage(new Image("/image/button/adventureStage/9.png"));
            stage10.setImage(new Image("/image/button/adventureStage/10.png"));
        }else if(page == 2){
            stage1.setImage(new Image("/image/button/adventureStage/11.png"));
            stage2.setImage(new Image("/image/button/adventureStage/12.png"));
            stage3.setImage(new Image("/image/button/adventureStage/13.png"));
            stage4.setImage(new Image("/image/button/adventureStage/14.png"));
            stage5.setImage(new Image("/image/button/adventureStage/15.png"));
            stage6.setImage(new Image("/image/button/adventureStage/16.png"));
            stage7.setImage(new Image("/image/button/adventureStage/17.png"));
            stage8.setImage(new Image("/image/button/adventureStage/18.png"));
            stage9.setImage(new Image("/image/button/adventureStage/19.png"));
            stage10.setImage(new Image("/image/button/adventureStage/20.png"));
        }
    }

    /**
     * 设置进入关卡按钮透明度的函数
     */
    private void stageOpacity(){
        stage10.setOpacity(0.2);
        stage9.setOpacity(0.2);
        stage8.setOpacity(0.2);
        stage7.setOpacity(0.2);
        stage6.setOpacity(0.2);
        stage5.setOpacity(0.2);
        stage4.setOpacity(0.2);
        stage3.setOpacity(0.2);
        stage2.setOpacity(0.2);
        stage1.setOpacity(0.2);
    }


    /**
     * 从此往下十个函数均为进入每一关的函数（分当前是第一页和第二页两种情况）
     */
    @FXML
    void mouseClickedStage1() {
        if(stage>=1&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(1);
        }else if(stage-10>=1&&page ==2){
            Setting.getInstance().shape =0;
            enterGame(11);
        }
    }

    @FXML
    void mouseClickedStage10() {
        if(stage>=10&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(10);
        }else if(stage-10>=10&&page ==2){
            Setting.getInstance().shape =0;
            enterGame(20);
        }
    }

    @FXML
    void mouseClickedStage2() {
        if(stage>=2&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(2);
        }else if(stage-10>=2&&page ==2){
            Setting.getInstance().shape =0;
            enterGame(12);
        }
    }

    @FXML
    void mouseClickedStage3() {
        if(stage>=3&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(3);
        }else if(stage-10>=3&&page ==2){
            Setting.getInstance().shape =0;
            enterGame(13);
        }
    }

    @FXML
    void mouseClickedStage4() {
        if(stage>=4&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(4);
        }else if(stage-10>=4&&page ==2){
            Setting.getInstance().shape =0;
            enterGame(14);
        }
    }

    @FXML
    void mouseClickedStage5() {
        if(stage>=5&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(5);
        }else if(stage-10>=5&&page ==2){
            Setting.getInstance().shape =1;
            enterGame(15);
        }
    }

    @FXML
    void mouseClickedStage6() {
        if(stage>=6&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(6);
        }else if(stage-10>=6&&page ==2){
            Setting.getInstance().shape =1;
            enterGame(16);
        }
    }

    @FXML
    void mouseClickedStage7() {
        if(stage>=7&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(7);
        }else if(stage-10>=7&&page ==2){
            Setting.getInstance().shape =1;
            enterGame(17);
        }
    }

    @FXML
    void mouseClickedStage8() {
        if(stage>=8&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(8);
        }else if(stage-10>=8&&page ==2){
            Setting.getInstance().shape =1;
            enterGame(18);
        }
    }

    @FXML
    void mouseClickedStage9() {
        if(stage>=9&&page==1) {
            Setting.getInstance().shape =1;
            enterGame(9);
        }else if(stage-10>=9&&page ==2){
            Setting.getInstance().shape =0;
            enterGame(19);
        }
    }
    /**
     * 点击离开键返回主页
     */
    @FXML
    void mouseClickedLeave() {
        Director.getInstance().toModelSelect();
        SoundEffect.play1();
    }

    /**
     * 从此往下均为鼠标进出按钮，设置透明度效果的函数
     */
    @FXML
    void mouseEnteredStage1() {
        if(stage>=1&&page==1) {
            stage1.setOpacity(0.6);
        }else if(stage-10>=1&&page ==2){
            stage1.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage10() {
        if(stage>=10&&page==1) {
            stage10.setOpacity(0.6);
        }else if(stage-10>=10&&page ==2){
            stage10.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage2() {
        if(stage>=2&&page==1) {
            stage2.setOpacity(0.6);
        }else if(stage-10>=2&&page ==2){
            stage2.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage3() {
        if(stage>=3&&page==1) {
            stage3.setOpacity(0.6);
        }else if(stage-10>=3&&page ==2){
            stage3.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage4() {
        if(stage>=4&&page==1) {
            stage4.setOpacity(0.6);
        }else if(stage-10>=4&&page ==2){
            stage4.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage5() {
        if(stage>=5&&page==1) {
            stage5.setOpacity(0.6);
        }else if(stage-10>=5&&page ==2){
            stage5.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage6() {
        if(stage>=6&&page==1) {
            stage6.setOpacity(0.6);
        }else if(stage-10>=6&&page ==2){
            stage6.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage7() {
        if(stage>=7&&page==1) {
            stage7.setOpacity(0.6);
        }else if(stage-10>=7&&page ==2){
            stage7.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage8() {
        if(stage>=8&&page==1) {
            stage8.setOpacity(0.6);
        }else if(stage-10>=8&&page ==2){
            stage8.setOpacity(0.6);
        }
    }

    @FXML
    void mouseEnteredStage9() {
        if(stage>=9&&page==1) {
            stage9.setOpacity(0.6);
        }else if(stage-10>=9&&page ==2){
            stage9.setOpacity(0.6);
        }
    }

    @FXML
    void mouseExitedStage1() {
        if(stage>=1&&page==1) {
            stage1.setOpacity(1);
        }else if(stage-10>=1&&page ==2){
            stage1.setOpacity(1);
        }
    }

    @FXML
    void mouseExitedStage10() {
        if(stage>=10&&page==1) {
            stage10.setOpacity(1);
        }else if(stage-10>=10&&page ==2){
            stage10.setOpacity(1);
        }
    }

    @FXML
    void mouseExitedStage2() {
        if(stage>=2&&page==1) {
            stage2.setOpacity(1);
        }else if(stage-10>=2&&page ==2){
            stage2.setOpacity(1);
        }
    }

    @FXML
    void mouseExitedStage3() {
        if(stage>=3&&page==1) {
            stage3.setOpacity(1);
        }else if(stage-10>=3&&page ==2){
            stage3.setOpacity(1);
        }
    }

    @FXML
    void mouseExitedStage4() {
        if(stage>=4&&page==1) {
            stage4.setOpacity(1);
        }else if(stage-10>=4&&page ==2){
            stage4.setOpacity(1);
        }
    }

    @FXML
    void mouseExitedStage5() {
        if(stage>=5&&page==1) {
            stage5.setOpacity(1);
        }else if(stage-10>=5&&page ==2){
            stage5.setOpacity(1);
        }
    }

    @FXML
    void mouseExitedStage6() {
        if(stage>=6&&page==1) {
            stage6.setOpacity(1);
        }else if(stage-10>=6&&page ==2){
            stage6.setOpacity(1);
        }
    }

    @FXML
    void mouseExitedStage7() {
        if(stage>=8&&page==1) {
            stage7.setOpacity(1);
        }else if(stage-10>=8&&page ==2){
            stage7.setOpacity(1);
        }
    }

    @FXML
    void mouseExitedStage8() {
        if(stage>=8&&page==1) {
            stage8.setOpacity(1);
        }else if(stage-10>=8&&page ==2){
            stage8.setOpacity(1);
        }
    }
    @FXML
    void mouseExitedStage9() {
        if(stage>=9&&page==1) {
            stage9.setOpacity(1);
        }else if(stage-10>=9&&page ==2){
            stage9.setOpacity(1);
        }
    }

    @FXML
    void mouseEnteredLeave() {
        leave.setOpacity(0.6);
    }

    @FXML
    void mouseExitedLeave() {
        leave.setOpacity(1);
    }
}
