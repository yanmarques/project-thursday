package br.common.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Scenario {
    protected Stage stage;
    protected FXMLLoader loader;
    protected Parent root;

    public Scenario(Stage stage, FXMLLoader loader) throws IOException {
        this.stage = stage;
        this.loader = loader;
        this.load();
    }

    public Stage getStage() {
        return stage;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Parent getRoot() {
        return root;
    }

    public void load() throws IOException {
        this.root = this.loader.load();
    }

    public void show() {
        this.getStage().setScene(new Scene(this.getRoot()));
        this.getStage().show();
    }
}
