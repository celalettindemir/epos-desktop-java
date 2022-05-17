package Home.Menu.NewProduct.AdditionStockTab;

import Home.Menu.NewProduct.AdditionStockTab.StockButton.StockButtonController;
import OrderSelection.OrderSelectionController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class AdditionStockTabController {


    private Stage stage;
    private AnchorPane MainFrame;
    private ArrayList<StockButtonController> StockButtonControllers=new ArrayList<>();

    @FXML
    private  JFXMasonryPane StockParent;
    @FXML
    private VBox ScrollArea;
    @FXML
    private VBox FrameResizer;



    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public JFXMasonryPane getStockParent() {
        return StockParent;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage)
    {



        setMainFrame(MainFrame);
        setStage(stage);






        FrameResize(ScrollArea,StockParent,FrameResizer,450,280,70);
    }


    public void addButtonToList(StockButtonController button){
        StockButtonControllers.add(button);

    }

    public void FrameResize(VBox scrollArea, JFXMasonryPane menuParent, VBox FrameResizer, Integer ExtraAreas, Integer Width, Integer Height)
    {
        //  double artan = (MainFrame.getWidth()-ExtraAreas)%Width;

        int nodeperrow=(int) (MainFrame.getWidth()-ExtraAreas)/Width;

        if(menuParent.getChildren().size()!=0 )
        {
            if(nodeperrow<1)nodeperrow++;
            scrollArea.setMaxWidth(((nodeperrow)*Width));

            scrollArea.setPrefHeight((((menuParent.getChildren().size()/nodeperrow))*Height )+ Height);

        }




    }
}
