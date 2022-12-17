package org.example.exercise1JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Exercise1JDBC {

    private static Connection connection;

    public static void main(String[] args) {

        /**
         * ZADANIA Z BAZY DANYCH - SLAJD 43 - KURS SDA "Katarzyna Musioł - Bazy danych (JDBC, Hibernate)"
         *
         * 1. Zapytanie zwracające z bazy dane użytkownika o podanej nazwie.
         * > Spróbuj wyciągnąć dane wszystkich użytkowników z bazy
         */

//        try {
//            openConnection();
//            printList(findUserByName());
//            closeConnection();
//        } catch(SQLException e){
//            e.printStackTrace();
//        }

        /**
         * 2. Zapytanie zwracające z bazy użytkowników o wybranym języku.
         * > Spróbuj usunąć tabelę Użytkownik z bazy
         */

        try {
            openConnection();
            printList(findUserByLanguage());
            closeConnection();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void openConnection() throws SQLException {
        final String DB_URL = "jdbc:mysql://localhost:3306/java_134";
        Properties properties = new Properties();
        properties.put("user","root");
        properties.put("password","piotfil900618!F");
        properties.put("serverTimezone","CET");
        properties.put("useSSL","false");

        connection = DriverManager.getConnection(DB_URL, properties);
    }

    public static void closeConnection() throws SQLException{
        connection.close();
    }

    public static List<User> findUserByName() throws SQLException {
        // it's a hack on data base ****************************************************************
        String name = "blablabla' or true or '";

        List<User> userFilterByName = new ArrayList<>();

        Statement stmt = connection.createStatement();

        String searchSql = "SELECT * FROM user WHERE user_name  = '" + name + "';";

        ResultSet rs = stmt.executeQuery(searchSql);

        while(rs.next()){
            int id = rs.getInt(1);
            String userName = rs.getString(2);
            String password = rs.getString(3);
            String language = rs.getString(4);

            userFilterByName.add(new User(id, userName, password, language));
        }
        return userFilterByName;
    }

    public static List<User> findUserByLanguage() throws SQLException {
        List<User> listUserByLanguage = new ArrayList<>();

//        Scanner scanner = new Scanner(System.in);
        String lang = "PL'; DELETE FROM user WHERE id='12";

        String searchSql = "SELECT * FROM user WHERE language  = '" + lang + "';";

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(searchSql);

        while (rs.next()) {
            int id = rs.getInt(1);
            String userName = rs.getString(2);
            String password = rs.getString(3);
            String language = rs.getString(4);

            listUserByLanguage.add(new User(id, userName, password, language));
        }
        return listUserByLanguage;
    }
    public static void printList(List<User> list){
        for(User user : list){
            System.out.println(user);
        }
    }
}
