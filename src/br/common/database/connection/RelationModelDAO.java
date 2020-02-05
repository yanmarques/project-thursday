package br.common.database.connection;

import br.common.database.TableMapping;

import java.util.ArrayList;

abstract public class RelationModelDAO<T extends TableMapping> extends ModelDAO<T> {
    private ArrayList<InnerModelRelation> relations;

    public RelationModelDAO() {
        this(true);
    }

    public RelationModelDAO(boolean withRelations) {
        super();
        this.relations = new ArrayList<>();

        if (withRelations) {
            this.addRelations(relations);
        }
    }

    abstract protected void addRelations(ArrayList<InnerModelRelation> relations);

    public ArrayList<InnerModelRelation> getRelations() {
        return relations;
    }

    public InnerModelRelation getInnerRelation(ModelDAO modelDAO, String referencedColumn) {
        String localColumn = this.getCompiler().getModel().getPrimaryKeyAttribute();
        return new InnerModelRelation(modelDAO, localColumn, referencedColumn);
    }

    public LeftModelRelation getLeftRelation(ModelDAO modelDAO, String referencedColumn) {
        String localColumn = this.getCompiler().getModel().getPrimaryKeyAttribute();
        return new LeftModelRelation(modelDAO, localColumn, referencedColumn);
    }

    @Override
    protected StringBuilder compileSelect() {
        if (this.getRelations().isEmpty()) {
            return super.compileSelect();
        }

        StringBuilder select = this.getCompiler().compileSelect();

        for (InnerModelRelation modelRelation: this.getRelations()) {
            select.append(", ");
            modelRelation.compileColumns(select);
        }

        this.getCompiler().compileFrom(select);

        for (InnerModelRelation modelRelation: this.getRelations()) {
            modelRelation.compileJoin(select, this.getRelationCompiler(modelRelation.getRelationDAO()));
        }

        return select;
    }
}
