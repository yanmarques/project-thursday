package br.configuration.controllers.gameConfig.ruleActions;

import br.configuration.controllers.ActionsInterface;

public class SpecificActionsInterface implements ActionsInterface<Actions> {
    @Override
    public Actions getAction() {
        return Actions.SPECIFIC;
    }

    @Override
    public String toString() {
        return "Específica";
    }

    @Override
    public String getDescription() {
        return "Aplica o maior número de regras de seleção disponível para conseguir obter resultados mais precisos.";
    }
}
