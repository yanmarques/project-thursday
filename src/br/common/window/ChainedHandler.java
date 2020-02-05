package br.common.window;

import br.common.window.interfaces.ScenarioHandler;

import java.util.ArrayList;

public class ChainedHandler implements ScenarioHandler {
    protected ArrayList<ScenarioHandler> handlers;
    protected Scenario scenario;

    public ChainedHandler() {
        this.handlers = new ArrayList<>();
    }

    public ChainedHandler(ArrayList<ScenarioHandler> handlers) {
        this.handlers = handlers;
    }

    public ChainedHandler add(ScenarioHandler handler) {
        this.handlers.add(handler);
        return this;
    }

    @Override
    public Scenario withScenario(Scenario scenario) {
        this.setScenario(scenario);
        this.handlers.forEach(this::applyHandler);
        scenario = this.getScenario();
        this.setScenario(null);
        return scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    protected void applyHandler(ScenarioHandler handler) {
        this.setScenario(handler.withScenario(this.getScenario()));
    }
}
