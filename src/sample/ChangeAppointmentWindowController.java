package sample;

import ClinicSoftware.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class ChangeAppointmentWindowController
{

    static Schedule schedule=new Schedule();

    static Appointment app=new Appointment();

    static SingleScheduleEntry row=new SingleScheduleEntry(app);

    static MainWindowController obj=new MainWindowController();


        @FXML
        private AnchorPane baseAnchorPane;

        @FXML
        private AnchorPane detailsPane;

        @FXML
        private Label headingLabel;

        @FXML
        private Label nameLabel;

        @FXML
        private Label phoneLabel;

        @FXML
        private Label descriptionLabel;

        @FXML
        private TextField nameTextField;

        @FXML
        private TextField phoneTextField;

        @FXML
        private TextArea descriptionTextArea;

        @FXML
        private AnchorPane buttonPane;

        @FXML
        private ButtonBar buttonBar;

        @FXML
        private HBox buttonBox;

        @FXML
        private Button cancelButton;

        @FXML
        private Button acceptButton;

        @FXML
        void cancel() {
            Stage stage=(Stage)cancelButton.getScene().getWindow();
            stage.close();
        }

        @FXML
        void save() {
            try {
                Record newPatient = new Record(nameTextField.getText(), phoneTextField.getText());
                RecordFile patientFile = new RecordFile(newPatient.getFileName());
                Record existingRecord=patientFile.readFile();
                if(existingRecord!=null)
                {
                    newPatient=existingRecord;
                }
                else
                {
                    RecordFile rf=new RecordFile(newPatient);
                }
                ScheduleFile newScheduleFile=new ScheduleFile(schedule);
                Appointment newAppointment=row.getAppointment();
                newAppointment.setPatient(newPatient);
                newAppointment.setProcedure(descriptionTextArea.getText());
                AppointmentFile appointmentFile=new AppointmentFile(newAppointment);
                if(newScheduleFile.editEntry(newAppointment)==null)
                {
                    System.out.println("Edited successfully");
                }
            }
            catch(IOException e)
            {
                System.err.println("IOException caught.");
            }
            cancel();
            Main.open();
            obj.closeWindow();
        }

    }



