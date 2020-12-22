package com.lab_05_db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<K, T> {
    protected Connection _connection;

    public DAO(Connection connection) {
        _connection = connection;
    }

    public abstract List<T> findAll();
    public abstract boolean delete(K id);

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;

        try {
            ps = _connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
