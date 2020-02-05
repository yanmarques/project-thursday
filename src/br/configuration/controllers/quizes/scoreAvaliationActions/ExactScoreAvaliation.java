package br.configuration.controllers.quizes.scoreAvaliationActions;

import br.configuration.controllers.ActionsInterface;

public class ExactScoreAvaliation implements ActionsInterface<Actions> {
    @Override
    public Actions getAction() {
        return Actions.EXACT;
    }

    @Override
    public String getDescription() {
        return "A pontuação é total se todas a respostas estiverem corretas.";
    }

    @Override
    public String toString() {
        return "Exata";
    }
}
