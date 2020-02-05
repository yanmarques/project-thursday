package br.configuration.controllers.quizes.answers;

import br.common.models.answer.Answer;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleStringProperty;

public class AnswerView extends ModelView<Answer> {
    private SimpleStringProperty description;
    private SimpleStringProperty isCorrectAnswer;

    public AnswerView(Answer model) {
        super(model);
        this.description = new SimpleStringProperty(model.getDescription());

        if (model.isCorrectAnswer()) {
            this.isCorrectAnswer = new SimpleStringProperty("SIM");
        } else {
            this.isCorrectAnswer = new SimpleStringProperty("NÃO");
        }
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getIsCorrectAnswer() {
        return isCorrectAnswer.get();
    }

    public SimpleStringProperty isCorrectAnswerProperty() {
        return isCorrectAnswer;
    }
}
