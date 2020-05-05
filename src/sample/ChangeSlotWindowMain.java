package sample;

import ClinicSoftware.Schedule;
import ClinicSoftware.SingleScheduleEntry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChangeSlotWindowMain extends Application {

    public MainWindowController obj;
    public SingleScheduleEntry row;
    public Schedule schedule;

    @Override
    public void start(Stage primaryStage)throws Exception
    {
        ChangeSlotWindowController.obj=obj;
        ChangeSlotWindowController.row=row;
        ChangeSlotWindowController.schedule=schedule;
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("ChangeSlotWindow.fxml"));
        primaryStage.setTitle("Change Slot");
        primaryStage.setScene(new Scene(anchorPane,400,168));
        primaryStage.show();
    }

    public void setMainWindowController(MainWindowController obj)
    {
        this.obj=obj;
    }

    public void setSelectedRow(SingleScheduleEntry row)
    {
        this.row=row;
    }

    public void setSchedule(Schedule schedule)
    {
        this.schedule=schedule;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
