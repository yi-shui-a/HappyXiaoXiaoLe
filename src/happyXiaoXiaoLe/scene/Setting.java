package happyXiaoXiaoLe.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Mxkn
 * @version 1.8.0_301
 */

public class Setting {

    private static final Setting instance = new Setting();

    public static Setting getInstance() {
        return instance;
    }

    private Setting(){}
    public int theme = 1;//主题标志，默认选择主题1
    public int sound = 1;//是否播放音乐，默认选择是
    public int help = 0;//对应不同模式的游戏帮助
    public int shape = 1;//对应的地图形状

    /**
     * 设置界面
     * @param stage 游戏主界面
     */
    public  static void  load(Stage stage){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Index.class.getResource("/fxml/Setting.fxml")));
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
