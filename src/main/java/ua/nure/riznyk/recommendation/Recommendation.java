package ua.nure.riznyk.recommendation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
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

    @Override
    public String toString() {
        return "Recommendation{" +
                "type=" + type +
                ", sql='" + sql + '\'' +
                '}';
    }
}
