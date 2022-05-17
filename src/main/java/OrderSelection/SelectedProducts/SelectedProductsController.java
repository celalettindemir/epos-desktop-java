package OrderSelection.SelectedProducts;

import OrderSelection.OrderSelectionController;
import OrderSelection.OrderSelectionMenuTab.OrderSelectionMenuTabController;
import OrderSelection.PersonTab.PersonTabController;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SelectedProductsController {

    private Stage stage;
    private AnchorPane MainFrame;
    private int person;
    private  PersonTabController Parent;
    private  OrderSelectionController orderSelectionController;
    private  SelectedProductsController selfController;
    private SelectedProductsController pairSelectedProductsController;
    private Boolean Typepair;
    private OrderSelectionMenuTabController orderSelectionMenuTabController;

    @FXML
    private Text Count;
    @FXML
    private VBox openSidesButton;
    @FXML
    private JFXTextField productName;
    @FXML
    private JFXTextField productPrice;


    public void setOrderSelectionMenuTabController(OrderSelectionMenuTabController orderSelectionMenuTabController) {
        this.orderSelectionMenuTabController = orderSelectionMenuTabController;
    }

    public void setPairSelectedProductsController(SelectedProductsController pairSelectedProductsController) {
        this.pairSelectedProductsController = pairSelectedProductsController;
    }

    public void setOrderSelectionController(OrderSelectionController orderSelectionController) {
        this.orderSelectionController = orderSelectionController;
    }

    public void setParent(PersonTabController parent) {
        Parent = parent;
    }

    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public void setSelfController(SelectedProductsController selfController) {
        this.selfController = selfController;
    }

    public void setTypepair(Boolean typepair) {
        Typepair = typepair;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage, Integer Person, PersonTabController Parent, OrderSelectionController orderSelectionController, SelectedProductsController selfController, SelectedProductsController pairController, Boolean type, OrderSelectionMenuTabController orderSelectionMenuTabController)
    {
        setMainFrame(MainFrame);
        setStage(stage);
        setPerson(Person);
        setParent(Parent);
        setOrderSelectionController(orderSelectionController);
        setSelfController(selfController);
        setTypepair(type);
        setOrderSelectionMenuTabController(orderSelectionMenuTabController);




        if (person!=0){

            try{
                FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("SelectedProducts.fxml"));
                javafx.scene.Parent loader= root.load();
                SelectedProductsController selectedProductsController = root.getController();
                selectedProductsController.useOnLoad(MainFrame,stage, 0,orderSelectionController.getCurruntPersonTabController(0),orderSelectionController,selectedProductsController,this,true, orderSelectionMenuTabController);

                orderSelectionController.getCurruntPersonTabController(0).parentpane.getChildren().add(loader);

                this.setPairSelectedProductsController(selectedProductsController);
                Parent.addSelectedProductToArray(this);
                System.out.println("person = 1");
            }catch (IOException er){

            }

        }else{

                Parent.addSelectedProductToArray(this);
                setPairSelectedProductsController(pairController);



            System.out.println("person = 0");
        }

        addPressAndHoldHandler(openSidesButton,Duration.seconds(0.5),event -> orderSelectionMenuTabController.OpenEditionSelection());
        addPressAndHoldHandler(productName,Duration.seconds(0.5),event -> orderSelectionMenuTabController.OpenEditionSelection());
        addPressAndHoldHandler(productPrice,Duration.seconds(0.5),event -> orderSelectionMenuTabController.OpenEditionSelection());


    }

    public void increaseCountPair(){
        Count.setText(Integer.toString(Integer.parseInt( Count.getText())+1)  );
    }
    public void decreaseCountPair(){
        Count.setText(Integer.toString(Integer.parseInt( Count.getText())-1)  );
    }


    public void increaseCount(){
        Count.setText(Integer.toString(Integer.parseInt( Count.getText())+1)  );
        if(Typepair==true){
            pairSelectedProductsController.increaseCountPair();
        }
    }

    public void decreaseCount(){
        Count.setText(Integer.toString(Integer.parseInt( Count.getText())-1)  );
        if(Integer.parseInt( Count.getText())<=0){
            System.out.println("remove");
            System.out.println();


            if(Typepair==true){
                pairSelectedProductsController.Parent.parentpane.getChildren().remove(pairSelectedProductsController.Parent.getSelectedProductsControllers().indexOf(pairSelectedProductsController.selfController));
                Parent.parentpane.getChildren().remove(Parent.getSelectedProductsControllers().indexOf(selfController));

                pairSelectedProductsController.Parent.getSelectedProductsControllers().remove(pairSelectedProductsController.selfController);
                Parent.getSelectedProductsControllers().remove(selfController);
            }
            else
            {
                Parent.parentpane.getChildren().remove(Parent.getSelectedProductsControllers().indexOf(selfController));
                Parent.getSelectedProductsControllers().remove(selfController);
            }

        }
        else {
            if(Typepair==true){
                pairSelectedProductsController.decreaseCountPair();
            }

        }

    }


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


}
