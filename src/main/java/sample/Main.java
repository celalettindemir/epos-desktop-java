package sample;

import Rest.ServiceConfiguration;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Boolean serviceState=ServiceConfiguration.checkWS();
        if(serviceState) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
            //primaryStage.setTitle("Hello World");
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            // scene.setFill(Color.TRANSPARENT);
            //  primaryStage.initStyle(StageStyle.TRANSPARENT);
            //primaryStage.setMaximized(true);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(800);
            primaryStage.show();
        }
        else
            System.exit(1);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
