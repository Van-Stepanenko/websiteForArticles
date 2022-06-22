package com.webPlusJava.demo.model;


        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.sql.*;

public class SQLrequest {
    public static Long getIdByLogin(String logUser) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("org.postgresql.Driver"); //подключаем драйвер для работы java приложения с postgresql

        } catch (ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
        Long stringValue = Long.valueOf(0);
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres","postgres");

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM login_password WHERE login_password.login ='"+logUser+"' "); //sqlRequests - команда на базу данных. rs - будет значение

            // https://metanit.com/java/database/2.5.php сайт с командами SELECT

            while (res.next()){ // пока в res есть доступные строки, будет выполнятся цикл , который будет переходить к следуюзей строке в наборе
                stringValue = res.getLong("id");// //дает значение из колонки с названием id
            }


            return stringValue;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return stringValue;
    }
    public static String getPasswordById(Long idUser) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("org.postgresql.Driver"); //подключаем драйвер для работы java приложения с postgresql

        } catch (ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
        String stringValue = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres","postgres");

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM login_password WHERE login_password.id ='"+idUser+"' "); //sqlRequests - команда на базу данных. rs - будет значение

            // https://metanit.com/java/database/2.5.php сайт с командами SELECT

            while (res.next()){ // пока в res есть доступные строки, будет выполнятся цикл , который будет переходить к следуюзей строке в наборе
                stringValue = res.getString("password");// //дает значение из колонки с названием id
            }


            return stringValue;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return stringValue;
    }

    public static String getIdUserByIdArticle(Long idArticle) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("org.postgresql.Driver"); //подключаем драйвер для работы java приложения с postgresql

        } catch (ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
        String stringValue = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", //коннект к базе
                    "postgres","postgres");

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM post WHERE post.id ='"+idArticle+"' "); //sqlRequests - команда на базу данных. rs - будет значение


            while (res.next()){ // пока в res есть доступные строки, будет выполнятся цикл , который будет переходить к следуюзей строке в наборе
                stringValue = res.getString("id_user");// //дает значение из колонки с названием id
            }


            return stringValue;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return stringValue;
    }
    public static String getNicknameById(Long idUser) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("org.postgresql.Driver"); //подключаем драйвер для работы java приложения с postgresql

        } catch (ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
        String stringValue = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres","postgres");

            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM login_password WHERE login_password.id ='"+idUser+"' "); //sqlRequests - команда на базу данных. rs - будет значение

            // https://metanit.com/java/database/2.5.php сайт с командами SELECT

            while (res.next()){ // пока в res есть доступные строки, будет выполнятся цикл , который будет переходить к следуюзей строке в наборе
                stringValue = res.getString("nickname");// //дает значение из колонки с названием id
            }


            return stringValue;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return stringValue;
    }

}
