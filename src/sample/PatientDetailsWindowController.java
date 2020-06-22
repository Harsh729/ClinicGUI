package sample;

import ClinicSoftware.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatientDetailsWindowController {

    @FXML
    private TextField NameTextField = new TextField();

    @FXML
    private TextField PhoneTextField = new TextField();

    @FXML
    private TextField AgeTextField = new TextField();

    @FXML
    private TextArea DescriptionTextArea = new TextArea();

    @FXML
    private Button CancelButton;

    @FXML
    private Button OKButton;

    @FXML
    private CheckBox HeartConditionCheckBox = new CheckBox();

    @FXML
    private CheckBox BloodPressureCheckBox = new CheckBox();

    @FXML
    private CheckBox AllergyCheckBox = new CheckBox();

    @FXML
    private CheckBox DiabetesCheckBox = new CheckBox();

    @FXML
    private TextField FirstAppointmentTextField = new TextField();

    @FXML
    private TextField LatestAppointmentTextField = new TextField();

    @FXML
    private TextField PendingAmountTextField = new TextField();

    @FXML
    private TextField AmountPaidTextField = new TextField();

    private static Patient patient = new Patient("Default","123");

    @FXML
    public void initialize()
    {
        NameTextField.setText(patient.getName());
        PhoneTextField.setText(patient.getPhone());
        AgeTextField.setText(String.valueOf(patient.getAge()));
        DescriptionTextArea.setText(patient.getDesc());
        HeartConditionCheckBox.setSelected(patient.getHeartCondition());
        AllergyCheckBox.setSelected(patient.getAllergy());
        BloodPressureCheckBox.setSelected(patient.getBloodPressure());
        DiabetesCheckBox.setSelected(patient.getBloodPressure());
        FirstAppointmentTextField.setText(patient.getDateFromAppointment(patient.getFirstAppointmentFile()));
        LatestAppointmentTextField.setText(patient.getDateFromAppointment(patient.getLatestAppointmentFile()));
        AmountPaidTextField.setText(String.valueOf(patient.getPaid()));
        PendingAmountTextField.setText(String.valueOf(patient.getMoney()));
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void done(ActionEvent event) {
        closeWindow(event);
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

}
