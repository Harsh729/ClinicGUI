package sample;

import ClinicSoftware.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ChangeSlotWindowController implements Initializable {

    public static Schedule schedule=new Schedule();
    Slot slot=new Slot();
    static MainWindowController obj=new MainWindowController();
    public static Appointment app=new Appointment();
    public static SingleScheduleEntry row=new SingleScheduleEntry(app);
    Appointment appointment=row.getAppointment();

    String slotStart="";
    String slotEnd="";

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        util=new FXUtilities(slotChooserStart,slotChooserEnd,schedule,data,slotStart,slotEnd);
        util.createComboBoxItems();
        updateData();
    }

    public void updateData()
    {
        slotChooserStart=util.slotChooserStart;
        slotChooserEnd=util.slotChooserEnd;
        data=util.data;
        schedule=util.schedule;
        slotStart=util.slotStart;
        slotEnd=util.slotEnd;
    }

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane detailsPane;

    @FXML
    private Label headingLabel;

    @FXML
    private Label slotLabel;

    @FXML
    private ComboBox slotChooserStart;

    @FXML
    private ComboBox slotChooserEnd;

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

    ObservableList<String> data= FXCollections.observableArrayList();

    FXUtilities util;

    @FXML
    void cancel() {
        Stage stage=(Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        try {
            util.setSlots(slotStart,slotEnd);
            LinkedList<String> timeSlotString=util.getTimeSlot();
            LinkedList<Slot> timeSlotSlot=new LinkedList<>();
            ScheduleFile newScheduleFile=new ScheduleFile(schedule);
            for(int i=0;i<timeSlotString.size();i++)
            {
                timeSlotSlot.add(slot.toSlot(timeSlotString.get(i)));
                if(newScheduleFile.editEntry(appointment,timeSlotSlot.get(i))==null)
                {
                    System.out.println("Edited successfully");
                }
            }
        }
        catch(IOException e)
        {
            System.err.println("IOException caught.");
        }
        cancel();
        if(obj.isOwnerWindow())
            Main.open();
        else
            Main.openAsst();
        obj.closeWindow();
    }

    @FXML
    void setSlotEnd(ActionEvent event) {
        slotEnd=slotChooserEnd.getValue().toString();
        slotEnd = util.decompressSlot(slotEnd,true);
        slotEnd = Slot.toDouble(slotEnd);
    }

    @FXML
    void setSlotStart(ActionEvent event) {
        slotStart=slotChooserStart.getValue().toString();
        slotStart = util.decompressSlot(slotStart,false);
        slotStart = Slot.toDouble(slotStart);
        System.out.println(slotStart);
//        slotChooserEnd.getItems().clear();
        slotChooserEnd.setItems(util.getValidSlots(slotStart));
    }

}