package happyXiaoXiaoLe.scene;

import happyXiaoXiaoLe.Director;
import happyXiaoXiaoLe.person.PersonDirectory;
import happyXiaoXiaoLe.sound.Music;
import happyXiaoXiaoLe.sound.SoundEffect;
import happyXiaoXiaoLe.sprite.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author yishui,Mxkun
 * @version 1.8.0_301
 * @see Card
 * @see CardList
 * @see Canvas
 * @see MouseProcess
 * @see GraphicsContext
 * @see AnimationTimer
 */


public class GameGemScene {

    /**
     *初始化一个画布对象，用来绘制图像
     */
    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    /**
     * 初始化鼠标操作的对象，用来接收鼠标操作的信息
     */
    private MouseProcess mouseProcess = new MouseProcess();

    /**
     *初始化一个计时器调用方法类
     */
    private Refresh refresh = new Refresh();

    /**
     * 判断当前程序是否在运行
     */
    private boolean running = false;

    /**
     * 方块的运行方向
     */
    public int moveRight = 0, moveDown = 0;

    /**
     * 创建背景对象
     */
    private Background background = new Background(1);

    /**
     * 创建设置栏的对象
     */
    private Set set = new Set();

    /**
     * 创建CardList对象，用来调用方块数据
     */
    public CardList cardList = new CardList();

    /**
     * 关卡当前等级
     */
    public int level;

    /**
     * 当前剩余步数
     */
    public int step;

    /**
     * 游戏是否结束
     * 最终的星级
     */
    private int result;

    private int end = 0;

    private boolean flagSwapMove = false;//方块移动动作是否做完,返回true代表交换完成，false代表交换未开始或未完成
    private int swapReturn = -2;//如果不能消除，返回-1;可以消除，返回相应的消除值；如果未完成，返回-2；
    private boolean dropSign = false;//一轮交换并且消除成功后发出该信号，触发下落动画
    private boolean dropSign2 = false;
    private boolean repaint1 = false;

    private boolean paintReview = false;
    private boolean isPaintReview1 = true;
    private boolean isPaintReview2 = false;
    private boolean isPaintReview3 = true;
    private int paintJudge1 = 9999;
    private int paintJudge2 = 0;

    /**
     * 游戏场景1
     *@param
     *@return boolean
     */
    private boolean paint() throws RuntimeException { //绘制游戏场景
        background.paint(graphicsContext);
        //如果通关，弹出游戏通关窗口
        if ((result = cardList.getModelJewel().passOrNot()) == 3) {
            gameSucceed(3);
        } else if ((result = cardList.getModelJewel().passOrNot()) == 2) {
            gameSucceed(2);
        } else if ((result = cardList.getModelJewel().passOrNot()) == 1) {
            gameSucceed(1);
        }
        //如果条件不满足，步数耗尽，弹出游戏失败窗口
        if (cardList.getModelJewel().getStepRemain() == 0) {
            result = 0;
            gameFail();
        } else {
            cardList.levelImage[level - 1].paint(graphicsContext);
            cardList.step[step - 1].paint(graphicsContext);
            cardList.goal.paint(graphicsContext);
        }
        graphicsContext.fillText("" + cardList.getModelJewel().getScore(), 204, 247);
        graphicsContext.fillText("X" + Math.max(cardList.getModelJewel().getBlockRemainNum(),0), 140, 85);
        if (cardList.x1 != -1 && cardList.x2 != -1) {
            if (flagSwapMove == false)
                flagSwapMove = cardList.swap(moveRight, moveDown);
            if (flagSwapMove == true) {
                swapReturn = cardList.swap2Jewel(moveRight, moveDown);
                if (swapReturn != -2) {
                    step = cardList.getModelJewel().decreaseStepRemain();
                    moveRight = 0;
                    moveDown = 0;
                    flagSwapMove = false;
                    dropSign = true;
                    repaint1 = true;
                }
            }
        }
        boolean dropFinish = false;
        if (dropSign) {
            cardList.map = cardList.getModelJewel().getMapDropChecked();
            cardList.initCard();
            cardList.cardDropJudge();//给每个方块制定将要下落的目标
            dropSign = false;
            dropSign2 = true;
        }
        if (dropSign2) {
            dropFinish = cardList.cardDrop();
            cardsPaint();
        }
        if (dropFinish) {
            dropSign2 = false;
            cardList.setMap(cardList.getModelJewel().getMapAfter());
            repaint1 = true;
            cardList.initCard();
            cardsPaint();
            return false;
        }
        cardsPaint();
        choicesPaint();
        return true;
    }

    /**
     * 游戏场景2
     *@param
     *@return boolean
     */
    private boolean paintReview() throws RuntimeException {

        background.paint(graphicsContext);

        graphicsContext.fillText("" + cardList.getModelJewel().getScore(), 204, 247);
        cardList.levelImage[level - 1].paint(graphicsContext);
        if (step != 0) {
            cardList.step[step - 1].paint(graphicsContext);
        }
        cardList.goal.paint(graphicsContext);
        graphicsContext.fillText("X" + Math.max(cardList.getModelJewel().getBlockRemainNum(),0), 140 , 85);
        boolean dropFinish = false;

        if (isPaintReview1 == true) {
            paintJudge1 = cardList.getModelJewel().getMapJudge();
            isPaintReview1 = false;
        }
        if (paintJudge1 == -1) {
            isPaintReview1 = true;
            return true;
        }
        if (paintJudge1 != -1 && isPaintReview3 == true) {
            cardList.map = cardList.getModelJewel().getMapDropChecked();
            cardList.initCard();
            SoundEffect.play2();
            cardList.cardDropJudge();//给每个方块制定将要下落的目标
            isPaintReview2 = true;
            isPaintReview3 = false;
        }
        if (isPaintReview2) {
            dropFinish = cardList.cardDrop();
            cardsPaint();
        }
        if (dropFinish) {
            cardList.setMap(cardList.getModelJewel().getMapAfter());
            cardList.initCard();
            System.out.println("当前分数：" + cardList.getModelJewel().getScore());
            cardsPaint();
            cardList.goal.paint(graphicsContext);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            paintJudge1 = 9999;
            isPaintReview1 = true;
            isPaintReview2 = false;
            isPaintReview3 = true;
        }
        return false;
    }

    /**
     * 游戏场景初始化
     *
     * @param stage
     * @param level1
     */
    public void init(Stage stage, int level1) {
        level = level1;
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnMouseClicked(mouseProcess);
        graphicsContext.setFont(Font.font(30));
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        running = true;
        initSprite();
        step = cardList.getModelJewel().getStepRemain();
        cardList.goal = new Goal(38, 28, cardList.getModelJewel().getRemoveBlock()[0]);
        refresh.start();
    }

    /**
     * 可视化对象的初始化
     */
    private void initSprite() {
        cardList.getModelJewel().setLevel(level);
        cardList.initJewelMap();
        cardList.initCard();
        cardList.initChoice();
        cardList.initLevelImage();
        cardList.initStep();
    }

    public void clear(Stage stage) {
        refresh.stop();
        cardList.cards = null;
        cardList.choices = null;
        cardList.setModelJewel(null);
        ;
    }

    /**
     * 卡片类绘制
     */
    public void cardsPaint() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Card card = cardList.cards[i][j];
                card.paint(graphicsContext);
            }
        }
    }

    /**
     * 方框类绘制
     */
    public void choicesPaint() {
        if (cardList.selected == 1) {
            cardList.choices[cardList.x1][cardList.y1].paint(graphicsContext);
        }
    }

    /**
     * 屏幕刷新类
     */
    private class Refresh extends AnimationTimer {
        private long last = 0;

        @Override
        public void handle(long now) {
            if (now - last >= 10_000_000) {

                //System.out.println("剩余时间： " + cardList.getModelEndless().getRemainTime());
                if (running) {
                    running = paint();
                }
                if (!running) {
                    running = paintReview();
                }

            }
            last = now;
        }
    }

    /**
     * 鼠标事件检测类
     */
    private class MouseProcess implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) { //鼠标点击事件处理
            double x = event.getX();
            double y = event.getY();
            if (set.isPoint(x, y)) {
                setStage();
            }
            int[] move = cardList.dealMouse(x, y);
            moveRight = move[0];
            moveDown = move[1];
        }
    }

    /**
     * 游戏中设置窗口的创建函数
     */
    public void setStage() {
        refresh.stop();
        Stage setting = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 500, 500);

        //背景图片设置
        ImageView background = new ImageView();
        background.setImage(new Image("/image/background/stage/setting.png"));
        background.setFitHeight(500);
        background.setFitWidth(500);

        //音量键设置
        ImageView music = new ImageView();
        if (Setting.getInstance().sound == 1) {
            music.setImage(new Image("/image/button/play.png"));
        } else if (Setting.getInstance().sound == 0) {
            music.setImage(new Image("/image/button/mute.png"));
        }
        music.setFitHeight(100);
        music.setFitWidth(100);
        music.setLayoutX(200);
        music.setLayoutY(110);
        music.setPickOnBounds(true);
        music.setOnMouseClicked(event -> {
            if (Setting.getInstance().sound == 1) {
                music.setImage(new Image("/image/button/mute.png"));
                Music.getInstance().mediaPlayer.stop();
                Setting.getInstance().sound = 0;
            } else if (Setting.getInstance().sound == 0) {
                music.setImage(new Image("/image/button/play.png"));
                Setting.getInstance().sound = 1;
                SoundEffect.play1();
                Music.getInstance().play2();
            }
        });
        music.setOnMouseEntered(event -> {
            music.setOpacity(0.8);
        });
        music.setOnMouseExited(event -> {
            music.setOpacity(1);
        });

        //退出键设置
        ImageView leave = new ImageView();
        leave.setImage(new Image("/image/button/stage/setReturn.png"));
        leave.setFitHeight(80);
        leave.setFitWidth(320);
        leave.setLayoutX(90);
        leave.setLayoutY(320);
        leave.setOnMouseClicked(event -> {
            SoundEffect.play1();
            setting.close();
            Music.getInstance().mediaPlayer.stop();

            Director.getInstance().jewelOver();

            Director.getInstance().toGemStage();

            Music.getInstance().play1();
        });
        leave.setOnMouseEntered(event -> {
            leave.setOpacity(0.8);
        });
        leave.setOnMouseExited(event -> {
            leave.setOpacity(1);
        });

        //再玩一次键设置
        ImageView again = new ImageView();
        again.setImage(new Image("/image/button/stage/setAgain.png"));
        again.setFitHeight(80);
        again.setFitWidth(320);
        again.setLayoutX(90);
        again.setLayoutY(240);
        again.setOnMouseClicked(event -> {
            SoundEffect.play1();
            setting.close();
            Director.getInstance().jewelStart(level);
        });
        again.setOnMouseEntered(event -> {
            again.setOpacity(0.8);
        });
        again.setOnMouseExited(event -> {
            again.setOpacity(1);
        });

        //设置界面取消键设置
        ImageView return0 = new ImageView();
        return0.setImage(new Image("/image/button/stage/settingClose.png"));
        return0.setFitHeight(60);
        return0.setFitWidth(60);
        return0.setLayoutX(430);
        return0.setLayoutY(15);
        return0.setPickOnBounds(true);
        return0.setOnMouseClicked(event -> {
            refresh.start();
            SoundEffect.play1();
            setting.close();
        });
        return0.setOnMouseEntered(event -> {
            return0.setOpacity(0.8);
        });
        return0.setOnMouseExited(event -> {
            return0.setOpacity(1);
        });

        //添加到anchorPane
        anchorPane.getChildren().add(background);
        anchorPane.getChildren().add(leave);
        anchorPane.getChildren().add(return0);
        anchorPane.getChildren().add(again);
        anchorPane.getChildren().add(music);

        //设置stage
        setting.setWidth(500);
        setting.setHeight(500);
        setting.setScene(scene);
        setting.setResizable(false);
        setting.initStyle(StageStyle.TRANSPARENT);
        setting.initModality(Modality.APPLICATION_MODAL);
        setting.show();
    }

    /**
     * 游戏成功窗口的创建函数
     *
     * @param i 游戏达到的星级
     */
    public void gameSucceed(int i) {
        refresh.stop();

        if (Director.getInstance().getUser().getJewelLevel() == level) {
            Director.getInstance().getUser().setJewelLevel(level + 1);
        }

        if(level == 10) {
            if (Director.getInstance().getUser().getAdventureLevel() == 21
                    && Director.getInstance().getUser().getJewelLevel() == 11
                    && Director.getInstance().getUser().getAchievement() / 1000 != 1) {
                Director.getInstance().getUser().setAchievement(
                        Director.getInstance().getUser().getAchievement() + 1000);
            }
        }

        Stage succeed = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 400, 500);

        //背景图片设置
        ImageView background = new ImageView();
        background.setImage(new Image("/image/background/stage/pass.png"));
        background.setFitHeight(500);
        background.setFitWidth(500);

        //下一关按键设置
        ImageView next = new ImageView();
        if(level == 10){
            next.setImage(new Image("/image/button/stage/returnSucceed.png"));
        }else {
            next.setImage(new Image("/image/button/stage/nextSucceed.png"));
        }
        next.setFitHeight(90);
        next.setFitWidth(300);
        next.setLayoutX(100);
        next.setLayoutY(390);
        next.setPickOnBounds(true);
        next.setOnMouseClicked(event -> {
            SoundEffect.play1();
            succeed.close();
            if(level == 10){
                Music.getInstance().mediaPlayer.stop();
                Music.getInstance().play1();
                Director.getInstance().toGemStage();
                Director.getInstance().jewelOver();
            }else {
                Director.getInstance().jewelStart(level + 1);
            }
        });
        next.setOnMouseEntered(event -> {
            next.setOpacity(0.8);
        });
        next.setOnMouseExited(event -> {
            next.setOpacity(1);
        });

        //最终得分的显示区域设置
        TextField score = new TextField();
        score.setText(cardList.getModelJewel().getScore() + "");
        score.setLayoutX(110);
        score.setLayoutY(310);
        score.setPrefSize(270, 60);
        score.setFont(Font.font(50));
        score.setAlignment(Pos.BASELINE_CENTER);
        score.setEditable(false);
        score.setFocusTraversable(false);
        score.setBackground(null);

        //星星设置
        ImageView star1 = new ImageView();
        star1.setImage(new Image("/image/button/stage/blackStar.png"));
        star1.setFitHeight(100);
        star1.setFitWidth(100);
        star1.setLayoutX(90);
        star1.setLayoutY(130);

        ImageView star2 = new ImageView();
        star2.setImage(new Image("/image/button/stage/blackStar.png"));
        star2.setFitHeight(100);
        star2.setFitWidth(100);
        star2.setLayoutX(200);
        star2.setLayoutY(130);

        ImageView star3 = new ImageView();
        star3.setImage(new Image("/image/button/stage/blackStar.png"));
        star3.setFitHeight(100);
        star3.setFitWidth(100);
        star3.setLayoutX(310);
        star3.setLayoutY(130);

        //添加到anchorPane
        anchorPane.getChildren().add(background);
        anchorPane.getChildren().add(next);
        anchorPane.getChildren().add(score);
        anchorPane.getChildren().add(star1);
        anchorPane.getChildren().add(star2);
        anchorPane.getChildren().add(star3);

        switch (i) {
            case 3:
                star3.setImage(new Image("/image/button/stage/star.png"));
            case 2:
                star2.setImage(new Image("/image/button/stage/star.png"));
            case 1:
                star1.setImage(new Image("/image/button/stage/star.png"));
        }

        PersonDirectory.getInstance().writeJewel(cardList.getModelJewel().toString(result));
        PersonDirectory.getInstance().readPersonJewel(Director.getInstance().getUser().getId());
        int sum = 0;
        for (int j = 0; j < 10; j++) {
            sum += Director.getInstance().getUser().getJewelData()[j][1];
        }
        if(sum == 30 && Director.getInstance().getUser().getAchievement()%10 != 1){
            Director.getInstance().getUser().setAchievement(
                    Director.getInstance().getUser().getAchievement() + 1);
        }
        Director.getInstance().getUser().writePassData();

        //stage设置
        succeed.setWidth(500);
        succeed.setHeight(500);
        succeed.setScene(scene);
        succeed.setResizable(false);
        succeed.initStyle(StageStyle.TRANSPARENT);
        succeed.initModality(Modality.APPLICATION_MODAL);
        succeed.show();
    }

    /**
     * 游戏失败界面窗口的创建函数
     */
    public void gameFail() {//游戏结束函数
        refresh.stop();
        Stage fail = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 500, 500);

        //背景图片设置
        ImageView background = new ImageView();
        background.setImage(new Image("/image/background/stage/fail.png"));
        background.setFitHeight(500);
        background.setFitWidth(500);

        //退出键设置
        ImageView leave = new ImageView();
        leave.setImage(new Image("/image/button/stage/leaveFail.png"));
        leave.setFitHeight(50);
        leave.setFitWidth(160);
        leave.setLayoutX(260);
        leave.setLayoutY(350);
        leave.setOnMouseClicked(event -> {
            SoundEffect.play1();
            fail.close();
            Music.getInstance().mediaPlayer.stop();
            Music.getInstance().play1();
            Director.getInstance().toGemStage();
            Director.getInstance().jewelOver();
        });
        leave.setOnMouseEntered(event -> {
            leave.setOpacity(0.8);
        });
        leave.setOnMouseExited(event -> {
            leave.setOpacity(1);
        });

        //再玩一次键设置
        ImageView again = new ImageView();
        again.setImage(new Image("/image/button/stage/againFail.png"));
        again.setFitHeight(50);
        again.setFitWidth(160);
        again.setLayoutX(80);
        again.setLayoutY(350);
        again.setOnMouseClicked(event -> {
            SoundEffect.play1();
            fail.close();
            Director.getInstance().jewelStart(level);
        });
        again.setOnMouseEntered(event -> {
            again.setOpacity(0.8);
        });
        again.setOnMouseExited(event -> {
            again.setOpacity(1);
        });

        //最终得分的显示区域设置
        TextField score = new TextField();
        score.setText(cardList.getModelJewel().getScore() + "");
        score.setLayoutX(110);
        score.setLayoutY(225);
        score.setPrefSize(270, 60);
        score.setFont(Font.font(50));
        score.setAlignment(Pos.BASELINE_CENTER);
        score.setEditable(false);
        score.setFocusTraversable(false);
        score.setBackground(null);

        //添加到anchorPane
        anchorPane.getChildren().add(background);
        anchorPane.getChildren().add(leave);
        anchorPane.getChildren().add(again);
        anchorPane.getChildren().add(score);

        //stage设置
        fail.setWidth(500);
        fail.setHeight(500);
        fail.setScene(scene);
        fail.setResizable(false);
        fail.initStyle(StageStyle.TRANSPARENT);
        fail.initModality(Modality.APPLICATION_MODAL);
        fail.show();
    }
}
