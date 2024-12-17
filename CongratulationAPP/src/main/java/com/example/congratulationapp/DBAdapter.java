package com.example.congratulationapp;

import java.sql.*;

public class DBAdapter {
    Connection con;
//подключаем таблицу
    void create_or_connection() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:app.sqlite"); //пытаемся подключиться к БД app.sqlite

            Statement stmt = con.createStatement();
            //формируем sql запрос
            String sql = """
                    create table if not exists userData
                    (
                        id                  integer primary key autoincrement,
                        name                text    not null,
                        gender              text    not null,
                        appeal              text    not null,
                        holiday             text    not null,
                        countCongratulation integer not null
                    );
                    
                    create table if not exists userCongratulation
                    (
                        id             integer primary key,
                        congratulation text not null
                    );""";
            stmt.execute(sql);
            System.out.println("Tables created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
//вставляем данные (сразу для двух таблиц)
    void insert_data(String name, String gender, String appeal, String holiday, int countCongratulation, String congratulation) throws SQLException {
        String sql = "insert into userData(name, gender, appeal, holiday, countCongratulation)  VALUES('" + name + "', '" + gender + "', '" + appeal + "', '" + holiday + "', " + countCongratulation + ")";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        sql = "insert into userCongratulation (id, congratulation)" +
                "values ((select id from userData where name='" + name + "' & gender='" + gender + "' " +
                "& appeal='" + appeal + "' & holiday='" + holiday + "' & countCongratulation='" + countCongratulation + "' " +
                "order by id desc limit 1) + 1, '" + congratulation + "');";
        stmt.execute(sql);
        stmt.close();
        System.out.println("Inserted data");
    }


}
