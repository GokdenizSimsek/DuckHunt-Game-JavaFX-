import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class DuckHunt extends Application{
    private Stage primaryStage;
    private Scene titleScene;
    private Scene backgroundSelectionScene;
    private Scene gameScene1;
    private Scene gameScene2;
    private Scene gameScene3;
    private Scene gameScene4;
    private Scene gameScene5;
    private Scene gameScene6;
    private ImageView titleImageView;
    private ImageView backgroundSelectionImageView;
    private boolean backgroundSelectionEnterPressed = false;
    private ImageView crosshairImageView;
    private ImageView backgroundImageView;
    private ImageView backgroundImageView2;
    private ImageView backgroundImageView3;
    private ImageView backgroundImageView4;
    private ImageView backgroundImageView5;
    private ImageView backgroundImageView6;
    private ImageView foregroundImageView;
    private ImageView foregroundImageView2;
    private ImageView foregroundImageView3;
    private ImageView foregroundImageView4;
    private ImageView foregroundImageView5;
    private ImageView foregroundImageView6;
    private Text titleBlinkingText;
    private Text backgroundSelectionText;
    private Timeline blinkTimeline;
    private Timeline duckAnimation1;
    private Timeline duckAnimation2;
    private Timeline duck1Animation3;
    private Timeline duck2Animation3;
    private Timeline duck1Animation4;
    private Timeline duck2Animation4;
    private Timeline duck1Animation5;
    private Timeline duck2Animation5;
    private Timeline duck3Animation5;
    private Timeline duck1Animation6;
    private Timeline duck2Animation6;
    private Timeline duck3Animation6;
    private Timeline duck4Animation6;
    private Timeline level1BlinkTimeline;
    private Timeline level2BlinkTimeline;
    private Timeline level3BlinkTimeline;
    private Timeline level4BlinkTimeline;
    private Timeline level5BlinkTimeline;
    private Timeline level6BlinkTimeline;
    private Integer backgroundNumber = 1;
    private Integer crosshairNumber = 1;
    private final double SCALE = 3; // to adjust the window size
    private double VOLUME = 0.025; // to set the volume between 0 and 1 (1 for maximum, 0 for minimum)
    private int currentImageIndex1 = 0;
    private int currentImageIndex2 = 0;
    private double duckX = 0;
    private double duckY = 0;
    private double duckX2 = 0;
    private double duckY2 = 0;
    private double duckX3 = 0;
    private double duckY3 = 0;
    private double duckX4 = 0;
    private double duckY4 = 0;
    private boolean isMovingRight = true;
    private boolean isMovingDown = false;
    private boolean isMovingRight1 = false;
    private boolean isMovingDown1 = true;
    private boolean isMovingRight2 = true;
    private boolean isMovingDown2 = false;
    private boolean isMovingRight3 = false;
    private boolean isMovingDown3 = false;
    private Integer level1Ammo = 3;
    private Integer level2Ammo = 3;
    private Integer level3Ammo = 6;
    private Integer level4Ammo = 6;
    private Integer level5Ammo = 9;
    private Integer level6Ammo = 12;
    private boolean level1IsDuckShot = false;
    private boolean level2IsDuckShot = false;
    private boolean level3IsDuck1Shot = false;
    private boolean level3IsDuck2Shot = false;
    private boolean level4IsDuck1Shot = false;
    private boolean level4IsDuck2Shot = false;
    private boolean level5IsDuck1Shot = false;
    private boolean level5IsDuck2Shot = false;
    private boolean level5IsDuck3Shot = false;
    private boolean level6IsDuck1Shot = false;
    private boolean level6IsDuck2Shot = false;
    private boolean level6IsDuck3Shot = false;
    private boolean level6IsDuck4Shot = false;
    private Media music;
    private MediaPlayer introMediaPlayer;
    private MediaPlayer titleSceneMediaPlayer;
    private MediaPlayer gunShotMediaPlayer;
    private MediaPlayer duckFallsMediaPlayer;
    private MediaPlayer gameOverMediaPlayer;
    private MediaPlayer levelCompletedMediaPlayer;
    private MediaPlayer gameCompletedMediaPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("HUBBM Duck Hunt");
        primaryStage.getIcons().add(new Image(new File("assets/favicon/1.png").toURI().toString()));

        Image titleImage = new Image(new File("assets/welcome/1.png").toURI().toString());

        titleImageView = new ImageView(titleImage);
        titleImageView.setFitWidth(256 * SCALE);
        titleImageView.setFitHeight(240 * SCALE);


        titleBlinkingText = new Text("PRESS ENTER TO START\n    PRESS ESC TO EXIT");
        titleBlinkingText.setFont(Font.font("Arial", 15 * SCALE));
        titleBlinkingText.setFill(Color.ORANGE);

        StackPane titleTextPane = new StackPane(titleBlinkingText);
        titleTextPane.setAlignment(Pos.CENTER);
        blinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> titleBlinkingText.setVisible(!titleBlinkingText.isVisible()))
        );
        blinkTimeline.setCycleCount(Animation.INDEFINITE);
        blinkTimeline.play();

        StackPane titleRoot = new StackPane();
        titleRoot.getChildren().addAll(titleImageView, createPaddingPane(titleTextPane, 100 * SCALE, 0, 0, 0));
        StackPane.setAlignment(titleTextPane, Pos.CENTER);

        titleScene = new Scene(titleRoot, 256 * SCALE, 240 * SCALE);

        music = new Media(new File("assets/effects/Title.mp3").toURI().toString());
        titleSceneMediaPlayer = new MediaPlayer(music);
        titleSceneMediaPlayer.setVolume(VOLUME);
        titleSceneMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        music = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
        gunShotMediaPlayer = new MediaPlayer(music);
        gunShotMediaPlayer.setVolume(VOLUME);
        gunShotMediaPlayer.setCycleCount(1);

        primaryStage.setScene(titleScene);
        primaryStage.show();
        titleSceneMediaPlayer.play();


        Image backgroundSelectionImage = new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString());

        backgroundSelectionImageView = new ImageView(backgroundSelectionImage);
        backgroundSelectionImageView.setFitWidth(256 * SCALE);
        backgroundSelectionImageView.setFitHeight(240 * SCALE);

        StackPane backgroundSelectionRoot = new StackPane();
        backgroundSelectionRoot.getChildren().add(backgroundSelectionImageView);

        Image crosshairImage = new Image(new File("assets/crosshair/" + crosshairNumber.toString() + ".png").toURI().toString());

        crosshairImageView = new ImageView(crosshairImage);
        crosshairImageView.setFitWidth(crosshairImageView.getImage().getWidth() * SCALE);
        crosshairImageView.setFitHeight(crosshairImageView.getImage().getHeight() * SCALE);

        backgroundSelectionText = new Text("USE ARROW KEYS TO NAVIGATE\n       PRESS ENTER TO START\n           PRESS ESC TO EXIT");
        backgroundSelectionText.setFont(Font.font("Arial", 10 * SCALE));
        backgroundSelectionText.setFill(Color.ORANGE);

        StackPane backgroundSelectionTextPane = new StackPane(backgroundSelectionText);
        backgroundSelectionTextPane.setAlignment(Pos.CENTER);

        StackPane crosshairRoot = new StackPane();
        crosshairRoot.getChildren().add(crosshairImageView);

        StackPane backGround = new StackPane();
        backGround.getChildren().addAll(backgroundSelectionRoot, crosshairRoot, createPaddingPane(backgroundSelectionTextPane, 0, 205 * SCALE, 0, 0));
        backgroundSelectionScene = new Scene(backGround, 256 * SCALE, 240 * SCALE);


        Image backgroundImage = new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString());

        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(256 * SCALE);
        backgroundImageView.setFitHeight(240 * SCALE);

        StackPane backGroundRoot = new StackPane();
        backGroundRoot.getChildren().add(backgroundImageView);

        Image foregroundImage = new Image(new File("assets/foreground/" + backgroundNumber.toString() +".png").toURI().toString());

        foregroundImageView = new ImageView(foregroundImage);
        foregroundImageView.setFitWidth(256 * SCALE);
        foregroundImageView.setFitHeight(240 * SCALE);

        StackPane foregroundRoot = new StackPane();
        foregroundRoot.getChildren().add(foregroundImageView);


        Text level1Text = new Text("Level 1/6");
        level1Text.setFont(Font.font("Arial", 10 * SCALE));
        level1Text.setFill(Color.ORANGE);

        StackPane level1TextPane = new StackPane(level1Text);
        level1TextPane.setAlignment(Pos.CENTER);


        Text level1AmmoText = new Text("Ammo Left: " + level1Ammo);
        level1AmmoText.setFont(Font.font("Arial", 10 * SCALE));
        level1AmmoText.setFill(Color.ORANGE);

        StackPane level1AmmoTextPane = new StackPane(level1AmmoText);
        level1AmmoTextPane.setAlignment(Pos.CENTER);


        Text level1GameFinalText = new Text();
        level1GameFinalText.setFont(Font.font("Arial", 20 * SCALE));
        level1GameFinalText.setFill(Color.ORANGE);

        StackPane level1GameFinalTextPane = new StackPane(level1GameFinalText);
        level1GameFinalTextPane.setAlignment(Pos.CENTER);
        level1GameFinalTextPane.setVisible(false);


        Text level1FinalBlinkingText = new Text();
        level1FinalBlinkingText.setFont(Font.font("Arial", 15 * SCALE));
        level1FinalBlinkingText.setFill(Color.ORANGE);

        StackPane level1FinalBlinkingTextPane = new StackPane(level1FinalBlinkingText);
        level1FinalBlinkingTextPane.setAlignment(Pos.CENTER);
        level1BlinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> level1FinalBlinkingText.setVisible(!level1FinalBlinkingText.isVisible()))
        );
        level1BlinkTimeline.setCycleCount(Animation.INDEFINITE);
        level1BlinkTimeline.play();
        level1FinalBlinkingTextPane.setVisible(false);


        Image duckImage1 = new Image(new File("assets/duck_red/4.png").toURI().toString());
        Image duckImage2 = new Image(new File("assets/duck_red/5.png").toURI().toString());
        Image duckImage3 = new Image(new File("assets/duck_red/6.png").toURI().toString());

        ImageView duckImageView = new ImageView();
        duckImageView.setFitWidth(35 * SCALE);
        duckImageView.setFitHeight(35 * SCALE);
        duckImageView.setImage(duckImage1);
        duckImageView.setTranslateY(-50 * SCALE);

        StackPane duckImageRoot = new StackPane();
        duckImageRoot.getChildren().add(duckImageView);
        duckY = -50 * SCALE;

        duckAnimation1 = duckAnimationH1(duckImage1,duckImage2,duckImage3,duckImageView);
        duckAnimation1.setCycleCount(Animation.INDEFINITE);


        StackPane gameBackGround = new StackPane();
        gameBackGround.getChildren().addAll(backGroundRoot, duckImageRoot, foregroundRoot, createPaddingPane(level1TextPane, 0, 225 * SCALE, 0, 0),
                createPaddingPane(level1AmmoTextPane, 0, 225 * SCALE, 0, 190 * SCALE), createPaddingPane(level1GameFinalTextPane, 0, 50 * SCALE, 0, 0),
                createPaddingPane(level1FinalBlinkingTextPane, 0, 0, 0, 0));
        gameScene1 = new Scene(gameBackGround, 256 * SCALE, 240 * SCALE);



        Text level2Text = new Text("Level 2/6");
        level2Text.setFont(Font.font("Arial", 10 * SCALE));
        level2Text.setFill(Color.ORANGE);

        StackPane level2TextPane = new StackPane(level2Text);
        level2TextPane.setAlignment(Pos.CENTER);


        backgroundImageView2 = new ImageView(backgroundImage);
        backgroundImageView2.setFitWidth(256 * SCALE);
        backgroundImageView2.setFitHeight(240 * SCALE);

        StackPane backGroundRoot2 = new StackPane();
        backGroundRoot2.getChildren().add(backgroundImageView2);


        foregroundImageView2 = new ImageView(foregroundImage);
        foregroundImageView2.setFitWidth(256 * SCALE);
        foregroundImageView2.setFitHeight(240 * SCALE);

        StackPane foregroundRoot2 = new StackPane();
        foregroundRoot2.getChildren().add(foregroundImageView2);


        Text level2AmmoText = new Text("Ammo Left: " + level2Ammo);
        level2AmmoText.setFont(Font.font("Arial", 10 * SCALE));
        level2AmmoText.setFill(Color.ORANGE);

        StackPane level2AmmoTextPane = new StackPane(level2AmmoText);
        level2AmmoTextPane.setAlignment(Pos.CENTER);


        Text level2GameFinalText = new Text();
        level2GameFinalText.setFont(Font.font("Arial", 20 * SCALE));
        level2GameFinalText.setFill(Color.ORANGE);

        StackPane level2GameFinalTextPane = new StackPane(level2GameFinalText);
        level2GameFinalTextPane.setAlignment(Pos.CENTER);
        level2GameFinalTextPane.setVisible(false);


        Text level2FinalBlinkingText = new Text();
        level2FinalBlinkingText.setFont(Font.font("Arial", 15 * SCALE));
        level2FinalBlinkingText.setFill(Color.ORANGE);

        StackPane level2FinalBlinkingTextPane = new StackPane(level2FinalBlinkingText);
        level2FinalBlinkingTextPane.setAlignment(Pos.CENTER);
        level2BlinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> level2FinalBlinkingText.setVisible(!level2FinalBlinkingText.isVisible()))
        );
        level2BlinkTimeline.setCycleCount(Animation.INDEFINITE);
        level2BlinkTimeline.play();
        level2FinalBlinkingTextPane.setVisible(false);


        Image level2duckImage1 = new Image(new File("assets/duck_blue/1.png").toURI().toString());
        Image level2duckImage2 = new Image(new File("assets/duck_blue/2.png").toURI().toString());
        Image level2duckImage3 = new Image(new File("assets/duck_blue/3.png").toURI().toString());

        ImageView level2duckImageView = new ImageView();
        level2duckImageView.setFitWidth(35 * SCALE);
        level2duckImageView.setFitHeight(35 * SCALE);
        level2duckImageView.setImage(level2duckImage1);
        level2duckImageView.setTranslateY(-50 * SCALE);

        StackPane level2duckImageRoot = new StackPane();
        level2duckImageRoot.getChildren().add(level2duckImageView);
        duckY = -50 * SCALE;


        StackPane game2BackGround = new StackPane();
        game2BackGround.getChildren().addAll(backGroundRoot2, level2duckImageRoot, foregroundRoot2, createPaddingPane(level2TextPane, 0, 225 * SCALE, 0, 0),
                createPaddingPane(level2AmmoTextPane, 0, 225 * SCALE, 0, 190 * SCALE), createPaddingPane(level2GameFinalTextPane, 0, 50 * SCALE, 0, 0),
                createPaddingPane(level2FinalBlinkingTextPane, 0, 0, 0, 0));
        gameScene2 = new Scene(game2BackGround, 256 * SCALE, 240 * SCALE);

        duckAnimation2 = duckAnimationC1(level2duckImage1,level2duckImage2,level2duckImage3,level2duckImageView);
        duckAnimation2.setCycleCount(Animation.INDEFINITE);


        Text level3Text = new Text("Level 3/6");
        level3Text.setFont(Font.font("Arial", 10 * SCALE));
        level3Text.setFill(Color.ORANGE);

        StackPane level3TextPane = new StackPane(level3Text);
        level3TextPane.setAlignment(Pos.CENTER);

        backgroundImageView3 = new ImageView(backgroundImage);
        backgroundImageView3.setFitWidth(256 * SCALE);
        backgroundImageView3.setFitHeight(240 * SCALE);

        StackPane backGroundRoot3 = new StackPane();
        backGroundRoot3.getChildren().add(backgroundImageView3);


        foregroundImageView3 = new ImageView(foregroundImage);
        foregroundImageView3.setFitWidth(256 * SCALE);
        foregroundImageView3.setFitHeight(240 * SCALE);

        StackPane foregroundRoot3 = new StackPane();
        foregroundRoot3.getChildren().add(foregroundImageView3);


        Text level3AmmoText = new Text("Ammo Left: " + level3Ammo);
        level3AmmoText.setFont(Font.font("Arial", 10 * SCALE));
        level3AmmoText.setFill(Color.ORANGE);

        StackPane level3AmmoTextPane = new StackPane(level3AmmoText);
        level3AmmoTextPane.setAlignment(Pos.CENTER);


        Text level3GameFinalText = new Text();
        level3GameFinalText.setFont(Font.font("Arial", 20 * SCALE));
        level3GameFinalText.setFill(Color.ORANGE);

        StackPane level3GameFinalTextPane = new StackPane(level3GameFinalText);
        level3GameFinalTextPane.setAlignment(Pos.CENTER);
        level3GameFinalTextPane.setVisible(false);


        Text level3FinalBlinkingText = new Text();
        level3FinalBlinkingText.setFont(Font.font("Arial", 15 * SCALE));
        level3FinalBlinkingText.setFill(Color.ORANGE);

        StackPane level3FinalBlinkingTextPane = new StackPane(level3FinalBlinkingText);
        level3FinalBlinkingTextPane.setAlignment(Pos.CENTER);
        level3BlinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> level3FinalBlinkingText.setVisible(!level3FinalBlinkingText.isVisible()))
        );
        level3BlinkTimeline.setCycleCount(Animation.INDEFINITE);
        level3BlinkTimeline.play();
        level3FinalBlinkingTextPane.setVisible(false);


        Image level3duck1Image1 = new Image(new File("assets/duck_black/4.png").toURI().toString());
        Image level3duck1Image2 = new Image(new File("assets/duck_black/5.png").toURI().toString());
        Image level3duck1Image3 = new Image(new File("assets/duck_black/6.png").toURI().toString());

        ImageView level3duck1ImageView = new ImageView();
        level3duck1ImageView.setFitWidth(35 * SCALE);
        level3duck1ImageView.setFitHeight(35 * SCALE);
        level3duck1ImageView.setImage(level3duck1Image1);
        level3duck1ImageView.setTranslateY(-50 * SCALE);

        StackPane level3duck1ImageRoot = new StackPane();
        level3duck1ImageRoot.getChildren().add(level3duck1ImageView);
        duckY = -50 * SCALE;

        Image level3duck2Image1 = new Image(new File("assets/duck_red/4.png").toURI().toString());
        Image level3duck2Image2 = new Image(new File("assets/duck_red/5.png").toURI().toString());
        Image level3duck2Image3 = new Image(new File("assets/duck_red/6.png").toURI().toString());

        ImageView level3duck2ImageView = new ImageView();
        level3duck2ImageView.setFitWidth(35 * SCALE);
        level3duck2ImageView.setFitHeight(35 * SCALE);
        level3duck2ImageView.setImage(level3duck2Image1);
        level3duck2ImageView.setTranslateY(-100 * SCALE);

        StackPane level3duck2ImageRoot = new StackPane();
        level3duck2ImageRoot.getChildren().add(level3duck2ImageView);
        duckY2 = -100 * SCALE;

        StackPane game3BackGround = new StackPane();
        game3BackGround.getChildren().addAll(backGroundRoot3, level3duck1ImageRoot, level3duck2ImageRoot, foregroundRoot3, createPaddingPane(level3TextPane, 0, 225 * SCALE, 0, 0),
                createPaddingPane(level3AmmoTextPane, 0, 225 * SCALE, 0, 190 * SCALE), createPaddingPane(level3GameFinalTextPane, 0, 50 * SCALE, 0, 0),
                createPaddingPane(level3FinalBlinkingTextPane, 0, 0, 0, 0));
        gameScene3 = new Scene(game3BackGround, 256 * SCALE, 240 * SCALE);

        duck1Animation3 = duckAnimationH1(level3duck1Image1,level3duck1Image2,level3duck1Image3,level3duck1ImageView);
        duck1Animation3.setCycleCount(Animation.INDEFINITE);

        duck2Animation3 = duckAnimationH2(level3duck2Image1,level3duck2Image2,level3duck2Image3,level3duck2ImageView);
        duck2Animation3.setCycleCount(Animation.INDEFINITE);


        Text level4Text = new Text("Level 4/6");
        level4Text.setFont(Font.font("Arial", 10 * SCALE));
        level4Text.setFill(Color.ORANGE);

        StackPane level4TextPane = new StackPane(level4Text);
        level4TextPane.setAlignment(Pos.CENTER);

        backgroundImageView4 = new ImageView(backgroundImage);
        backgroundImageView4.setFitWidth(256 * SCALE);
        backgroundImageView4.setFitHeight(240 * SCALE);

        StackPane backGroundRoot4 = new StackPane();
        backGroundRoot4.getChildren().add(backgroundImageView4);


        foregroundImageView4 = new ImageView(foregroundImage);
        foregroundImageView4.setFitWidth(256 * SCALE);
        foregroundImageView4.setFitHeight(240 * SCALE);

        StackPane foregroundRoot4 = new StackPane();
        foregroundRoot4.getChildren().add(foregroundImageView4);


        Text level4AmmoText = new Text("Ammo Left: " + level4Ammo);
        level4AmmoText.setFont(Font.font("Arial", 10 * SCALE));
        level4AmmoText.setFill(Color.ORANGE);

        StackPane level4AmmoTextPane = new StackPane(level4AmmoText);
        level4AmmoTextPane.setAlignment(Pos.CENTER);


        Text level4GameFinalText = new Text();
        level4GameFinalText.setFont(Font.font("Arial", 20 * SCALE));
        level4GameFinalText.setFill(Color.ORANGE);

        StackPane level4GameFinalTextPane = new StackPane(level4GameFinalText);
        level4GameFinalTextPane.setAlignment(Pos.CENTER);
        level4GameFinalTextPane.setVisible(false);


        Text level4FinalBlinkingText = new Text();
        level4FinalBlinkingText.setFont(Font.font("Arial", 15 * SCALE));
        level4FinalBlinkingText.setFill(Color.ORANGE);


        StackPane level4FinalBlinkingTextPane = new StackPane(level4FinalBlinkingText);
        level4FinalBlinkingTextPane.setAlignment(Pos.CENTER);
        level4BlinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> level4FinalBlinkingText.setVisible(!level4FinalBlinkingText.isVisible()))
        );
        level4BlinkTimeline.setCycleCount(Animation.INDEFINITE);
        level4BlinkTimeline.play();
        level4FinalBlinkingTextPane.setVisible(false);


        Image level4duck1Image1 = new Image(new File("assets/duck_blue/1.png").toURI().toString());
        Image level4duck1Image2 = new Image(new File("assets/duck_blue/2.png").toURI().toString());
        Image level4duck1Image3 = new Image(new File("assets/duck_blue/3.png").toURI().toString());

        ImageView level4duck1ImageView = new ImageView();
        level4duck1ImageView.setFitWidth(35 * SCALE);
        level4duck1ImageView.setFitHeight(35 * SCALE);
        level4duck1ImageView.setImage(level4duck1Image1);
        level4duck1ImageView.setTranslateY(-100 * SCALE);

        StackPane level4duck1ImageRoot = new StackPane();
        level4duck1ImageRoot.getChildren().add(level4duck1ImageView);
        duckY = -100 * SCALE;

        Image level4duck2Image1 = new Image(new File("assets/duck_red/1.png").toURI().toString());
        Image level4duck2Image2 = new Image(new File("assets/duck_red/2.png").toURI().toString());
        Image level4duck2Image3 = new Image(new File("assets/duck_red/3.png").toURI().toString());

        ImageView level4duck2ImageView = new ImageView();
        level4duck2ImageView.setFitWidth(35 * SCALE);
        level4duck2ImageView.setFitHeight(35 * SCALE);
        level4duck2ImageView.setImage(level4duck2Image1);
        level4duck2ImageView.setTranslateY(-50 * SCALE);

        StackPane level4duck2ImageRoot = new StackPane();
        level4duck2ImageRoot.getChildren().add(level4duck2ImageView);
        duckY2 = -50 * SCALE;

        StackPane game4BackGround = new StackPane();
        game4BackGround.getChildren().addAll(backGroundRoot4, level4duck1ImageRoot, level4duck2ImageRoot, foregroundRoot4, createPaddingPane(level4TextPane, 0, 225 * SCALE, 0, 0),
                createPaddingPane(level4AmmoTextPane, 0, 225 * SCALE, 0, 190 * SCALE), createPaddingPane(level4GameFinalTextPane, 0, 50 * SCALE, 0, 0),
                createPaddingPane(level4FinalBlinkingTextPane, 0, 0, 0, 0));
        gameScene4 = new Scene(game4BackGround, 256 * SCALE, 240 * SCALE);

        duck1Animation4 = duckAnimationC1(level4duck1Image1,level4duck1Image2,level4duck1Image3,level4duck1ImageView);
        duck1Animation4.setCycleCount(Animation.INDEFINITE);

        duck2Animation4 = duckAnimationC2(level4duck2Image1,level4duck2Image2,level4duck2Image3,level4duck2ImageView);
        duck2Animation4.setCycleCount(Animation.INDEFINITE);



        Text level5Text = new Text("Level 5/6");
        level5Text.setFont(Font.font("Arial", 10 * SCALE));
        level5Text.setFill(Color.ORANGE);

        StackPane level5TextPane = new StackPane(level5Text);
        level5TextPane.setAlignment(Pos.CENTER);

        backgroundImageView5 = new ImageView(backgroundImage);
        backgroundImageView5.setFitWidth(256 * SCALE);
        backgroundImageView5.setFitHeight(240 * SCALE);

        StackPane backGroundRoot5 = new StackPane();
        backGroundRoot5.getChildren().add(backgroundImageView5);


        foregroundImageView5 = new ImageView(foregroundImage);
        foregroundImageView5.setFitWidth(256 * SCALE);
        foregroundImageView5.setFitHeight(240 * SCALE);

        StackPane foregroundRoot5 = new StackPane();
        foregroundRoot5.getChildren().add(foregroundImageView5);


        Text level5AmmoText = new Text("Ammo Left: " + level5Ammo);
        level5AmmoText.setFont(Font.font("Arial", 10 * SCALE));
        level5AmmoText.setFill(Color.ORANGE);

        StackPane level5AmmoTextPane = new StackPane(level5AmmoText);
        level5AmmoTextPane.setAlignment(Pos.CENTER);


        Text level5GameFinalText = new Text();
        level5GameFinalText.setFont(Font.font("Arial", 20 * SCALE));
        level5GameFinalText.setFill(Color.ORANGE);

        StackPane level5GameFinalTextPane = new StackPane(level5GameFinalText);
        level5GameFinalTextPane.setAlignment(Pos.CENTER);
        level5GameFinalTextPane.setVisible(false);


        Text level5FinalBlinkingText = new Text();
        level5FinalBlinkingText.setFont(Font.font("Arial", 15 * SCALE));
        level5FinalBlinkingText.setFill(Color.ORANGE);


        StackPane level5FinalBlinkingTextPane = new StackPane(level5FinalBlinkingText);
        level5FinalBlinkingTextPane.setAlignment(Pos.CENTER);
        level5BlinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> level5FinalBlinkingText.setVisible(!level5FinalBlinkingText.isVisible()))
        );
        level5BlinkTimeline.setCycleCount(Animation.INDEFINITE);
        level5BlinkTimeline.play();
        level5FinalBlinkingTextPane.setVisible(false);


        Image level5duck1Image1 = new Image(new File("assets/duck_blue/4.png").toURI().toString());
        Image level5duck1Image2 = new Image(new File("assets/duck_blue/5.png").toURI().toString());
        Image level5duck1Image3 = new Image(new File("assets/duck_blue/6.png").toURI().toString());

        ImageView level5duck1ImageView = new ImageView();
        level5duck1ImageView.setFitWidth(35 * SCALE);
        level5duck1ImageView.setFitHeight(35 * SCALE);
        level5duck1ImageView.setImage(level5duck1Image1);
        level5duck1ImageView.setTranslateY(-50 * SCALE);

        StackPane level5duck1ImageRoot = new StackPane();
        level5duck1ImageRoot.getChildren().add(level5duck1ImageView);
        duckY = -50 * SCALE;

        Image level5duck2Image1 = new Image(new File("assets/duck_red/4.png").toURI().toString());
        Image level5duck2Image2 = new Image(new File("assets/duck_red/5.png").toURI().toString());
        Image level5duck2Image3 = new Image(new File("assets/duck_red/6.png").toURI().toString());

        ImageView level5duck2ImageView = new ImageView();
        level5duck2ImageView.setFitWidth(35 * SCALE);
        level5duck2ImageView.setFitHeight(35 * SCALE);
        level5duck2ImageView.setImage(level5duck2Image1);
        level5duck2ImageView.setTranslateY(-100 * SCALE);

        StackPane level5duck2ImageRoot = new StackPane();
        level5duck2ImageRoot.getChildren().add(level5duck2ImageView);
        duckY2 = -100 * SCALE;

        Image level5duck3Image1 = new Image(new File("assets/duck_black/1.png").toURI().toString());
        Image level5duck3Image2 = new Image(new File("assets/duck_black/2.png").toURI().toString());
        Image level5duck3Image3 = new Image(new File("assets/duck_black/3.png").toURI().toString());

        ImageView level5duck3ImageView = new ImageView();
        level5duck3ImageView.setFitWidth(35 * SCALE);
        level5duck3ImageView.setFitHeight(35 * SCALE);
        level5duck3ImageView.setImage(level5duck3Image1);
        level5duck3ImageView.setTranslateY(-50 * SCALE);

        StackPane level5duck3ImageRoot = new StackPane();
        level5duck3ImageRoot.getChildren().add(level5duck3ImageView);
        duckY3 = -50 * SCALE;

        StackPane game5BackGround = new StackPane();
        game5BackGround.getChildren().addAll(backGroundRoot5, level5duck1ImageRoot, level5duck2ImageRoot, level5duck3ImageRoot, foregroundRoot5, createPaddingPane(level5TextPane, 0, 225 * SCALE, 0, 0),
                createPaddingPane(level5AmmoTextPane, 0, 225 * SCALE, 0, 190 * SCALE), createPaddingPane(level5GameFinalTextPane, 0, 50 * SCALE, 0, 0),
                createPaddingPane(level5FinalBlinkingTextPane, 0, 0, 0, 0));
        gameScene5 = new Scene(game5BackGround, 256 * SCALE, 240 * SCALE);

        duck1Animation5 = duckAnimationH1(level5duck1Image1,level5duck1Image2,level5duck1Image3,level5duck1ImageView);
        duck1Animation5.setCycleCount(Animation.INDEFINITE);

        duck2Animation5 = duckAnimationH2(level5duck2Image1,level5duck2Image2,level5duck2Image3,level5duck2ImageView);
        duck2Animation5.setCycleCount(Animation.INDEFINITE);

        duck3Animation5 = duckAnimationC3(level5duck3Image1,level5duck3Image2,level5duck3Image3,level5duck3ImageView);
        duck3Animation5.setCycleCount(Animation.INDEFINITE);



        Text level6Text = new Text("Level 6/6");
        level6Text.setFont(Font.font("Arial", 10 * SCALE));
        level6Text.setFill(Color.ORANGE);

        StackPane level6TextPane = new StackPane(level6Text);
        level6TextPane.setAlignment(Pos.CENTER);

        backgroundImageView6 = new ImageView(backgroundImage);
        backgroundImageView6.setFitWidth(256 * SCALE);
        backgroundImageView6.setFitHeight(240 * SCALE);

        StackPane backGroundRoot6 = new StackPane();
        backGroundRoot6.getChildren().add(backgroundImageView6);


        foregroundImageView6 = new ImageView(foregroundImage);
        foregroundImageView6.setFitWidth(256 * SCALE);
        foregroundImageView6.setFitHeight(240 * SCALE);

        StackPane foregroundRoot6 = new StackPane();
        foregroundRoot6.getChildren().add(foregroundImageView6);


        Text level6AmmoText = new Text("Ammo Left: " + level6Ammo);
        level6AmmoText.setFont(Font.font("Arial", 10 * SCALE));
        level6AmmoText.setFill(Color.ORANGE);

        StackPane level6AmmoTextPane = new StackPane(level6AmmoText);
        level6AmmoTextPane.setAlignment(Pos.CENTER);


        Text level6GameFinalText = new Text();
        level6GameFinalText.setFont(Font.font("Arial", 17 * SCALE));
        level6GameFinalText.setFill(Color.ORANGE);

        StackPane level6GameFinalTextPane = new StackPane(level6GameFinalText);
        level6GameFinalTextPane.setAlignment(Pos.CENTER);
        level6GameFinalTextPane.setVisible(false);


        Text level6FinalBlinkingText = new Text();
        level6FinalBlinkingText.setFont(Font.font("Arial", 12 * SCALE));
        level6FinalBlinkingText.setFill(Color.ORANGE);

        StackPane level6FinalBlinkingTextPane = new StackPane(level6FinalBlinkingText);
        level6FinalBlinkingTextPane.setAlignment(Pos.CENTER);
        level6BlinkTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> level6FinalBlinkingText.setVisible(!level6FinalBlinkingText.isVisible()))
        );
        level6BlinkTimeline.setCycleCount(Animation.INDEFINITE);
        level6BlinkTimeline.play();
        level6FinalBlinkingTextPane.setVisible(false);


        Image level6duck1Image1 = new Image(new File("assets/duck_blue/4.png").toURI().toString());
        Image level6duck1Image2 = new Image(new File("assets/duck_blue/5.png").toURI().toString());
        Image level6duck1Image3 = new Image(new File("assets/duck_blue/6.png").toURI().toString());

        ImageView level6duck1ImageView = new ImageView();
        level6duck1ImageView.setFitWidth(35 * SCALE);
        level6duck1ImageView.setFitHeight(35 * SCALE);
        level6duck1ImageView.setImage(level6duck1Image1);
        level6duck1ImageView.setTranslateY(-50 * SCALE);

        StackPane level6duck1ImageRoot = new StackPane();
        level6duck1ImageRoot.getChildren().add(level6duck1ImageView);
        duckY = -50 * SCALE;

        Image level6duck2Image1 = new Image(new File("assets/duck_red/4.png").toURI().toString());
        Image level6duck2Image2 = new Image(new File("assets/duck_red/5.png").toURI().toString());
        Image level6duck2Image3 = new Image(new File("assets/duck_red/6.png").toURI().toString());

        ImageView level6duck2ImageView = new ImageView();
        level6duck2ImageView.setFitWidth(35 * SCALE);
        level6duck2ImageView.setFitHeight(35 * SCALE);
        level6duck2ImageView.setImage(level6duck2Image1);
        level6duck2ImageView.setTranslateY(-100 * SCALE);

        StackPane level6duck2ImageRoot = new StackPane();
        level6duck2ImageRoot.getChildren().add(level6duck2ImageView);
        duckY2 = -100 * SCALE;

        Image level6duck3Image1 = new Image(new File("assets/duck_black/1.png").toURI().toString());
        Image level6duck3Image2 = new Image(new File("assets/duck_black/2.png").toURI().toString());
        Image level6duck3Image3 = new Image(new File("assets/duck_black/3.png").toURI().toString());

        ImageView level6duck3ImageView = new ImageView();
        level6duck3ImageView.setFitWidth(35 * SCALE);
        level6duck3ImageView.setFitHeight(35 * SCALE);
        level6duck3ImageView.setImage(level6duck3Image1);
        level6duck3ImageView.setTranslateY(-50 * SCALE);

        StackPane level6duck3ImageRoot = new StackPane();
        level6duck3ImageRoot.getChildren().add(level6duck3ImageView);
        duckY3 = -50 * SCALE;

        Image level6duck4Image1 = new Image(new File("assets/duck_blue/1.png").toURI().toString());
        Image level6duck4Image2 = new Image(new File("assets/duck_blue/2.png").toURI().toString());
        Image level6duck4Image3 = new Image(new File("assets/duck_blue/3.png").toURI().toString());

        ImageView level6duck4ImageView = new ImageView();
        level6duck4ImageView.setFitWidth(35 * SCALE);
        level6duck4ImageView.setFitHeight(35 * SCALE);
        level6duck4ImageView.setImage(level6duck4Image1);
        level6duck4ImageView.setTranslateY(-50 * SCALE);

        StackPane level6duck4ImageRoot = new StackPane();
        level6duck4ImageRoot.getChildren().add(level6duck4ImageView);
        duckY4 = 50 * SCALE;

        StackPane game6BackGround = new StackPane();
        game6BackGround.getChildren().addAll(backGroundRoot6, level6duck1ImageRoot, level6duck2ImageRoot, level6duck3ImageRoot, level6duck4ImageRoot, foregroundRoot6,
                createPaddingPane(level6TextPane, 0, 225 * SCALE, 0, 0), createPaddingPane(level6AmmoTextPane, 0, 225 * SCALE, 0, 190 * SCALE),
                createPaddingPane(level6GameFinalTextPane, 0, 50 * SCALE, 0, 0), createPaddingPane(level6FinalBlinkingTextPane, 0, 0, 0, 0));
        gameScene6 = new Scene(game6BackGround, 256 * SCALE, 240 * SCALE);

        duck1Animation6 = duckAnimationH1(level6duck1Image1,level6duck1Image2,level6duck1Image3,level6duck1ImageView);
        duck1Animation6.setCycleCount(Animation.INDEFINITE);

        duck2Animation6 = duckAnimationH2(level6duck2Image1,level6duck2Image2,level6duck2Image3,level6duck2ImageView);
        duck2Animation6.setCycleCount(Animation.INDEFINITE);

        duck3Animation6 = duckAnimationC3(level6duck3Image1,level6duck3Image2,level6duck3Image3,level6duck3ImageView);
        duck3Animation6.setCycleCount(Animation.INDEFINITE);

        duck4Animation6 = duckAnimationC4(level6duck4Image1,level6duck4Image2,level6duck4Image3,level6duck4ImageView);
        duck4Animation6.setCycleCount(Animation.INDEFINITE);

        titleScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // to reset background and crosshair selection
                backgroundSelectionEnterPressed = false;
                crosshairNumber = 1;
                backgroundNumber = 1;
                crosshairImageView.setImage(new Image(new File("assets/crosshair/" + crosshairNumber + ".png").toURI().toString()));
                backgroundSelectionImageView.setImage(new Image(new File("assets/background/" + backgroundNumber +".png").toURI().toString()));
                primaryStage.setScene(backgroundSelectionScene);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                primaryStage.close();
            }
        });

        backgroundSelectionScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                primaryStage.setScene(titleScene);
            } else if (event.getCode() == KeyCode.ENTER) {
                // necessary adjustments for the game to work correctly
                backgroundSelectionEnterPressed = true;
                duckY = -50 * SCALE;
                duckX = 0;
                level1IsDuckShot = false;
                isMovingRight = true;
                level1Ammo = 3;
                level1AmmoText.setText("Ammo Left: " + level1Ammo);
                titleSceneMediaPlayer.stop();

                music = new Media(new File("assets/effects/Intro.mp3").toURI().toString());
                introMediaPlayer = new MediaPlayer(music);
                introMediaPlayer.setVolume(VOLUME);
                introMediaPlayer.setCycleCount(1);
                // for playing once then set the game scene on stage after the music is over
                introMediaPlayer.setOnEndOfMedia(() -> {
                    introMediaPlayer.stop();
                    primaryStage.setScene(gameScene1);
                    duckAnimation1.play();
                });
                introMediaPlayer.play();

            } else if (event.getCode() == KeyCode.RIGHT) {
                if (!backgroundSelectionEnterPressed){
                    backgroundNumber++;
                    if (backgroundNumber == 7) {
                        backgroundNumber = 1;
                    }
                }
            } else if (event.getCode() == KeyCode.LEFT) {
                if (!backgroundSelectionEnterPressed){
                    backgroundNumber--;
                    if (backgroundNumber == 0) {
                        backgroundNumber = 6;
                    }
                }
            } else if (event.getCode() == KeyCode.DOWN) {
                if (!backgroundSelectionEnterPressed) {
                    crosshairNumber--;
                    if (crosshairNumber == 0) {
                        crosshairNumber = 6;
                    }
                }
            } else if (event.getCode() == KeyCode.UP) {
                if (!backgroundSelectionEnterPressed) {
                    crosshairNumber++;
                    if (crosshairNumber == 7) {
                        crosshairNumber = 1;
                    }
                }
            }
            crosshairImageView.setImage(new Image(new File("assets/crosshair/" + crosshairNumber.toString() + ".png").toURI().toString()));
            backgroundSelectionImageView.setImage(new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString()));

            backgroundImageView.setImage(new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString()));
            foregroundImageView.setImage(new Image(new File("assets/foreground/" + backgroundNumber.toString() +".png").toURI().toString()));

            backgroundImageView2.setImage(new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString()));
            foregroundImageView2.setImage(new Image(new File("assets/foreground/" + backgroundNumber.toString() +".png").toURI().toString()));

            backgroundImageView3.setImage(new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString()));
            foregroundImageView3.setImage(new Image(new File("assets/foreground/" + backgroundNumber.toString() +".png").toURI().toString()));

            backgroundImageView4.setImage(new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString()));
            foregroundImageView4.setImage(new Image(new File("assets/foreground/" + backgroundNumber.toString() +".png").toURI().toString()));

            backgroundImageView5.setImage(new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString()));
            foregroundImageView5.setImage(new Image(new File("assets/foreground/" + backgroundNumber.toString() +".png").toURI().toString()));

            backgroundImageView6.setImage(new Image(new File("assets/background/" + backgroundNumber.toString() +".png").toURI().toString()));
            foregroundImageView6.setImage(new Image(new File("assets/foreground/" + backgroundNumber.toString() +".png").toURI().toString()));

            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);

            Image crosshairImage2 = crosshairImageView.snapshot(parameters, null);

            double hotspotX = crosshairImage2.getWidth() / 2;
            double hotspotY = crosshairImage2.getHeight() / 2;

            Cursor customCursor = new ImageCursor(crosshairImage2, hotspotX, hotspotY);
            titleScene.setCursor(Cursor.DISAPPEAR);
            backgroundSelectionScene.setCursor(Cursor.DISAPPEAR);
            gameScene1.setCursor(customCursor);
            gameScene2.setCursor(customCursor);
            gameScene3.setCursor(customCursor);
            gameScene4.setCursor(customCursor);
            gameScene5.setCursor(customCursor);
            gameScene6.setCursor(customCursor);
        });

        gameScene1.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (level1Ammo > 0 && !level1IsDuckShot) {
                    music = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
                    gunShotMediaPlayer = new MediaPlayer(music);
                    gunShotMediaPlayer.setVolume(VOLUME);
                    gunShotMediaPlayer.setCycleCount(1);
                    gunShotMediaPlayer.play();
                    level1Ammo--;
                    double clickX = event.getX();
                    double clickY = event.getY();
                    if ((duckX + (221 * SCALE / 2) <= clickX && duckX + (291 * SCALE / 2) >= clickX) && (clickY >= duckY + (205 * SCALE / 2) && clickY <= duckY + (275 * SCALE / 2))) {
                        if (!level1IsDuckShot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level1IsDuckShot = true;
                    }
                    level1AmmoText.setText("Ammo Left: " + level1Ammo);
                    if (level1IsDuckShot) {
                        duckImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                        duckAnimation1.stop();
                        music = new Media(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
                        levelCompletedMediaPlayer = new MediaPlayer(music);
                        levelCompletedMediaPlayer.setVolume(VOLUME);
                        levelCompletedMediaPlayer.setCycleCount(1);
                        levelCompletedMediaPlayer.play();
                        level1GameFinalText.setText("YOU WIN!");
                        level1GameFinalTextPane.setVisible(true);
                        level1FinalBlinkingText.setText("Press ENTER to play next level");
                        level1FinalBlinkingTextPane.setVisible(true);
                        gameScene1.setOnKeyPressed(event1 -> {
                            if (event1.getCode() == KeyCode.ENTER) {
                                if (level1IsDuckShot) {
                                    duckFallsMediaPlayer.stop();
                                    levelCompletedMediaPlayer.stop();
                                    duckAnimation1.stop();
                                    duckY = -50 * SCALE;
                                    duckX = 0;
                                    level2IsDuckShot = false;
                                    level2Ammo = 3;
                                    level2AmmoText.setText("Ammo Left: " + level2Ammo);
                                    isMovingRight = true;
                                    level2duckImageView.setImage(level2duckImage3);
                                    primaryStage.setScene(gameScene2);
                                    duckAnimation2.play();
                                    level1GameFinalTextPane.setVisible(false);
                                    level1FinalBlinkingTextPane.setVisible(false);
                                }
                            }
                        });
                    }
                }
                if (level1Ammo == 0) {
                    if (!level1IsDuckShot) {
                        music = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
                        gameOverMediaPlayer = new MediaPlayer(music);
                        gameOverMediaPlayer.setVolume(VOLUME);
                        gameOverMediaPlayer.setCycleCount(1);
                        gameOverMediaPlayer.play();
                        level1GameFinalText.setText("GAME OVER!");
                        level1GameFinalTextPane.setVisible(true);
                        level1FinalBlinkingText.setText("Press ENTER to play again\n    Press ESC to exit");
                        level1FinalBlinkingTextPane.setVisible(true);
                        gameScene1.setOnKeyPressed(event1 -> {
                            if (event1.getCode() == KeyCode.ESCAPE) {
                                gameOverMediaPlayer.stop();
                                duckAnimation1.stop();
                                duckY = -50 * SCALE;
                                duckX = 0;
                                duckImageView.setImage(duckImage1);
                                duckImageView.setTranslateX(1);
                                primaryStage.setScene(titleScene);
                                titleSceneMediaPlayer.play();
                                level1GameFinalTextPane.setVisible(false);
                                level1FinalBlinkingTextPane.setVisible(false);
                            } else if (event1.getCode() == KeyCode.ENTER) {
                                if (level1Ammo == 0) {
                                    gameOverMediaPlayer.stop();
                                    duckY = -50 * SCALE;
                                    duckX = 0;
                                    level1IsDuckShot = false;
                                    level1Ammo = 3;
                                    isMovingRight = true;
                                    level1AmmoText.setText("Ammo Left: " + level1Ammo);
                                    level1GameFinalTextPane.setVisible(false);
                                    level1FinalBlinkingTextPane.setVisible(false);
                                }
                            }
                        });
                    }
                }
            }
        });

        gameScene2.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (level2Ammo > 0 && !level2IsDuckShot) {
                    music = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
                    gunShotMediaPlayer = new MediaPlayer(music);
                    gunShotMediaPlayer.setVolume(VOLUME);
                    gunShotMediaPlayer.setCycleCount(1);
                    gunShotMediaPlayer.play();
                    level2Ammo--;
                    double clickX = event.getX();
                    double clickY = event.getY();
                    if ((duckX + (221 * SCALE / 2) <= clickX && duckX + (291 * SCALE / 2) >= clickX) && (clickY >= duckY + (205 * SCALE / 2) && clickY <= duckY + (275 * SCALE / 2))) {
                        if (!level2IsDuckShot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level2IsDuckShot = true;
                    }
                    level2AmmoText.setText("Ammo Left: " + level2Ammo);
                    if (level2IsDuckShot) {
                        level2duckImageView.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                        duckAnimation2.stop();
                        music = new Media(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
                        levelCompletedMediaPlayer = new MediaPlayer(music);
                        levelCompletedMediaPlayer.setVolume(VOLUME);
                        levelCompletedMediaPlayer.setCycleCount(1);
                        levelCompletedMediaPlayer.play();
                        level2GameFinalText.setText("YOU WIN!");
                        level2GameFinalTextPane.setVisible(true);
                        level2FinalBlinkingText.setText("Press ENTER to play next level");
                        level2FinalBlinkingTextPane.setVisible(true);
                        gameScene2.setOnKeyPressed(event1 -> {
                            if (event1.getCode() == KeyCode.ENTER) {
                                if (level2IsDuckShot) {
                                    duckFallsMediaPlayer.stop();
                                    levelCompletedMediaPlayer.stop();
                                    duckAnimation2.stop();
                                    duckY = -50 * SCALE;
                                    duckX = 0;
                                    duckY2 = -100 * SCALE;
                                    duckX2 = 0;
                                    level3IsDuck1Shot = false;
                                    level3IsDuck2Shot = false;
                                    level3Ammo = 6;
                                    level3AmmoText.setText("Ammo Left: " + level3Ammo);
                                    isMovingRight = true;
                                    isMovingDown = false;
                                    isMovingRight1 = false;
                                    level3duck1ImageView.setImage(level3duck1Image3);
                                    level3duck2ImageView.setImage(level3duck2Image3);
                                    primaryStage.setScene(gameScene3);
                                    duck1Animation3.play();
                                    duck2Animation3.play();
                                    level2GameFinalTextPane.setVisible(false);
                                    level2FinalBlinkingTextPane.setVisible(false);
                                }
                            }
                        });
                    }
                }
                if (level2Ammo == 0) {
                    if (!level2IsDuckShot) {
                        music = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
                        gameOverMediaPlayer = new MediaPlayer(music);
                        gameOverMediaPlayer.setVolume(VOLUME);
                        gameOverMediaPlayer.setCycleCount(1);
                        gameOverMediaPlayer.play();
                        level2GameFinalText.setText("GAME OVER!");
                        level2GameFinalTextPane.setVisible(true);
                        level2FinalBlinkingText.setText("Press ENTER to play again\n    Press ESC to exit");
                        level2FinalBlinkingTextPane.setVisible(true);
                        gameScene2.setOnKeyPressed(event1 -> {
                            if (event1.getCode() == KeyCode.ESCAPE) {
                                gameOverMediaPlayer.stop();
                                duckAnimation2.stop();
                                primaryStage.setScene(titleScene);
                                titleSceneMediaPlayer.play();
                                level2GameFinalTextPane.setVisible(false);
                                level2FinalBlinkingTextPane.setVisible(false);
                            } else if (event1.getCode() == KeyCode.ENTER) {
                                gameOverMediaPlayer.stop();
                                duckAnimation2.stop();
                                duckY = -50 * SCALE;
                                duckX = 0;
                                level1IsDuckShot = false;
                                isMovingRight = true;
                                level1Ammo = 3;
                                level1AmmoText.setText("Ammo Left: " + level1Ammo);
                                duckImageView.setImage(duckImage3);
                                primaryStage.setScene(gameScene1);
                                duckAnimation1.play();
                                level2GameFinalTextPane.setVisible(false);
                                level2FinalBlinkingTextPane.setVisible(false);
                            }
                        });
                    }
                }
            }
        });

        gameScene3.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (level3Ammo > 0 && (!level3IsDuck1Shot || !level3IsDuck2Shot)) {
                    music = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
                    gunShotMediaPlayer = new MediaPlayer(music);
                    gunShotMediaPlayer.setVolume(VOLUME);
                    gunShotMediaPlayer.setCycleCount(1);
                    gunShotMediaPlayer.play();
                    level3Ammo--;
                    double clickX = event.getX();
                    double clickY = event.getY();
                    if ((duckX + (221 * SCALE / 2) <= clickX && duckX + (291 * SCALE / 2) >= clickX) && (clickY >= duckY + (205 * SCALE / 2) && clickY <= duckY + (275 * SCALE / 2))) {
                        if (!level3IsDuck1Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level3IsDuck1Shot = true;
                    }
                    if ((duckX2 + (221 * SCALE / 2) <= clickX && duckX2 + (291 * SCALE / 2) >= clickX) && (clickY >= duckY2 + (205 * SCALE / 2) && clickY <= duckY2 + (275 * SCALE / 2))) {
                        if (!level3IsDuck2Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level3IsDuck2Shot = true;
                    }
                    level3AmmoText.setText("Ammo Left: " + level3Ammo);
                    if (level3IsDuck1Shot) {
                        level3duck1ImageView.setImage(new Image(new File("assets/duck_black/7.png").toURI().toString()));
                        duck1Animation3.stop();
                        if (level3IsDuck2Shot) {
                            level3duck2ImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                            duck2Animation3.stop();
                            music = new Media(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
                            levelCompletedMediaPlayer = new MediaPlayer(music);
                            levelCompletedMediaPlayer.setVolume(VOLUME);
                            levelCompletedMediaPlayer.setCycleCount(1);
                            levelCompletedMediaPlayer.play();
                            level3GameFinalText.setText("YOU WIN!");
                            level3GameFinalTextPane.setVisible(true);
                            level3FinalBlinkingText.setText("Press ENTER to play next level");
                            level3FinalBlinkingTextPane.setVisible(true);
                            gameScene3.setOnKeyPressed(event1 -> {
                                if (event1.getCode() == KeyCode.ENTER) {
                                    if (level3IsDuck1Shot && level3IsDuck2Shot) {
                                        duckFallsMediaPlayer.stop();
                                        levelCompletedMediaPlayer.stop();
                                        duck1Animation3.stop();
                                        duck2Animation3.stop();
                                        duckX = 70 * SCALE;
                                        duckX2 = 0;
                                        duckY = 0;
                                        duckY2 = 0;
                                        level4IsDuck1Shot = false;
                                        level4IsDuck2Shot = false;
                                        level4Ammo = 6;
                                        level4AmmoText.setText("Ammo Left: " + level4Ammo);
                                        isMovingRight = true;
                                        isMovingDown = false;
                                        isMovingDown1 = false;
                                        isMovingRight1 = false;
                                        level4duck1ImageView.setImage(level4duck1Image3);
                                        level4duck2ImageView.setImage(level4duck2Image3);
                                        primaryStage.setScene(gameScene4);
                                        duck1Animation4.play();
                                        duck2Animation4.play();
                                        level3GameFinalTextPane.setVisible(false);
                                        level3FinalBlinkingTextPane.setVisible(false);
                                    }
                                }
                            });
                        }
                    }
                    if (level3IsDuck2Shot) {
                        level3duck2ImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                        duck2Animation3.stop();
                    }
                }
                if (level3Ammo == 0) {
                    if (!(level3IsDuck1Shot && level3IsDuck2Shot)) {
                        music = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
                        gameOverMediaPlayer = new MediaPlayer(music);
                        gameOverMediaPlayer.setVolume(VOLUME);
                        gameOverMediaPlayer.setCycleCount(1);
                        gameOverMediaPlayer.play();
                        level3GameFinalText.setText("GAME OVER!");
                        level3GameFinalTextPane.setVisible(true);
                        level3FinalBlinkingText.setText("Press ENTER to play again\n    Press ESC to exit");
                        level3FinalBlinkingTextPane.setVisible(true);
                        gameScene3.setOnKeyPressed(event1 -> {
                            if (event1.getCode() == KeyCode.ESCAPE) {
                                gameOverMediaPlayer.stop();
                                duck1Animation3.stop();
                                duck2Animation3.stop();
                                primaryStage.setScene(titleScene);
                                titleSceneMediaPlayer.play();
                                level3GameFinalTextPane.setVisible(false);
                                level3FinalBlinkingTextPane.setVisible(false);
                            } else if (event1.getCode() == KeyCode.ENTER) {
                                gameOverMediaPlayer.stop();
                                duck1Animation3.stop();
                                duck2Animation3.stop();
                                duckY = -50 * SCALE;
                                duckX = 0;
                                level1IsDuckShot = false;
                                isMovingRight = true;
                                level1Ammo = 3;
                                level1AmmoText.setText("Ammo Left: " + level1Ammo);
                                duckImageView.setImage(duckImage3);
                                primaryStage.setScene(gameScene1);
                                duckAnimation1.play();
                                level3GameFinalTextPane.setVisible(false);
                                level3FinalBlinkingTextPane.setVisible(false);
                            }
                        });
                    }
                }
            }
        });

        gameScene4.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (level4Ammo > 0 && (!level4IsDuck1Shot || !level4IsDuck2Shot)) {
                    music = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
                    gunShotMediaPlayer = new MediaPlayer(music);
                    gunShotMediaPlayer.setVolume(VOLUME);
                    gunShotMediaPlayer.setCycleCount(1);
                    gunShotMediaPlayer.play();
                    level4Ammo--;
                    double clickX = event.getX();
                    double clickY = event.getY();
                    if ((duckX + (221 * SCALE / 2) <= clickX && duckX + (291 * SCALE / 2) >= clickX) && (clickY >= duckY + (205 * SCALE / 2) && clickY <= duckY + (275 * SCALE / 2))) {
                        if (!level4IsDuck1Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level4IsDuck1Shot = true;
                    }
                    if ((duckX2 + (221 * SCALE / 2) <= clickX && duckX2 + (291 * SCALE / 2) >= clickX) && (clickY >= duckY2 + (205 * SCALE / 2) && clickY <= duckY2 + (275 * SCALE / 2))) {
                        if (!level4IsDuck2Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level4IsDuck2Shot = true;
                    }
                    level4AmmoText.setText("Ammo Left: " + level4Ammo);
                    if (level4IsDuck1Shot) {
                        level4duck1ImageView.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                        duck1Animation4.stop();
                        if (level4IsDuck2Shot) {
                            level4duck2ImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                            duck2Animation4.stop();
                            music = new Media(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
                            levelCompletedMediaPlayer = new MediaPlayer(music);
                            levelCompletedMediaPlayer.setVolume(VOLUME);
                            levelCompletedMediaPlayer.setCycleCount(1);
                            levelCompletedMediaPlayer.play();
                            level4GameFinalText.setText("YOU WIN!");
                            level4GameFinalTextPane.setVisible(true);
                            level4FinalBlinkingText.setText("Press ENTER to play next level");
                            level4FinalBlinkingTextPane.setVisible(true);
                            gameScene4.setOnKeyPressed(event1 -> {
                                if (event1.getCode() == KeyCode.ENTER) {
                                    if (level4IsDuck1Shot && level4IsDuck2Shot) {
                                        duckFallsMediaPlayer.stop();
                                        levelCompletedMediaPlayer.stop();
                                        duck1Animation4.stop();
                                        duck2Animation4.stop();
                                        duckX = 0;
                                        duckX2 = 0;
                                        duckX3 = 0;
                                        duckY = -50 * SCALE;
                                        duckY2 = -100 * SCALE;
                                        duckY3 = -50 * SCALE;
                                        level5IsDuck1Shot = false;
                                        level5IsDuck2Shot = false;
                                        level5IsDuck3Shot = false;
                                        level5Ammo = 9;
                                        level5AmmoText.setText("Ammo Left: " + level5Ammo);
                                        isMovingRight = true;
                                        isMovingDown = false;
                                        isMovingRight1 = false;
                                        level5duck1ImageView.setImage(level5duck1Image3);
                                        level5duck2ImageView.setImage(level5duck2Image3);
                                        primaryStage.setScene(gameScene5);
                                        duck1Animation5.play();
                                        duck2Animation5.play();
                                        duck3Animation5.play();
                                        level4GameFinalTextPane.setVisible(false);
                                        level4FinalBlinkingTextPane.setVisible(false);
                                    }
                                }
                            });
                        }
                    }
                    if (level4IsDuck2Shot) {
                        level4duck2ImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                        duck2Animation4.stop();
                    }
                }
                if (level4Ammo == 0) {
                    if (!(level4IsDuck1Shot && level4IsDuck2Shot)) {
                        music = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
                        gameOverMediaPlayer = new MediaPlayer(music);
                        gameOverMediaPlayer.setVolume(VOLUME);
                        gameOverMediaPlayer.setCycleCount(1);
                        gameOverMediaPlayer.play();
                        level4GameFinalText.setText("GAME OVER!");
                        level4GameFinalTextPane.setVisible(true);
                        level4FinalBlinkingText.setText("Press ENTER to play again\n    Press ESC to exit");
                        level4FinalBlinkingTextPane.setVisible(true);
                        gameScene4.setOnKeyPressed(event1 -> {
                            if (event1.getCode() == KeyCode.ESCAPE) {
                                gameOverMediaPlayer.stop();
                                duck1Animation4.stop();
                                duck2Animation4.stop();
                                primaryStage.setScene(titleScene);
                                titleSceneMediaPlayer.play();
                                level4GameFinalTextPane.setVisible(false);
                                level4FinalBlinkingTextPane.setVisible(false);
                            } else if (event1.getCode() == KeyCode.ENTER) {
                                gameOverMediaPlayer.stop();
                                duck1Animation4.stop();
                                duck2Animation4.stop();
                                duckY = -50 * SCALE;
                                duckX = 0;
                                level1IsDuckShot = false;
                                isMovingRight = true;
                                level1Ammo = 3;
                                level1AmmoText.setText("Ammo Left: " + level1Ammo);
                                duckImageView.setImage(duckImage3);
                                primaryStage.setScene(gameScene1);
                                duckAnimation1.play();
                                level4GameFinalTextPane.setVisible(false);
                                level4FinalBlinkingTextPane.setVisible(false);
                            }
                        });
                    }
                }
            }
        });

        gameScene5.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (level5Ammo > 0 && (!level5IsDuck1Shot || !level5IsDuck2Shot || !level5IsDuck3Shot)) {
                    music = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
                    gunShotMediaPlayer = new MediaPlayer(music);
                    gunShotMediaPlayer.setVolume(VOLUME);
                    gunShotMediaPlayer.setCycleCount(1);
                    gunShotMediaPlayer.play();
                    level5Ammo--;
                    double clickX = event.getX();
                    double clickY = event.getY();
                    if ((duckX + (221 * SCALE / 2) <= clickX && duckX + (291 * SCALE / 2) >= clickX) && (clickY >= duckY + (205 * SCALE / 2) && clickY <= duckY + (275 * SCALE / 2))) {
                        if (!level5IsDuck1Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level5IsDuck1Shot = true;
                    }
                    if ((duckX2 + (221 * SCALE / 2) <= clickX && duckX2 + (291 * SCALE / 2) >= clickX) && (clickY >= duckY2 + (205 * SCALE / 2) && clickY <= duckY2 + (275 * SCALE / 2))) {
                        if (!level5IsDuck2Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level5IsDuck2Shot = true;
                    }
                    if ((duckX3 + (221 * SCALE / 2) <= clickX && duckX3 + (291 * SCALE / 2) >= clickX) && (clickY >= duckY3 + (205 * SCALE / 2) && clickY <= duckY3 + (275 * SCALE / 2))) {
                        if (!level5IsDuck3Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level5IsDuck3Shot = true;
                    }
                    level5AmmoText.setText("Ammo Left: " + level5Ammo);
                    if (level5IsDuck3Shot) {
                        level5duck3ImageView.setImage(new Image(new File("assets/duck_black/7.png").toURI().toString()));
                        duck3Animation5.stop();
                        if (level5IsDuck2Shot) {
                            level5duck2ImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                            duck2Animation5.stop();
                            if (level5IsDuck1Shot) {
                                level5duck1ImageView.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                                duck1Animation5.stop();
                                music = new Media(new File("assets/effects/LevelCompleted.mp3").toURI().toString());
                                levelCompletedMediaPlayer = new MediaPlayer(music);
                                levelCompletedMediaPlayer.setVolume(VOLUME);
                                levelCompletedMediaPlayer.setCycleCount(1);
                                levelCompletedMediaPlayer.play();
                                level5GameFinalText.setText("YOU WIN!");
                                level5GameFinalTextPane.setVisible(true);
                                level5FinalBlinkingText.setText("Press ENTER to play next level");
                                level5FinalBlinkingTextPane.setVisible(true);
                                gameScene5.setOnKeyPressed(event1 -> {
                                    if (event1.getCode() == KeyCode.ENTER) {
                                        if (level5IsDuck1Shot && level5IsDuck2Shot && level5IsDuck3Shot) {
                                            duckFallsMediaPlayer.stop();
                                            levelCompletedMediaPlayer.stop();
                                            duck1Animation5.stop();
                                            duck2Animation5.stop();
                                            duck3Animation5.stop();
                                            duckX = 70 * SCALE;
                                            duckY = -50 * SCALE;
                                            duckX2 = 0;
                                            duckY2 = -100 * SCALE;
                                            duckX3 = 0;
                                            duckY3 = -50 * SCALE;
                                            duckX4 = 0;
                                            duckY4 = 50 * SCALE;
                                            level6IsDuck1Shot = false;
                                            level6IsDuck2Shot = false;
                                            level6IsDuck3Shot = false;
                                            level6IsDuck4Shot = false;
                                            level6Ammo = 12;
                                            level6AmmoText.setText("Ammo Left: " + level6Ammo);
                                            isMovingRight = true;
                                            isMovingDown = false;
                                            isMovingRight1 = false;
                                            isMovingRight2 = true;
                                            level6duck1ImageView.setImage(level6duck1Image3);
                                            level6duck2ImageView.setImage(level6duck2Image3);
                                            level6duck3ImageView.setImage(level6duck3Image3);
                                            level6duck4ImageView.setImage(level6duck4Image3);
                                            primaryStage.setScene(gameScene6);
                                            duck1Animation6.play();
                                            duck2Animation6.play();
                                            duck3Animation6.play();
                                            duck4Animation6.play();
                                            level5GameFinalTextPane.setVisible(false);
                                            level5FinalBlinkingTextPane.setVisible(false);
                                        }
                                    }
                                });
                            }
                        }
                    }
                    if (level5IsDuck1Shot) {
                        level5duck1ImageView.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                        duck1Animation5.stop();
                    }
                    if (level5IsDuck2Shot) {
                        level5duck2ImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                        duck2Animation5.stop();
                    }
                }
                if (level5Ammo == 0) {
                    if (!(level5IsDuck1Shot && level5IsDuck2Shot && level5IsDuck3Shot)) {
                        music = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
                        gameOverMediaPlayer = new MediaPlayer(music);
                        gameOverMediaPlayer.setVolume(VOLUME);
                        gameOverMediaPlayer.setCycleCount(1);
                        gameOverMediaPlayer.play();
                        level5GameFinalText.setText("GAME OVER!");
                        level5GameFinalTextPane.setVisible(true);
                        level5FinalBlinkingText.setText("Press ENTER to play again\n    Press ESC to exit");
                        level5FinalBlinkingTextPane.setVisible(true);
                        gameScene5.setOnKeyPressed(event1 -> {
                            if (event1.getCode() == KeyCode.ESCAPE) {
                                gameOverMediaPlayer.stop();
                                duck1Animation5.stop();
                                duck2Animation5.stop();
                                duck3Animation5.stop();
                                primaryStage.setScene(titleScene);
                                titleSceneMediaPlayer.play();
                                level5GameFinalTextPane.setVisible(false);
                                level5FinalBlinkingTextPane.setVisible(false);
                            } else if (event1.getCode() == KeyCode.ENTER) {
                                gameOverMediaPlayer.stop();
                                duck1Animation5.stop();
                                duck2Animation5.stop();
                                duck3Animation5.stop();
                                duckY = -50 * SCALE;
                                duckX = 0;
                                level1IsDuckShot = false;
                                isMovingRight = true;
                                level1Ammo = 3;
                                level1AmmoText.setText("Ammo Left: " + level1Ammo);
                                duckImageView.setImage(duckImage3);
                                primaryStage.setScene(gameScene1);
                                duckAnimation1.play();
                                level5GameFinalTextPane.setVisible(false);
                                level5FinalBlinkingTextPane.setVisible(false);
                            }
                        });
                    }
                }
            }
        });

        gameScene6.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (level6Ammo > 0 && (!level6IsDuck1Shot || !level6IsDuck2Shot || !level6IsDuck3Shot || !level6IsDuck4Shot)) {
                    music = new Media(new File("assets/effects/Gunshot.mp3").toURI().toString());
                    gunShotMediaPlayer = new MediaPlayer(music);
                    gunShotMediaPlayer.setVolume(VOLUME);
                    gunShotMediaPlayer.setCycleCount(1);
                    gunShotMediaPlayer.play();
                    level6Ammo--;
                    double clickX = event.getX();
                    double clickY = event.getY();
                    if ((duckX + (221 * SCALE / 2) <= clickX && duckX + (291 * SCALE / 2) >= clickX) && (clickY >= duckY + (205 * SCALE / 2) && clickY <= duckY + (275 * SCALE / 2))) {
                        if (!level6IsDuck1Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level6IsDuck1Shot = true;
                    }
                    if ((duckX2 + (221 * SCALE / 2) <= clickX && duckX2 + (291 * SCALE / 2) >= clickX) && (clickY >= duckY2 + (205 * SCALE / 2) && clickY <= duckY2 + (275 * SCALE / 2))) {
                        if (!level6IsDuck2Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level6IsDuck2Shot = true;
                    }
                    if ((duckX3 + (221 * SCALE / 2) <= clickX && duckX3 + (291 * SCALE / 2) >= clickX) && (clickY >= duckY3 + (205 * SCALE / 2) && clickY <= duckY3 + (275 * SCALE / 2))) {
                        if (!level6IsDuck3Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level6IsDuck3Shot = true;
                    }
                    if ((duckX4 + (221 * SCALE / 2) <= clickX && duckX4 + (291 * SCALE / 2) >= clickX) && (clickY >= duckY4 + (205 * SCALE / 2) && clickY <= duckY4 + (275 * SCALE / 2))) {
                        if (!level6IsDuck4Shot) {
                            music = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
                            duckFallsMediaPlayer = new MediaPlayer(music);
                            duckFallsMediaPlayer.setVolume(VOLUME);
                            duckFallsMediaPlayer.setCycleCount(1);
                            duckFallsMediaPlayer.play();
                        }
                        level6IsDuck4Shot = true;
                    }
                    level6AmmoText.setText("Ammo Left: " + level6Ammo);
                    if (level6IsDuck4Shot) {
                        level6duck4ImageView.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                        duck4Animation6.stop();
                        if (level6IsDuck3Shot) {
                            level6duck3ImageView.setImage(new Image(new File("assets/duck_black/7.png").toURI().toString()));
                            duck3Animation6.stop();
                            if (level6IsDuck2Shot) {
                                level6duck2ImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                                duck2Animation6.stop();
                                if (level6IsDuck1Shot) {
                                    level6duck1ImageView.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                                    duck1Animation6.stop();
                                    music = new Media(new File("assets/effects/GameCompleted.mp3").toURI().toString());
                                    gameCompletedMediaPlayer = new MediaPlayer(music);
                                    gameCompletedMediaPlayer.setVolume(VOLUME);
                                    gameCompletedMediaPlayer.setCycleCount(1);
                                    gameCompletedMediaPlayer.play();
                                    level6GameFinalText.setText("You have completed the game!");
                                    level6GameFinalTextPane.setVisible(true);
                                    level6FinalBlinkingText.setText("Press ENTER to play again\n    Press ESC to exit");
                                    level6FinalBlinkingTextPane.setVisible(true);
                                    gameScene6.setOnKeyPressed(event1 -> {
                                        if (event1.getCode() == KeyCode.ENTER) {
                                            if (level6IsDuck1Shot && level6IsDuck2Shot && level6IsDuck3Shot && level6IsDuck4Shot) {
                                                duckFallsMediaPlayer.stop();
                                                gameCompletedMediaPlayer.stop();
                                                duck1Animation6.stop();
                                                duck2Animation6.stop();
                                                duck3Animation6.stop();
                                                duck4Animation6.stop();
                                                duckY = -50 * SCALE;
                                                duckX = 0;
                                                level1IsDuckShot = false;
                                                isMovingRight = true;
                                                level1Ammo = 3;
                                                level1AmmoText.setText("Ammo Left: " + level1Ammo);
                                                duckImageView.setImage(duckImage3);
                                                primaryStage.setScene(gameScene1);
                                                duckAnimation1.play();
                                                level6GameFinalTextPane.setVisible(false);
                                                level6FinalBlinkingTextPane.setVisible(false);
                                            }
                                        } else if (event1.getCode() == KeyCode.ESCAPE) {
                                            duckFallsMediaPlayer.stop();
                                            gameCompletedMediaPlayer.stop();
                                            primaryStage.setScene(titleScene);
                                            titleSceneMediaPlayer.play();
                                            level6GameFinalTextPane.setVisible(false);
                                            level6FinalBlinkingTextPane.setVisible(false);
                                        }
                                    });
                                }
                            }
                        }
                    }
                    if (level6IsDuck1Shot) {
                        level6duck1ImageView.setImage(new Image(new File("assets/duck_blue/7.png").toURI().toString()));
                        duck1Animation6.stop();
                    }
                    if (level6IsDuck3Shot) {
                        level6duck3ImageView.setImage(new Image(new File("assets/duck_black/7.png").toURI().toString()));
                        duck3Animation6.stop();
                    }
                    if (level6IsDuck2Shot) {
                        level6duck2ImageView.setImage(new Image(new File("assets/duck_red/7.png").toURI().toString()));
                        duck2Animation6.stop();
                    }
                }
                if (level6Ammo == 0) {
                    if (!(level6IsDuck1Shot && level6IsDuck2Shot && level6IsDuck3Shot && level6IsDuck4Shot)) {
                        music = new Media(new File("assets/effects/GameOver.mp3").toURI().toString());
                        gameOverMediaPlayer = new MediaPlayer(music);
                        gameOverMediaPlayer.setVolume(VOLUME);
                        gameOverMediaPlayer.setCycleCount(1);
                        gameOverMediaPlayer.play();
                        level6GameFinalText.setText("GAME OVER!");
                        level6GameFinalTextPane.setVisible(true);
                        level6FinalBlinkingText.setText("Press ENTER to play again\n    Press ESC to exit");
                        level6FinalBlinkingTextPane.setVisible(true);
                        gameScene6.setOnKeyPressed(event1 -> {
                            if (event1.getCode() == KeyCode.ESCAPE) {
                                gameOverMediaPlayer.stop();
                                duck1Animation6.stop();
                                duck2Animation6.stop();
                                duck3Animation6.stop();
                                duck4Animation6.stop();
                                primaryStage.setScene(titleScene);
                                titleSceneMediaPlayer.play();
                                level6GameFinalTextPane.setVisible(false);
                                level6FinalBlinkingTextPane.setVisible(false);
                            } else if (event1.getCode() == KeyCode.ENTER) {
                                gameOverMediaPlayer.stop();
                                duck1Animation6.stop();
                                duck2Animation6.stop();
                                duck3Animation6.stop();
                                duck4Animation6.stop();
                                duckY = -50 * SCALE;
                                duckX = 0;
                                level1IsDuckShot = false;
                                isMovingRight = true;
                                level1Ammo = 3;
                                level1AmmoText.setText("Ammo Left: " + level1Ammo);
                                duckImageView.setImage(duckImage3);
                                primaryStage.setScene(gameScene1);
                                duckAnimation1.play();
                                level6GameFinalTextPane.setVisible(false);
                                level6FinalBlinkingTextPane.setVisible(false);
                            }
                        });
                    }
                }
            }
        });
    }
    private StackPane createPaddingPane(StackPane contentPane, double top, double bottom, double right, double left) {
        StackPane paddingPane = new StackPane(contentPane);
        paddingPane.setPadding(new javafx.geometry.Insets(top, right, bottom, left));
        return paddingPane;
    }

    // Switches between three images for duck animation
    private void switchImage1(Image first, Image second, Image third, ImageView duck) {
        if (currentImageIndex1 == 0) {
            duck.setImage(second);
            currentImageIndex1 = 1;
        } else if (currentImageIndex1 == 1) {
            duck.setImage(third);
            currentImageIndex1 = 2;
        } else if (currentImageIndex1 == 2) {
            duck.setImage(first);
            currentImageIndex1 = 0;
        }
    }

    // Switches between three images for duck animation
    private void switchImage2(Image first, Image second, Image third, ImageView duck) {
        if (currentImageIndex2 == 0) {
            duck.setImage(second);
            currentImageIndex2 = 1;
        } else if (currentImageIndex2 == 1) {
            duck.setImage(third);
            currentImageIndex2 = 2;
        } else if (currentImageIndex2 == 2) {
            duck.setImage(first);
            currentImageIndex2 = 0;
        }
    }

    // Defines horizontal duck animation for levels (same functions for different ducks)
    private Timeline duckAnimationH1(Image duckImage1, Image duckImage2, Image duckImage3, ImageView duckImageView) {
        Timeline duckAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    duckImageView.setImage(duckImage1);
                    if (isMovingRight) {
                        duckX += 5 * SCALE;
                        switchImage1(duckImage1, duckImage2, duckImage3, duckImageView);
                        duckImageView.setScaleX(1);
                    } else {
                        duckX -= 5 * SCALE;
                        switchImage1(duckImage1, duckImage2, duckImage3, duckImageView);
                        duckImageView.setScaleX(-1);
                    }
                    duckImageView.setTranslateX(duckX);
                    if (duckX <= (-230 * SCALE) / 2 || duckX >= (230 * SCALE) / 2) {
                        isMovingRight = !isMovingRight;
                    }
                })
        );
        return duckAnimation;
    }

    // Defines cross duck animation for levels (same functions for different ducks)
    private Timeline duckAnimationC1(Image level2duckImage1, Image level2duckImage2, Image level2duckImage3, ImageView level2duckImageView){
        Timeline duckAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    level2duckImageView.setImage(level2duckImage1);
                    if (isMovingRight && isMovingDown) {
                        duckX += 5 * SCALE;
                        duckY += 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(1);
                        level2duckImageView.setScaleY(-1);
                    } else if (!isMovingRight && isMovingDown){
                        duckX -= 5 * SCALE;
                        duckY += 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(-1);
                        level2duckImageView.setScaleY(-1);
                    } else if (isMovingRight && !isMovingDown){
                        duckX += 5 * SCALE;
                        duckY -= 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(1);
                        level2duckImageView.setScaleY(1);
                    } else{
                        duckX -= 5 * SCALE;
                        duckY -= 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(-1);
                        level2duckImageView.setScaleY(1);
                    }
                    level2duckImageView.setTranslateX(duckX);
                    level2duckImageView.setTranslateY(duckY);
                    if (duckX <= (-235 * SCALE) / 2 || duckX >= (235 * SCALE) / 2){
                        isMovingRight = !isMovingRight;
                    }
                    if (duckY <= (-215 * SCALE / 2) || duckY >= (215 * SCALE / 2)){
                        isMovingDown = !isMovingDown;
                    }
                })
        );
        return duckAnimation;
    }
    private Timeline duckAnimationH2(Image duckImage1, Image duckImage2, Image duckImage3, ImageView duckImageView) {
        Timeline duckAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    duckImageView.setImage(duckImage1);
                    if (isMovingRight1) {
                        duckX2 += 5 * SCALE;
                        switchImage1(duckImage1, duckImage2, duckImage3, duckImageView);
                        duckImageView.setScaleX(1);
                    } else {
                        duckX2 -= 5 * SCALE;
                        switchImage1(duckImage1, duckImage2, duckImage3, duckImageView);
                        duckImageView.setScaleX(-1);
                    }
                    duckImageView.setTranslateX(duckX2);
                    if (duckX2 <= (-230 * SCALE) / 2 || duckX2 >= (230 * SCALE) / 2) {
                        isMovingRight1 = !isMovingRight1;
                    }
                })
        );
        return duckAnimation;
    }
    private Timeline duckAnimationC2(Image level2duckImage1, Image level2duckImage2, Image level2duckImage3, ImageView level2duckImageView){
        Timeline duckAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    level2duckImageView.setImage(level2duckImage1);
                    if (isMovingRight1 && isMovingDown1) {
                        duckX2 += 5 * SCALE;
                        duckY2 += 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(1);
                        level2duckImageView.setScaleY(-1);
                    } else if (!isMovingRight1 && isMovingDown1){
                        duckX2 -= 5 * SCALE;
                        duckY2 += 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(-1);
                        level2duckImageView.setScaleY(-1);
                    } else if (isMovingRight1 && !isMovingDown1){
                        duckX2 += 5 * SCALE;
                        duckY2 -= 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(1);
                        level2duckImageView.setScaleY(1);
                    } else{
                        duckX2 -= 5 * SCALE;
                        duckY2 -= 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(-1);
                        level2duckImageView.setScaleY(1);
                    }
                    level2duckImageView.setTranslateX(duckX2);
                    level2duckImageView.setTranslateY(duckY2);
                    if (duckX2 <= (-235 * SCALE) / 2 || duckX2 >= (235 * SCALE) / 2){
                        isMovingRight1 = !isMovingRight1;
                    }
                    if (duckY2 <= (-215 * SCALE / 2) || duckY2 >= (215 * SCALE / 2)){
                        isMovingDown1 = !isMovingDown1;
                    }
                })
        );
        return duckAnimation;
    }
    private Timeline duckAnimationH3(Image duckImage1, Image duckImage2, Image duckImage3, ImageView duckImageView) {
        Timeline duckAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    duckImageView.setImage(duckImage1);
                    if (isMovingRight2) {
                        duckX3 += 5 * SCALE;
                        switchImage1(duckImage1, duckImage2, duckImage3, duckImageView);
                        duckImageView.setScaleX(1);
                    } else {
                        duckX3 -= 5 * SCALE;
                        switchImage1(duckImage1, duckImage2, duckImage3, duckImageView);
                        duckImageView.setScaleX(-1);
                    }
                    duckImageView.setTranslateX(duckX3);
                    if (duckX3 <= (-230 * SCALE) / 2 || duckX3 >= (230 * SCALE) / 2) {
                        isMovingRight2 = !isMovingRight2;
                    }
                })
        );
        return duckAnimation;
    }
    private Timeline duckAnimationC3(Image level2duckImage1, Image level2duckImage2, Image level2duckImage3, ImageView level2duckImageView){
        Timeline duckAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    level2duckImageView.setImage(level2duckImage1);
                    if (isMovingRight2 && isMovingDown2) {
                        duckX3 += 5 * SCALE;
                        duckY3 += 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(1);
                        level2duckImageView.setScaleY(-1);
                    } else if (!isMovingRight2 && isMovingDown2){
                        duckX3 -= 5 * SCALE;
                        duckY3 += 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(-1);
                        level2duckImageView.setScaleY(-1);
                    } else if (isMovingRight2 && !isMovingDown2){
                        duckX3 += 5 * SCALE;
                        duckY3 -= 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(1);
                        level2duckImageView.setScaleY(1);
                    } else{
                        duckX3 -= 5 * SCALE;
                        duckY3 -= 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(-1);
                        level2duckImageView.setScaleY(1);
                    }
                    level2duckImageView.setTranslateX(duckX3);
                    level2duckImageView.setTranslateY(duckY3);
                    if (duckX3 <= (-235 * SCALE) / 2 || duckX3 >= (235 * SCALE) / 2){
                        isMovingRight2 = !isMovingRight2;
                    }
                    if (duckY3 <= (-215 * SCALE / 2) || duckY3 >= (215 * SCALE / 2)){
                        isMovingDown2 = !isMovingDown2;
                    }
                })
        );
        return duckAnimation;
    }
    private Timeline duckAnimationH4(Image duckImage1, Image duckImage2, Image duckImage3, ImageView duckImageView) {
        Timeline duckAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    duckImageView.setImage(duckImage1);
                    if (isMovingRight3) {
                        duckX4 += 5 * SCALE;
                        switchImage1(duckImage1, duckImage2, duckImage3, duckImageView);
                        duckImageView.setScaleX(1);
                    } else {
                        duckX4 -= 5 * SCALE;
                        switchImage1(duckImage1, duckImage2, duckImage3, duckImageView);
                        duckImageView.setScaleX(-1);
                    }
                    duckImageView.setTranslateX(duckX4);
                    if (duckX4 <= (-230 * SCALE) / 2 || duckX4 >= (230 * SCALE) / 2) {
                        isMovingRight3 = !isMovingRight3;
                    }
                })
        );
        return duckAnimation;
    }
    private Timeline duckAnimationC4(Image level2duckImage1, Image level2duckImage2, Image level2duckImage3, ImageView level2duckImageView){
        Timeline duckAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    level2duckImageView.setImage(level2duckImage1);
                    if (isMovingRight3 && isMovingDown3) {
                        duckX4 += 5 * SCALE;
                        duckY4 += 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(1);
                        level2duckImageView.setScaleY(-1);
                    } else if (!isMovingRight3 && isMovingDown3){
                        duckX4 -= 5 * SCALE;
                        duckY4 += 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(-1);
                        level2duckImageView.setScaleY(-1);
                    } else if (isMovingRight3 && !isMovingDown3){
                        duckX4 += 5 * SCALE;
                        duckY4 -= 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(1);
                        level2duckImageView.setScaleY(1);
                    } else{
                        duckX4 -= 5 * SCALE;
                        duckY4 -= 5 * SCALE;
                        switchImage2(level2duckImage1, level2duckImage2, level2duckImage3, level2duckImageView);
                        level2duckImageView.setScaleX(-1);
                        level2duckImageView.setScaleY(1);
                    }
                    level2duckImageView.setTranslateX(duckX4);
                    level2duckImageView.setTranslateY(duckY4);
                    if (duckX4 <= (-235 * SCALE) / 2 || duckX4 >= (235 * SCALE) / 2){
                        isMovingRight3 = !isMovingRight3;
                    }
                    if (duckY4 <= (-215 * SCALE / 2) || duckY4 >= (215 * SCALE / 2)){
                        isMovingDown3 = !isMovingDown3;
                    }
                })
        );
        return duckAnimation;
    }
}