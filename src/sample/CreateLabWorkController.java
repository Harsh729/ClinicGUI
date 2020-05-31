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

public class CreateLabWorkController {

    public static String name="";

    public static AddScheduleWindowController obj=new AddScheduleWindowController();

    public static MainWindowController mainWindowController = new MainWindowController();

    @FXML
    private Button CancelButton;

    @FXML
    private Button OKButton;

    @FXML
    private TextField PatientNameTextField;

    @FXML
    private TextField LabNameTextField;

    @FXML
    private TextField LabWorkTextField;

    @FXML
    private DatePicker SentDatePicker;

    @FXML
    private DatePicker ReceivedDatePicker;

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
        MyDate receivedDate;
        if(ReceivedDatePicker.getValue()==null)
            receivedDate = MyDate.nullDate();
        MyDate sentDate=new MyDate(SentDatePicker.getValue().toString());
        receivedDate=new MyDate(ReceivedDatePicker.getValue().toString());
        LabWork newLabWork=new LabWork(sentDate.toString(),receivedDate.toString(),LabNameTextField.getText(),LabWorkTextField.getText(),PatientNameTextField.getText());
        LabWorkFile file=new LabWorkFile(newLabWork);
        obj.setLab(newLabWork);
        mainWindowController.initializeLabWorkTable();
        closeWindow();
    }

    public static void setMainWindowController(MainWindowController obj)
    {
        mainWindowController = obj;
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
