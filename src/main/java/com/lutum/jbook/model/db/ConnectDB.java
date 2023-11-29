package com.lutum.jbook.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectDB {
    
    public Connection connectDB() {

        Connection connect = null;

        try {
            
            String url = "jdbc:mysql://18.230.6.129/HT3014401?user=HT3014401&password=HT3014401";
            connect = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println();
            System.err.println("Ocorreu um erro: " + e.getMessage());
            System.exit(0);
        }

        return connect;
    }

}