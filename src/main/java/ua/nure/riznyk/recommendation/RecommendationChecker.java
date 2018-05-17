package ua.nure.riznyk.recommendation;

import net.sf.jsqlparser.JSQLParserException;
import ua.nure.riznyk.model.Explain;

import java.util.List;

public interface RecommendationChecker {
    void check(String query, Explain explain, List<Recommendation> recommendations);

    default String clearQuery(String query) {
        return query.replaceAll("\\n", " ");
    }
}
