package ua.nure.riznyk.service;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.riznyk.recommendation.Recommendation;
import ua.nure.riznyk.recommendation.RecommendationChecker;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private List<RecommendationChecker> recommendationCheckers;

    public List<Recommendation> checkForRecommendations() throws JSQLParserException {


        return null;
    }

}
