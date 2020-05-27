package sample;

import ClinicSoftware.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateLabWorkController {

    public static String name="";

    public static AddScheduleWindowController obj=new AddScheduleWindowController();

    public static MainWindowController mainWindowController = new MainWindowController();

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
    void closeWindow() {
        Stage stage=(Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        MyDate sentDate=new MyDate(SentDatePicker.getValue().toString());
        MyDate receivedDate=new MyDate(ReceivedDatePicker.getValue().toString());
        LabWork newLabWork=new LabWork(sentDate.toString(),receivedDate.toString(),LabNameTextField.getText(),LabWorkTextField.getText(),PatientNameTextField.getText());
        LabWorkFile file=new LabWorkFile(newLabWork);
        obj.setLab(newLabWork);
        mainWindowController.initializeLabWorkTable();
        closeWindow();
    }

    public void setMainWindowController(MainWindowController obj)
    {
        mainWindowController = obj;
    }

}
