package ua.nure.riznyk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryRequest {
    private String query;
    private String schema;
    private String database;
}
