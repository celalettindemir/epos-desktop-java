package Home.Menu.NewProduct.AllergenButton;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

import java.awt.event.ActionEvent;


public class AllergenButtonController {

    Boolean isSelected = false;

    @FXML
    private JFXButton Button;


    public void allergenClick() {

        if (isSelected==true){
            Button.setStyle("-fx-background-color:#6F2626;-fx-background-radius:10;");
            isSelected=false;
        }else{
            Button.setStyle("-fx-background-color:#266F4D;-fx-background-radius:10;");
            isSelected=true;
        }

    }
}
