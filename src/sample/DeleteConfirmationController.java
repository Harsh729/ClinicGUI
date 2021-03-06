package sample;

import ClinicSoftware.ClinicFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteConfirmationController implements Initializable {

    @FXML
    private Label ToBeDeleted;

    @FXML
    private Label WarningLabel;

    @FXML
    private Button CancelButton;

    @FXML
    private Button OKButton;

    public static MainWindowController obj = new MainWindowController();

    private static ClinicFile File;


    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void execute(ActionEvent event) {
        if(File.deleteFile())
            System.out.println("File deleted successfully.");

        this.initializeTable();
        closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        String folder = File.getFolderName();
        folder = folder.substring(0,folder.length()-1);
        ToBeDeleted.setText(folder+": "+File.getFileName());
        setWarningLabel();
    }

    public static void setFile(ClinicFile file) {
        File = file;
    }

    public static void setObj(MainWindowController obj) {
        DeleteConfirmationController.obj = obj;
    }

    public void initializeTable()//to initialize the table pertaining to the file under consideration
    {
        String folderName = File.getFolderName();
        switch(folderName)
        {
            case "Lab Work/":
                obj.initializeLabWorkTable();
                break;
            case "Prescriptions/":
                obj.initializePrescriptionTable();
                break;
            case "Records/":
                obj.initializePatientTable();
                break;
        }
    }

    public void setWarningLabel()
    {
        if(File.getFolderName().equals("Records/"))
            WarningLabel.setText("Warning: Corresponding appointments will become vacant in the Schedule!");
    }
}
