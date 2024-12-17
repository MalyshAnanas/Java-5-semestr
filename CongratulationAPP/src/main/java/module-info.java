module com.example.congratulationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.congratulationapp to javafx.fxml;
    exports com.example.congratulationapp;
}