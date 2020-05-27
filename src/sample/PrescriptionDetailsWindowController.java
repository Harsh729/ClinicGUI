package sample;

import ClinicSoftware.Medicine;
import ClinicSoftware.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class PrescriptionDetailsWindowController implements Initializable {

    public static Prescription prescription = new Prescription();

    @FXML
    private TextField NameTextField = new TextField();

    @FXML
    private TextField DateTextField = new TextField();

    @FXML
    private Button CancelButton;

    @FXML
    private Button OKButton;

    @FXML
    private TableView MedicineTable;

    public static void setPrescription(Prescription pre)
    {
        prescription = pre;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeTable();
    }

    void initializeTable()
    {
        try {
            NameTextField.setText(prescription.getPatientName());
            DateTextField.setText(prescription.getDate());

            TableColumn medicines = new TableColumn("Medicines");
            TableColumn instructions = new TableColumn("Instructions");

            MedicineTable.getColumns().addAll(medicines,instructions);

            medicines.setCellValueFactory(new PropertyValueFactory<Medicine,String>("medicine"));
            instructions.setCellValueFactory(new PropertyValueFactory<Medicine,String>("instruction"));

            ObservableList<Medicine> data = FXCollections.observableArrayList();

            LinkedList<String> meds = prescription.getMedicines();
            LinkedList<String> inst = prescription.getInstructions();

            for(int i=0;i<meds.size();i++)
            {
                data.add(new Medicine(meds.get(i), inst.get(i)));
            }

            MedicineTable.setItems(data);
            MedicineTable.setEditable(false);
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
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

}
