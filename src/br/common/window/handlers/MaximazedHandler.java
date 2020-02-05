package br.common.window.handlers;

import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;

public class MaximazedHandler implements ScenarioHandler {
    protected boolean isMaximazed;

    public MaximazedHandler(boolean isMaximazed) {
        this.isMaximazed = isMaximazed;
    }

    @Override
    public Scenario withScenario(Scenario scenario) {
        scenario.getStage().setMaximized(this.isMaximazed);
        return scenario;
    }
}
