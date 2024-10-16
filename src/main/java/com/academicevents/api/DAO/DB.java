package com.academicevents.api.DAO;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class DB {
    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if(conn == null || conn.isClosed()) {
                Properties props = loadProperties();
                String url = props.getProperty("spring.datasource.url");
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Properties loadProperties() {
        try {
            FileInputStream fs = new FileInputStream("src/main/resources/application.properties");
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
