package ua.nure.riznyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Service
public class DatabaseService {

    private final DataSource dataSource;

    @Autowired
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String createNewDatabase() throws SQLException {
        String name = UUID.randomUUID().toString();

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP DATABASE IF EXISTS `" + name + "`");
        statement.executeUpdate("CREATE DATABASE `" + name + "`");
        return name;
    }

    public void dropDatabase(String name) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP DATABASE IF EXISTS `" + name + "`");
    }

    public Connection getConnection(String name) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setCatalog(name);
        return connection;
    }

}
