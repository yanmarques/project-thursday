package br.common.database.connection;

public class BelongsModelRelation extends InnerModelRelation {
    public BelongsModelRelation(ModelDAO relationDao, String localColumn) {
        super(relationDao, localColumn, relationDao.getCompiler().getModel().getPrimaryKeyAttribute());
    }

    public BelongsModelRelation(ModelDAO relationDao, String localColumn, String referencedColumn) {
        super(relationDao, localColumn, referencedColumn);
    }
}
