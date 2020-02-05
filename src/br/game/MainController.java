package br.game;

import br.common.models.alumn.Alumn;
import br.common.models.alumn.AlumnDAO;
import br.common.window.Carrier;
import br.game.common.GameFxml;
import br.game.common.handlers.PlayerGatheringHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MainController {
    @FXML
    private Label alumnName;

    private ArrayList<Alumn> alumns;
    private int index;

    @FXML
    public void initialize() {
        alumns = new AlumnDAO().all();
        index = new Random().nextInt(alumns.size());
        this.registerName();
    }

    public void choose() {
        URL fxml = GameFxml.get("player-home.fxml");
        String title = "Lar doce lar...";

        try {
            Carrier.proxyOpen(new PlayerGatheringHandler(this.getAlumn()), fxml, title);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Carrier.close(this.alumnName);
    }

    @FXML
    public void moveLeft() {
        if (this.index == 0) {
            this.index = this.alumns.size() - 1;
        } else {
            this.index--;
        }

        this.registerName();
    }

    @FXML
    public void moveRight() {
        if (this.index == this.alumns.size() - 1) {
            this.index = 0;
        } else {
            this.index++;
        }

        this.registerName();
    }

    private void registerName() {
        this.alumnName.setText(this.getAlumn().getName());
    }

    private Alumn getAlumn() {
        return this.alumns.get(this.index);
    }
}
