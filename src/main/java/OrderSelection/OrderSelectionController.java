package OrderSelection;


import Home.HomeController;
import OrderSelection.OrderSelectionMenuTab.MenuButtons.MenuButtonsController;
import OrderSelection.OrderSelectionMenuTab.OrderSelectionMenuTabController;
import OrderSelection.PersonTab.PersonTabController;
import OrderSelection.TakeawayComponents.TakeawayComponentsController;
import Tables.TablesController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderSelectionController {

    private Stage stage;
    private AnchorPane MainFrame;
    private Integer indexOfSelectedProductsPane=new Integer(0);
    private Boolean CategortyButtonsAvailable=true;
    private Boolean isTakeaway;

    private ArrayList<PersonTabController> personTabControllers=new ArrayList<>();



    @FXML
    private VBox LeftPanel;
    @FXML
    private JFXButton CancelButton;
    @FXML
    private VBox PopupBlur;
    @FXML
    private VBox PopUpFrame;
    @FXML
    private HBox MenuButtonsParent;
    @FXML
    private AnchorPane OrderSelectionPageFrame;
    @FXML
    private JFXTabPane MenuTabPane;
    @FXML
    private JFXTabPane SelectedProductsPane;


    public HBox getMenuButtonsParent() {
        return MenuButtonsParent;
    }

    public Boolean getCategortyButtonsAvailable() {
        return CategortyButtonsAvailable;
    }

    public Integer getIndexOfSelectedProductsPane() {
        return this.indexOfSelectedProductsPane;
    }
    public VBox getCurruntPersonTab(){
        return personTabControllers.get(SelectedProductsPane.getSelectionModel().getSelectedIndex()).parentpane;
    }

    public PersonTabController getCurruntPersonTabController(int index){
        return personTabControllers.get(0);
    }
    public PersonTabController getCurruntPersonTabController(){
        return personTabControllers.get(SelectedProductsPane.getSelectionModel().getSelectedIndex());
    }

    public void setIndexOfSelectedProductsPane(Integer indexOfSelectedProductsPane) {
        this.indexOfSelectedProductsPane = indexOfSelectedProductsPane;
    }

    public ArrayList<PersonTabController> getPersonTabControllers() {
        return personTabControllers;
    }

    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTakeaway(Boolean takeaway) {
        isTakeaway = takeaway;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage, Integer personCount, Boolean isTakeaway)
    {

        setMainFrame(MainFrame);
        setStage(stage);
        setTakeaway(isTakeaway);

        this.MainFrame.setTopAnchor(OrderSelectionPageFrame, 40.0);
        this.MainFrame.setLeftAnchor(OrderSelectionPageFrame, 0.0);
        this.MainFrame.setRightAnchor(OrderSelectionPageFrame, 0.0);
        this.MainFrame.setBottomAnchor(OrderSelectionPageFrame,0.0);



        SelectedProductsPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            setIndexOfSelectedProductsPane(SelectedProductsPane.getSelectionModel().getSelectedIndex());
        });
        //addPressAndHoldHandler(CancelButton,Duration.seconds(0.5),event -> this.OpenPopup());

        addPersonTab("All");

        for(int i=0;i<personCount;i++){


            String tabName=Integer.toString(i+1);
            addPersonTab(tabName);

    }
        addMenuButtons();
        if (isTakeaway){
            takeawayDetail();
        }

    }

    private void takeawayDetail(){
        try{
            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("TakeawayComponents.fxml"));
            Parent loader= root.load();
            TakeawayComponentsController takeawayComponentsController = root.getController();


            LeftPanel.getChildren().add(0,loader);

            LeftPanel.getStylesheets().add("Takeaway.css");
        }catch (IOException er){

        }
    }

    public void addMenuTab(){

        MenuTabPane.getTabs().clear();




        Random r =new Random();
        Integer  rand=r.nextInt(3)+2;
        for (int i =0 ;i<rand;i++)
        {
            Tab tab = new Tab();
           // tab.setText("deneme");
            Label Ltext =new Label("Breakfast");
            Ltext.setStyle("-fx-rotate: -90;-fx-text-fill:#f5f5f5");
            tab.setGraphic(Ltext);


            try {


                //  stage.setMaximized(false);


                FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("OrderSelectionMenuTab.fxml"));
                //HomeController homecontroller =new HomeController() ;
                //root.setController(homecontroller);
                Parent loader = root.load();
                OrderSelectionMenuTabController orderSelectionMenuTabController=root.getController();

                orderSelectionMenuTabController.useOnLoad(MainFrame,stage,this);
                tab.setContent(loader);
                //TimeControllers.add(menuTabController);



                MenuTabPane.getTabs().add(tab);


            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }






    }

    public void addPersonTab(String tabName){


        Tab tab = new Tab();
        tab.setText(tabName);


        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("PersonTab.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            PersonTabController personTabController=root.getController();

            personTabController.useOnLoad(MainFrame,stage);
            tab.setContent(loader);
            //TimeControllers.add(menuTabController);




            SelectedProductsPane.getTabs().add(tab);


            personTabControllers.add(SelectedProductsPane.getTabs().indexOf(tab),personTabController);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }




    }


    public void ReturnTablesPage()
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


    private void addMenuButtons(){
        Random r =new Random();
        int k=15;
        for (int i =0; i<k;i++){
            try{
                FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("MenuButtons.fxml "));
                Parent loader= root.load();
                MenuButtonsController menuButtonsController = root.getController();
                menuButtonsController.useOnLoad(MainFrame,stage,this,2/*,this*//*,ProductParent*/);

                MenuButtonsParent.getChildren().add(loader);

            }catch (IOException er){

            }
        }
    }

//basılı tutma eventi yoktu bu o işe yarıyo oluşturulucak olan node u buna veriyosun oluyo
    private void addPressAndHoldHandler(Node node, Duration holdTime,
                                        javafx.event.EventHandler<MouseEvent> handler) {

        class Wrapper<T> { T content ; }
        Wrapper<MouseEvent> eventWrapper = new Wrapper<>();

        PauseTransition holdTimer = new PauseTransition(holdTime);
        holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));


        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eventWrapper.content = event ;
            holdTimer.playFromStart();
        });
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
        node.addEventHandler(MouseEvent.DRAG_DETECTED, event -> holdTimer.stop());
    }




    @FXML
    void disableButtonsWhileDrag(MouseEvent event) {
        System.out.println("false");
        CategortyButtonsAvailable=false;
    }
    @FXML
    void DragRelease(MouseEvent event) {


        System.out.println("true");

        Timer myTimer = new Timer();
        TimerTask deneme= new TimerTask() {
            @Override
            public void run() {
                CategortyButtonsAvailable=true;
            }
        };
        myTimer.schedule(deneme,10);
    }
}
