package br.configuration.controllers.quizes.answers;

import br.common.models.answer.Answer;
import br.common.models.answer.AnswerDAO;
import br.common.validation.RuleNames;
import br.configuration.interactions.database.Action;
import br.configuration.interactions.database.InteractiveController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class StoreController extends InteractiveController<Answer, AnswerView, AnswerDAO> {
    @FXML
    protected TextArea answer;

    @FXML
    protected CheckBox isCorrectAnswer;

    @Override
    protected Node getOwner() {
        return this.answer;
    }

    @Override
    protected Answer getModel() {
        return new Answer();
    }

    @Override
    protected void fillModel(Answer model) {
        model.setDescription(this.answer.getText());
        model.setCorrectAnswer(this.isCorrectAnswer.isSelected());
    }

    @Override
    protected Action getAction() {
        return Action.ADD_TO_VIEW;
    }

    @FXML
    public void initialize() {
        this.getValidator().createRule(this.answer, "resposta")
                .add(RuleNames.REQUIRED);
    }
}
