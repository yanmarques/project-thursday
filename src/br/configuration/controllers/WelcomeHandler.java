package br.configuration.controllers;

import br.common.window.ModalAdapterController;
import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;
import br.configuration.DatabaseStarterCallback;
import javafx.scene.Node;

public class WelcomeHandler implements ScenarioHandler {
    private Node owner;

    public WelcomeHandler(Node owner) {
        this.owner = owner;
    }

    @Override
    public Scenario withScenario(Scenario scenario) {
        ModalAdapterController controller = scenario.getLoader().getController();
        controller.setOnClose(new DatabaseStarterCallback(this.owner));
        return scenario;
    }
}
