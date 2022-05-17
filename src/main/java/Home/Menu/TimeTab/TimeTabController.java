package Home.Menu.TimeTab;

import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TimeTabController {


    private Stage stage;
    private AnchorPane MainFrame;


    @FXML
    private VBox editProduct;

    @FXML
    private VBox newProduct;

    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void useOnLoad(AnchorPane MainFrame, Stage stage) {
        setMainFrame(MainFrame);
        setStage(stage);
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
}
