package br.common.database.connection;

import br.common.database.RelationCompiler;

public class InnerModelRelation {
    private ModelDAO relationDao;
    private String localColumn;
    private String referencedColumn;

    public InnerModelRelation(ModelDAO relationDao, String localColumn, String referencedColumn) {
        this.relationDao = relationDao;
        this.localColumn = localColumn;
        this.referencedColumn = referencedColumn;
    }

    public StringBuilder compileJoin(StringBuilder select, RelationCompiler compiler) {
        return compiler.compileInnerJoin(select, this.getLocalColumn(), "=", this.getReferencedColumn());
    }

    public StringBuilder compileColumns(StringBuilder select) {
        return this.getRelationDAO().getCompiler().compileColumns(select);
    }

    public ModelDAO getRelationDAO() {
        return relationDao;
    }

    public String getLocalColumn() {
        return localColumn;
    }

    public String getReferencedColumn() {
        return referencedColumn;
    }
}
