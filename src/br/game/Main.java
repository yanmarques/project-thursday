package br.game;

import br.common.application.DatabaseAdapter;
import br.common.window.Carrier;
import br.common.window.handlers.MaximazedHandler;
import br.game.common.GameFxml;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends DatabaseAdapter {
    @Override
    public void start(Stage primaryStage) {
        try {
            URL fxml = GameFxml.get("main.fxml");
            String title = "Projeto teste";

            Carrier.addDefaultHandler(new MaximazedHandler(true));

            Carrier.openStaged(primaryStage, fxml, title);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
