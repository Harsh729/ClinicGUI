package sample;

import ClinicSoftware.Patient;
import ClinicSoftware.PatientFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatientEditWindowController {

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

    public static MainWindowController mainWindowController;

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
        try
        {

            if(AgeTextField.getText().equals(""))
                AgeTextField.setText("-1");
            Patient newPatient =new Patient(NameTextField.getText(),PhoneTextField.getText(),FirstAppointmentTextField.getText(),Integer.valueOf(AgeTextField.getText()),DescriptionTextArea.getText(),LatestAppointmentTextField.getText(),HeartConditionCheckBox.isSelected(),AllergyCheckBox.isSelected(),BloodPressureCheckBox.isSelected(),DiabetesCheckBox.isSelected());
            deleteFile(newPatient);
            PatientFile newFile=new PatientFile(newPatient);
            mainWindowController.initializePatientTable();
            closeWindow(event);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public void deleteFile(Patient pat)
    {
        if(patient.getFileName().equals(pat.getFileName()))
        {
            PatientFile del = new PatientFile(patient.getFileName());
            del.deleteFile();
        }
    }

    public static void setMainWindowController(MainWindowController obj)
    {
        mainWindowController = obj;
    }

}
