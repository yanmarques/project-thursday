package br.common.models.correctAnswer;

import br.common.database.DataBuilder;
import br.common.database.connection.BelongsModelRelation;
import br.common.database.connection.InnerModelRelation;
import br.common.database.connection.RelationModelDAO;
import br.common.models.answer.Answer;
import br.common.models.answer.AnswerDAO;
import br.common.models.quiz.QuizDAO;

import java.util.ArrayList;

public class CorrectAnswerDAO extends RelationModelDAO<CorrectAnswer> {
    @Override
    public DataBuilder<CorrectAnswer> getDataBuilder() {
        return new CorrectAnswerDataBuilder();
    }

    public boolean createFromAnswer(Answer answer) {
        return this.create(this.newInstanceFromAnswer(answer));
    }

    public boolean deleteFromAnswer(Answer answer) {
        return this.delete(this.newInstanceFromAnswer(answer));
    }

    @Override
    protected void addRelations(ArrayList<InnerModelRelation> relations) {
        relations.add(new BelongsModelRelation(new QuizDAO(false), CorrectAnswer.QUIZ_ID));
        relations.add(new BelongsModelRelation(new AnswerDAO(false), CorrectAnswer.ANSWER_ID));
    }

    private CorrectAnswer newInstanceFromAnswer(Answer answer) {
        CorrectAnswer correctAnswer = new CorrectAnswer();
        correctAnswer.setQuizId(answer.getQuizId());
        correctAnswer.setAnswerId(answer.getPrimaryKey());
        return correctAnswer;
    }
}
