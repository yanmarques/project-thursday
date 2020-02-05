package br.game.common;

import java.net.URL;

public class GameFxml {
    public static URL get(String fxml) {
        return GameFxml.class.getResource("../../fxml/game/" + fxml);
    }
}
