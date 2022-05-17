package Tables;


import Home.HomeController;
import OrderSelection.OrderSelectionController;
import Tables.TableTab.TableTabController;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
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

public class TablesController {


    private Stage stage ;
    private AnchorPane MainFrame;

    @FXML
    private AnchorPane TablesPageFrame;
    @FXML
    private JFXMasonryPane TablesParent;
    @FXML
    private VBox ScrollArea;
    @FXML
    private VBox OpenNewTable;
    @FXML
    private VBox EditTable;
    @FXML
    private JFXTextField PersonCount;
    @FXML
    private VBox PopupBlur;
    @FXML
    private HBox PopUpFrame;
    @FXML
    private JFXTabPane TableTabPane;

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


        this.MainFrame.setTopAnchor(TablesPageFrame, 40.0);
        this.MainFrame.setLeftAnchor(TablesPageFrame, 0.0);
        this.MainFrame.setRightAnchor(TablesPageFrame, 0.0);
        this.MainFrame.setBottomAnchor(TablesPageFrame,0.0);





        // int nodeperrow= (int)(stage.getHeight()-250)/200;

        // ScrollArea.setPrefHeight((TablesParent.getChildren().size()/nodeperrow)*200);
        // ScrollArea.setPrefHeight(Control.USE_COMPUTED_SIZE);
        addTableCategory();
        addTableCategory();
        addTableCategory();
        addTableCategory();

    }

    private void addTableCategory(){
        try {

            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("TableTab.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            TableTabController tableTabController=root.getController();
            tableTabController.useOnLoad(MainFrame,stage);

            Tab tab=new Tab();
            tab.setText("denem kat");

            tab.setContent(loader);
            TableTabPane.getTabs().add(tab);
            //tableButtonsController.useOnLoad(MainFrame,stage);




        } catch (IOException ex) {
            Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }





    public  void Degis()
    {
        if(EditTable.isVisible()==true)
        {
            EditTable.setVisible(false);
            OpenNewTable.setVisible(true);
        }
        else{
            EditTable.setVisible(true);
            OpenNewTable.setVisible(false);
        }
    }

    public  void AddPerson()
    {
        try{
            int personc=  Integer.parseInt(PersonCount.getText().trim());
            personc++;
            PersonCount.setText(Integer.toString(personc));
        }catch(Exception e){

        }
    }

    public  void RemovePerson()
    {
        if ( Integer.parseInt(PersonCount.getText().trim())>0)
        {
            try{
                int personc=  Integer.parseInt(PersonCount.getText().trim());
                personc--;
                PersonCount.setText(Integer.toString(personc));
            }catch(Exception e){

            }
        }

    }


    public void ReturnHomePage(){
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

    public void OpenTable(ActionEvent event)
    {

        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("OrderSelection.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            OrderSelectionController orderselectioncontroller=root.getController();


            orderselectioncontroller.useOnLoad(MainFrame,stage,Integer.parseInt( PersonCount.getText()),false);

            MainFrame.getChildren().remove(1);
            MainFrame.getChildren().add(loader);



        } catch (IOException ex) {
            Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void OpenPopup()
    {
        PopUpFrame.setVisible(true);
        PopupBlur.setVisible(true);

    }

    public void ClosePopup()
    {
        PopUpFrame.setVisible(false);
        PopupBlur.setVisible(false);

    }

}
