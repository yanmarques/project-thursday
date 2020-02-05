package br.game.common.handlers;

import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;
import br.game.ChooseImageController;
import br.game.common.SelectionCycle;

public class CyclicControllerHandler implements ScenarioHandler {
    private SelectionCycle selectionCycle;

    public CyclicControllerHandler(SelectionCycle selectionCycle) {
        this.selectionCycle = selectionCycle;
    }

    @Override
    public Scenario withScenario(Scenario scenario) {
        ChooseImageController imageController = scenario.getLoader().getController();
        imageController.setSelectionCycles(this.selectionCycle);
        return scenario;
    }
}
