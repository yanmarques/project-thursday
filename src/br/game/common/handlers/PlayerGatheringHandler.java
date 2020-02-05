package br.game.common.handlers;

import br.common.models.alumn.Alumn;
import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;
import br.game.common.PlayerGatherer;

public class PlayerGatheringHandler implements ScenarioHandler {
    private Alumn player;

    public PlayerGatheringHandler(Alumn player) {
        this.player = player;
    }

    @Override
    public Scenario withScenario(Scenario scenario) {
        ((PlayerGatherer) scenario.getLoader().getController()).setPlayer(this.player);
        return scenario;
    }
}
