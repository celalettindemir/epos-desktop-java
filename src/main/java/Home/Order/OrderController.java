package Home.Order;

import Home.HomeController;
import OrderSelection.OrderSelectionController;
import Tables.TableButtons.TableButtonsController;
import Tables.TablesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderController {

    private Stage stage;
    private AnchorPane MainFrame;

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

    public void OpenTablePage()
    {
        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Tables.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            TablesController tablescontroller=root.getController();

            tablescontroller.useOnLoad(MainFrame,stage);

            MainFrame.getChildren().remove(1);
            MainFrame.getChildren().add(loader);



        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void OpenTakeaway()
    {
        System.out.println(1);

        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("OrderSelection.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            OrderSelectionController orderselectioncontroller=root.getController();


            orderselectioncontroller.useOnLoad(MainFrame,stage,0,true);

            MainFrame.getChildren().remove(1);
            MainFrame.getChildren().add(loader);


        }
        catch (IOException e){

        }
        catch (IllegalStateException er){}
    }

}
