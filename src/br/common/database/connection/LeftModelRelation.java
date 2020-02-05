package br.common.database.connection;

import br.common.database.RelationCompiler;

public class LeftModelRelation extends InnerModelRelation {
    public LeftModelRelation(ModelDAO relationDao, String localColumn, String referencedColumn) {
        super(relationDao, localColumn, referencedColumn);
    }

    @Override
    public StringBuilder compileJoin(StringBuilder select, RelationCompiler compiler) {
        return compiler.compileLeftJoin(select, this.getLocalColumn(), "=", this.getReferencedColumn());
    }
}
