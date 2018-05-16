package ua.nure.riznyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.riznyk.dto.QueryRequest;
import ua.nure.riznyk.model.Explain;
import ua.nure.riznyk.service.ExplainService;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/explain")
public class ExplainController {

    @Autowired
    private ExplainService explainService;

    @PostMapping("/query")
    public Explain explainQuery(@RequestBody QueryRequest queryRequest) throws SQLException {
        return explainService.explainQuery(queryRequest.getQuery(), queryRequest.getSchema(), queryRequest.getDatabase());
    }

}
