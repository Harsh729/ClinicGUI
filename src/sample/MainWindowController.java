package sample;

import ClinicSoftware.*;
import javafx.collections.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private static String dir=System.getProperty("user.dir")+"\\Directories\\";



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

            initializeButtons();
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

            initializeButtons();
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
        schedule=new Schedule("", userSignature);
        ScheduleFile file=new ScheduleFile(date,schedule.getUserSignature());
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

            initializeScheduleButtons();

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
        TableColumn description = new TableColumn("Description");
        TableColumn money=new TableColumn("Pending amount");

        PatientTable.getColumns().addAll(patientName,phone,age,description,money);

//        patientName.setCellValueFactory(new PropertyValueFactory<Patient,String>("name"));
//        phone.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
//        age.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("age"));
//        description.setCellValueFactory(new PropertyValueFactory<Patient,String>("desc"));
//        money.setCellValueFactory(new PropertyValueFactory<Patient,Double>("money"));

        ObservableList<PatientTableWrapper> data2 = FXCollections.observableArrayList();

        try{
            File folder=new File(dir+"Records\\");
            File[] RecordFiles=folder.listFiles();
            ObservableList<Patient> data=FXCollections.observableArrayList();
            for(File file: RecordFiles)
            {
                PatientFile patientFile =new PatientFile(file.getName().split("\\.")[0]);
                Patient rec= patientFile.readFile();
                if(rec!=null) {
                    //rec.buildAppointments(userSignature);
                    PatientTableWrapper pat = new PatientTableWrapper(rec);
                    data.add(rec);
                    data2.add(pat);
                }
            }
           //PatientTable.setItems(data);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        patientName.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper,String>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper,String>("phone"));
        age.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper,Integer>("age"));
        description.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper,String>("desc"));
        money.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper,Double>("money"));

        TableColumn latest = new TableColumn("Latest");
        TableColumn heart = new TableColumn("Heart Condition");
        TableColumn allergy = new TableColumn("Allergies");
        TableColumn bp = new TableColumn("Blood Pressure");
        TableColumn diabetes = new TableColumn("Diabetes");

        PatientTable.getColumns().addAll(latest,heart,allergy,bp,diabetes);

        latest.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper, String>("latest"));
        heart.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper, ImageView>("heart"));
        allergy.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper, ImageView>("allergy"));
        bp.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper, ImageView>("bp"));
        diabetes.setCellValueFactory(new PropertyValueFactory<PatientTableWrapper, ImageView>("diabetes"));

        heart.setMaxWidth(60);
        allergy.setMaxWidth(60);
        bp.setMaxWidth(60);
        diabetes.setMaxWidth(60);
        PatientTable.setItems(data2);

        initializeButtons();

    }

    public void initializeScheduleButtons()
    {
        DeleteScheduleEntryButton.setDisable(true);
        ChangeAppointmentButton.setDisable(true);
        ChangeSlotButton.setDisable(true);
        PayButton.setDisable(true);
    }

    public void initializeButtons()
    {
        DeleteLabWorkButton.setDisable(true);
        DeletePatientButton.setDisable(true);
        DeletePrescriptionButton.setDisable(true);
        DisplayPatientDetailsButton.setDisable(true);
        PrescriptionDetailsButton.setDisable(true);
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
    private MenuItem Menu_File_CreatePatient;

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
    private Button DeleteLabWorkButton;

    @FXML
    private Button DeletePrescriptionButton;

    @FXML
    private Button DeletePatientButton;

    @FXML
    private Button AddLabWorkButton;

    @FXML
    private Button AddPrescriptionButton;

    @FXML
    private Button AddPatientButton;

    @FXML
    private AnchorPane ScheduleAnchorPane;

    @FXML
    private TableView ScheduleTable;

    @FXML
    private DatePicker ScheduleDatePicker;

    @FXML
    private Button DeleteScheduleEntryButton;

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
    private Button DisplayPatientDetailsButton;

    @FXML
    private Button PrescriptionDetailsButton;

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
    public void checkIfSelectedSchedule()
    {
        if(!ScheduleTable.getSelectionModel().isEmpty()) {
            DeleteScheduleEntryButton.setDisable(false);
            ChangeSlotButton.setDisable(false);
            ChangeAppointmentButton.setDisable(false);
            PayButton.setDisable(false);
        }
    }

    @FXML
    public void checkIfSelectedPatient()
    {
        if(!PatientTable.getSelectionModel().isEmpty()){
            DeletePatientButton.setDisable(false);
            DisplayPatientDetailsButton.setDisable(false);
        }
    }

    @FXML
    public void checkIfSelectedPrescription()
    {
        if(!PrescriptionsTable.getSelectionModel().isEmpty()){
            DeletePrescriptionButton.setDisable(false);
            PrescriptionDetailsButton.setDisable(false);
        }
    }

    @FXML
    public void checkIfSelectedLabWork()
    {
        if(!LabWorkTable.getSelectionModel().isEmpty()){
            DeleteLabWorkButton.setDisable(false);
        }
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
                schedule.remove(row.getSlot());
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
        SingleScheduleEntry check = (SingleScheduleEntry)ScheduleTable.getSelectionModel().getSelectedItem();
        check.updateAppointment();
        obj.setSelected(check);
        obj.setObj(this);
    }

    @FXML
    public void closeWindow(){
        Stage stage=(Stage) ScheduleTabButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void createPatient(){
        try {
            CreatePatientMain obj = new CreatePatientMain();
            Stage stage = new Stage();
            obj.setMainWindowController(this);
            obj.start(stage);
            //initializePatientTable(); can't do this as the CreatePatientWindow gets terminated after this line is executed.
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void deletePatient()
    {
        PatientTableWrapper wrap = (PatientTableWrapper)PatientTable.getSelectionModel().getSelectedItem();
        PatientFile file = new PatientFile(wrap.getFileName());
        DeleteConfirmationMain obj = new DeleteConfirmationMain();
        obj.setFile(file);
        obj.setObj(this);
        try {
            obj.start(new Stage());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void openPatientDetailsWindow()
    {
        PatientDetailsWindowMain obj = new PatientDetailsWindowMain();
        PatientTableWrapper wrap = (PatientTableWrapper)PatientTable.getSelectionModel().getSelectedItem();
        PatientFile file = new PatientFile(wrap.getFileName());
        try {
            Patient patient = file.readFile();
            obj.setPatient(patient);
            Stage stage = new Stage();
            obj.start(stage);
        }
        catch(Exception e)
        {
            System.out.print("Exception at openPatientWindow:");
            e.printStackTrace();

        }
    }
    @FXML
    void createLabWork(){
        CreateLabWorkMain obj=new CreateLabWorkMain();
        obj.setMainWindowController(this);
        Stage stage=new Stage();
        obj.start(stage);
    }

    @FXML
    void deleteLabWork(){
        LabWork selected = (LabWork)LabWorkTable.getSelectionModel().getSelectedItem();
        LabWorkFile file = new LabWorkFile(selected.getFileName());//file to be deleted
        if(file.deleteFile())
            System.out.println("File Deleted Successfully");
        initializeLabWorkTable();
    }

    @FXML
    void createPrescription(){
        CreatePrescriptionMain obj=new CreatePrescriptionMain();
        Stage stage=new Stage();
        obj.start(stage);
    }

    @FXML
    void deletePrescription(){
        Prescription selected = (Prescription)PrescriptionsTable.getSelectionModel().getSelectedItem();
        PrescriptionFile file = new PrescriptionFile(selected.getFileName());
        if(file.deleteFile())
        {
            System.out.println("File deleted successfully");
        }
        initializePrescriptionTable();
    }

    @FXML
    public void openPrescriptionDetailsWindow()
    {
        try {
            Prescription prescription = (Prescription) PrescriptionsTable.getSelectionModel().getSelectedItem();
            PrescriptionFile file = new PrescriptionFile(prescription.getFileName());
            prescription = file.readFile();
            PrescriptionDetailsWindowController.setPrescription(prescription);
            PrescriptionDetailsWindowMain obj = new PrescriptionDetailsWindowMain();
            obj.start(new Stage());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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

    public static void doLabWorkReminder()
    {
        LabWorkReminderController obj = new LabWorkReminderController();
        MyDate today = new MyDate();
        File folder = new File(dir+"Lab Work\\");
        File[] files = folder.listFiles();
        for(File file:files)
        {
            LabWorkFile lf = new LabWorkFile(file.getName().split("\\.")[0]);
            LabWork lb = lf.readFile();
            if(lb!=null)//avoiding NullPointerException
            {
                if(today.toString().equals(lb.getReceivedDate()))
                    obj.addLabWorkToList(lb);
            }
        }
        if(!obj.isListEmpty()) {
            try {
                LabWorkReminderMain obj2 = new LabWorkReminderMain();
                obj2.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList search(String search)
    {
        ObservableList<String> data = FXCollections.observableArrayList();
        PatientFile pf = new PatientFile("");
        File folder = new File(dir+pf.getFolderName());
        File files[] = folder.listFiles();
        for(File file:files)
        {
            String fileName = file.getName().split("\\.")[0];
            if(fileName.contains(search))
            {
                data.add(fileName);
            }
        }
        return data;
    }

    public void useSearchInPatientTable(String fileName)
    {
        PatientFile pf = new PatientFile(fileName);
        try {
            Patient searched = pf.readFile();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}