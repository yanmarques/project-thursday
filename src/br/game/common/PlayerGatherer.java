package br.game.common;

import br.common.models.alumn.Alumn;
import br.configuration.window.handlers.controllers.Renderizable;

abstract public class PlayerGatherer implements Renderizable {
    private Alumn player;

    public void setPlayer(Alumn player) {
        this.player = player;

        this.render();
    }

    public Alumn getPlayer() throws NullPointerException {
        if (player == null) {
            throw new NullPointerException("Player must not be null.");
        }

        return player;
    }
}
