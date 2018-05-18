package ua.nure.riznyk.recommendation.checker;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TResultColumnList;
import org.springframework.stereotype.Component;
import ua.nure.riznyk.model.ExplainPlan;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AsteriskChecker implements RecommendationChecker {
    @Override
    public void check(String query, ExplainPlan explainPlan, List<Recommendation> recommendations) {
        if (hasAsteriskAsResultColumn(query)) {
            Recommendation recommendation = new Recommendation();
            recommendation.setType(RecommendationType.ASTERISK);
            recommendations.add(recommendation);
        }
    }

    private boolean hasAsteriskAsResultColumn(String query) {
        TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmysql);
        sqlparser.setSqltext(query);
        sqlparser.parse();

        Set<String> columnNames = new HashSet<>();
        for (int i = 0; i < sqlparser.getSqlstatements().size(); i++) {
            getResultColumnNames(sqlparser.getSqlstatements().get(i).getResultColumnList(), columnNames);
        }
        return columnNames.contains("*");
    }

    private void getResultColumnNames(TResultColumnList columnList, Set<String> columnNames) {
        for (int i = 0; i < columnList.size(); i++) {
            columnNames.add(columnList.getResultColumn(i).getColumnNameOnly());
        }
    }
}
