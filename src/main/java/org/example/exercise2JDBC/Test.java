package org.example.exercise2JDBC;


import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        try{
            JDBCConnection.openConnection();
            List<Client> clientList = JDBCConnection.filterAllClients();
            JDBCConnection.printList(clientList);
            JDBCConnection.closeConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }




    }
}
