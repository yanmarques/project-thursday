package br.common.window.validation;

import br.common.validation.ValidationErrorHandler;
import br.common.window.Carrier;
import br.configuration.window.ConfigFxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class ModalValidationHandler implements ValidationErrorHandler {
    @Override
    public void handleFailed(Map<String, ArrayList<String>> rules) {
        ValidationPaneBuilder builder = new ValidationPaneBuilder();
        builder.addAllRules(rules);

        PaneBuilderHandler handler = new PaneBuilderHandler(builder);
        URL fxml = ConfigFxml.get("modal.fxml");

        try {
            Carrier.proxyOpen(handler, fxml, "Oops...algo não parece correto.");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
