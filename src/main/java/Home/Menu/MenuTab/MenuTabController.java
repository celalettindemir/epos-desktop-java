package Home.Menu.MenuTab;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Random;

public class MenuTabController {

    private Stage stage;
    private AnchorPane MainFrame;




    @FXML
    private VBox FrameResizer;
    @FXML
    private VBox ScrollArea;

    @FXML
    private JFXMasonryPane MenuParent;
    @FXML
    private VBox MenuButtons;


    public VBox getMenuButtons() {
        return MenuButtons;
    }

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

        FrameResizer.widthProperty().addListener((obs, oldVal, newVal) -> {
            FrameResize(ScrollArea,MenuParent);

        });

        Random r =new Random();
        for (int i =0 ;i<200;i++)
        {
            JFXButton btn =new JFXButton();
            btn.setText( Integer.toString(i));
            double wid =100;
            double he =75;
            btn.setPrefSize(wid,he);
            btn.setMinSize(wid,he);
            btn.setStyle("-fx-background-color:rgb(" +r.nextInt(255)+ ","+r.nextInt(255)+","+r.nextInt(255)+");");
            MenuParent.getChildren().add(btn);

        }


        FrameResize(ScrollArea,MenuParent);


    }

    public void FrameResize(VBox scrollArea, JFXMasonryPane menuParent)
    {
        int nodeperrow=(int)(MainFrame.getWidth()-201)/75;
        if(MenuParent.getChildren().size()!=0)
        {
            if(nodeperrow<1)nodeperrow++;
                scrollArea.setMaxWidth(((nodeperrow)*75));

                scrollArea.setPrefHeight(((menuParent.getChildren().size()/nodeperrow))*75+75);

        }


    }
}


