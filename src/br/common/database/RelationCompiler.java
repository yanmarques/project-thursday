package br.common.database;

import java.util.ArrayList;

public class RelationCompiler {
    private SQLCompiler localTable;
    private SQLCompiler referencedTable;

    public RelationCompiler(SQLCompiler localTable, SQLCompiler referencedTable) {
        this.localTable = localTable;
        this.referencedTable = referencedTable;
    }

    public StringBuilder compileSelect(ArrayList<String> locallyExcept, ArrayList<String> referencesExcept) {
        StringBuilder select = this.localTable.compileSelect(locallyExcept);
        select.append(",");
        this.referencedTable.compileColumns(select, referencesExcept);
        return select;
    }

    public StringBuilder compileFrom(StringBuilder select) {
        this.localTable.compileFrom(select);
        return select;
    }

    public StringBuilder compileSelect() {
        return this.compileSelect(new ArrayList<>(), new ArrayList<>());
    }

    public StringBuilder compileInnerJoin(StringBuilder select, String column, String operator, String references) {
        return this.compileRelation(select, "INNER JOIN", column, operator, references);
    }

    public StringBuilder compileLeftJoin(StringBuilder select, String column, String operator, String references) {
        return this.compileRelation(select, "LEFT JOIN", column, operator, references);
    }

    protected StringBuilder compileRelation(StringBuilder sql, String clause, String column, String operator,
                                            String references) {
        sql.append(" ");
        sql.append(clause);
        sql.append(" ");
        sql.append(this.referencedTable.getModel().getTableName());
        sql.append(" ON ");
        sql.append(this.localTable.wrap(column));
        sql.append(" ");
        sql.append(operator);
        sql.append(" ");
        sql.append(this.referencedTable.wrap(references));
        return sql;
    }
}
