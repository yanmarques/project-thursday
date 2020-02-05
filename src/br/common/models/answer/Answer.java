package br.common.models.answer;

import br.common.database.DataModel;
import br.common.models.quiz.Quiz;

import java.util.ArrayList;

public class Answer extends DataModel {
    final public static String DESCRIPTION = "description";
    final public static String QUIZ_ID = "quiz_id";

    private Quiz quiz;
    private boolean isCorrectAnswer = false;

    @Override
    public String getTableName() {
        return "answer";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(DESCRIPTION);
        columns.add(QUIZ_ID);
    }

    public void setDescription(String description) {
        this.setString(DESCRIPTION, description);
    }

    public String getDescription() {
        return this.getString(DESCRIPTION);
    }

    public void setQuizId(int quizId) {
        this.setInt(QUIZ_ID, quizId);
    }

    public Integer getQuizId() {
        return this.getInt(QUIZ_ID);
    }

    public void setQuiz(Quiz quiz) {
        this.setQuizId(quiz.getPrimaryKey());
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public void setCorrectAnswer(boolean status) {
        if (this.isCorrectAnswer != status) {
            this.isDirty = true;
            this.isCorrectAnswer = status;
        }
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }
}
