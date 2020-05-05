package sample;

import ClinicSoftware.*;
import javafx.collections.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private String dir=System.getProperty("user.dir")+"\\Directories\\";



    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

//        if(Main.ctr==0) {
//            Main.ctr++;
//            Main obj = new Main();
//            obj.setUserSignature(1);
//            obj.setTitle("Assistant Doctor");
//            flag = true;
//            try {
//                obj.start(new Stage());
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//        }

        initializeLabWorkTable();
        initializePrescriptionTable();
        MyDate date=new MyDate();
        String d=date.toString();
        //System.out.println(d);
        initializeScheduleTable(d);
        initializePatientTable();
    }

    public void init(String date)
    {
        initializeLabWorkTable();
        initializePrescriptionTable();
        initializeScheduleTable(date);
        initializePatientTable();
    }

    public void initializeLabWorkTable()
    {

        try {
            setUserSignature();
            LabWorkTable.getColumns().clear();
        }
        catch(NullPointerException e)
        {
            System.err.println("Null pointer exception");
        }

        TableColumn LabWorkSentDate=new TableColumn("Sent Date");
        TableColumn LabWorkPatientName=new TableColumn("Patient Name");
        TableColumn LabWorkLabName=new TableColumn("Lab Name");
        TableColumn LabWorkWork=new TableColumn("Work");

        LabWorkTable.getColumns().addAll(LabWorkSentDate,LabWorkPatientName,LabWorkLabName,LabWorkWork);



        LabWorkSentDate.setCellValueFactory(new PropertyValueFactory<LabWork,String>("sentDate"));
        LabWorkPatientName.setCellValueFactory(new PropertyValueFactory<LabWork,String>("patientName"));
        LabWorkLabName.setCellValueFactory(new PropertyValueFactory<LabWork,String>("labName"));
        LabWorkWork.setCellValueFactory(new PropertyValueFactory<LabWork,String>("work"));

        LabWork lab=new LabWork();
        try {
            //LabWorkFile file2 = new LabWorkFile("Name 12-1-2019");
            File folder=new File(dir+"Lab Work\\");
            File[] LabWorkFiles=folder.listFiles();
            ObservableList<LabWork> data = FXCollections.observableArrayList();
            for(int i=0;i<LabWorkFiles.length;i++) {
                LabWorkFile labFile = new LabWorkFile(LabWorkFiles[i].getName().split("\\.")[0]);
                lab= labFile.readFile();
                data.add(lab);

            }
            LabWorkTable.setItems(data);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public void initializePrescriptionTable()
    {
        try {
            setUserSignature();
            PrescriptionsTable.getColumns().clear();
        }
        catch(NullPointerException e)
        {
            System.err.println("Null pointer exception");
        }

        TableColumn PrescriptionDate=new TableColumn("Date");
        TableColumn PrescriptionPatientName=new TableColumn("Patient Name");

        PrescriptionsTable.getColumns().addAll(PrescriptionDate,PrescriptionPatientName);

        PrescriptionDate.setCellValueFactory(new PropertyValueFactory<Prescription,String>("date"));
        PrescriptionPatientName.setCellValueFactory(new PropertyValueFactory<Prescription,String>("patientName"));

        Prescription pre=new Prescription();
        try {
            //LabWorkFile file2 = new LabWorkFile("Name 12-1-2019");
            File folder=new File(dir+"Prescriptions\\");
            File[] PrescriptionFiles=folder.listFiles();
            ObservableList<Prescription> data = FXCollections.observableArrayList();
            for(int i=0;i<PrescriptionFiles.length;i++) {
                PrescriptionFile prescriptionFile = new PrescriptionFile(PrescriptionFiles[i].getName().split("\\.")[0]);
                pre=prescriptionFile.readFile();
                data.add(pre);

            }
            PrescriptionsTable.setItems(data);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initializeScheduleTable(String date)
    {
        try {
            setUserSignature();
            ScheduleTable.getColumns().clear();
        }
        catch(NullPointerException e)
        {
            System.err.println("Null pointer exception");
        }
        schedule=new Schedule();
        ScheduleFile file=new ScheduleFile(date);
        try {
            file.setUserSignature(userSignature);
            schedule = file.readFile();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

            LinkedList<Appointment> appointments = new LinkedList<>();

            try {

                appointments = schedule.getAppointments();
            } catch (NullPointerException e) {
                System.err.println("Null pointer exception, probably because File was not found");
            }


            Iterator j = appointments.iterator();

            ObservableList<SingleScheduleEntry> data = FXCollections.observableArrayList();

            while (j.hasNext()) {
                Appointment app=new Appointment();
                if((app=(Appointment)j.next()).getUserSignature()==userSignature)
                data.add(new SingleScheduleEntry(app));
            }

            TableColumn slotColumn = new TableColumn("Time");
            TableColumn appointmentColumnPatientName = new TableColumn("Appointment");
            TableColumn description = new TableColumn("Description");

            ScheduleTable.getColumns().addAll(slotColumn, appointmentColumnPatientName, description);

            slotColumn.setCellValueFactory(new PropertyValueFactory<SingleScheduleEntry, String>("time"));
            appointmentColumnPatientName.setCellValueFactory(new PropertyValueFactory<SingleScheduleEntry, String>("patientName"));
            description.setCellValueFactory(new PropertyValueFactory<SingleScheduleEntry, String>("description"));

            ScheduleTable.setItems(data);

    }

    public void initializePatientTable()
    {
        try {
            PatientTable.getColumns().clear();
        }
        catch(NullPointerException e)
        {
            System.err.println("NullPointerException:");
            e.printStackTrace();
        }
        TableColumn patientName=new TableColumn("Patient Name");
        TableColumn phone=new TableColumn("Phone No.");
        TableColumn age=new TableColumn("Age");
        TableColumn money=new TableColumn("Pending amount");

        PatientTable.getColumns().addAll(patientName,phone,age,money);

        patientName.setCellValueFactory(new PropertyValueFactory<Patient,String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
        age.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("age"));
        money.setCellValueFactory(new PropertyValueFactory<Patient,Double>("money"));

        try{
            File folder=new File(dir+"Records\\");
            File[] RecordFiles=folder.listFiles();
            ObservableList<Patient> data=FXCollections.observableArrayList();
            for(File file: RecordFiles)
            {
                PatientFile patientFile =new PatientFile(file.getName().split("\\.")[0]);
                Patient rec= patientFile.readFile();
                data.add(rec);
            }
            PatientTable.setItems(data);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public Schedule schedule;
    public static int userSignature;

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private Menu MenuBar_Menu_File;

    @FXML
    private MenuItem Menu_File_Close;

    @FXML
    private MenuItem Menu_File_CreateLabWork;

    @FXML
    private MenuItem Menu_File_CreatePrescriptions;

    @FXML
    private MenuItem Menu_File_CreateSchedule;

    @FXML
    private MenuItem Menu_File_CreateRecord;

    @FXML
    private Button ScheduleTabButton;

    @FXML
    private Button ScheduleTableAddButton;

    @FXML
    private Button LabWorkTabButton;

    @FXML
    private Button PrescriptionsTabButton;

    @FXML
    private AnchorPane LabWorkAnchorPane;

    @FXML
    private TableView LabWorkTable;

    @FXML
    private AnchorPane ScheduleAnchorPane;

    @FXML
    private TableView ScheduleTable;

    @FXML
    private DatePicker ScheduleDatePicker;

    @FXML
    private Button ChangeAppointmentButton;

    @FXML
    private Button ChangeSlotButton;

    @FXML
    private Button PayButton;

    @FXML
    private AnchorPane PrescriptionsTabAnchorPane;

    @FXML
    private TableView PrescriptionsTable;

    @FXML
    private AnchorPane PatientAnchorPane;

    @FXML
    private Button PatientTabButton;

    @FXML
    private TableView PatientTable;

    @FXML
    void openLabWorkTab(ActionEvent event) {
        if(LabWorkTabButton.getStyleClass().size()<2)
        {
            initializeLabWorkTable();
            LabWorkAnchorPane.toFront();
            LabWorkTabButton.getStyleClass().add("tab-button-selected");
            ScheduleTabButton.getStyleClass().remove("tab-button-selected");
            PatientTabButton.getStyleClass().remove("tab-button-selected");
            PrescriptionsTabButton.getStyleClass().remove("tab-button-selected");
        }
    }

    @FXML
    void openScheduleTab(ActionEvent event) {
        if(ScheduleTabButton.getStyleClass().size()<2) {
            ScheduleAnchorPane.toFront();
            LabWorkTabButton.getStyleClass().remove("tab-button-selected");
            ScheduleTabButton.getStyleClass().add("tab-button-selected");
            PatientTabButton.getStyleClass().remove("tab-button-selected");
            PrescriptionsTabButton.getStyleClass().remove("tab-button-selected");
        }
    }

    @FXML
    void openPrescriptionsTab(){
        if(PrescriptionsTabButton.getStyleClass().size()<2) {
            initializePrescriptionTable();
            PrescriptionsTabAnchorPane.toFront();
            LabWorkTabButton.getStyleClass().remove("tab-button-selected");
            ScheduleTabButton.getStyleClass().remove("tab-button-selected");
            PatientTabButton.getStyleClass().remove("tab-button-selected");
            PrescriptionsTabButton.getStyleClass().add("tab-button-selected");
        }
    }

    @FXML
    void openPatientTab()
    {
        if(PatientTabButton.getStyleClass().size()<2) {
            initializePatientTable();
            PatientAnchorPane.toFront();
            LabWorkTabButton.getStyleClass().remove("tab-button-selected");
            ScheduleTabButton.getStyleClass().remove("tab-button-selected");
            PrescriptionsTabButton.getStyleClass().remove("tab-button-selected");
            PatientTabButton.getStyleClass().add("tab-button-selected");
        }
    }

    @FXML
    void openScheduleAddWindow(ActionEvent event)
    {
        setUserSignature();
        AddScheduleWindowController obj=new AddScheduleWindowController();
        obj.setUserSignature(userSignature);
        obj.setSchedule(schedule);
        AddScheduleWindowMain obj2=new AddScheduleWindowMain();
        Stage stage=new Stage();
        try {
            obj2.setMainWindowController(this);
            obj2.start(stage);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //System.out.println("Reach");
    }

    @FXML
    public void openChangeSlotWindow()
    {
        SingleScheduleEntry selected=(SingleScheduleEntry)ScheduleTable.getSelectionModel().getSelectedItem();
        ChangeSlotWindowMain obj=new ChangeSlotWindowMain();
        obj.setSelectedRow(selected);
        obj.setMainWindowController(this);
        obj.setSchedule(schedule);
        Stage stage=new Stage();
        try
        {
            obj.start(stage);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    public void openChangeAppointmentWindow()
    {
        SingleScheduleEntry row=(SingleScheduleEntry)ScheduleTable.getSelectionModel().getSelectedItem();
        ChangeAppointmentWindowMain obj=new ChangeAppointmentWindowMain();
        obj.setSelectedRow(row);
        obj.setMainWindowController(this);
        obj.setSchedule(schedule);
        Stage stage=new Stage();
        try
        {
            obj.start(stage);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteScheduleEntry()
    {
        SingleScheduleEntry row=(SingleScheduleEntry)ScheduleTable.getSelectionModel().getSelectedItem();
        try
        {
            ScheduleFile file=new ScheduleFile(schedule);
            if(file.deleteEntry(row.getSlot())==null)
            {
                System.out.println("Deleted successfully.");
            }
            initializeScheduleTable(schedule.getDate());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    @FXML
    public void openPayWindow()
    {
        PayWindowMain obj=new PayWindowMain();
        Stage stage=new Stage();
        obj.start(stage);
        obj.setSelected((SingleScheduleEntry)ScheduleTable.getSelectionModel().getSelectedItem());
        obj.setObj(this);
    }

    @FXML
    public void closeWindow(){
        Stage stage=(Stage) ScheduleTabButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void createRecord(){
        try {
            CreatePatientMain obj = new CreatePatientMain();
            Stage stage = new Stage();
            obj.start(stage);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void createLabWork(){
        CreateLabWorkMain obj=new CreateLabWorkMain();
        Stage stage=new Stage();
        obj.start(stage);
    }

    @FXML
    void createPrescription(){
        CreatePrescriptionMain obj=new CreatePrescriptionMain();
        Stage stage=new Stage();
        obj.start(stage);
    }

    @FXML
    void createSchedule(){
        CreateScheduleMain obj=new CreateScheduleMain();
        obj.setObj(this);
        Stage stage=new Stage();
        obj.start(stage);
    }

    @FXML
    void setScheduleDate(){
        MyDate date=new MyDate(ScheduleDatePicker.getValue().toString());
        String mydate=date.toString();
        System.out.println(mydate);
        //ScheduleTable.getColumns().clear();
        initializeScheduleTable(mydate);
    }

    public void setUserSignature(int userSignature) {
        this.userSignature = userSignature;
    }

    public void setUserSignature()
    {
        Stage stage = (Stage) ScheduleDatePicker.getScene().getWindow();
        String title = stage.getTitle();
        switch (title)
        {
            case "Dr. Parul Doshi": userSignature=0;
                break;
            case "Assistant Doctor": userSignature=1;
                break;
        }
    }

    public boolean isOwnerWindow()
    {
        setUserSignature();
        if(userSignature==0)
            return true;
        else
            return false;
    }

    public int getUserSignature()
    {
        return userSignature;
    }

    @FXML
    void testResponse(){
        System.out.println("Test successful");
    }

}