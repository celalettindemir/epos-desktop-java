package Home.Table.TableTab;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TableTab1Controller {

    private Stage stage;
    private AnchorPane MainFrame;

    @FXML
    private JFXMasonryPane TableParent;

    public JFXMasonryPane getTableParent() {
        return TableParent;
    }

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
    }
}
