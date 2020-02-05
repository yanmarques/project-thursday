package br.common.models.answer;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.models.correctAnswer.CorrectAnswer;
import br.common.models.correctAnswer.CorrectAnswerDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDataBuilder implements DataBuilder<Answer> {
    @Override
    public Answer getModel() {
        return new Answer();
    }

    @Override
    public Answer build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Answer answer = this.getModel();

        String description = compiler.alias(Answer.DESCRIPTION);
        answer.setDescription(resultSet.getString(description));

        String quizId = compiler.alias(Answer.QUIZ_ID);
        answer.setQuizId(resultSet.getInt(quizId));

        String correctAnswerQuizId = new CorrectAnswerDAO().getCompiler().alias(CorrectAnswer.QUIZ_ID);
        if (resultSet.getInt(correctAnswerQuizId) != 0) {
            answer.setCorrectAnswer(true);
        }

        return answer;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Answer answer) throws SQLException {
        preparedStatement.setString(1, answer.getDescription());
        preparedStatement.setInt(2, answer.getQuizId());
    }
}
