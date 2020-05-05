package sample;

import ClinicSoftware.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreatePrescriptionController {

    public static AddScheduleWindowController obj=new AddScheduleWindowController();

    public static String name="";

    @FXML
    private Button CancelButton;

    @FXML
    private Button OKButton;

    @FXML
    private TextField PatientNameTextField;

    @FXML
    private TextArea MedicineTextArea;

    @FXML
    private TextArea InstructionTextArea;

    @FXML
    private DatePicker datePicker;

    @FXML
    void closeWindow() {
        Stage stage=(Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        MyDate date=new MyDate(datePicker.getValue().toString());
        String inst=InstructionTextArea.getText();
        String med=MedicineTextArea.getText();
        String instructions[]=inst.split("\\r?\\n");
        String meds[]=med.split("\\r?\\n");
        Prescription newPrescription=new Prescription(PatientNameTextField.getText(),date.toString());
        for(int i=0;i<meds.length;i++)
        {
            newPrescription.addMedicineEntry(meds[i],instructions[i]);
        }
        PrescriptionFile file=new PrescriptionFile(newPrescription);
        obj.setPrescription(newPrescription);
        closeWindow();
    }

}
