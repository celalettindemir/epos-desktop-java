package Home.Sides.NewSide;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class NewSideController {


    private Stage stage;
    private AnchorPane MainFrame;
    private  VBox PopupBlur;
    private HBox PopUpFrame;

    @FXML
    private ImageView newProductUploadedImage;
    @FXML
    private JFXComboBox AllergenSelectionID;
    @FXML
    private VBox SelectedAllergens;


    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPopupBlur(VBox popupBlur) {
        PopupBlur = popupBlur;
    }

    public void setPopUpFrame(HBox popUpFrame) {
        PopUpFrame = popUpFrame;
    }

    public void useOnLoad(AnchorPane MainFrame, Stage stage, VBox PopupBlur, HBox PopUpFrame)
    {
        setMainFrame(MainFrame);
        setStage(stage);
        setPopupBlur(PopupBlur);
        setPopUpFrame(PopUpFrame);



    }

    public void AllergenSelection()
    {
        try {
            if (AllergenSelectionID.valueProperty().get()!=null)
            {

                JFXButton jfxButton=new JFXButton();
                jfxButton.setText(AllergenSelectionID.valueProperty().get().toString());
                SelectedAllergens.getChildren().add(jfxButton);

            }


        }catch (IndexOutOfBoundsException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void ClosePopup()
    {
        PopUpFrame.setVisible(false);
        PopupBlur.setVisible(false);
    }



    public void newProductUploadImage(){
        FileChooser fileChooser = new FileChooser();



        String userDirectoryString= System.getProperty("user.home")+"\\Pictures";
        File userDirectory =new File(userDirectoryString);

        if(!userDirectory.canRead())
        {
            userDirectory=new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);



        File filePath = fileChooser.showOpenDialog(stage);


        try {
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image ımage= SwingFXUtils.toFXImage(bufferedImage,null);
            newProductUploadedImage.setImage(ımage);

        }catch (IOException e){


        }catch (IllegalArgumentException err)
        {
            try {

                FileInputStream inputstream = new FileInputStream(System.getProperty("user.dir")+"\\src\\uploadimage.png");
                Image image = new Image(inputstream);

                newProductUploadedImage.setImage(image);

            }catch (IOException er)
            {
                System.out.println(er.getMessage());
            }
        }

    }
}
