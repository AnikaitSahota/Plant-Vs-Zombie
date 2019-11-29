package sample;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Random;

public class Controller {
    @FXML
    private Label login_label , numSun ;
    @FXML
    private TextField UserName ;
    @FXML
    private PasswordField Passcode ;
    @FXML
    private ImageView zombie;
    @FXML
    private AnchorPane GamePagePane ;
    @FXML
    private GridPane garden ;

    private Stage primaryStage = null ;
    private Node node = null ;
    private back_end_controler be_ctrl = new back_end_controler() ;

    public void showSettingWindow(ActionEvent event) throws Exception {
        Stage settingWindow = new Stage() ;
        settingWindow.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("SettingWindow.fxml"));
        settingWindow.getIcons().add(new Image(new FileInputStream("src/images/Plants-vs-Zombies-icon.png")));
        settingWindow.setTitle("Plant Vs Zombie Setting");
        Scene firstScene = new Scene(root) ;
        settingWindow.setScene(firstScene);
        settingWindow.showAndWait();
    }
    public void showOptionMenu(ActionEvent event) throws Exception {
        Stage MenuWindow = new Stage() ;
        MenuWindow.initModality(Modality.APPLICATION_MODAL);
//        Parent root = FXMLLoader.load(getClass().getResource("SettingWindow.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("OptionMenu.fxml"));
        MenuWindow.getIcons().add(new Image(new FileInputStream("src/images/Plants-vs-Zombies-icon.png")));
        MenuWindow.setTitle("Plant Vs Zombie Option Menu");
        Scene MenuScene = new Scene(root) ;
        MenuWindow.setScene(MenuScene);
        MenuWindow.showAndWait();

    }
    public void showLogInScene(ActionEvent event) throws Exception {
        if(primaryStage == null)    primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene firstScene = new Scene(root) ;
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }
    public void exitGame(ActionEvent event) {
        if(primaryStage == null)    primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    public void showMainScene(ActionEvent event) throws Exception {
        if(primaryStage == null)    primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene firstScene = new Scene(root) ;
        primaryStage.setScene(firstScene);
        primaryStage.show() ;
    }
    private boolean checkCredentials(String name , String passcode) {
        if(name.equals("Anikait") && passcode.equals("asdf")) return true;
        // TODO: make a valid crediantial checker
        return false ;
    }
    public void logInHandler(ActionEvent event) throws Exception{
        if(!checkCredentials(UserName.getText() , Passcode.getText()))  login_label.setText("Wrong Crediantials");
        else {
            login_label.setText("Hi Anikait");
            showGameMenu() ;
        }
    }
    private void showGameMenu() throws Exception{
        try {
            if(primaryStage == null)    primaryStage = (Stage) ((Node)login_label).getScene().getWindow();
        }
        catch(Exception e) {
            primaryStage = (Stage) ((Node)GamePagePane).getScene().getWindow() ;
        }
        Parent root = FXMLLoader.load(getClass().getResource("GameMenu.fxml"));
        Scene GameMenuScene = new Scene(root) ;
        primaryStage.setScene(GameMenuScene);
        primaryStage.show() ;
    }
    public void showNewGame(ActionEvent event) throws Exception{
        if(primaryStage == null)    primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
        Scene GamePageScene = new Scene(root) ;
        primaryStage.setScene(GamePageScene);
        primaryStage.show() ;
    }
    public void plantDraged(MouseEvent event){
//        System.out.println(event.getSource());
        node = (Node)event.getSource() ;
//        numSun.setText("0"+numSun.getText()) ;
        node.setCursor(Cursor.MOVE) ;
        event.consume();
    }
    public void plantDrop(MouseEvent event) throws Exception{
        setplant(((Node)(event.getSource())).getId(),event.getSceneX()-20,event.getSceneY()-20);
        node.setCursor(Cursor.DEFAULT) ;
    }
    private String getPlantImgLoc(String ID) {
        if(ID.equals("beetroot")) return "src/images/Plants/beetroot.gif" ;
        if(ID.equals("peashooter")) return "src/images/Plants/pea_shooter.gif" ;
        if(ID.equals("sunflower")) return "src/images/Plants/sun_flower.gif" ;
        if(ID.equals("walnut")) return "src/images/Plants/walnut_full_life.gif" ;
        return "null" ;
    }
    private void setplant(String ID , double X , double Y) throws Exception{
        ImageView plant = new ImageView(new Image(new FileInputStream(getPlantImgLoc(ID)))) ;
        if(ID.equals("peashooter"))
            plant.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> shoot_pea(event));

        Point p = gridIndex(X,Y) ;
        if(p != null) {
            garden.add(plant, (int) p.getY(), (int) p.getX()); // sending in format of Column and Row ie. Column major format
            be_ctrl.addPlant(plant , ID);
        }
        return;
    }
    private Point gridIndex(double X , double Y ) {
        Point gridPt = new Point() ;    // gritPt represent the row and column of the grid plane
        if(X <= 330.0 || X >= 1335.0 || Y <= 112.0 || Y >= 918.0) return null ;

        // x --> Row index
        if(X< 438.0)   gridPt.y = 0 ;
        else if(X < 551.0) gridPt.y = 1 ;
        else if(X < 671.0) gridPt.y = 2 ;
        else if(X < 769.0) gridPt.y = 3 ;
        else if(X < 879.0) gridPt.y = 4 ;
        else if(X < 993.0) gridPt.y = 5 ;
        else if(X < 1047.0) gridPt.y = 6 ;
        else if(X < 1220.0) gridPt.y = 7 ;
        else gridPt.y = 8 ;

        // y --> column index ;
        if(Y < 273) gridPt.x = 0 ;
        else if(Y < 433.0)  gridPt.x = 1 ;
        else if(Y < 605.0)  gridPt.x = 2 ;
        else if(Y < 759.0)  gridPt.x = 3 ;
        else gridPt.x = 4 ;

        for(Node imageview : garden.getChildren()) {
            if(GridPane.getRowIndex(imageview) == null ) continue;
            if (GridPane.getRowIndex(imageview) == (int) gridPt.getX() && GridPane.getColumnIndex(imageview) == (int) gridPt.getY())
                return null;
        }

        return gridPt ;
    }

    public void startTransion(MouseEvent event) {
        TranslateTransition transition = new TranslateTransition() ;
        transition.setDuration(Duration.seconds(20));
        transition.setToX(-1000);
//        transition.setAutoReverse(false);
//        transition.setCycleCount(Animation.INDEFINITE);
        transition.setNode((Node) zombie);
        transition.play() ;
    }
    public void dropSun(ActionEvent event) throws Exception{
        TranslateTransition transition = new TranslateTransition() ;
        transition.setDuration(Duration.seconds(10));
        transition.setToY((600 * new Random().nextDouble())+ 200 );
//        transition.setDuration(Duration.seconds(2));
        transition.setAutoReverse(false);

        ImageView sun = new ImageView(new Image(new FileInputStream( "src/images/sun.gif"  ))) ;
        sun.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->  catchSun(event1) ) ;
        GamePagePane.getChildren().add(sun) ;
        sun.setX((1000 * new Random().nextDouble())+300);sun.setY(0);

        transition.setNode((Node) sun);

        transition.play() ;
    }
    public void catchSun(MouseEvent event){
        ImageView sun_token = (ImageView)event.getSource() ;
        if(sun_token.getImage() == null) return;
        int n = Integer.parseInt(numSun.getText()) ;
        numSun.setText(Integer.toString(n+1));
        sun_token.setImage(null);
    }
    public void showGameMenu(ActionEvent event) throws Exception{
        showGameMenu();
    }
    private void shoot_pea(MouseEvent event) {
        try {
            ImageView peashooter = (ImageView)(event.getSource()) ;
            ImageView pea = new ImageView(new Image(new FileInputStream("src/images/Pea.png"))) ;

            TranslateTransition transition = new TranslateTransition() ;
            transition.setDuration(Duration.seconds(10));
            transition.setToX(850);
            transition.setAutoReverse(false);

            pea.setX(peashooter.getLayoutX() + garden.getLayoutX() +65);
            pea.setY(peashooter.getLayoutY() + garden.getLayoutY() +16);
            GamePagePane.getChildren().add(pea) ;

            transition.setNode((Node) pea);
            transition.play() ;
        }
        catch (Exception e) {
            System.out.println("Exception caught during shooting pea :\n"+e);
        }

    }
}
