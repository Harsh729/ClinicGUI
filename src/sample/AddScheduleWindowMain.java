package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddScheduleWindowMain extends Application {

    public MainWindowController obj;

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("AddScheduleWindow.fxml"));
        primaryStage.setTitle("Add Schedule");
        primaryStage.setScene(new Scene(root, 400, 439));
        primaryStage.show();
        AddScheduleWindowController.obj=obj;
    }


    public void setMainWindowController(MainWindowController obj)
    {
        this.obj=obj;
    }

    public static void main(String[] args) {
        try {
            launch(args);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
