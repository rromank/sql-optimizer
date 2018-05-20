package ua.nure.riznyk.recommendation.checker;

import org.junit.Test;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubqueriesCheckerTest {

    private SubqueriesChecker checker = new SubqueriesChecker();

    @Test
    public void check_whenSubqueryInAssignsToColumn() {
        List<Recommendation> recommendations = new ArrayList<>();

        checker.check("SELECT column1 = (SELECT name FROM table WHERE id = 1), column2 FROM table2 WHERE id = 2", null, recommendations);

        Recommendation actualRecommendation = recommendations.get(0);
        assertThat(actualRecommendation.getType()).isEqualTo(RecommendationType.SUBQUERIES);
    }

    @Test
    public void check_whenSubqueryInWhereClause() {
        List<Recommendation> recommendations = new ArrayList<>();

        checker.check("SELECT name FROM employee WHERE salary = (\n" +
                "    SELECT MAX(salary) FROM employee_details\n" +
                ") AND age = (\n" +
                "    SELECT MAX(age) FROM employee_details\n" +
                ") AND emp_dept = 'Electronics';", null, recommendations);

        Recommendation actualRecommendation = recommendations.get(0);
        assertThat(actualRecommendation.getType()).isEqualTo(RecommendationType.SUBQUERIES);
    }

}