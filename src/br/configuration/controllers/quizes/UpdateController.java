package br.configuration.controllers.quizes;

import br.common.models.quiz.Quiz;
import br.configuration.controllers.ActionsInterface;
import br.configuration.controllers.quizes.scoreAvaliationActions.Actions;
import br.configuration.interactions.database.Action;

public class UpdateController extends StoreController {
    private QuizView view;

    public UpdateController(QuizView view) {
        this.view = view;
    }

    @Override
    protected Quiz getModel() {
        return this.view.getModel();
    }

    @Override
    protected Action getAction() {
        return Action.UPDATE;
    }

    @Override
    protected double getSpinnerInitialValue() {
        return this.getModel().getScore();
    }

    @Override
    public void initialize() {
        super.initialize();
        this.question.setText(this.getModel().getQuestion());

        ActionsInterface<Actions> quizAction = this.getBoxActions().findAction(this.getModel().getScoreAvaliation());

        if (quizAction == null) {
            System.out.println("Valor do método de pontuação inválido");
        } else {
            this.scoreAvaliation.getSelectionModel().select(quizAction);
        }

        this.subjectsBox.getSelectionModel().select(this.view.getSubjectView());
        this.getClickableController().getDbInteraction().selectWith(db -> db.allByQuiz(this.getModel()));
    }
}
