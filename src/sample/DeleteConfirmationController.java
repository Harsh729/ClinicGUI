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
        obj.initializePatientTable();
        closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        String folder = File.getFolderName();
        folder = folder.substring(0,folder.length()-1);
        ToBeDeleted.setText(folder+": "+File.getFileName());
    }

    public static void setFile(ClinicFile file) {
        File = file;
    }

    public static void setObj(MainWindowController obj) {
        DeleteConfirmationController.obj = obj;
    }
}
