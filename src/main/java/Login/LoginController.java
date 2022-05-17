package Login;

import Home.HomeController;
import Shared.SharedTopBar.SharedTopBarController;
import Shared.TopBar.TopBarController;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LoginController {

    @FXML
    private Pane Sidebar;

    @FXML
    private VBox LoginPlace;

    @FXML
    private VBox WelcomeText;

    @FXML
    private AnchorPane MainFrame;




    public void initialize() {
        WelcomeText.setVisible(false);




    }



    @FXML
    private void loginClicked(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Login(stage);
      /*  TranslateTransition t =new TranslateTransition(Duration.seconds(1),Sidebar);
        t.setByX(-713);
        t.play();*/
       /* RotateTransition t2 =new RotateTransition(Duration.seconds(1),Sidebar);
        t2.setByAngle(90);
        t2.play();
        ScaleTransition t3 =new ScaleTransition(Duration.seconds(1),Sidebar);
        t3.setToX(1.5);
        t3.play();*/


       /*
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), LoginPlace);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();

        ft.setOnFinished(
                event1 -> {
                    RotateTransition t2 =new RotateTransition(Duration.seconds(1),Sidebar);
                    t2.setByAngle(90);
                    t2.play();
                    ScaleTransition t3 =new ScaleTransition(Duration.seconds(1),Sidebar);
                    t3.setToX(1.5);
                    t3.play();
                    t3.setOnFinished(
                            event2 -> {

                                FadeTransition ft2 = new FadeTransition(Duration.seconds(1.5), WelcomeText.getParent());
                                ft2.setFromValue(0.0);
                                ft2.setToValue(1.0);
                                WelcomeText.setVisible(true);
                                ft2.play();

                                ft2.setOnFinished(
                                        event3 -> {
                                            ScaleTransition s2 =new ScaleTransition(Duration.seconds(0.2),Sidebar);
                                            s2.setToX(0);
                                            s2.play();
                                            s2.setOnFinished(
                                                    event4 -> {

                                                        Login(stage);


                                                    }
                                            );
                                        }
                                );
                            }
                    );
                }
        );*/

    }

    public void Login(Stage stage){
        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Home.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            HomeController homecontroller=root.getController();

            homecontroller.useOnLoad(MainFrame,stage);


            FXMLLoader root2 =new FXMLLoader(getClass().getClassLoader().getResource("SharedTopBar.fxml"));
            Parent loader2 = root2.load();
            SharedTopBarController sharedTopBarController = root2.getController();

            sharedTopBarController.useOnLoad(MainFrame,stage);

            MainFrame.getChildren().remove(0,MainFrame.getChildren().size());


            MainFrame.getChildren().add(loader2);

            MainFrame.getChildren().add(loader);




        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
