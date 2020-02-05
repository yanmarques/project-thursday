package br.common.window.handlers;

import br.common.application.Callback;
import br.common.window.ModalAdapterController;
import br.common.window.Scenario;
import br.common.window.interfaces.ScenarioHandler;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Window;

public class ModalHandler implements ScenarioHandler {
    private Window owner;
    private Callback onClose;

    public ModalHandler(Node node) {
        this(node, null);
    }

    public ModalHandler(Node node, Callback onClose) {
        this.owner = node.getScene().getWindow();
        this.onClose = onClose;
    }

    @Override
    public Scenario withScenario(Scenario scenario) {
        scenario.getStage().initOwner(this.owner);
        scenario.getStage().initModality(Modality.APPLICATION_MODAL);

//        if (this.onClose != null) {
//            ModalAdapterController controller = scenario.getLoader().getController();
//            controller.setOnClose(this.onClose);
//        }

        return scenario;
    }
}
