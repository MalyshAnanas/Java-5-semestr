package com.example.congratulationapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField Name;

    @FXML
    private TextField countCongratulation;

    @FXML
    private ChoiceBox <String> Gender;

    @FXML
    private ChoiceBox <String> Appeal;

    @FXML
    private ChoiceBox <String> Holiday;

    public static boolean Eto_Chislo_Vopros(String str) { //даёт true, если str - число
        try {
            Integer.parseInt(str);

            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @FXML
    private void onHelloButtonClick() throws IOException, SQLException {
        String nameText = Name.getText();
        String getGender = Gender.getValue();
        String getAppeal = Appeal.getValue();
        String getHoliday = Holiday.getValue();
        if (Eto_Chislo_Vopros(countCongratulation.getText()) && !nameText.isEmpty() && Eto_Chislo_Vopros(countCongratulation.getText())) { // Если ввели правильно
            // создаем новое окно
            Stage stage = (Stage) (welcomeText.getScene().getWindow());
            stage.hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("congratulation-view.fxml")); //загружает новое окно
            Scene scene = new Scene(loader.load(), 660, 460);
            stage = new Stage();
            stage.setTitle("~~~Поздравление~~~");
            stage.setScene(scene);
            stage.setResizable(false); // запрет на изменение размера
            stage.show();

            CongratulationController CongratulationController = loader.getController(); //получаем объект класса CongratulationController
            CongratulationController.EndText(getGender, nameText, getAppeal, getHoliday, Integer.parseInt(countCongratulation.getText()));
            //Вызываем метод
        }
        else {
            welcomeText.setText("Заполните все поля КОРРЕКТНО!!!");
        }
    }

}