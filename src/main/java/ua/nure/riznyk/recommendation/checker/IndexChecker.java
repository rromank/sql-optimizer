package ua.nure.riznyk.recommendation.checker;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TGSqlParser;
import org.springframework.stereotype.Component;
import ua.nure.riznyk.model.ExplainPlan;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.*;

@Component
public class IndexChecker implements RecommendationChecker {

    @Override
    public void check(String query, ExplainPlan explainPlan, List<Recommendation> recommendations) {
        query = clearQuery(query);
        Set<String> columns = getColumns(query);

        columns.stream()
                .filter(column -> !column.split("\\.")[1].equals("*"))
                .forEach(column ->  {
                    Recommendation recommendation = new Recommendation();
                    recommendation.setType(RecommendationType.INDEX);
                    String tableName = column.split("\\.")[0];
                    String columnName = column.split("\\.")[1];

                    recommendation.setSql("ALTER TABLE `" + tableName + "` ADD INDEX `" + tableName + "_idx_" + columnName + "` (`" + columnName + "`);");
                    recommendations.add(recommendation);
                });
    }

    private Set<String> getColumns(String sql) {
        Set<String> foundCols = new TreeSet<>(String::compareToIgnoreCase);
        TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmysql);
        sqlparser.setSqltext(sql);

        if (sqlparser.parse() == 0) {
            sqlparser.getSqlstatements().forEachRemaining(statement -> {
                for (int i = 0; i < statement.getTables().size(); i++) {
                    if (statement.tables.getTable(i).isBaseTable()) {
                        for (int j = 0; j < statement.tables.getTable(i).getObjectNameReferences().size(); j++) {
                            foundCols.add(statement.tables.getTable(i).getFullName()
                                    + "."
                                    + statement.tables.getTable(i).getObjectNameReferences().getObjectName(j).getColumnNameOnly());
                        }
                    }
                }
            });
        }
        return foundCols;
    }

}
