package ua.nure.riznyk.recommendation.checker;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.ESqlStatementType;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;
import org.springframework.stereotype.Component;
import ua.nure.riznyk.model.ExplainPlan;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.List;

@Component
public class SubqueriesChecker implements RecommendationChecker {

    @Override
    public void check(String query, ExplainPlan explainPlan, List<Recommendation> recommendations) {
        query = query.replaceAll("\\n", "");
        if (hasSubqueries(query)) {
            Recommendation recommendation = new Recommendation();
            recommendation.setType(RecommendationType.SUBQUERIES);
            recommendations.add(recommendation);
        }
    }

    private boolean hasSubqueries(String query) {
        TGSqlParser sqlParser = new TGSqlParser(EDbVendor.dbvmysql);
        sqlParser.setSqltext(query);
        sqlParser.parse();

        for (int i = 0; i < sqlParser.getSqlstatements().size(); i++) {
            TSelectSqlStatement selectSqlStatement = (TSelectSqlStatement) sqlParser.getSqlstatements().get(i);

            for (int j = 0; j < selectSqlStatement.getStatements().size(); j++) {
                if (selectSqlStatement.getStatements().get(j).sqlstatementtype == ESqlStatementType.sstselect) {
                    return true;
                }
            }
        }
        return false;
    }

}
