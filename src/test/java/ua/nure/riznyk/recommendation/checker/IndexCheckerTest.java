package ua.nure.riznyk.recommendation.checker;

import org.junit.Test;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexCheckerTest {

    private IndexChecker checker = new IndexChecker();

    @Test
    public void check() {
        List<Recommendation> recommendations = new ArrayList<>();

        checker.check("SELECT name FROM employee WHERE salary = (\n" +
                "    SELECT MAX(salary) FROM employee_details\n" +
                ") AND age = (\n" +
                "    SELECT MAX(age) FROM employee_details\n" +
                ") AND emp_dept = 'Electronics';", null, recommendations);

        Recommendation actualRecommendation = recommendations.get(0);
        assertThat(actualRecommendation.getType()).isEqualTo(RecommendationType.INDEX);

    }
}