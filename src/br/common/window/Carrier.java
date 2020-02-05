package br.common.window;

import br.common.window.interfaces.ScenarioHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Carrier {
    private static ChainedHandler defaultHandlers = new ChainedHandler();

    public static void close(Node node) {
        ((Stage) node.getScene().getWindow()).close();
    }

    public static void addDefaultHandler(ScenarioHandler handler) {
        defaultHandlers.add(handler);
    }

    public static Scenario load(URL fxml, String title) throws IOException {
        return Carrier.create(new Stage(), fxml, title);
    }

    public static Scenario proxyLoad(ScenarioHandler handler, URL fxml, String title) throws IOException {
        return handler.withScenario(Carrier.load(fxml, title));
    }

    public static void open(URL fxml, String title) throws IOException {
        Carrier.load(fxml, title).show();
    }

    public static void proxyOpen(ScenarioHandler handler, URL fxml, String title) throws IOException {
        handler.withScenario(Carrier.load(fxml, title)).show();
    }

    public static Scenario proxyStaged(Stage stage, ScenarioHandler handler, URL fxml, String title) throws IOException {
        return handler.withScenario(Carrier.create(stage, fxml, title));
    }

    public static void openStaged(Stage stage, URL fxml, String title) throws IOException {
        Carrier.create(stage, fxml, title).show();
    }

    public static void proxyOpenWithController(Object controller, ScenarioHandler handler, URL fxml,
                                               String title) throws IOException {
        FXMLLoader loader = getFXMLLoader(fxml);
        loader.setController(controller);
        handler.withScenario(Carrier.create(loader, new Stage(), title)).show();
    }

    public static void proxyOpenStaged(Stage stage, ScenarioHandler handler, URL fxml, String title) throws IOException {
        Carrier.proxyStaged(stage, handler, fxml, title).show();
    }

    public static Scenario create(Stage stage, URL fxml, String title) throws IOException {
        return create(getFXMLLoader(fxml), stage, title);
    }

    public static Scenario create(FXMLLoader loader, Stage stage, String title) throws IOException {
        if (title != null) {
            stage.setTitle(title);
        }

        return defaultHandlers.withScenario(new Scenario(stage, loader));
    }

    private static FXMLLoader getFXMLLoader(URL fxml) {
        return new FXMLLoader(fxml);
    }
}
