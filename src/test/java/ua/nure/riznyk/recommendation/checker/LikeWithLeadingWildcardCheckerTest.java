package ua.nure.riznyk.recommendation.checker;

import org.junit.Test;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LikeWithLeadingWildcardCheckerTest {

    private LikeWithLeadingWildcardChecker checker = new LikeWithLeadingWildcardChecker();

    @Test
    public void check_shouldFindAsteriskInSelectClause() {
        List<Recommendation> recommendations = new ArrayList<>();

        checker.check("SELECT * FROM users WHERE users.name LIKE '%bar%'", null, recommendations);

        Recommendation actualRecommendation = recommendations.get(0);
        assertThat(actualRecommendation.getType()).isEqualTo(RecommendationType.LIKE_WITH_LEADING_WILDCARD);
    }

}