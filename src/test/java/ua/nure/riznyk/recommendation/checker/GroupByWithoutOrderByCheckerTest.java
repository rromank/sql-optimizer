package ua.nure.riznyk.recommendation.checker;

import org.junit.Test;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupByWithoutOrderByCheckerTest {

    private GroupByWithoutOrderByChecker checker = new GroupByWithoutOrderByChecker();

    @Test
    public void check() {
        List<Recommendation> recommendations = new ArrayList<>();

        checker.check("SELECT * FROM test_table tbl1 GROUP BY tbl1.name", null, recommendations);

        Recommendation actualRecommendation = recommendations.get(0);
        assertThat(actualRecommendation.getType()).isEqualTo(RecommendationType.GROUP_BY_WITHOUT_ORDER_BY);
    }

}