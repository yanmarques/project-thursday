package br.configuration.window;

import java.net.URL;

public class ConfigFxml {
    public static URL get(String fxml) {
        return ConfigFxml.class.getResource("../../fxml/configuration/" + fxml);
    }
}
