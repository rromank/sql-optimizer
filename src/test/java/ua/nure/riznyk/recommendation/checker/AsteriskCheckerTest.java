package ua.nure.riznyk.recommendation.checker;

import org.junit.Test;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationType;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AsteriskCheckerTest {

    private AsteriskChecker asteriskChecker = new AsteriskChecker();

    @Test
    public void check_shouldFindAsteriskInSelectClause() {
        List<Recommendation> recommendations = new ArrayList<>();

        asteriskChecker.check("SELECT asd, * FROM user WHERE id = 1", null, recommendations);

        Recommendation actualRecommendation = recommendations.get(0);
        assertThat(actualRecommendation.getType()).isEqualTo(RecommendationType.ASTERISK);
    }
}