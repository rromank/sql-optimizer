package ua.nure.riznyk.service;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TGSqlParser;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    public void checkQuery(String query) {
        TGSqlParser sqlParser = new TGSqlParser(EDbVendor.dbvmysql);
        sqlParser.setSqltext(query);
        if (sqlParser.parse() != 0) {
            throw new IllegalArgumentException("Query is not valid: " + sqlParser.getErrormessage());
        }
    }

}
