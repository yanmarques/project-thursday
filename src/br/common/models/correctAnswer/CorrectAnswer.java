package br.common.models.correctAnswer;

import br.common.database.DataModel;
import br.common.models.answer.Answer;
import br.common.models.quiz.Quiz;

import java.util.ArrayList;

public class CorrectAnswer extends DataModel {
    final public static String QUIZ_ID = "quiz_id";
    final public static String ANSWER_ID = "answer_id";

    private Quiz quiz;
    private Answer answer;

    @Override
    public String getPrimaryKeyAttribute() {
        return ANSWER_ID;
    }

    @Override
    public boolean isAutoIncrement() {
        return false;
    }

    @Override
    public String getTableName() {
        return "correct_answer";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(QUIZ_ID);
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

    public void setAnswerId(int answerId) {
        this.setInt(ANSWER_ID, answerId);
    }

    public Integer getAnswerId() {
        return this.getInt(ANSWER_ID);
    }

    public void setAnswer(Answer answer) {
        this.setAnswerId(answer.getPrimaryKey());
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }
}
