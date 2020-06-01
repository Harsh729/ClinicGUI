package sample;

import ClinicSoftware.LabWork;
import ClinicSoftware.LabWorkFile;
import ClinicSoftware.MyDate;
import ClinicSoftware.PatientFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditLabWorkWindowController implements Initializable {

    public static String name="";

    public static AddScheduleWindowController obj=new AddScheduleWindowController();

    public static MainWindowController mainWindowController = new MainWindowController();

    public static LabWork labWork = new LabWork();

    @FXML
    private Button CancelButton;

    @FXML
    private Button OKButton;

    @FXML
    private TextField PatientNameTextField;

    @FXML
    private TextField LabNameTextField;

    @FXML
    private TextField LabWorkTextField;

    @FXML
    private DatePicker SentDatePicker;

    @FXML
    private DatePicker ReceivedDatePicker;


    @FXML
    public void initialize(URL url, ResourceBundle rb)
    {
        PatientNameTextField.setText(labWork.getPatientName());
        String date = labWork.getSentDate();
        MyDate myDate = new MyDate(date);
        int[] dates = MyDate.toIntegerArray(myDate);
        LocalDate localDate = LocalDate.of(dates[0],dates[1],dates[2]);
        SentDatePicker.setValue(localDate);
        if(!labWork.getReceivedDate().equals("Date not entered"))
        {
            date = labWork.getReceivedDate();
            dates = MyDate.toIntegerArray(new MyDate(date));
            ReceivedDatePicker.setValue(LocalDate.of(dates[0],dates[1],dates[2]));
        }
        LabNameTextField.setText(labWork.getLabName());
        LabWorkTextField.setText(labWork.getWork());
    }

    @FXML
    void closeWindow() {
        Stage stage=(Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        MyDate receivedDate;
        if(ReceivedDatePicker.getValue()==null)
            receivedDate = MyDate.nullDate();
        else
            receivedDate=new MyDate(ReceivedDatePicker.getValue().toString());
        MyDate sentDate=new MyDate(SentDatePicker.getValue().toString());
        LabWork newLabWork=new LabWork(sentDate.toString(),receivedDate.toString(),LabNameTextField.getText(),LabWorkTextField.getText(),PatientNameTextField.getText());
        LabWorkFile file=new LabWorkFile(newLabWork);
        obj.setLab(newLabWork);
        mainWindowController.initializeLabWorkTable();
        closeWindow();
    }

    public static void setMainWindowController(MainWindowController obj)
    {
        mainWindowController = obj;
    }

    public static void setLabWork(LabWork lab) { labWork = lab;}
}
