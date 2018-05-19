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
}
