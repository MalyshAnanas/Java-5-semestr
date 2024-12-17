package com.example.congratulationapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class CongratulationController implements Initializable { //implements Initializable - наследование интерфейса для инициализации
    Random random = new Random();
    DBAdapter adapter = new DBAdapter(); //получаем объект класса DBAdapter
    @FXML
    private TextArea congratulation;

    public void EndText(String Gender, String Name, String Appeal, String Holiday, Integer countCongratulation) throws SQLException {
        /*
         * Дорогой, Name!
         * Поздравляю Appeal с Holiday! Желаю тебе Wishes
         * */
        String temp;
        String result = "";
        if (Gender.equals("Мужской")) {temp = Congratulation.GenderList[0];}
        else {temp = Congratulation.GenderList[1];}
        result += temp;
        result += ", " + Name + "!\n" + "Поздравляю ";

        if (Appeal.equals("Ты")){temp = Congratulation.AppealList[0];}
        else {temp = Congratulation.AppealList[1];}

        result += temp + "c ";
        if (Holiday.equals("Новый год")){
            temp = "Новым годом";
        }
        else if (Holiday.equals("День рождения")) {
            temp = "Днём рождения";
        }
        else if (Holiday.equals("Хороший день")) {
            int randomIndex = random.nextInt(Congratulation.GoodDayList.length);
            temp = Congratulation.GoodDayList[randomIndex];
        }

        result += temp + "! Желаю ";
        if (Appeal.equals("Ты")){temp = "тебе ";}
        else {temp = "Вам ";}

        result += temp;

        for (int i = 0 ; i < countCongratulation; i++){
            int randomIndex = random.nextInt(Congratulation.Wishes.length);
            result += Congratulation.Wishes[randomIndex];
            if (i != countCongratulation - 1){
                result += ", ";
            }
            else {
                result += "!";
            }
        }
        congratulation.setText(result);
        adapter.insert_data(Name, Gender, Appeal, Holiday, countCongratulation, result); //Записываем наши данные в БД
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adapter.create_or_connection();
    } //Подключаемся к БД во время создания окна
}
