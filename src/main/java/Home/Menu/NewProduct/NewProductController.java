package Home.Menu.NewProduct;

import Home.HomeController;
import Home.Menu.NewProduct.AdditionStockTab.AdditionStockTabController;
import Home.Menu.NewProduct.AdditionStockTab.StockButton.StockButtonController;
import Home.Menu.NewProduct.AllergenButton.AllergenButtonController;
import Home.Menu.NewProduct.MealArrangementFrame.MealArrangementFrameController;
import Home.Order.OrderController;
import OrderSelection.PersonTab.PersonTabController;
import com.jfoenix.controls.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewProductController {

    private Stage stage;
    private AnchorPane MainFrame;
    private  VBox PopupBlur;
    private HBox PopUpFrame;
    private ArrayList<AdditionStockTabController> StockTabControllers=new ArrayList<>();

    @FXML
    private ImageView newProductUploadedImage;
    @FXML
    private  JFXMasonryPane SideParent;
    @FXML
    private VBox ScrollArea;
    @FXML
    private VBox FrameResizer;
    @FXML
    private VBox SelectedAllergens;

    @FXML
    private JFXComboBox AllergenSelectionID;

    @FXML
    private JFXComboBox StockSelection;



    @FXML
    private JFXTabPane AdditionStockPane;

    @FXML
    private  JFXMasonryPane AllergenParent;
    @FXML
    private VBox ScrollArea1;
    @FXML
    private VBox FrameResizer1;

    @FXML
    private JFXTextField AdditionName;

    @FXML
    private JFXTextField StockAmount;


    @FXML
    private VBox MealsParent;


    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPopupBlur(VBox popupBlur) {
        PopupBlur = popupBlur;
    }

    public void setPopUpFrame(HBox popUpFrame) {
        PopUpFrame = popUpFrame;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage, VBox PopupBlur, HBox PopUpFrame)
    {
        setMainFrame(MainFrame);
        setStage(stage);
        setPopupBlur(PopupBlur);
        setPopUpFrame(PopUpFrame);




        addallergen();


        FrameResize(ScrollArea1,AllergenParent,100,100);
        StockSelection.getItems().add("domates");
        StockSelection.getItems().add("kaşar");




        addMeals();


        AdditionStockPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            AdditionName.setText(AdditionStockPane.getSelectionModel().getSelectedItem().getText());
        });
    }



    private void addallergen()
    {
        for (int i =0 ;i<50;i++)
        {

            try {


                //  stage.setMaximized(false);


                FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("AllergenButton.fxml"));
                //HomeController homecontroller =new HomeController() ;
                //root.setController(homecontroller);
                Parent loader = root.load();
                AllergenButtonController allergenButtonController=root.getController();

                // allergenButtonController.useOnLoad(MainFrame,stage);
                //TimeControllers.add(menuTabController);


                AllergenParent.getChildren().add(loader);


            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }



    }

    public void addStockToAddition()
    {

        StockSelection.getSelectionModel().getSelectedItem();
        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("StockButton.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            StockButtonController stockButtonController=root.getController();

            stockButtonController.useOnLoad(MainFrame,stage,StockSelection,StockAmount);
            //TimeControllers.add(menuTabController);

            StockTabControllers.get(AdditionStockPane.getSelectionModel().getSelectedIndex()).getStockParent().getChildren().add(loader);
            StockTabControllers.get(AdditionStockPane.getSelectionModel().getSelectedIndex()).addButtonToList(stockButtonController);




        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void editAddition(){
        if(!AdditionStockPane.getTabs().isEmpty()){
            AdditionStockPane.getSelectionModel().getSelectedItem().setText(AdditionName.getText());
        }

    }

    public void addAddition(){


               Tab tab = new Tab();
               tab.setText(AdditionName.getText());
               try {


                   //  stage.setMaximized(false);


                   FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("AdditionStockTab.fxml"));
                   //HomeController homecontroller =new HomeController() ;
                   //root.setController(homecontroller);
                   Parent loader = root.load();
                   AdditionStockTabController additionStockPane=root.getController();

                   additionStockPane.useOnLoad(MainFrame,stage);

                   tab.setContent(loader);

                   AdditionStockPane.getTabs().add(tab);
                   StockTabControllers.add(AdditionStockPane.getTabs().indexOf(tab),additionStockPane);

               } catch (IOException ex) {
                   Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
               }



    }


    public void ClosePopup()
    {
        PopUpFrame.setVisible(false);
        PopupBlur.setVisible(false);
    }




    public void newProductUploadImage(){
        FileChooser fileChooser = new FileChooser();



        String userDirectoryString= System.getProperty("user.home")+"\\Pictures";
        File userDirectory =new File(userDirectoryString);

        if(!userDirectory.canRead())
        {
            userDirectory=new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);



        File filePath = fileChooser.showOpenDialog(stage);


        try {
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image ımage= SwingFXUtils.toFXImage(bufferedImage,null);
            newProductUploadedImage.setImage(ımage);

        }catch (IOException e){


        }catch (IllegalArgumentException err)
        {
            try {

                FileInputStream inputstream = new FileInputStream(System.getProperty("user.dir")+"\\src\\uploadimage.png");
                Image image = new Image(inputstream);

                newProductUploadedImage.setImage(image);

            }catch (IOException er)
            {
                System.out.println(er.getMessage());
            }
        }

    }


    public void FrameResize(VBox scrollArea, JFXMasonryPane menuParent,Integer Width,Integer Height)
    {
        //  double artan = (MainFrame.getWidth()-ExtraAreas)%Width;

        int nodeperrow= (499)/Width;

        if(menuParent.getChildren().size()!=0 )
        {
            if(nodeperrow<1)nodeperrow++;
            scrollArea.setMaxWidth(((nodeperrow)*Width));

            scrollArea.setPrefHeight((((menuParent.getChildren().size()/nodeperrow))*Height )+ Height);

        }




    }

    public void addMeals(){

        for(int i =0;i<5;i++){
            try {


                //  stage.setMaximized(false);


                FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("MealArrangementFrame.fxml"));
                //HomeController homecontroller =new HomeController() ;
                //root.setController(homecontroller);
                Parent loader = root.load();
                MealArrangementFrameController mealArrangementFrameController=root.getController();

                //mealArrangementFrameController.useOnLoad(MainFrame,stage);
                MealsParent.getChildren().add(loader);


            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    public void AllergenSelection()
    {
        try {
            if (AllergenSelectionID.valueProperty().get()!=null)
            {

                JFXButton jfxButton=new JFXButton();
                jfxButton.setText(AllergenSelectionID.valueProperty().get().toString());
                SelectedAllergens.getChildren().add(jfxButton);

            }


        }catch (IndexOutOfBoundsException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
