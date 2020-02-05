package br.configuration.window;

import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class TabLoader {
    public static void load(ScenarioHandler handler, Tab tab, URL fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(fxml);
        Scenario scenario = handler.withScenario(new Scenario(null, loader));
        tab.setContent(scenario.getRoot());
    }
}
