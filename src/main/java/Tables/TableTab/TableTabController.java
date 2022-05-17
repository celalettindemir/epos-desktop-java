package Tables.TableTab;

import Tables.TableButtons.TableButtonsController;
import Tables.TablesController;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableTabController {
    private Stage stage ;
    private AnchorPane MainFrame;

    @FXML
    private JFXMasonryPane TableParent;
    @FXML
    private VBox ScrollArea;
    @FXML
    private VBox FrameResizer;


    public void setMainFrame(AnchorPane mainFrame) {
        MainFrame = mainFrame;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void useOnLoad(AnchorPane MainFrame, Stage stage)
    {
        setMainFrame(MainFrame);
        setStage(stage);
        FrameResizer.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.FrameResize(ScrollArea,TableParent,200,100,100);
        });



        Random r =new Random();
        Integer limit=r.nextInt(20)+20;
        for (int i =0 ;i<limit;i++)
        {
            addTableButtons();

        }


        // int nodeperrow= (int)(stage.getHeight()-250)/200;

        // ScrollArea.setPrefHeight((TablesParent.getChildren().size()/nodeperrow)*200);
        // ScrollArea.setPrefHeight(Control.USE_COMPUTED_SIZE);

        this.FrameResize(ScrollArea,TableParent,200,100,100);
    }


    private void addTableButtons(){
        try {

            FXMLLoader root =new FXMLLoader(getClass().getClassLoader().getResource("../../../resources/TableButtons.fxml"));
            //HomeController homecontroller =new HomeController() ;
            //root.setController(homecontroller);
            Parent loader = root.load();
            TableButtonsController tableButtonsController=root.getController();
            tableButtonsController.useOnLoad("1");

            TableParent.getChildren().add(loader);
            //tableButtonsController.useOnLoad(MainFrame,stage);




        } catch (IOException ex) {
            Logger.getLogger(TablesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalStateException er){

        }

    }

    public void FrameResize(VBox scrollArea, JFXMasonryPane tablesParent,Integer ExtraAreas,Integer Width,Integer Height)
    {
        double artan = (MainFrame.getWidth()-ExtraAreas)%Width;
        int nodeperrow=(int)(MainFrame.getWidth()-(ExtraAreas+1)-artan)/Width;
        if(TableParent.getChildren().size()!=0 && nodeperrow!=0)
        {
            scrollArea.setMaxWidth(((nodeperrow)*Width));

            scrollArea.setPrefHeight(((tablesParent.getChildren().size()/nodeperrow)+1)*Height);

        }

    }
}
