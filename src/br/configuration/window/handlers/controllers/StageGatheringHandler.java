package br.configuration.window.handlers.controllers;

import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;
import br.common.window.interfaces.StageGather;

public class StageGatheringHandler implements ScenarioHandler {
    @Override
    public Scenario withScenario(Scenario scenario) {
        StageGather controller = scenario.getLoader().getController();
        controller.setCurrentStage(scenario.getStage());
        return scenario;
    }
}
