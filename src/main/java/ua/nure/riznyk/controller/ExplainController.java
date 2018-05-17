package ua.nure.riznyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.riznyk.dto.QueryRequest;
import ua.nure.riznyk.model.Explain;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.service.ExplainService;
import ua.nure.riznyk.service.RecommendationService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/explain")
public class ExplainController {

    private final ExplainService explainService;
    private final RecommendationService recommendationService;

    @Autowired
    public ExplainController(ExplainService explainService,
                             RecommendationService recommendationService) {
        this.explainService = explainService;
        this.recommendationService = recommendationService;
    }

    @PostMapping("/query")
    public Explain explainQuery(@RequestBody QueryRequest queryRequest) throws SQLException {
        Explain explain = explainService.explainQuery(queryRequest.getQuery(), queryRequest.getSchema());
        List<Recommendation> recommendations = recommendationService.checkForRecommendations(queryRequest.getQuery(), explain);
        return explain;
    }

}
