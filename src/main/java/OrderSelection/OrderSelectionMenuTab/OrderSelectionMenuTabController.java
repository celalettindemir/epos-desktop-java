package OrderSelection.OrderSelectionMenuTab;

import OrderSelection.OrderSelectionController;
import OrderSelection.SelectionButton.SelectionButtonController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class OrderSelectionMenuTabController {


    private Stage stage;
    private AnchorPane MainFrame;
    private OrderSelectionController orderSelectionController;

    @FXML
    private JFXMasonryPane ProductParent;
    @FXML
    private VBox MenuButtonsParent;

    @FXML
    private VBox ScrollArea;
    @FXML
    private VBox FrameResizer;
    @FXML
    private VBox Blur;
    @FXML
    private VBox OrderEdititonSelection;


    @FXML
    private  JFXMasonryPane SideParent;
    @FXML
    private VBox ScrollArea1;
    @FXML
    private VBox FrameResizer1;



    @FXML
    private  JFXMasonryPane ExtraParent;
    @FXML
    private VBox ScrollArea2;
    @FXML
    private VBox FrameResizer2;


    public VBox getScrollArea() {
        return ScrollArea;
    }

    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setOrderSelectionController(OrderSelectionController orderSelectionController) {
        this.orderSelectionController = orderSelectionController;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage, OrderSelectionController orderSelectionController)
    {

        FrameResizer.widthProperty().addListener((obs, oldVal, newVal) -> {
            FrameResize(ScrollArea,ProductParent,FrameResizer,340,180,65);
        });
        FrameResizer1.widthProperty().addListener((obs, oldVal, newVal) -> {
            FrameResize(ScrollArea1,SideParent,FrameResizer1,340,180,65);
        });
        FrameResizer2.widthProperty().addListener((obs, oldVal, newVal) -> {
            FrameResize(ScrollArea2,ExtraParent,FrameResizer2,340,180,65);
        });
        setMainFrame(MainFrame);
        setStage(stage);
        setOrderSelectionController(orderSelectionController);

        addSides();
        addExtras();
        addSelectionButtons();



        FrameResize(ScrollArea,ProductParent,FrameResizer,340,280,70);
        FrameResize(ScrollArea1,SideParent,FrameResizer1,340,75,75);
        FrameResize(ScrollArea2,ExtraParent,FrameResizer2,340,75,75);
    }



    public void OpenEditionSelection(){
        Blur.setVisible(true);
        OrderEdititonSelection.setVisible(true);
    }

    public void addSides(){
        for (int i =0 ;i<20;i++)
        {
            try{
                FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("SelectionButton.fxml"));
                Parent loader= root.load();
                SelectionButtonController selectionButtonController = root.getController();
                selectionButtonController.useOnLoad(MainFrame,stage,orderSelectionController,this);

                SideParent.getChildren().add(loader);

            }catch (IOException er){

            }


        }
        FrameResize(ScrollArea1,SideParent,FrameResizer1,340,180,65);
    }
    public void addExtras(){
        for (int i =0 ;i<20;i++)
        {
            try{
                FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("SelectionButton.fxml"));
                Parent loader= root.load();
                SelectionButtonController selectionButtonController = root.getController();
                selectionButtonController.useOnLoad(MainFrame,stage,orderSelectionController,this);

                ExtraParent.getChildren().add(loader);

            }catch (IOException er){

            }


        }
        FrameResize(ScrollArea2,ExtraParent,FrameResizer2,340,180,65);
    }
    public void HideEditionSelection(){
        Blur.setVisible(false);
        OrderEdititonSelection.setVisible(false);
    }

    public void addSelectionButtons(){

        for (int i =0 ;i<20;i++)
        {
            try{
                FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("SelectionButton.fxml"));
                Parent loader= root.load();
                SelectionButtonController selectionButtonController = root.getController();
                selectionButtonController.useOnLoad(MainFrame,stage,orderSelectionController,this);

                ProductParent.getChildren().add(loader);

            }catch (IOException er){

            }


        }
        FrameResize(ScrollArea,ProductParent,FrameResizer,340,180,65);

    }



    public void FrameResize(VBox scrollArea, JFXMasonryPane menuParent,VBox FrameResizer,Integer ExtraAreas,Integer Width,Integer Height)
    {
      //  double artan = (MainFrame.getWidth()-ExtraAreas)%Width;

        int nodeperrow=(int) (FrameResizer.getWidth())/Width;

        if(menuParent.getChildren().size()!=0 )
        {
            if(nodeperrow<1)nodeperrow++;
            scrollArea.setMaxWidth((nodeperrow*Width));

            scrollArea.setPrefHeight((((menuParent.getChildren().size()/nodeperrow))*Height )+ Height);

        }




    }





}
