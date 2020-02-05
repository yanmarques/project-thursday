package br.common.models.classes;

import br.common.database.DataBuilder;
import br.common.database.connection.InnerModelRelation;
import br.common.database.connection.RelationModelDAO;

import java.util.ArrayList;

public class ClassDAO extends RelationModelDAO<Class> {
    @Override
    public DataBuilder<Class> getDataBuilder() {
        return new ClassDataBuilder();
    }

    @Override
    protected void addRelations(ArrayList<InnerModelRelation> relations) {
        //
    }

    public ArrayList<Class> allWithScore() {
        this.getRelations().add(new SumAlumScoreRelation());
        ArrayList<Class> models = this.all();
        this.getRelations().clear();
        return models;
    }

    @Override
    protected StringBuilder compileSelect() {
        StringBuilder select = super.compileSelect();
        this.getCompiler().compileGroupped(select, Class.ID_ATTRIBUTE);
        return select;
    }
}
