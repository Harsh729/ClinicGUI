package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateScheduleMain extends Application {

    public void start(Stage primaryStage)
    {
        try
        {
            AnchorPane root= FXMLLoader.load(getClass().getResource("CreateSchedule.fxml"));
            primaryStage.setTitle("Create Schedule");
            primaryStage.setScene(new Scene(root,400,200));
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setObj(MainWindowController obj)
    {
        CreateScheduleController.obj=obj;
    }

    public static void main(String args[])
    {
        launch(args);
    }
}
