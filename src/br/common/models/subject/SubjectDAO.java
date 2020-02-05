package br.common.models.subject;

import br.common.database.DataBuilder;
import br.common.database.connection.InnerModelRelation;
import br.common.database.connection.RelationModelDAO;

import java.util.ArrayList;

public class SubjectDAO extends RelationModelDAO<Subject> {
    public SubjectDAO() {
        this(true);
    }

    public SubjectDAO(boolean withRelations) {
        super(withRelations);
    }

    @Override
    public DataBuilder<Subject> getDataBuilder() {
        return new SubjectDataBuilder();
    }

    @Override
    protected void addRelations(ArrayList<InnerModelRelation> relations) {
        relations.add(new CountCuriositiesRelation());
    }

    @Override
    protected StringBuilder compileSelect() {
        StringBuilder select = super.compileSelect();
        this.getCompiler().compileGroupped(select, Subject.ID_ATTRIBUTE);
        return select;
    }
}
