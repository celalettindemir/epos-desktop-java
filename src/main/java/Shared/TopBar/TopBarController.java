package Shared.TopBar;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class TopBarController {

    private Stage stage;
    private AnchorPane MainFrame;

    @FXML
    private HBox TopBarFrame;
    @FXML
    private JFXButton LogOut;


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


        LogOut.onActionProperty().setValue(event -> LogOutFun());

    }


    public void LogOutFun() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
            //primaryStage.setTitle("Hello World");
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            // scene.setFill(Color.TRANSPARENT);
            //primaryStage.initStyle(StageStyle.TRANSPARENT);
            stage.show();



        }catch (IOException err)
        {
            System.out.println(err.getMessage());
        }
        catch (NullPointerException err2){
            System.out.println(err2.getMessage());
            System.out.println(err2.getMessage());
        }
      //  stage.setMaximized(false);
      //  stage.setMaximized(true);
    }

}
