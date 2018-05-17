package ua.nure.riznyk.recommendation.checker;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TGSqlParser;

import java.util.Set;
import java.util.TreeSet;


class Finder {
    public static void main(String args[]) {
        Finder finder = new Finder();
        System.out.println(finder.run("SELECT name FROM table WHERE col = 2"));
    }

    private Set<String> run(String sql) {
        Set<String> foundCols = new TreeSet<>(String::compareToIgnoreCase);
        TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvmysql);
        sqlparser.setSqltext(sql);

        if (sqlparser.parse() == 0) {
            sqlparser.getSqlstatements().forEachRemaining(statement -> {
                for (int i = 0; i < statement.getTables().size(); i++) {
                    if (statement.tables.getTable(i).isBaseTable()) {
                        for (int j = 0; j < statement.tables.getTable(i).getObjectNameReferences().size(); j++) {
                            foundCols.add(statement.tables.getTable(i).getFullName()
                                    + "."
                                    + statement.tables.getTable(i).getObjectNameReferences().getObjectName(j).getColumnNameOnly());
                        }
                    }
                }
            });
        }
        return foundCols;
    }
}
