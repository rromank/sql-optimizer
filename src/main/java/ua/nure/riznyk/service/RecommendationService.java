package ua.nure.riznyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.riznyk.model.ExplainPlan;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private ExplainService explain;

    @Autowired
    private List<RecommendationChecker> recommendationCheckers;

    public List<Recommendation> checkForRecommendations(String query, String schema) {
        ExplainPlan explainPlan = explain.explainQuery(query, schema);

        List<Recommendation> recommendations = new ArrayList<>();
        recommendationCheckers.forEach(checker -> checker.check(query, explainPlan, recommendations));
        return recommendations;
    }

}
