package ua.nure.riznyk.recommendation;

import ua.nure.riznyk.model.ExplainPlan;

import java.util.List;

public interface RecommendationChecker {
    void check(String query, ExplainPlan explainPlan, List<Recommendation> recommendations);
}
