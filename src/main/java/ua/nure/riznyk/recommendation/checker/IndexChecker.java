//package ua.nure.riznyk.recommendation.checker;
//
//import gudusoft.gsqlparser.EDbVendor;
//import gudusoft.gsqlparser.TCustomSqlStatement;
//import gudusoft.gsqlparser.TGSqlParser;
//import net.sf.jsqlparser.JSQLParserException;
//import net.sf.jsqlparser.parser.CCJSqlParserUtil;
//import net.sf.jsqlparser.statement.select.Select;
//import net.sf.jsqlparser.util.TablesNamesFinder;
//import org.springframework.stereotype.Component;
//import ua.nure.riznyk.model.Explain;
//import ua.nure.riznyk.recommendation.Recommendation;
//import ua.nure.riznyk.recommendation.RecommendationChecker;
//import ua.nure.riznyk.recommendation.RecommendationType;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//@Component
//public class IndexChecker implements RecommendationChecker {
//
//    String columns[][] = {
//            {"dbo", "subselect3table1", "f1"},
//            {"dbo", "subselect3table2", "s3t1a1"}
//    };
//
//    @Override
//    public Recommendation check(String query, Explain explain) throws JSQLParserException {
//        query = clearQuery(query);
//        Select selectStatement = (Select) CCJSqlParserUtil.parse(query);
//
//        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
//        List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
//
//        tableList.forEach(tableName -> {
//            Recommendation recommendation = new Recommendation();
//            recommendation.setType(RecommendationType.INDEX);
//            recommendation.setSql("ALTER TABLE `" + tableName + "` ADD INDEX `" + tableName + "_idx_" + explain. + "` (`name`);");
//        });
//
//        return null;
//    }
//
//    private List<String> getColumns(String sql) {
//        TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmysql);
//        sqlparser.sqltext = sql;
//        int i = sqlparser.parse();
//        if (i == 0) {
//            WhereCondition w = new WhereCondition(sqlparser.sqlstatements.get(0).getWhereClause().getCondition());
//            return w.getColumns();
//        }
//        return new ArrayList<>();
//    }
//
//    public boolean checkColumn(String schema, String table, String column) {
//        boolean bSchema, bTable, bColumn, bRet = false;
//        for (int i = 0; i < columns.length; i++) {
//            if (schema == null) {
//                bSchema = true;
//            } else {
//                bSchema = columns[i][0].equalsIgnoreCase(schema);
//            }
//
//            if (!bSchema) continue;
//
//            bTable = columns[i][1].equalsIgnoreCase(table);
//            if (!bTable) continue;
//
//            bColumn = columns[i][2].equalsIgnoreCase(column);
//            if (!bColumn) continue;
//
//            bRet = true;
//            break;
//
//        }
//
//        return bRet;
//    }
//
//}
//
//class Finder {
//
//    String[] foundTables = new String[10000];
//    String[] foundColumns = new String[10000];
//    int foundTableCount = 0;
//    int foundColumnsCount = 0;
//    StringBuffer stringBuffer;
//    EDbVendor dbvendor;
//    private String sqltext = "";
//
//    Finder(EDbVendor db) {
//        this.dbvendor = db;
//        stringBuffer = new StringBuffer(1024);
//    }
//
//    public static void main(String args[]) {
//        Finder g = new Finder(EDbVendor.dbvmysql);
//
//        System.out.println(g.run());
//
//        // System.out.println("Time Escaped: "+ (System.currentTimeMillis() - t) );
//    }
//
//    public void setSqltext(String sqltext) {
//        this.sqltext = sqltext;
//    }
//
//    String run(String sqltext) {
//
//        TGSqlParser sqlparser = new TGSqlParser(this.dbvendor);
//
//        foundColumnsCount = 0;
//        foundTableCount = 0;
//        sqlparser.sqltext = sqltext;
//
//        // if you need a callback function to help determine
//        // table and column relationship, set it here!
//        //sqlparser.setMetaDatabase(new metaDB());
//
//        int ret = sqlparser.parse();
//        if (ret == 0) {
//
//            TCustomSqlStatement stmt = null;
//            for (int i = 0; i < sqlparser.sqlstatements.size(); i++) {
//                analyzeStmt(sqlparser.sqlstatements.get(i));
//            }
//
//            String[] foundTables2 = new String[foundTableCount];
//            for (int k1 = 0; k1 < foundTableCount; k1++) {
//                foundTables[k1] = foundTables[k1].toLowerCase();
//            }
//            System.arraycopy(foundTables, 0, foundTables2, 0, foundTableCount);
//            Set set = new HashSet(Arrays.asList(foundTables2));
//            Object[] foundTables3 = set.toArray();
//            Arrays.sort(foundTables3);
//
//            String[] foundColumns2 = new String[foundColumnsCount];
//            for (int k1 = 0; k1 < foundColumnsCount; k1++) {
//                foundColumns[k1] = foundColumns[k1].toLowerCase();
//            }
//            System.arraycopy(foundColumns, 0, foundColumns2, 0, foundColumnsCount);
//            //System.out.println("before sort:"+foundColumnsCount);
//
//            Set set2 = new HashSet(Arrays.asList(foundColumns2));
//            Object[] foundColumns3 = set2.toArray();
//            Arrays.sort(foundColumns3);
//            //System.out.println("after sort:"+foundColumns3.length);
//
//
//            //System.out.println("Tables:");
//            stringBuffer.append("Tables:\n");
//            for (int j = 0; j < foundTables3.length; j++) {
//                //System.out.println(foundTables3[j]);
//                stringBuffer.append(foundTables3[j] + "\n");
//            }
//
//            //System.out.println("\nColumns:");
//            stringBuffer.append("Columns:\n");
//            for (int j = 0; j < foundColumns3.length; j++) {
//                //System.out.println(foundColumns3[j]);
//                stringBuffer.append(foundColumns3[j] + "\n");
//            }
//
//        } else {
//            //System.out.println(sqlparser.getErrormessage());
//            stringBuffer.append(sqlparser.getErrormessage() + "\n");
//        }
//
//        return stringBuffer.toString();
//    }
//
//    protected void analyzeStmt(TCustomSqlStatement stmt) {
//        for (int i = 0; i < stmt.tables.size(); i++) {
//            if (stmt.tables.getTable(i).isBaseTable()) {
//                if ((stmt.dbvendor == EDbVendor.dbvmssql)
//                        && ((stmt.tables.getTable(i).getFullName().equalsIgnoreCase("deleted"))
//                        || (stmt.tables.getTable(i).getFullName().equalsIgnoreCase("inserted"))
//                )
//                        ) {
//                    continue;
//                }
//
//                foundTables[foundTableCount] = stmt.tables.getTable(i).getFullName();
//                foundTableCount++;
//                for (int j = 0; j < stmt.tables.getTable(i).getObjectNameReferences().size(); j++) {
//                    foundColumns[foundColumnsCount] = stmt.tables.getTable(i).getFullName() + "." + stmt.tables.getTable(i).getObjectNameReferences().getObjectName(j).getColumnNameOnly();
//                    foundColumns[foundColumnsCount] += "(table determined:" + stmt.tables.getTable(i).getObjectNameReferences().getObjectName(j).isTableDetermined() + ")";
//                    foundColumnsCount++;
//                }
//            }
//            //System.out.println(stmt.tables.getTable(i).getFullName());
//        }
//
//        for (int i = 0; i < stmt.getStatements().size(); i++) {
//            analyzeStmt(stmt.getStatements().get(i));
//        }
//    }
//
//    public String run(EDbVendor dbVendor, String sqltext) {
//        Finder g = new Finder(dbVendor);
//
//        g.setSqltext(sqltext);
//        return g.run();
//    }
//
//}
