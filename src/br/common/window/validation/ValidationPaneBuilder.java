package br.common.window.validation;

import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValidationPaneBuilder {
    private Map<String, ArrayList<String>> rules = new HashMap<>();

    public ValidationPaneBuilder addRule(String name, ArrayList<String> messages) {
        this.rules.put(name, messages);
        return this;
    }

    public ValidationPaneBuilder addAllRules(Map<String, ArrayList<String>> rules) {
        this.rules.putAll(rules);
        return this;
    }

    public ArrayList<TitledPane> build() {
        ArrayList<TitledPane> panes = new ArrayList<>();

        this.rules.forEach((name, rules) -> panes.add(this.parseRule(name, rules)));

        return panes;
    }

    private TitledPane parseRule(String name, ArrayList<String> messages) {
        TitledPane pane = new TitledPane();
        pane.setText(name);

        ArrayList<Label> labels = new ArrayList<>();

        messages.forEach(message -> labels.add(new Label("* " + message)));

        VBox messagesStack = new VBox();
        messagesStack.getChildren().addAll(labels);

        pane.setContent(messagesStack);

        return pane;
    }
}
