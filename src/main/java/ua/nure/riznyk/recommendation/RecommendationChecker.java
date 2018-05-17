package ua.nure.riznyk.recommendation;

import net.sf.jsqlparser.JSQLParserException;
import ua.nure.riznyk.model.Explain;

public interface RecommendationChecker {
    Recommendation check(String query, Explain explain) throws JSQLParserException;

    default String clearQuery(String query) {
        return query.replaceAll("\\n", " ");
    }
}
