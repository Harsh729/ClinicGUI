package sample;

import ClinicSoftware.LabWork;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LabWorkReminderController implements Initializable {

    @FXML
    private TableView labWorkReminderTable;

    @FXML
    private TableColumn sentDateColumn;

    @FXML
    private TableColumn patientNameColumn;

    @FXML
    private TableColumn labNameColumn;

    @FXML
    private TableColumn workColumn;

    private static ObservableList<LabWork> labWorkList = FXCollections.observableArrayList();

    public void addLabWorkToList(LabWork labWork)
    {
        labWorkList.add(labWork);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeTable();
        labWorkReminderTable.setItems(labWorkList);
    }

    public void initializeTable()
    {
        this.sentDateColumn = new TableColumn("Sent Date");
        this.patientNameColumn = new TableColumn("Patient Name");
        this.labNameColumn = new TableColumn("Lab Name");
        this.workColumn = new TableColumn("Work");
        this.labWorkReminderTable.getColumns().addAll(sentDateColumn,patientNameColumn,labNameColumn,workColumn);
        //setting values to cells:
        sentDateColumn.setCellValueFactory(new PropertyValueFactory<LabWork,String>("sentDate"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<LabWork,String>("patientName"));
        labNameColumn.setCellValueFactory(new PropertyValueFactory<LabWork,String>("labName"));
        workColumn.setCellValueFactory(new PropertyValueFactory<LabWork,String>("work"));

    }

}
