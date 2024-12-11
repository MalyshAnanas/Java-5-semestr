module com.example.congratulationapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.congratulationapp to javafx.fxml;
    exports com.example.congratulationapp;
}