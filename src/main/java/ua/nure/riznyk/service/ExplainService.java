package ua.nure.riznyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.riznyk.model.Explain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ExplainService {

    @Autowired
    private DatabaseService databaseService;

    public Explain explainQuery(String selectQuery, String schema) throws SQLException {
        String database = databaseService.createNewDatabase();
        Connection connection = databaseService.getConnection(database);

        Statement statement = connection.createStatement();
        statement.executeUpdate(schema);
        Explain explain = explainQuery(statement, selectQuery);
        databaseService.dropDatabase(database);
        return explain;
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
