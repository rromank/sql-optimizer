package ua.nure.riznyk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.riznyk.dto.DatabaseResponse;
import ua.nure.riznyk.dto.QueryRequest;
import ua.nure.riznyk.service.DatabaseService;

import java.sql.SQLException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/create")
    public DatabaseResponse createTempDatabase() throws SQLException {
        String databaseName = UUID.randomUUID().toString();
        databaseService.createAndUseDatabase(databaseName);
        return new DatabaseResponse(databaseName);
    }

    @PostMapping("/drop/{name}")
    public void dropDatabase(@PathVariable("name") String name) throws SQLException {
        databaseService.dropDatabase(name);
    }

}
