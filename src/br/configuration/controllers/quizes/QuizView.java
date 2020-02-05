package br.configuration.controllers.quizes;

import br.common.models.quiz.Quiz;
import br.configuration.controllers.ModelView;
import br.configuration.controllers.subject.SubjectView;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class QuizView extends ModelView<Quiz> {
    private SimpleStringProperty question;
    private SimpleDoubleProperty score;
    private SimpleStringProperty scoreAvaliation;
    private SimpleIntegerProperty correctAnswersCount;
    private SubjectView subjectView;

    public QuizView(Quiz model) {
        super(model);
        this.question = new SimpleStringProperty(model.getQuestion());
        this.score = new SimpleDoubleProperty(model.getScore());
        this.scoreAvaliation = new SimpleStringProperty(model.getScoreAvaliation());
        this.correctAnswersCount = new SimpleIntegerProperty(model.getCorrectAnswersCount());
        this.subjectView = new SubjectView(model.getSubject());
    }

    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public double getScore() {
        return score.get();
    }

    public SimpleDoubleProperty scoreProperty() {
        return score;
    }

    public String getScoreAvaliation() {
        return scoreAvaliation.get();
    }

    public SimpleStringProperty scoreAvaliationProperty() {
        return scoreAvaliation;
    }

    public SubjectView getSubjectView() {
        return subjectView;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount.get();
    }

    public SimpleIntegerProperty correctAnswersCountProperty() {
        return correctAnswersCount;
    }
}
