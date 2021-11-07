package ua.com.alevel.util;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ua.com.alevel.util.ConstantModuleApplication.*;

@Log4j2
public class DBConnection {

    Connection connection;

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL_DATA_BASE_CONNECTION, DATA_BASE_USER, DATA_BASE_PASSWORD);
        } catch (SQLException e) {
            log.info("Problems " + e.getMessage());
        }
        return connection;
    }
}
