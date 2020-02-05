package br.common.window.validation;

import br.common.window.ModalAdapterController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.util.ArrayList;

public class ErroredModalController extends ModalAdapterController {
    @FXML
    private Accordion accordionPane;

    @Override
    protected Node getOwner() {
        return this.accordionPane;
    }

    public void setBuilder(ValidationPaneBuilder builder) {
        ArrayList<TitledPane> panes = builder.build();
        accordionPane.getPanes().addAll(panes);
        accordionPane.setExpandedPane(panes.get(0));
    }
}
