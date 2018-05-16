package ua.nure.riznyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.riznyk.model.Explain;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ExplainService {

    @Autowired
    private DataSource dataSource;

    public Explain explainQuery(String selectQuery, String schema, String database) throws SQLException {
        System.out.println("Query: ");
        System.out.println(selectQuery);
        System.out.println("Schema: ");
        System.out.println(schema);

        Connection connection = dataSource.getConnection();
        connection.setCatalog(database);

        Statement statement = connection.createStatement();
        statement.executeUpdate(schema);
        return explainQuery(statement, selectQuery);
    }

    private Explain explainQuery(Statement statement, String selectQuery) throws SQLException {
        ResultSet resultSet = statement.executeQuery("EXPLAIN " + selectQuery);
        resultSet.next();

        Explain explain = new Explain();
        explain.setId(resultSet.getString("id"));
        explain.setSelectType(resultSet.getString("select_type"));
        explain.setTable(resultSet.getString("table"));
        explain.setType(resultSet.getString("type"));
        explain.setPossibleKeys(resultSet.getString("possible_keys"));
        explain.setKey(resultSet.getString("key"));
        explain.setKey_len(resultSet.getString("key_len"));
        explain.setRef(resultSet.getString("ref"));
        explain.setRows(resultSet.getString("rows"));
        explain.setExtra(resultSet.getString("Extra"));
        return explain;
    }

}
