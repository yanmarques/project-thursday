package br.common.models.answer;

import br.common.database.DataBuilder;
import br.common.database.connection.InnerModelRelation;
import br.common.database.connection.RelationModelDAO;
import br.common.models.correctAnswer.CorrectAnswer;
import br.common.models.correctAnswer.CorrectAnswerDAO;
import br.common.models.quiz.Quiz;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerDAO extends RelationModelDAO<Answer> {
    public AnswerDAO() {
        this(true);
    }

    public AnswerDAO(boolean withRelations) {
        super(withRelations);
    }

    @Override
    public DataBuilder<Answer> getDataBuilder() {
        return new AnswerDataBuilder();
    }

    public boolean create(Answer answer) {
        if (super.create(answer)) {
            if (answer.isCorrectAnswer()) {
                return new CorrectAnswerDAO().createFromAnswer(answer);
            }

            return true;
        }

        return false;
    }

    public boolean delete(Answer answer) {
        if (super.delete(answer)) {
            if (answer.isCorrectAnswer()) {
                return new CorrectAnswerDAO().deleteFromAnswer(answer);
            }

            return true;
        }

        return false;
    }

    public int update(Answer answer) {
        int result = super.update(answer);

        if (! answer.isCorrectAnswer()) {
            new CorrectAnswerDAO().deleteFromAnswer(answer);
        }

        return result;
    }

    public ArrayList<Answer> allByQuiz(Quiz quiz) {
        try {
            StringBuilder select = this.compileSelect();
            this.getCompiler().compileWhere(select, Answer.QUIZ_ID, "=");

            PreparedStatement preparedStatement = this.getQuery().prepareStatement(select.toString());
            preparedStatement.setInt(1, quiz.getPrimaryKey());

            return this.getQuery().getAll(preparedStatement);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    protected void addRelations(ArrayList<InnerModelRelation> relations) {
        relations.add(this.getLeftRelation(new CorrectAnswerDAO(), CorrectAnswer.ANSWER_ID));
    }
}
