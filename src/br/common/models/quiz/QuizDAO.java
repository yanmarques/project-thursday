package br.common.models.quiz;

import br.common.database.DataBuilder;
import br.common.database.connection.BelongsModelRelation;
import br.common.database.connection.InnerModelRelation;
import br.common.database.connection.RelationModelDAO;
import br.common.models.subject.SubjectDAO;

import java.util.ArrayList;

public class QuizDAO extends RelationModelDAO<Quiz> {
    public QuizDAO() {
        this(true);
    }

    public QuizDAO(boolean withRelations) {
        super(withRelations);
    }

    @Override
    public DataBuilder<Quiz> getDataBuilder() {
        return new QuizDataBuilder();
    }

    @Override
    protected void addRelations(ArrayList<InnerModelRelation> relations) {
        relations.add(new BelongsModelRelation(new SubjectDAO(), Quiz.SUBJECT_ID));
        relations.add(new CountCorrectAnswerRelation());
    }

    @Override
    protected StringBuilder compileSelect() {
        StringBuilder select = super.compileSelect();
        this.getCompiler().compileGroupped(select, Quiz.ID_ATTRIBUTE);
        return select;
    }
}
