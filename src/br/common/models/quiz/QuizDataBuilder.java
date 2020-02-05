package br.common.models.quiz;

import br.common.database.DataBuilder;
import br.common.database.SQLCompiler;
import br.common.models.subject.Subject;
import br.common.models.subject.SubjectDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizDataBuilder implements DataBuilder<Quiz> {
    @Override
    public Quiz getModel() {
        return new Quiz();
    }

    @Override
    public Quiz build(SQLCompiler compiler, ResultSet resultSet) throws SQLException {
        Quiz quiz = this.getModel();

        String question = compiler.alias(Quiz.QUESTION);
        quiz.setQuestion(resultSet.getString(question));

        String score = compiler.alias(Quiz.SCORE);
        quiz.setScore(resultSet.getDouble(score));

        String scoreAvaliation = compiler.alias(Quiz.SCORE_AVALIATION);
        quiz.setScoreAvaliation(resultSet.getString(scoreAvaliation));

        quiz.setCorrectAnswersCount(resultSet.getInt(Quiz.CORRECT_ANSWERS_COUNT));

        Subject subject = new SubjectDAO().buildModel(resultSet);
        quiz.setSubject(subject);

        return quiz;
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement, Quiz quiz) throws SQLException {
        preparedStatement.setString(1, quiz.getQuestion());
        preparedStatement.setDouble(2, quiz.getScore());
        preparedStatement.setString(3, quiz.getScoreAvaliation());
        preparedStatement.setInt(4, quiz.getSubjectId());
    }
}
