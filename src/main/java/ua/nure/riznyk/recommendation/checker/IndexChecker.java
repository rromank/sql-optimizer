package ua.nure.riznyk.recommendation.checker;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TTable;
import org.springframework.stereotype.Component;
import ua.nure.riznyk.model.ExplainPlan;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.*;

import static gudusoft.gsqlparser.ESqlStatementType.sstselect;

@Component
public class IndexChecker implements RecommendationChecker {

    @Override
    public void check(String query, ExplainPlan explainPlan, List<Recommendation> recommendations) {
        Set<String> columns = getColumns(query);

        Recommendation recommendation = new Recommendation();
        recommendation.setType(RecommendationType.INDEX);

        columns.stream()
                .filter(column -> !column.split("\\.")[1].equals("*"))
                .forEach(column -> {
                    String tableName = column.split("\\.")[0];
                    String columnName = column.split("\\.")[1];

                    recommendation.addSql("ALTER TABLE `" + tableName + "` ADD INDEX `" + tableName + "_idx_" + columnName + "` (`" + columnName + "`);");
                });

        if (!recommendation.getSql().isEmpty()) {
            recommendations.add(recommendation);
        }
    }

    private Set<String> getColumns(String sql) {
        Set<String> foundCols = new TreeSet<>(String::compareToIgnoreCase);
        TGSqlParser sqlParser = new TGSqlParser(EDbVendor.dbvmysql);
        sqlParser.setSqltext(sql);
        sqlParser.parse();

        for (int i = 0; i < sqlParser.getSqlstatements().size(); i++) {
            TCustomSqlStatement statement = sqlParser.getSqlstatements().get(i);
            getFromStatement(statement, foundCols);
        }
        return foundCols;
    }

    private void getFromStatement(TCustomSqlStatement statement, Set<String> foundCols) {
        if (statement.sqlstatementtype != sstselect) {
            return;
        }

        if (statement.getStatements().size() > 0) {
            for (int i = 0; i < statement.getStatements().size(); i++) {
                getFromStatement(statement.getStatements().get(i), foundCols);
            }
        }

        for (int j = 0; j < statement.getTables().size(); j++) {
            TTable table = statement.getTables().getTable(j);
            String tableName = table.getName();
            for (int k = 0; k < table.getObjectNameReferences().size(); k++) {
                String columnName = table.getObjectNameReferences().getObjectName(k).getColumnNameOnly();
                foundCols.add(tableName + "." + columnName);
            }
        }
    }
}
