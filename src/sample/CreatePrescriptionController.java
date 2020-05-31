package sample;

import ClinicSoftware.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreatePrescriptionController {

    public static AddScheduleWindowController obj=new AddScheduleWindowController();

    public static MainWindowController mainWindowController;

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
    private TextField searchTextField;

    @FXML
    private ContextMenu searchContextMenu;

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

    @FXML
    public void search()
    {
        String search = searchTextField.getText();
        searchContextMenu.getItems().clear();
        ObservableList<String> data = mainWindowController.search(search, new PatientFile(""));
        ObservableList<MenuItem> items = FXCollections.observableArrayList();

        for(String s:data)
        {
            MenuItem item = new MenuItem();
            item.setGraphic(mainWindowController.highlight(s,search));
            EventHandler event = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    searchTextField.setText(s);
                }
            };
            item.setOnAction(event);
            items.add(item);
        }

        searchContextMenu.getItems().addAll(items);
        searchTextField.setContextMenu(searchContextMenu);
        Bounds dims = searchTextField.localToScreen(searchTextField.getBoundsInLocal());
        searchContextMenu.show(searchTextField,dims.getMinX(),dims.getMaxY());
    }

    @FXML
    public void useSearch()
    {
        String fileName = searchTextField.getText();
        int separate = fileName.lastIndexOf(" ");
        String name = fileName.substring(0,separate);
        String phone = fileName.substring(separate+1);
        PatientNameTextField.setText(name);
    }

}
