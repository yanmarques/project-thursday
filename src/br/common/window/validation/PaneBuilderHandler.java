package br.common.window.validation;

import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;

public class PaneBuilderHandler implements ScenarioHandler {
    private ValidationPaneBuilder builder;

    public PaneBuilderHandler(ValidationPaneBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Scenario withScenario(Scenario scenario) {
        ErroredModalController controller = scenario.getLoader().getController();
        controller.setBuilder(this.builder);
        return scenario;
    }
}
