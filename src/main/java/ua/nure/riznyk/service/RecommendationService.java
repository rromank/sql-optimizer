package ua.nure.riznyk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.riznyk.model.Explain;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private List<RecommendationChecker> recommendationCheckers;

    public List<Recommendation> checkForRecommendations(String query, Explain explain) {
        List<Recommendation> recommendations = new ArrayList<>();
        recommendationCheckers.forEach(checker -> checker.check(query, explain, recommendations));
        return recommendations;
    }

}
