package Shared.SharedTopBar;

import Home.HomeController;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SharedTopBarController {


    double x,y;
    Boolean isdraged =false;

    private Stage stage;
    private AnchorPane MainFrame;

    @FXML
    private AnchorPane TopBarFrame;


    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void useOnLoad(AnchorPane MainFrame,Stage stage)
    {
        setMainFrame(MainFrame);
        setStage(stage);

        this.MainFrame.setTopAnchor(TopBarFrame, 0.0);
        this.MainFrame.setLeftAnchor(TopBarFrame, 0.0);
        this.MainFrame.setRightAnchor(TopBarFrame, 0.0);


       // LogOut.onActionProperty().setValue(event -> LogOutFun());

    }


    public void LogOutFun()  throws Exception{

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setMaximized(false);


    }


    @FXML
    void Dragged(MouseEvent event) {
            stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);

        isdraged=true;
    }

    @FXML
    void Pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();

    }

    @FXML
    void MinApp(MouseEvent event) {
        stage.setIconified(true);

    }
    @FXML
    void MaxApp(MouseEvent event) {

                if(stage.isMaximized()){
                    stage.setMaximized(false);
                }else{
                    stage.setMaximized(true);
                    stage.setX(0);
                    stage.setY(0);
                    stage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().width);

                    Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
                    Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

                    int taskBarHeight = scrnSize.height - winSize.height;
                    stage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().height-taskBarHeight);
                }

    }

    @FXML
    void Keyboard(MouseEvent event) {


        try{
            Runtime.getRuntime().exec("cmd /c osk");

        }catch (Exception e){

        }

    }

    @FXML
    void Home(MouseEvent event) {

        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Home.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            HomeController homecontroller=root.getController();

            homecontroller.useOnLoad(MainFrame,stage);

            MainFrame.getChildren().remove(1);
            MainFrame.getChildren().add( 1,loader);




        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    void CloseApp(MouseEvent event) {
        stage.close();

    }



    @FXML
    void Released(MouseEvent event) {

        if(isdraged==true){
            isdraged=false;
            stage.setMaximized(false);

        }

    }




}
