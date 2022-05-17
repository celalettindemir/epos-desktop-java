package Tables.TableButtons;

import Home.Table.TableSelectionItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class TableButtonsController {

    @FXML
    private Text addTableText;
    public void useOnLoad(String ButtonName)
    {
        addTableText.setText(ButtonName);

    }

}
