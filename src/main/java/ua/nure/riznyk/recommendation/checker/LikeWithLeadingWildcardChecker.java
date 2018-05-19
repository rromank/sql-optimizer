package ua.nure.riznyk.recommendation.checker;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TWhereClause;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;
import org.springframework.stereotype.Component;
import ua.nure.riznyk.model.ExplainPlan;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.List;

@Component
public class LikeWithLeadingWildcardChecker implements RecommendationChecker {

    @Override
    public void check(String query, ExplainPlan explainPlan, List<Recommendation> recommendations) {
        if (hasLikeWithLeadingWildcard(query)) {
            Recommendation recommendation = new Recommendation();
            recommendation.setType(RecommendationType.LIKE_WITH_LEADING_WILDCARD);
            recommendations.add(recommendation);
        }
    }

    private boolean hasLikeWithLeadingWildcard(String query) {
        TGSqlParser sqlParser = new TGSqlParser(EDbVendor.dbvmysql);
        sqlParser.setSqltext(query);
        sqlParser.parse();

        for (int i = 0; i < sqlParser.getSqlstatements().size(); i++) {
            TSelectSqlStatement selectSqlStatement = (TSelectSqlStatement) sqlParser.getSqlstatements().get(i);
            TWhereClause where = selectSqlStatement.getWhereClause();
            if (where == null) {
                return false;
            }
            String operator = where.getCondition().getOperatorToken().getTextWithoutQuoted();
            String rightOperand = where.getCondition().getRightOperand().toScript();

            if ("LIKE".equals(operator) && rightOperand.startsWith("'%")) {
                return true;
            }
        }
        return false;
    }

}
