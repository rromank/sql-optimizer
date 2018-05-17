package ua.nure.riznyk.recommendation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommendation {
    private RecommendationType type;
    private String sql;
}
