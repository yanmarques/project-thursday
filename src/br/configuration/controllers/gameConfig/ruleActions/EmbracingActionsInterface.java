package br.configuration.controllers.gameConfig.ruleActions;

import br.configuration.controllers.ActionsInterface;

public class EmbracingActionsInterface implements ActionsInterface<Actions> {
    @Override
    public Actions getAction() {
        return Actions.EMBRACING;
    }

    @Override
    public String toString() {
        return "Generalizada";
    }

    @Override
    public String getDescription() {
        return "Aplica as regras de seleções com menos rigidez, aumentando a chance de escolher uma regra diferente.";
    }
}
