package Home.Sides;

import Home.Sides.NewSide.NewSideController;
import Home.Table.TableController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SidesController {


    private Stage stage;
    private AnchorPane MainFrame;

    @FXML
    private  JFXMasonryPane SideParent;
    @FXML
    private JFXTextField addSideText;
    @FXML
    private JFXTextField addSidePrice;

    @FXML
    private VBox PopupBlur;
    @FXML
    private HBox PopUpFrame;





    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void useOnLoad(AnchorPane MainFrame, Stage stage)
    {
        setMainFrame(MainFrame);
        setStage(stage);




    }


    public void addSide()
    {



        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("NewSide.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            NewSideController newSideController=root.getController();

            newSideController.useOnLoad(MainFrame,stage,PopupBlur,PopUpFrame);
            PopUpFrame.getChildren().clear();
            PopUpFrame.getChildren().add(loader);



        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PopupBlur.setVisible(true);
        PopUpFrame.setVisible(true);


       /* JFXMasonryPane jfxMasonryPane=  SideParent;



        Random r =new Random();

            JFXButton btn =new JFXButton();
            btn.setText( addSideText.getText()+"\n"+addSidePrice.getText());
            double wid =75;
            double he =75;
            btn.setPrefSize(wid,he);
            btn.setMinSize(wid,he);
            btn.setStyle("-fx-background-color:rgb(" +r.nextInt(255)+ ","+r.nextInt(255)+","+r.nextInt(255)+");");
            jfxMasonryPane.getChildren().add(btn);*/


    }

    public void deleteSide()
    {

    }
    public void editSide()
    {

    }




}
