package br.configuration.controllers.quizes.scoreAvaliationActions;

import br.configuration.controllers.ActionsInterface;

public class AverageScoreAvaliation implements ActionsInterface<Actions> {
    @Override
    public Actions getAction() {
        return Actions.AVERAGE;
    }

    @Override
    public String getDescription() {
        return "A pontuação é uma média ponderada entre as respostas corretas e os acertos.";
    }

    @Override
    public String toString() {
        return "Média ponderada";
    }
}
