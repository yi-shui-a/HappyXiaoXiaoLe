package happyXiaoXiaoLe.controller;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.person.PersonDirectory;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class PersonInfController {

    /**
     * 个人信息页面初始化方法
     */
    @FXML
    private void initialize() {
        //存档记录信息的初始化
        adventure.setText(String.valueOf(Director.getInstance().getUser().getAdventureLevel()-1));
        endless.setText(String.valueOf(Director.getInstance().getUser().getEndlessScore()));
        gem.setText(String.valueOf(Director.getInstance().getUser().getJewelLevel()-1));

        //个人信息文本框初始化
        name0.setText(Director.getInstance().getUser().getName());
        id.setText(Director.getInstance().getUser().getId());

        //成就图标点亮初始化
        if(Director.getInstance().getUser().getAchievement()/1000 == 1){
            achievement1.setImage(new Image("/image/imageView/personInf/通关达人.png"));
        }
        if(Director.getInstance().getUser().getAchievement()/100%10 == 1){
            achievement2.setImage(new Image("/image/imageView/personInf/摘星能手.png"));
        }
        if(Director.getInstance().getUser().getAchievement()/10%10 == 1){
            achievement3.setImage(new Image("/image/imageView/personInf/得分大师.png"));
        }
        if(Director.getInstance().getUser().getAchievement()%10 == 1){
            achievement4.setImage(new Image("/image/imageView/personInf/宝石专家.png"));
        }
    }
    @FXML
    private Label adventure;

    @FXML
    private Label gem;

    @FXML
    private Label endless;

    @FXML
    private TextArea achievementT1 = new TextArea();
    @FXML
    private TextArea achievementT2 = new TextArea();
    @FXML
    private TextArea achievementT3 = new TextArea();
    @FXML
    private TextArea achievementT4 = new TextArea();


    @FXML
    private ImageView leave;

    @FXML
    private TextField name0;

    @FXML
    private TextField id;

    @FXML
    private ImageView layout;

    @FXML
    private ImageView achievement1;

    @FXML
    private ImageView achievement2;

    @FXML
    private ImageView achievement3;

    @FXML
    private ImageView achievement4;


    /**
     * 点击显示信息键，生成修改信息弹窗
     */
    @FXML
    void mouseClickedLayout() {

        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane,450,450);

        //名字文本框
        TextField name = new TextField();
        TextFieldCreat(name,115);
        name.setText(Director.getInstance().getUser().getName());

        //密码文本框
        TextField password = new TextField();
        TextFieldCreat(password,180);
        password.setText(Director.getInstance().getUser().getPassword());

        //email文本框
        TextField email = new TextField();
        TextFieldCreat(email,245);
        email.setText(Director.getInstance().getUser().getEmail());

        //电话号码文本框
        TextField phone = new TextField();
        TextFieldCreat(phone,310);
        phone.setText(Director.getInstance().getUser().getPhone());

        //背景图片的设置
        ImageView background = new ImageView();
        background.setImage(new Image("/image/background/editInf.png"));
        background.setFitHeight(450);
        background.setFitWidth(450);

        //返回键的设置
        ImageView leave = new ImageView();
        leave.setImage(new Image("/image/button/return.png"));
        leave.setFitHeight(40);
        leave.setFitWidth(40);
        leave.setLayoutX(40);
        leave.setLayoutY(40);
        leave.setPickOnBounds(true);
        leave.setOnMouseClicked(event -> {
            SoundEffect.play1();
            stage.close();
        });
        leave.setOnMouseExited(event -> leave.setOpacity(1));
        leave.setOnMouseEntered(event -> leave.setOpacity(0.6));

        //修改信息键的设置
        ImageView change = new ImageView();
        change.setImage(new Image("/image/button/personInf/change.png"));
        change.setFitHeight(45);
        change.setFitWidth(200);
        change.setLayoutX(140);
        change.setLayoutY(375);
        //点击修改键
        change.setOnMouseClicked(event -> {
            SoundEffect.play1();

            if (name.getText().length() == 0 || password.getText().length() == 0
                    || email.getText().length() == 0 || phone.getText().length() == 0) {
                //如果有一个文本框为空，弹窗提醒“请将信息填写完整！”
                event.consume();
                Director.getInstance().WarningAlertCreat("修改失败","请将信息填写完整！");

            } else if (name.getText().contains("-") || password.getText().contains("-")
                    || email.getText().contains("-") || phone.getText().contains("-")) {
                //如果有一个文本框包含“-”，弹窗提醒"请勿填写“-”字符！"
                event.consume();
                Director.getInstance().WarningAlertCreat("修改失败","请勿填写“-”字符！");

            }else{
                //其余情况则修改成功，将修改的信息存入文件，并修改user的信息
                for (int i = 0; i < PersonDirectory.getInstance().getNumberOfPerson(); i++) {
                    if(id.getText().equals(PersonDirectory.getInstance().getPerson(i).getId())) {
                        PersonDirectory.getInstance().getPerson(i).setPerson(name.getText(), password.getText(), email.getText(), phone.getText());
                        Director.getInstance().setUser(PersonDirectory.getInstance().getPerson(i));
                    }
                }
                PersonDirectory.getInstance().writePerson();

                name0.setText(Director.getInstance().getUser().getName());//修改个人信息页面的昵称，实现刷新功能

                event.consume();
                Director.getInstance().InformationAlertCreat("修改成功！","点击返回信息页面！");

                stage.close();
            }
        });

        change.setOnMouseEntered(event -> change.setOpacity(0.8));

        change.setOnMouseExited(event -> change.setOpacity(1));

        //添加到anchorPane
        anchorPane.getChildren().add(background);
        anchorPane.getChildren().add(change);
        anchorPane.getChildren().add(name);
        anchorPane.getChildren().add(password);
        anchorPane.getChildren().add(email);
        anchorPane.getChildren().add(phone);
        anchorPane.getChildren().add(leave);

        //stage设置
        stage.setWidth(450);
        stage.setHeight(450);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        SoundEffect.play1();
    }

    /**
     * 点击离开键返回主页
     */
    @FXML
    void mouseClickedLeave() {
        Director.getInstance().toIndex();
        SoundEffect.play1();
    }

    /**
     * TextField的初始化操作
     * @param textField 文本框
     * @param y 设置TextField的Y坐标
     */
    public void TextFieldCreat(TextField textField,int y){
        textField.setLayoutX(125);
        textField.setLayoutY(y);
        textField.setPrefSize(250,50);
        textField.setFont(Font.font(20));
        textField.setEditable(false);
        textField.setBackground(null);
        textField.setFocusTraversable(false);
        textField.setOnMouseClicked(event -> textField.setEditable(true));
    }

    /**
     * 从此往下均为鼠标进出按钮，设置透明度效果的函数
     */
    @FXML
    void enterAchievement1() {
        achievementT1.setOpacity(1);
    }

    @FXML
    void exitedAchievement1() {
        achievementT1.setOpacity(0);
    }

    @FXML
    void enterAchievement2() {
        achievementT2.setOpacity(1);
    }

    @FXML
    void exitedAchievement2() {
        achievementT2.setOpacity(0);
    }

    @FXML
    void enterAchievement3() {
        achievementT3.setOpacity(1);
    }

    @FXML
    void exitedAchievement3() {
        achievementT3.setOpacity(0);
    }

    @FXML
    void enterAchievement4() {
        achievementT4.setOpacity(1);
    }

    @FXML
    void exitedAchievement4() {
        achievementT4.setOpacity(0);
    }

    @FXML
    void mouseEnteredLayout() {
        layout.setOpacity(0.8);
    }

    @FXML
    void mouseEnteredLeave() {
        leave.setOpacity(0.6);
    }

    @FXML
    void mouseExitedLayout() {
        layout.setOpacity(1);
    }

    @FXML
    void mouseExitedLeave() {
        leave.setOpacity(1);
    }
}

