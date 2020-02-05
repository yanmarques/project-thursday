package br.configuration.controllers.quizes.answers;

import br.common.models.answer.Answer;
import br.configuration.interactions.database.Action;
import javafx.fxml.FXML;

public class UpdateController extends StoreController {
    private AnswerView view;

    public UpdateController(AnswerView view) {
        this.view = view;
    }

    @Override
    protected Answer getModel() {
        return this.view.getModel();
    }

    @Override
    protected Action getAction() {
        return Action.UPDATE_FROM_VIEW;
    }

    @FXML
    public void initialize() {
        super.initialize();
        this.answer.setText(this.getModel().getDescription());
        this.isCorrectAnswer.setSelected(this.getModel().isCorrectAnswer());
    }

    @Override
    protected AnswerView getViewToRemove() {
        return this.view;
    }
}
