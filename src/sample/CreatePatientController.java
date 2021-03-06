package sample;

import ClinicSoftware.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreatePatientController {

    public static AddScheduleWindowController obj=new AddScheduleWindowController();

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField PhoneTextField;

    @FXML
    private TextField AgeTextField;

    @FXML
    private TextArea DescriptionTextArea;

    @FXML
    private CheckBox HeartConditionCheckBox;

    @FXML
    private CheckBox BloodPressureCheckBox;

    @FXML
    private CheckBox AllergiesCheckBox;

    @FXML
    private CheckBox DiabetesCheckBox;

    @FXML
    private Button CancelButton;

    @FXML
    private Button OKButton;

    public static MainWindowController mainWindowController = new MainWindowController();

    @FXML
    void closeWindow() {
        Stage stage=(Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        try
        {
            if(AgeTextField.getText().equals(""))
                AgeTextField.setText("-1");
            Patient newPatient =new Patient(NameTextField.getText(),PhoneTextField.getText(),"",Integer.valueOf(AgeTextField.getText()),DescriptionTextArea.getText(),"",HeartConditionCheckBox.isSelected(),AllergiesCheckBox.isSelected(),BloodPressureCheckBox.isSelected(),DiabetesCheckBox.isSelected());
            PatientFile newFile=new PatientFile(newPatient);
            obj.setPatient(newPatient);
            mainWindowController.initializePatientTable();
            closeWindow();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setMainWindowController(MainWindowController mainWindowController) {
        CreatePatientController.mainWindowController = mainWindowController;
    }
}
