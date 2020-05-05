module ClinicGUI {
    exports sample;
    requires javafx.fxml;
    requires javafx.controls;
    requires opencsv;
    opens ClinicSoftware;
    opens sample;
}