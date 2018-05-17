package ua.nure.riznyk.recommendation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recommendation {
    private RecommendationType type;
    private String sql;

    public RecommendationType getType() {
        return type;
    }

    public void setType(RecommendationType type) {
        this.type = type;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
