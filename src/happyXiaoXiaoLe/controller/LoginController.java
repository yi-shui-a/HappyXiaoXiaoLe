package happyXiaoXiaoLe.controller;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.person.PersonDirectory;
import happyXiaoXiaoLe.sound.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class LoginController {
    @FXML
    private ImageView login;

    @FXML
    private TextField id;

    @FXML
    private TextField password;

    @FXML
    private ImageView logon;

    /**
     * 点击登录键登录帐号
     * @param event 鼠标事件
     */
    @FXML
    void mouseClickedLogin(MouseEvent event) {
        SoundEffect.play1();
        boolean flag = false;
        if (id.getText().length() == 0 && password.getText().length() == 0) {
            //如果账号文本框，密码文本框都为空，弹窗提醒"请输入账号和密码"
            event.consume();
            Director.getInstance().WarningAlertCreat("登陆失败","请输入账号和密码");
            flag =true;
        } else if (id.getText().length() == 0 && password.getText().length() != 0) {
            //如果账号文本框为空，弹窗提醒"请输入账号"
            event.consume();
            Director.getInstance().WarningAlertCreat("登陆失败","请输入账号");
            flag =true;
        }else if (id.getText().length() != 0 && password.getText().length() == 0) {
            //如果密码文本框为空，弹窗提醒"请输入密码"
            event.consume();
            Director.getInstance().WarningAlertCreat("登陆失败","请输入密码");
            flag =true;
        }else {
            //其余情况为登陆成功，读取user的信息，进入主页
            for (int i = 0; i < PersonDirectory.getInstance().getNumberOfPerson(); i++) {
                if (id.getText().equals(PersonDirectory.getInstance().getPerson(i).getId())
                        && password.getText().equals(PersonDirectory.getInstance().getPerson(i).getPassword())) {
                    event.consume();
                    Director.getInstance().InformationAlertCreat("登陆成功！","点击进入游戏！");
                    Director.getInstance().toIndex();
                    flag =true;
                    Director.getInstance().setUser(PersonDirectory.getInstance().getPerson(i));
                    PersonDirectory.getInstance().readPersonGameData(Director.getInstance().getUser().getId());
                    System.out.println(Director.getInstance().getUser().getAchievement());
                }
            }
        }
        //如果flag为false，说明不符合上述任何情况，则登陆失败
        if(!flag) {
            event.consume();
            Director.getInstance().WarningAlertCreat("登陆失败","账号或密码错误！请重新输入");
        }
    }

    /**
     * 点击注册键到达注册页面
     */
    @FXML
    void mouseClickedLogon() {
        SoundEffect.play1();
        Director.getInstance().toLogon();
    }
    /**
     * 从此往下均为鼠标进出按钮，设置透明度效果的函数
     */
    @FXML
    void mouseEnteredLogin() {
        login.setOpacity(0.8);
    }

    @FXML
    void mouseExitedLogin() {
        login.setOpacity(1);
    }

    @FXML
    void mouseEnteredLogon() {
        logon.setOpacity(0.8);
    }

    @FXML
    void mouseExitedLogon() {
        logon.setOpacity(1);
    }

}
