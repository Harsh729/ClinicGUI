package sample;

import ClinicSoftware.ClinicFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteConfirmationMain extends Application {

    public ClinicFile file;

    public void setFile(ClinicFile file)
    {
        this.file = file;
    }

    public void start(Stage primaryStage)throws IOException
    {
        DeleteConfirmationController.setFile(file);
        AnchorPane root = FXMLLoader.load(getClass().getResource("DeleteConfirmation.fxml"));
        primaryStage.setTitle("Delete Confirmation");
        primaryStage.setScene(new Scene(root,400,150));
        primaryStage.show();
    }

    public void setObj(MainWindowController obj) { DeleteConfirmationController.setObj(obj); }

    public static void main(String[] args) {
        launch(args);
    }
}
