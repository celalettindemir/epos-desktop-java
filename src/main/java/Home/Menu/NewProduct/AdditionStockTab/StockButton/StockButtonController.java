package Home.Menu.NewProduct.AdditionStockTab.StockButton;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StockButtonController {

    private String StockName="name";
    private Integer Amount =0;
    private Stage stage;
    private AnchorPane MainFrame;
    private JFXComboBox StockNameCB;
    private JFXTextField StockAmountTF;

    @FXML
    private JFXButton Button;



    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStockAmountTF(JFXTextField stockAmountTF) {
        StockAmountTF = stockAmountTF;
    }

    public void setStockNameCB(JFXComboBox stockNameCB) {
        StockNameCB = stockNameCB;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage, JFXComboBox StockNameCB, JFXTextField StockAmountTF)
    {

        setMainFrame(MainFrame);
        setStage(stage);
        setStockAmountTF(StockAmountTF);
        setStockNameCB(StockNameCB);


        if (StockNameCB.getSelectionModel().getSelectedItem()!=null && StockAmountTF !=null)
        {
            StockName=StockNameCB.getSelectionModel().getSelectedItem().toString();
            Amount=Integer.parseInt( StockAmountTF.getText());
        }

            Button.setText(StockName+"\n"+Amount);

        //
    }

    public void StockButtonClick(){

        StockAmountTF.setText(Integer.toString( Amount));
        StockNameCB.setAccessibleText(StockName);

    }
}
