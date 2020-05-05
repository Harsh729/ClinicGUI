package sample;

import ClinicSoftware.Appointment;
import ClinicSoftware.SingleScheduleEntry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PayWindowMain extends Application
{

    public void setSelected(SingleScheduleEntry selected)
    {
        PayWindowController.selected=selected;
    }

    public void setObj(MainWindowController obj)
    {
        PayWindowController.obj=obj;
    }

    public void start(Stage primaryStage)
    {
        try
        {
            AnchorPane root= FXMLLoader.load(getClass().getResource("PayWindow.fxml"));
            primaryStage.setTitle("Pay Window");
            primaryStage.setScene(new Scene(root,400,200));
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}