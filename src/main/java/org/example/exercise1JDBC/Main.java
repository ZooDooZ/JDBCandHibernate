package org.example.exercise1JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {

    // private static final String USER_INSERT_SQL = "INSERT INTO user (user_name, password, language) VALUES (?,?,?)";

    public static void main(String[] args) {

        final String DB_URL = "jdbc:mysql://localhost:3306/java_134";
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "piotfil900618!F");
        connectionProps.put("serverTimezone", "CET");
        connectionProps.put("useSSL", "false");

        /**
         Zapytanie do bazy o wersje programy MySQL
         */
//        Statement stmt = null;
//
//        try(Connection conn = DriverManager.getConnection(DB_URL, connectionProps)) {
//
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select version()");
//
//            while(rs.next()){
//                System.out.println("MySQL version: " + rs.getString(1));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(stmt != null) {
//                    stmt.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * Dodawanie nowego użytkownika
         */

//        Statement stmt = null;
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, connectionProps)) {
//            stmt = conn.createStatement();

            /**
             * Dodawanie nowego użytkownika:
             */
//            stmt.executeUpdate("INSERT INTO user(user_name,password) " +
//                                    "VALUES ('Filip','qwerty')");
            /**
             * Modyfikowanie użytkownika:
             */
//            stmt.executeUpdate("UPDATE user " +
//                    "SET language = 'PL' WHERE id = 1");
//            stmt.executeUpdate("UPDATE user " +
//                    "SET user_name = 'Martin', password = 'qwerty123', language = 'EN' WHERE id = 4");
            /**
             * Usuwanie użytkownika:
             */
//            int resultUp = stmt.executeUpdate("DELETE FROM user WHERE id = 4");
//            System.out.println("Result number: " + resultUp);
            /**
             * Wyświetlanie użytkowników posługujących się danym językiem:
             */
//            ResultSet rsAll = stmt.executeQuery("SELECT * FROM user WHERE language = 'PL'");
//            while (rsAll.next()) {
//                System.out.print("\nID: " + rsAll.getInt(1));
//                System.out.print("\nUser name: " + rsAll.getString(2));
//                System.out.print("\nLanguage: " + rsAll.getString(4));
//            }


//            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
//            while(rs.next()) {
//                System.out.print("\nID: " + rs.getInt(1));
//                System.out.print("\nUser name: " + rs.getString(2));
//                System.out.print("\nPassword: " + rs.getString(3));
//                System.out.print("\nLanguage: " + rs.getString(4));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * Dodawanie użytkownika przez metodę:
         */

//        User user = new User("John", "1900rambos", "PL");
//        saveUser(user, DB_URL, connectionProps);

        /**
         * Usuwanie użytkownika przez metodę:
         */

//        User user = new User(7);
//        removeUser(user, DB_URL, connectionProps);

        /**
         *  Modyfikacja hasła przez metodę:
         */
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter new password:");
//        String newPassword = scanner.nextLine();
//
//        User user = new User(5, newPassword);
//        modifyUserPassword(user, DB_URL, connectionProps);

        /**
         *  Filtrowanie po języku i osobno po nazwie użytkownika przez metodę:
         */

        //filtrowanie po języku:
//        List<User> userByLanguage = filterUserByLanguage("PL",DB_URL, connectionProps);
//        for(User user : userByLanguage){
//            System.out.println(user.toString());
//        }

        //filtrowanie po nazwie użytkownika:
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter name which you want to search:");
//        String name = scanner.nextLine();
//        List<User> userByName = filterUserByName(name,DB_URL, connectionProps);
//        for(User user : userByName){
//            System.out.println(user.toString());
//        }
    }
    public static void saveUser (User user, String DB_URL, Properties connectionProps ){
            Statement stmt = null;

            try (Connection connection = DriverManager.getConnection(DB_URL, connectionProps)) {
                stmt = connection.createStatement();
                PreparedStatement preparedStatement
                        = connection.prepareStatement("INSERT INTO user (user_name, password, language) VALUES (?,?,?)");
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getLanguage());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void removeUser(User user, String DB_URL, Properties connectionProps ){
        Statement stmt = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, connectionProps)) {
            stmt = connection.createStatement();
            PreparedStatement preparedStatement
                    = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void modifyUserPassword(User user, String DB_URL, Properties connectionProps ){
        Statement stmt = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, connectionProps)) {
            stmt = connection.createStatement();
            PreparedStatement preparedStatement
                    = connection.prepareStatement("UPDATE user SET password = ? WHERE id = ?");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<User> filterUserByLanguage(String lang, String DB_URL, Properties connectionProps){
        Statement stmt = null;
        List<User> userByLanguage = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, connectionProps)) {
            stmt = connection.createStatement();
            PreparedStatement preparedStatement
                    = connection.prepareStatement("SELECT * FROM user WHERE language = ?");
            preparedStatement.setString(1, lang);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String language = rs.getString(4);

                userByLanguage.add(new User(id, name, password, language));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userByLanguage;
    }

    public static List<User> filterUserByName(String uName, String DB_URL, Properties connectionProps){
        Statement stmt = null;
        List<User> userByName = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, connectionProps)) {
            stmt = connection.createStatement();
            PreparedStatement preparedStatement
                    = connection.prepareStatement("SELECT * FROM user WHERE user_name = ?");
            preparedStatement.setString(1, uName);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String language = rs.getString(4);

                userByName.add(new User(id, name, password, language));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userByName;
    }
}