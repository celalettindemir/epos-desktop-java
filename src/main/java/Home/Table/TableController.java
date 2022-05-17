package Home.Table;

import Home.Table.TableTab.TableTab1Controller;
import Rest.controller.PlaceCRUD;
import Rest.controller.TableCRUD;
import Rest.model.Place.CreatePlace;
import Rest.model.Place.PlaceDTO;
import Rest.model.Place.UpdatePlace;
import Rest.model.Table.CreateTable;
import Rest.model.Table.TableDTO;
import Rest.model.Table.UpdateTable;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sun.security.krb5.internal.Ticket;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableController {

    private Stage stage;
    private AnchorPane MainFrame;
    public List<TableTabSelectionItem> FloorControllers;

    ObservableList<TableSelectionItem> floorItem;

    ObservableList<ObservableList<TableSelectionItem>> tableItem;
    private PlaceCRUD placeCRUD;
    private TableCRUD tableCRUD;



    @FXML
    private JFXTextField deleteTableCount;
    @FXML
    private JFXTextField editFloorNewText;
    @FXML
    private JFXTextField addTableCount;
    @FXML
    private JFXTextField addFloorText;
    @FXML
    private JFXComboBox<TableSelectionItem> editFloorSelection;
    @FXML
    private JFXTabPane TableTabPane;
    @FXML
    private JFXComboBox<TableSelectionItem> editTableFloorSelection;




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
        FloorControllers=new ArrayList<>();
        floorItem = FXCollections.observableArrayList();
        tableItem = FXCollections.observableArrayList();
        placeCRUD=new PlaceCRUD();
        tableCRUD=new TableCRUD();
        List<PlaceDTO> placeDTOS;
        for (PlaceDTO placeDTO:placeCRUD.findAllPlace()) {
            addFloor(placeDTO);
            floorItem.addAll(new TableSelectionItem(placeDTO.id,placeDTO.placeName));
            tableItem.add(FXCollections.observableArrayList());
            for(TableDTO tableDTO:placeDTO.tableDTOS)
            {
                addTable(tableDTO.tableName,tableDTO.PlaceId);
                tableItem.get(tableItem.size()-1).add(new TableSelectionItem(tableDTO.id,tableDTO.tableName));
            }
        }
        editFloorSelection.setItems(floorItem);
        editFloorSelection.setConverter(new StringConverter<TableSelectionItem>() {

            @Override
            public String toString(TableSelectionItem object) {
                return object.getName();
            }

            @Override
            public TableSelectionItem fromString(String string) {
                return editFloorSelection.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
        if(tableItem.size()!=0)
        {
            editTableFloorSelection.setItems(tableItem.get(TableTabPane.getSelectionModel().getSelectedIndex()));
        }
        editTableFloorSelection.setConverter(new StringConverter<TableSelectionItem>() {

            @Override
            public String toString(TableSelectionItem object) {
                return object.getName();
            }

            @Override
            public TableSelectionItem fromString(String string) {
                return editTableFloorSelection.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
        TableTabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        if(tableItem.size()!=0)
                            editTableFloorSelection.setItems(tableItem.get(TableTabPane.getSelectionModel().getSelectedIndex()));
                    }
                }
        );
    }

    public void addFloor()
    {
        PlaceDTO placeDTO=placeCRUD.savePlace(addFloorText.getText());
        addFloor(placeDTO);
        floorItem.addAll(new TableSelectionItem(placeDTO.id,placeDTO.placeName));
    }
    public void addFloor(PlaceDTO placeDTO)
    {
        Tab tab = new Tab();
        tab.setText(placeDTO.placeName);
        try {
            //  stage.setMaximized(false);

            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("TableTab1.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            TableTab1Controller tableTabController=root.getController();
            tableTabController.useOnLoad(MainFrame,stage);
            tab.setContent(loader);
            TableTabPane.getTabs().add(tab);
            FloorControllers.add(new TableTabSelectionItem(placeDTO.id,tableTabController));



        } catch (IOException ex) {
            Logger.getLogger(TableTab1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void deleteFloor()
    {
        FloorControllers.remove(editFloorSelection.getSelectionModel().getSelectedIndex());
        TableTabPane.getTabs().remove(editFloorSelection.getSelectionModel().getSelectedIndex());
        TableSelectionItem tableSelectionItem =editFloorSelection.getSelectionModel().getSelectedItem();
        placeCRUD.removePlace(tableSelectionItem.getID());
        floorItem.remove(tableSelectionItem);
    }
    public  void editFloor()
    {
        TableSelectionItem tableSelectionItem=editFloorSelection.getSelectionModel().getSelectedItem();
        int index =editFloorSelection.getSelectionModel().getSelectedIndex();
        String text=editFloorNewText.getText();

        TableTabPane.getTabs().get(index).setText(text);
        tableSelectionItem.setName(text);

        placeCRUD.updatePlace(new UpdatePlace(tableSelectionItem.getID(),tableSelectionItem.getName()));
        floorItem.remove(index);
        floorItem.add(index,tableSelectionItem);
    }
    public void addTable()
    {
        //PlaceDTO placeDTO=placeCRUD.savePlace(addFloorText.getText());
        int tableTabIndex=TableTabPane.getSelectionModel().getSelectedIndex();
        TableDTO tableDTO=tableCRUD.saveTable(new CreateTable(addTableCount.getText(),FloorControllers.get(TableTabPane.getSelectionModel().getSelectedIndex()).getID()));
        addTable(addTableCount.getText(),floorItem.get(tableTabIndex).getID());
        if(tableItem.get(tableTabIndex)==null)
            tableItem.add(tableTabIndex,FXCollections.observableArrayList());
        tableItem.get(tableTabIndex).add(new TableSelectionItem(tableDTO.id,tableDTO.tableName));

        //floorItem.addAll(new TableSelectionItem(placeDTO.id,placeDTO.placeName));

    }

    public void addTable(String TableName,String placeId)
    {
        JFXMasonryPane jfxMasonryPane = null;
        for(TableTabSelectionItem tableSelectionItem:FloorControllers){
            if(tableSelectionItem.getID()==placeId) {
                jfxMasonryPane=tableSelectionItem.getTableTab1Controller().getTableParent();
            }
        }

        JFXButton btn =new JFXButton();
        btn.setText(TableName);
        double wid =75;
        double he =75;
        btn.setPrefSize(wid,he);
        btn.setMinSize(wid,he);
        //btn.setStyle("-fx-background-color:rgb(" +r.nextInt(255)+ ","+r.nextInt(255)+","+r.nextInt(255)+");");

        jfxMasonryPane.getChildren().add(btn);
    }
    public void deleteTable()
    {
        JFXMasonryPane jfxMasonryPane = null;
        jfxMasonryPane=FloorControllers.get(TableTabPane.getSelectionModel().getSelectedIndex()).getTableTab1Controller().getTableParent();
        jfxMasonryPane.getChildren().remove(editTableFloorSelection.getSelectionModel().getSelectedIndex());

        tableCRUD.removeTable(editTableFloorSelection.getSelectionModel().getSelectedItem().getID());
        //tableList'ten siliniyor
        tableItem.get(TableTabPane.getSelectionModel().getSelectedIndex()).remove(editTableFloorSelection.getSelectionModel().getSelectedIndex());
        editTableFloorSelection.getSelectionModel().clearSelection();


    }
    public void editTable()
    {

        TableSelectionItem tableSelectionItem=editTableFloorSelection.getSelectionModel().getSelectedItem();
        tableSelectionItem.setName(deleteTableCount.getText());

        tableCRUD.updateTable(new UpdateTable(tableSelectionItem.getID(),tableSelectionItem.getName()));
        tableItem.get(TableTabPane.getSelectionModel().getSelectedIndex()).set(editTableFloorSelection.getSelectionModel().getSelectedIndex(),tableSelectionItem);

        JFXMasonryPane jfxMasonryPane = null;
        jfxMasonryPane=FloorControllers.get(TableTabPane.getSelectionModel().getSelectedIndex()).getTableTab1Controller().getTableParent();
        JFXButton btn =(JFXButton)jfxMasonryPane.getChildren().get(editTableFloorSelection.getSelectionModel().getSelectedIndex());
        btn.setText(deleteTableCount.getText());
        jfxMasonryPane.getChildren().set(editTableFloorSelection.getSelectionModel().getSelectedIndex(),btn);
        editTableFloorSelection.getSelectionModel().clearSelection();
    }
}
