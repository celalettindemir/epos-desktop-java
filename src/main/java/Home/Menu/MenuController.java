package Home.Menu;

import Home.HomeController;
import Home.Menu.MenuTab.MenuTabController;
import Home.Menu.NewProduct.NewProductController;
import Home.Menu.TimeTab.TimeTabController;
import Home.Table.TableSelectionItem;
import Home.Table.TableTab.TableTab1Controller;
import Rest.controller.MealCRUD;
import Rest.model.Meal.MealDTO;
import Rest.model.Meal.UpdateMeal;
import Rest.model.Place.UpdatePlace;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController {

    private Stage stage;
    private AnchorPane MainFrame;
    public List<MenuTabController> TimeControllers=new ArrayList<>();
    private MealCRUD mealCRUD;

    @FXML
    private VBox editProduct;

    @FXML
    private VBox newProduct;
    @FXML
    private JFXTextField addTimeText;
    @FXML
    private JFXTextField addMenuText;
    @FXML
    private JFXTabPane TimeTabPane;
    @FXML
    private JFXTabPane ProductTimeTab;
    @FXML
    private JFXComboBox addMenuTimeSelection;
    @FXML
    private VBox SelectedTimes;
    @FXML
    private VBox RightBar;
    @FXML
    private JFXButton openRightBarButton;
    @FXML
    private HBox PopUpFrame;
    @FXML
    private VBox PopupBlur;
    @FXML
    private VBox openpopupbutton;
    @FXML
    private JFXTextField editMealText;
    @FXML
    private JFXComboBox<TableSelectionItem> editMealSelection;

    ObservableList<TableSelectionItem> mealItem;

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
        popupSetup();
        List<MealDTO> mealDTOS;
        mealItem = FXCollections.observableArrayList();
        mealCRUD = new MealCRUD();
        for(MealDTO mealDTO:mealCRUD.findAllMeal()){
            createTime(mealDTO);
            mealItem.add(new TableSelectionItem(mealDTO.id,mealDTO.mealName));
        }
        editMealSelection.setItems(mealItem);
        editMealSelection.setConverter(new StringConverter<TableSelectionItem>() {

            @Override
            public String toString(TableSelectionItem object) {
                return object.getName();
            }

            @Override
            public TableSelectionItem fromString(String string) {
                return editMealSelection.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }
    public void hideRightBar(){
        RightBar.setVisible(false);
        openRightBarButton.setVisible(true);

        openpopupbutton.setPrefWidth(50);
    }
    public void openRightBar(){
        RightBar.setVisible(true);
        openRightBarButton.setVisible(false);

        openpopupbutton.setPrefWidth(0);
    }
    public void addMenu(){


        ArrayList<String> indexes=new ArrayList<String>();

        for ( MenuTabController deneme  :TimeControllers ) {
            System.out.println(TimeControllers.indexOf(deneme));
        }

        for (MenuTabController time : TimeControllers)
        {
            try {

                indexes.add(SelectedTimes.getChildren().get( TimeControllers.indexOf(time)).toString());
                System.out.println(SelectedTimes.getChildren().get( TimeControllers.indexOf(time)).toString().split("'")[1]);
            } catch (IndexOutOfBoundsException error) {
                // Output expected IndexOutOfBoundsExceptions.
                System.out.println(error);
            }
        }
        for (String time : indexes)
        {
            System.out.println();
            JFXButton jfxButton =new JFXButton();
            jfxButton.setText(addMenuText.getText());

           TimeControllers.get(Integer.parseInt(time.split("'")[1])-1).getMenuButtons().getChildren().add(jfxButton);
        }

        SelectedTimes.getChildren().clear();
        addMenuTimeSelection.valueProperty().set(null);
    }
    public void TimeSelection(){
        if (addMenuTimeSelection.valueProperty().get()!=null)
        {
            JFXButton jfxButton=new JFXButton();
            jfxButton.setText(TimeTabPane.getTabs().get( addMenuTimeSelection.getItems().indexOf(addMenuTimeSelection.getSelectionModel().getSelectedItem())).getText() );
            SelectedTimes.getChildren().add(jfxButton);
        }

    }
    public  void createTime(){
        MealDTO mealDTO=mealCRUD.saveMeal(addTimeText.getText());
        createTime(mealDTO);

    }
    public void createTime(MealDTO mealDTO){

        Tab tab = new Tab();
        tab.setText(mealDTO.mealName);

        try {

            //  stage.setMaximized(false);
            FXMLLoader root = new FXMLLoader(getClass().getClassLoader().getResource("MenuTab.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            MenuTabController menuTabController=root.getController();

            menuTabController.useOnLoad(MainFrame,stage);
            tab.setContent(loader);
            //TimeControllers.add(menuTabController);

            TimeTabPane.getTabs().add(tab);

            addMenuTimeSelection.getItems().add( TimeTabPane.getTabs().indexOf(tab),tab.getText());

            TimeControllers.add(TimeTabPane.getTabs().indexOf(tab),menuTabController);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteMeal(){
        TimeTabPane.getTabs().remove(editMealSelection.getSelectionModel().getSelectedIndex());
        TableSelectionItem tableSelectionItem =editMealSelection.getSelectionModel().getSelectedItem();
        mealCRUD.removeMeal(tableSelectionItem.getID());
        mealItem.remove(tableSelectionItem);
        editMealSelection.getSelectionModel().clearSelection();
    }
    public void editMeal(){
        TableSelectionItem tableSelectionItem=editMealSelection.getSelectionModel().getSelectedItem();
        int index =editMealSelection.getSelectionModel().getSelectedIndex();
        String text=editMealText.getText();

        TimeTabPane.getTabs().get(index).setText(text);
        tableSelectionItem.setName(text);

        mealCRUD.updateMeal(new UpdateMeal(tableSelectionItem.getID(),tableSelectionItem.getName()));
        mealItem.remove(index);
        mealItem.add(index,tableSelectionItem);
        editMealSelection.getSelectionModel().clearSelection();
    }
    public void newProduct()
    {

        editProduct.setVisible(true);
        newProduct.setVisible(false);
    }

    public void deleteProduct()
    {

        editProduct.setVisible(false);
        newProduct.setVisible(true);
    }

    public void OpenPopup()
    {
        PopUpFrame.setVisible(true);
        PopupBlur.setVisible(true);

    }



    public void popupSetup()
    {


        try {


            //  stage.setMaximized(false);


            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("NewProduct.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            NewProductController newProductController=root.getController();

            newProductController.useOnLoad(MainFrame,stage,PopupBlur,PopUpFrame);
            PopUpFrame.getChildren().clear();
            PopUpFrame.getChildren().add(loader);



        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
