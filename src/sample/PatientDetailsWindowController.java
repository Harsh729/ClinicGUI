package sample;

import ClinicSoftware.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private TextField HeartConditionTextField = new TextField();

    @FXML
    private TextField BloodPressureTextField = new TextField();

    @FXML
    private TextField AllergyTextField = new TextField();

    @FXML
    private TextField DiabetesTextField = new TextField();

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
        HeartConditionTextField.setText(String.valueOf(patient.getHeartCondition()));
        AllergyTextField.setText(String.valueOf(patient.getAllergy()));
        BloodPressureTextField.setText(String.valueOf(patient.getBloodPressure()));
        DiabetesTextField.setText(String.valueOf(patient.getDiabetes()));
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
