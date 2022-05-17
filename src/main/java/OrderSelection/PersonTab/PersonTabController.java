package OrderSelection.PersonTab;

import OrderSelection.OrderSelectionController;
import OrderSelection.SelectedProducts.SelectedProductsController;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class PersonTabController {

    private Stage stage;
    private AnchorPane MainFrame;

    public Integer denemesayı;
    public VBox parentpane;

    /*class SelectedProduct
    {
        public SelectedProductsController selfController;
        public SelectedProductsController pairfController;
    }*/

    private ArrayList<SelectedProductsController> selectedProductsControllers=new ArrayList<>();



    @FXML
    private VBox SelectedProductParent;


    public VBox getSelectedProductParent() {
        return SelectedProductParent;
    }

    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ArrayList<SelectedProductsController> getSelectedProductsControllers() {
        return selectedProductsControllers;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage)
    {
        parentpane=SelectedProductParent;



        setMainFrame(MainFrame);
        setStage(stage);

        Random r=new Random();
        denemesayı=r.nextInt(100);

    }

    public void addSelectedProductToArray(SelectedProductsController selectedProductsController){
        selectedProductsControllers.add(selectedProductsController);

    }



}
