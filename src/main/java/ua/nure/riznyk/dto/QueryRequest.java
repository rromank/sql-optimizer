package ua.nure.riznyk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryRequest {
    private String query;
    private String schema;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
