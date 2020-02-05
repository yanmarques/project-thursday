package br.common.models.quiz;

import br.common.database.DataModel;
import br.common.models.subject.Subject;

import java.util.ArrayList;

public class Quiz extends DataModel {
    final public static String QUESTION = "question";
    final public static String SCORE = "score";
    final public static String SCORE_AVALIATION = "score_avaliation";
    final public static String SUBJECT_ID = "subject_id";
    final public static String CORRECT_ANSWERS_COUNT = "correct_answers_count";

    private Subject subject;
    private int correctAnswersCount;

    @Override
    public String getTableName() {
        return "quiz";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(QUESTION);
        columns.add(SCORE);
        columns.add(SCORE_AVALIATION);
        columns.add(SUBJECT_ID);
    }

    public void setQuestion(String question) {
        this.setString(QUESTION, question);
    }

    public String getQuestion() {
        return this.getString(QUESTION);
    }

    public void setScore(double score) {
        this.setDouble(SCORE, score);
    }

    public Double getScore() {
        return this.getDouble(SCORE);
    }

    public void setScoreAvaliation(String scoreAvaliation) {
        this.setString(SCORE_AVALIATION, scoreAvaliation);
    }

    public String getScoreAvaliation() {
        return this.getString(SCORE_AVALIATION);
    }

    public void setSubjectId(int subjectId) {
        this.setInt(SUBJECT_ID, subjectId);
    }

    public Integer getSubjectId() {
        return this.getInt(SUBJECT_ID);
    }

    public void setSubject(Subject subject) {
        this.setSubjectId(subject.getPrimaryKey());
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void setCorrectAnswersCount(int correctAnswersCount) {
        this.correctAnswersCount = correctAnswersCount;
    }
}
