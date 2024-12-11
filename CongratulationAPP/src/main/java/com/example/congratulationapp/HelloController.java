package com.example.congratulationapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        welcomeText.setText("Ваше поздравление готово!!!");
        Stage stage = (Stage) (welcomeText.getScene().getWindow());
        stage.hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("congratulation-view.fxml"));
        Scene scene = new Scene(loader.load(), 660, 460);
        stage = new Stage();
        stage.setTitle("~~~Поздравление~~~");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        CongratulationController goodbyeController = loader.getController();
    }
}