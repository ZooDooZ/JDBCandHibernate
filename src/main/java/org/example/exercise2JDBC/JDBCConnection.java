package org.example.exercise2JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCConnection {

    /**
        KOLEJNOŚĆ TWORZENIA APLIKACJI JDBC:
     1. Dodawanie sterownika bazy danych do <dependency> oraz zaimportowanie java.sql.*
     2. Otworzenie połączenia z bazą za sprawą obiektu z klasy Connection -> np. Connection connection.
     nastepnie korzystamy z klasy DriverManager, połączenie otwierane jest za pomocą adresu i danych użytkownika
     (klasa Properties) -> connection = DriverManager.getConnection('adres URL', 'properties'(dane użytkownika w obiekcie)).
     3. Wykonanie zapytania przy pomocy obiektu Statement lub PreparedStatement w formie tekstu - String.
     4. Do odczytu danych wykorzystuje się metode executeQuery() a do modyfikacji danych executeUpdate() którą następnie
     zapisuje się do obiektu klasy ResultSet i pożniej np w przypadku odczytu korzystamy z petli while stosując metode
     resultSet.next() do iteracji po wynikach.
     5. Na koniec należy pozamykać wszystkie połączenia z bazą danych m.in.: ResultSet, Statement/PreparedStatement i Connection

     */

    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;
    final private static String SELECT_ALL_CLIENTS_SQL = "SELECT * FROM clients;";

    public static void openConnection() throws SQLException {
        final String DB_URL = "jdbc:mysql://localhost:3306/akademia_holistyczna";
        Properties properties = new Properties();
        properties.put("user","root");
        properties.put("password","piotfil900618!F");
        properties.put("serverTimezone","CET");
        properties.put("useSSL","false");

        connection = DriverManager.getConnection(DB_URL, properties);
    }

    public static void closeConnection() throws SQLException{
        connection.close();
        ps.close();
        rs.close();
    }

    public static List<Client> filterAllClients() throws SQLException{
        List<Client> allClients = new ArrayList<>();

        ps = connection.prepareStatement(SELECT_ALL_CLIENTS_SQL);

        rs = ps.executeQuery();

        while(rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            int age = rs.getInt(4);
            String address = rs.getString(5);
            String statement = rs.getString(6);
            String description = rs.getString(7);

            allClients.add(new Client(id,name,surname,age,address,statement,description));
        }
        return allClients;
    }

    public static void printList(List<Client> list){
        for(Client client : list){
            System.out.println("\n" + client);
        }

    }
}
