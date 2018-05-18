package ua.nure.riznyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.riznyk.model.ExplainPlan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ExplainService {

    @Autowired
    private DatabaseService databaseService;

    public ExplainPlan explainQuery(String selectQuery, String schema) {
        try {
            String database = databaseService.createNewDatabase();
            Connection connection = databaseService.getConnection(database);

            Statement statement = connection.createStatement();
            statement.executeUpdate(schema);
            ExplainPlan explainPlan = explainQuery(statement, selectQuery);
            databaseService.dropDatabase(database);
            return explainPlan;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ExplainPlan();
    }

    private ExplainPlan explainQuery(Statement statement, String selectQuery) throws SQLException {
        ResultSet resultSet = statement.executeQuery("EXPLAIN " + selectQuery);
        resultSet.next();

        ExplainPlan explainPlan = new ExplainPlan();
        explainPlan.setId(resultSet.getString("id"));
        explainPlan.setSelectType(resultSet.getString("select_type"));
        explainPlan.setTable(resultSet.getString("table"));
        explainPlan.setType(resultSet.getString("type"));
        explainPlan.setPossibleKeys(resultSet.getString("possible_keys"));
        explainPlan.setKey(resultSet.getString("key"));
        explainPlan.setKey_len(resultSet.getString("key_len"));
        explainPlan.setRef(resultSet.getString("ref"));
        explainPlan.setRows(resultSet.getString("rows"));
        explainPlan.setExtra(resultSet.getString("Extra"));
        return explainPlan;
    }

}
