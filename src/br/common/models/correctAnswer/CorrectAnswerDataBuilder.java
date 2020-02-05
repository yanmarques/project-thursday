package br.common.models.correctAnswer;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.models.answer.Answer;
import br.common.models.answer.AnswerDAO;
import br.common.models.quiz.Quiz;
import br.common.models.quiz.QuizDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CorrectAnswerDataBuilder implements DataBuilder<CorrectAnswer> {
    @Override
    public CorrectAnswer getModel() {
        return new CorrectAnswer();
    }

    @Override
    public CorrectAnswer build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        CorrectAnswer correctAnswer = this.getModel();

        Quiz quiz = new QuizDAO().buildModel(resultSet);
        correctAnswer.setQuiz(quiz);

        Answer answer = new AnswerDAO().buildModel(resultSet);
        correctAnswer.setAnswer(answer);

        return correctAnswer;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, CorrectAnswer correctAnswer) throws SQLException {
        preparedStatement.setInt(1, correctAnswer.getQuizId());
        preparedStatement.setInt(2, correctAnswer.getAnswerId());
    }
}
