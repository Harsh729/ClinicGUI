package sample;

import ClinicSoftware.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class CreateScheduleController {

    public static MainWindowController obj=new MainWindowController();

    @FXML
    private DatePicker date;

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
        try {
            MyDate mydate = new MyDate(date.getValue().toString());
            Schedule schedule = new Schedule(mydate.toString());
            ScheduleFile file = new ScheduleFile(schedule);
            obj.initializeScheduleTable(mydate.toString());
            closeWindow();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
