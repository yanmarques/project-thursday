package br.configuration.window.handlers.controllers;

import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;

public class RenderizableHandler implements ScenarioHandler {
    @Override
    public Scenario withScenario(Scenario scenario) {
        Object controller = scenario.getLoader().getController();

        if (controller instanceof Renderizable) {
            ((Renderizable) controller).render();
        }

        return scenario;
    }
}
