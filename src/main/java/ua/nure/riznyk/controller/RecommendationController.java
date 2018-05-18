package ua.nure.riznyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.riznyk.dto.QueryRequest;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.service.ExplainService;
import ua.nure.riznyk.service.QueryService;
import ua.nure.riznyk.service.RecommendationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendation")
public class RecommendationController {

    private final ExplainService explainService;
    private final RecommendationService recommendationService;
    private final QueryService queryService;

    @Autowired
    public RecommendationController(ExplainService explainService,
                                    RecommendationService recommendationService, QueryService queryService) {
        this.explainService = explainService;
        this.recommendationService = recommendationService;
        this.queryService = queryService;
    }

    @PostMapping("/query")
    public List<Recommendation> checkForRecommendations(@RequestBody QueryRequest queryRequest) {
        queryService.checkQuery(queryRequest.getQuery());

        return recommendationService.checkForRecommendations(queryRequest.getQuery(), queryRequest.getSchema());
    }

}
