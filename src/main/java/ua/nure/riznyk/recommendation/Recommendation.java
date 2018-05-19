package ua.nure.riznyk.recommendation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class Recommendation {
    private RecommendationType type;
    private List<String> sql = new ArrayList<>();

    public void addSql(String query) {
        sql.add(query);
    }

    public RecommendationType getType() {
        return type;
    }

    public void setType(RecommendationType type) {
        this.type = type;
    }

    public List<String> getSql() {
        return sql;
    }

    public void setSql(List<String> sql) {
        this.sql = sql;
    }
}
