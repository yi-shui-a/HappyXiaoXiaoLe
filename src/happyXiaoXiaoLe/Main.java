package happyXiaoXiaoLe;

import happyXiaoXiaoLe.person.PersonDirectory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Mxkun
 * @version 1.8.0_301
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Director.getInstance().init(primaryStage);
    }

    public static void main(String[] args) {
        try {
            //读取储存在文件里的用户信息
            PersonDirectory.getInstance().setPersons(PersonDirectory.getInstance().creatPerson("data/PersonData/personList.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Application.launch(args);

    }
}
