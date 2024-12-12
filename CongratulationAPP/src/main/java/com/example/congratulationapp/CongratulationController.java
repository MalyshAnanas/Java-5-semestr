package com.example.congratulationapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.Random;

public class CongratulationController {
    final Random random = new Random();
    @FXML
    private TextArea congratulation;

    public void EndText(String Gender, String Name, String Appeal, String Holiday, Integer countCongratulation) {
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
//        System.out.println(Holiday);
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
    }
}
