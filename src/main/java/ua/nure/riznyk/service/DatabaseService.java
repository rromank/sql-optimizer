package ua.nure.riznyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class DatabaseService {

    @Autowired
    private DataSource dataSource;

    public void createAndUseDatabase(String name) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP DATABASE IF EXISTS `" + name + "`");
        statement.executeUpdate("CREATE DATABASE `" + name + "`");
        connection.setCatalog(name);
    }

    public void dropDatabase(String name) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP DATABASE IF EXISTS `" + name + "`");
    }

}
