package OrderSelection.SelectionButton;

import OrderSelection.OrderSelectionController;
import OrderSelection.OrderSelectionMenuTab.OrderSelectionMenuTabController;
import OrderSelection.SelectedProducts.SelectedProductsController;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectionButtonController {

    private Stage stage;
    private AnchorPane MainFrame;
    private OrderSelectionController orderSelectionController;
    private OrderSelectionMenuTabController orderSelectionMenuTabController;


    public void setOrderSelectionController(OrderSelectionController orderSelectionController) {
        this.orderSelectionController = orderSelectionController;
    }

    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setOrderSelectionMenuTabController(OrderSelectionMenuTabController orderSelectionMenuTabController) {
        this.orderSelectionMenuTabController = orderSelectionMenuTabController;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage, OrderSelectionController orderSelectionController, OrderSelectionMenuTabController orderSelectionMenuTabController)
    {
        setMainFrame(MainFrame);
        setStage(stage);
        setOrderSelectionController(orderSelectionController);
        setOrderSelectionMenuTabController(orderSelectionMenuTabController);

       // addPressAndHoldHandler(loader, Duration.seconds(0.5), event -> this.OpenEditionSelection());

    }

    private void addPressAndHoldHandler(Node node, javafx.util.Duration holdTime,
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

    public void OpenEditionSelection(){
        orderSelectionMenuTabController.OpenEditionSelection();
    }
    public void SelectProduct()
    {



        try{
            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("SelectedProducts.fxml"));
            Parent loader= root.load();
            SelectedProductsController selectedProductsController = root.getController();
            System.out.println(orderSelectionController.getIndexOfSelectedProductsPane());
            if(orderSelectionController.getIndexOfSelectedProductsPane()==0){
                selectedProductsController.useOnLoad(MainFrame,stage,orderSelectionController.getIndexOfSelectedProductsPane(),orderSelectionController.getCurruntPersonTabController(),orderSelectionController,selectedProductsController,null,false,orderSelectionMenuTabController);

            }
            else
            {
                selectedProductsController.useOnLoad(MainFrame,stage,orderSelectionController.getIndexOfSelectedProductsPane(),orderSelectionController.getCurruntPersonTabController(),orderSelectionController,selectedProductsController,null,true,orderSelectionMenuTabController);

            }

            orderSelectionController.getCurruntPersonTab().getChildren().add(loader);

         }catch (IOException er){

        }


    }
}
