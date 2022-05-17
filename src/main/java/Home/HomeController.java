package Home;


import Home.Order.OrderController;
import Home.Settings.SettingsController;
import Tables.TablesController;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController {


    private Stage stage;
    private AnchorPane MainFrame;

    @FXML
    private AnchorPane HomePageFrame;

    @FXML
    private VBox PopupBlur;
    @FXML
    private HBox PopUpFrame;

    @FXML
    private VBox editProduct;

    @FXML
    private VBox newProduct;
    @FXML
    private JFXTextField addMenuText;
    @FXML
    private JFXTabPane MenuTabPane;
    @FXML
    private JFXTabPane OptionsTabPane;










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

        this.MainFrame.setTopAnchor(HomePageFrame, 40.0);
        this.MainFrame.setLeftAnchor(HomePageFrame, 0.0);
        this.MainFrame.setRightAnchor(HomePageFrame, 0.0);
        this.MainFrame.setBottomAnchor(HomePageFrame,0.0);




        addOrderTab();
        addSettingsTab();


    }



    private void addOrderTab()
    {

        Tab tab = new Tab();
        tab.setText("Sipariş");


        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Order.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            OrderController orderController=root.getController();

            orderController.useOnLoad(MainFrame,stage);

            tab.setContent(loader);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        OptionsTabPane.getTabs().add(tab);
    }

    private void addSettingsTab()
    {
        Tab tab = new Tab();
        tab.setText("Ayarlar");


        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("Settings.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            SettingsController settingsController=root.getController();

            settingsController.useOnLoad(MainFrame,stage);

            tab.setContent(loader);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        OptionsTabPane.getTabs().add(tab);
    }


    public void OpenPopup()
    {
        PopUpFrame.setVisible(true);
        PopupBlur.setVisible(false);

    }

    public void ClosePopup()
    {
        PopUpFrame.setVisible(false);
        PopupBlur.setVisible(false);

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

            MainFrame.getChildren().removeAll();
            MainFrame.getChildren().setAll(loader);



        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
