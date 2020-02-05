package br.common.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SQLCompiler {
    protected ArrayList<String> columns;
    protected TableMapping model;
    protected Map<String, String[]> attributesCache = new HashMap<>();

    public SQLCompiler(TableMapping model) {
        this.model = model;
        this.columns = model.getColumns();
    }

    public StringBuilder compileSelect(ArrayList<String> except) {
        StringBuilder select = new StringBuilder("SELECT");
        this.compileColumns(select, except);
        return select;
    }

    public StringBuilder compileSelect() {
        return this.compileSelect(new ArrayList<>());
    }

    public StringBuilder compileInsert(ArrayList<String> except) {
        StringBuilder insert = new StringBuilder("INSERT INTO ");
        insert.append(this.model.getTableName());
        insert.append(" (");
        this.parseColumns(insert, except, column -> {
            insert.append(column);
            insert.append(",");
        });
        insert.append(") VALUES (");
        this.parseColumns(insert, except, column -> insert.append("?,"));
        insert.append(")");
        return insert;
    }

    public StringBuilder compileInsert() {
        return this.compileInsert(new ArrayList<>());
    }

    public StringBuilder compileColumns(StringBuilder select, ArrayList<String> except) {
        return this.parseColumns(select, except, column -> {
            select.append(" ");
            select.append(this.wrap(column));
            select.append(" AS ");
            select.append(this.alias(column));
            select.append(",");
        });
    }

    public StringBuilder compileGroupped(StringBuilder sql, String column) {
        sql.append(" GROUP BY ");
        sql.append(this.wrap(column));
        return sql;
    }

    public StringBuilder compileSum(StringBuilder select, String column, String alias) {
        return this.compileFunction(select, "SUM", column, alias);
    }

    public StringBuilder compileCount(StringBuilder select, String column, String alias) {
        return this.compileFunction(select, "COUNT", column, alias);
    }

    public StringBuilder compileColumns(StringBuilder sql) {
        return this.compileColumns(sql, new ArrayList<>());
    }

    public StringBuilder compileFrom(StringBuilder select) {
        select.append(" FROM ");
        select.append(this.model.getTableName());
        return select;
    }

    public StringBuilder compileUpdate(ArrayList<String> except) {
        StringBuilder update = new StringBuilder("UPDATE ");
        update.append(this.model.getTableName());
        this.compileUpdateColumns(update, except);
        this.compileWherePrimaryKey(update);
        return update;
    }

    public StringBuilder compileUpdate() {
        return this.compileUpdate(new ArrayList<>());
    }

    public StringBuilder compileUpdateColumns(StringBuilder update, ArrayList<String> except) {
        update.append(" SET");
        return this.parseColumns(update, except, column -> {
            update.append(" ");
            update.append(column);
            update.append(" = ?,");
        });
    }

    public StringBuilder compileWhere(StringBuilder sql, String column, String operator) {
        return this.compileClauseOperator(sql, "WHERE", column, operator);
    }

    public StringBuilder compileAnd(StringBuilder sql, String column, String operator) {
        return this.compileClauseOperator(sql,"AND", column, operator);
    }

    public StringBuilder compileOrderBy(StringBuilder select, String column, String ordenation) {
        select.append(" ORDER BY ");
        select.append(this.wrap(column));
        select.append(" ");
        select.append(ordenation);
        return select;
    }

    public StringBuilder compileWherePrimaryKey(StringBuilder sql) {
        return this.compileWhere(sql, this.model.getPrimaryKeyAttribute(), "=");
    }

    public StringBuilder compileDelete() {
        StringBuilder delete = new StringBuilder("DELETE FROM ");
        delete.append(this.model.getTableName());
        this.compileWherePrimaryKey(delete);
        return delete;
    }

    public String wrap(String attribute) {
        String[] value = getAttributeFromCache(attribute);

        if (value == null) {
            value = new String[2];
            value[0] = model.qualifyColumn(attribute);
            value[1] = value[0].replace(".", "_");
            attributesCache.put(attribute, value);
        }

        return value[0];
    }

    public String alias(String attribute) {
        String[] value = getAttributeFromCache(attribute);

        if (value == null) {
            this.wrap(attribute);
            return this.alias(attribute);
        }

        return value[1];
    }

    public TableMapping getModel() {
        return model;
    }

    protected StringBuilder compileFunction(StringBuilder sql, String function, String attribute, String alias) {
        sql.append(function);
        sql.append("(");
        sql.append(this.wrap(attribute));
        sql.append(") AS ");
        sql.append(alias);
        return sql;
    }

    protected StringBuilder compileClauseOperator(StringBuilder sql, String clause, String column, String operator) {
        sql.append(" ");
        sql.append(clause);
        sql.append(" ");
        sql.append(this.wrap(column));
        sql.append(" ");
        sql.append(operator);
        sql.append(" ");
        sql.append("?");
        return sql;
    }

    protected ArrayList<String> withColumns(ArrayList<String> except) {
        ArrayList<String> newColumns = new ArrayList<>();

        this.columns.forEach(column -> {
            if (! except.contains(column)) {
                newColumns.add(column);
            }
        });

        return newColumns;
    }

    protected StringBuilder parseColumns(StringBuilder sql, ArrayList<String> except, Consumer<String> consumer) {
        ArrayList<String> columns = this.withColumns(except);

        if (columns.isEmpty()) {
            throw new IllegalArgumentException("At least 1 column must be specified.");
        }

        columns.forEach(consumer);

        // Removes the last colon.
        sql.deleteCharAt(sql.length() - 1);

        return sql;
    }

    private String[] getAttributeFromCache(String attribute) {
        return attributesCache.getOrDefault(attribute, null);
    }
}
