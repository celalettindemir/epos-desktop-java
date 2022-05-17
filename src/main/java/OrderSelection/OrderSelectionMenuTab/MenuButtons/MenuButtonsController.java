package OrderSelection.OrderSelectionMenuTab.MenuButtons;

import OrderSelection.OrderSelectionController;
import OrderSelection.OrderSelectionMenuTab.OrderSelectionMenuTabController;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.Random;

public class MenuButtonsController {


    private Stage stage;
    private AnchorPane MainFrame;
    private OrderSelectionController orderSelectionController;
    private  JFXMasonryPane productParent;
    private  OrderSelectionMenuTabController orderSelectionMenuTabController;
    private Integer TypeofButton;

    @FXML
    private Label ButtonLabel;
    @FXML
    private Text ButtonText;


    public void setOrderSelectionController(OrderSelectionController orderSelectionController) {
        this.orderSelectionController = orderSelectionController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setProductParent(JFXMasonryPane productParent) {
        this.productParent = productParent;
    }

    public void setOrderSelectionMenuTabController(OrderSelectionMenuTabController orderSelectionMenuTabController) {
        this.orderSelectionMenuTabController = orderSelectionMenuTabController;
    }

    public void setTypeofButton(Integer typeofButton) {
        TypeofButton = typeofButton;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage, OrderSelectionController orderSelectionController, Integer TypeofButton/*, OrderSelectionMenuTabController orderSelectionMenuTabController, JFXMasonryPane productParent*/)
    {
        setOrderSelectionController(orderSelectionController);
        setStage(stage);
        setMainFrame(MainFrame);
        setProductParent(productParent);
        setOrderSelectionMenuTabController(orderSelectionMenuTabController);
        setTypeofButton(TypeofButton);

        if (TypeofButton==1){
            ButtonText.setText("SubKategori");
        }else if(TypeofButton==2){
            ButtonText.setText("Kategori");
        }else if(TypeofButton==3){
            ButtonText.setText("geri");
        }

    }



    @FXML
    void onClick(MouseEvent event) {
        if (orderSelectionController.getCategortyButtonsAvailable()==true){

            if (TypeofButton==1)
            {
                orderSelectionController.addMenuTab();
            }else if(TypeofButton==2){

                orderSelectionController.getMenuButtonsParent().getChildren().remove(0,orderSelectionController.getMenuButtonsParent().getChildren().size());
                Random r =new Random();
                int k=r.nextInt(5)+3;
                try{
                    FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("MenuButtons.fxml "));
                    Parent loader= root.load();
                    MenuButtonsController menuButtonsController = root.getController();
                    menuButtonsController.useOnLoad(MainFrame,stage,orderSelectionController,3/*,this*//*,ProductParent*/);

                    menuButtonsController.ButtonLabel.setStyle("-fx-background-color:red;");
                    orderSelectionController.getMenuButtonsParent().getChildren().add(loader);

                }catch (IOException er){

                }
                for (int i =0; i<k;i++){
                    try{
                        FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("MenuButtons.fxml "));
                        Parent loader= root.load();
                        MenuButtonsController menuButtonsController = root.getController();
                        menuButtonsController.useOnLoad(MainFrame,stage,orderSelectionController,1/*,this*//*,ProductParent*/);

                        orderSelectionController.getMenuButtonsParent().getChildren().add(loader);

                    }catch (IOException er){

                    }
                }


            }
            else if(TypeofButton==3){
                orderSelectionController.getMenuButtonsParent().getChildren().remove(0,orderSelectionController.getMenuButtonsParent().getChildren().size());
                for (int i =0; i<15;i++){
                    try{
                        FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("MenuButtons.fxml "));
                        Parent loader= root.load();
                        MenuButtonsController menuButtonsController = root.getController();
                        menuButtonsController.useOnLoad(MainFrame,stage,orderSelectionController,2/*,this*//*,ProductParent*/);

                        orderSelectionController.getMenuButtonsParent().getChildren().add(loader);

                    }catch (IOException er){

                    }
                }
            }
        }



    }/*
    public void onClick(){


    }*/
}
