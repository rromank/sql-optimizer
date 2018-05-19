package ua.nure.riznyk.recommendation.checker;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TGroupBy;
import gudusoft.gsqlparser.nodes.TOrderBy;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;
import org.springframework.stereotype.Component;
import ua.nure.riznyk.model.ExplainPlan;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.List;

@Component
public class GroupByWithoutOrderByChecker implements RecommendationChecker {
    @Override
    public void check(String query, ExplainPlan explainPlan, List<Recommendation> recommendations) {
        if (hasGroupByWithoutOrderBy(query)) {
            Recommendation recommendation = new Recommendation();
            recommendation.setType(RecommendationType.GROUP_BY_WITHOUT_ORDER_BY);
            recommendations.add(recommendation);
        }
    }

    private boolean hasGroupByWithoutOrderBy(String query) {
        TGSqlParser sqlParser = new TGSqlParser(EDbVendor.dbvmysql);
        sqlParser.setSqltext(query);
        sqlParser.parse();

        for (int i = 0; i < sqlParser.getSqlstatements().size(); i++) {
            TSelectSqlStatement selectSqlStatement = (TSelectSqlStatement) sqlParser.getSqlstatements().get(i);
            TGroupBy groupBy = selectSqlStatement.getGroupByClause();
            TOrderBy orderBy = selectSqlStatement.getOrderbyClause();
            if (groupBy != null && orderBy == null) {
                return true;
            }
        }
        return false;
    }
}
