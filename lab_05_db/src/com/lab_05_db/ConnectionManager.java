package com.lab_05_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private final Connection _connection;

    public ConnectionManager(String url, String user, String password)
            throws SQLException {
        _connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return _connection;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            _connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.finalize();
        }
    }
}
