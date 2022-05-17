package Home.Settings;

import Home.HomeController;
import Home.Menu.MenuController;
import Home.Sides.SidesController;
import Home.Stock.StockController;
import Home.Table.TableController;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsController {

    private Stage stage;
    private AnchorPane MainFrame;

    @FXML
    private JFXTabPane SettingsTabPane;

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
        addMenuTab();
        addTableTab();
       // addSidesTab();
        addStockTab();

    }


    private void addStockTab()
    {

        Tab tab = new Tab();
        tab.setText("Stock");


        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Stock.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            StockController stockController=root.getController();

            stockController.useOnLoad(MainFrame,stage);

            tab.setContent(loader);




        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SettingsTabPane.getTabs().add(tab);
    }

    private void addTableTab()
    {

        Tab tab = new Tab();
        tab.setText("Masa");


        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Table.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            TableController tableController=root.getController();

            tableController.useOnLoad(MainFrame,stage);

            tab.setContent(loader);




        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SettingsTabPane.getTabs().add(tab);
    }


    private void addMenuTab()
    {

        Tab tab = new Tab();
        tab.setText("Menu");


        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Menu.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            MenuController menuController=root.getController();

            menuController.useOnLoad(MainFrame,stage);

            tab.setContent(loader);




        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SettingsTabPane.getTabs().add(tab);
    }

    private void addSidesTab()
    {
        Tab tab = new Tab();
        tab.setText("Sides");


        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Sides.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            SidesController sidesController=root.getController();

            sidesController.useOnLoad(MainFrame,stage);

            tab.setContent(loader);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SettingsTabPane.getTabs().add(tab);
    }
}
