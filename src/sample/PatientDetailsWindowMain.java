package sample;

import ClinicSoftware.Patient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.PathElement;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientDetailsWindowMain extends Application{

    Patient patient;

    public void start(Stage primaryStage)throws IOException
    {
        PatientDetailsWindowController obj =new PatientDetailsWindowController();
        if(patient != null)
            obj.setPatient(patient);
        AnchorPane root = FXMLLoader.load(getClass().getResource("PatientDetailsWindow.fxml"));
        primaryStage.setTitle("Patient Details");
        primaryStage.setScene(new Scene(root,400,496));
        primaryStage.show();
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public Patient getPatient()
    {
        return  patient;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
