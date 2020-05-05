package sample;

import ClinicSoftware.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateRecordController {

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

    @FXML
    void closeWindow() {
        Stage stage=(Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save() {
        try
        {
            Record newRecord=new Record(NameTextField.getText(),PhoneTextField.getText(),"",Integer.valueOf(AgeTextField.getText()),DescriptionTextArea.getText(),"",HeartConditionCheckBox.isSelected(),AllergiesCheckBox.isSelected(),BloodPressureCheckBox.isSelected(),DiabetesCheckBox.isSelected());
            RecordFile newFile=new RecordFile(newRecord);
            obj.setRecord(newRecord);
            closeWindow();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
