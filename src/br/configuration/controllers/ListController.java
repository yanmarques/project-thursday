package br.configuration.controllers;

import br.configuration.window.ConfigFxml;
import br.configuration.window.TabLoader;
import br.configuration.window.handlers.controllers.RenderizableHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

import java.io.IOException;
import java.net.URL;

public class ListController {
    @FXML
    private Tab alumns;

    @FXML
    private Tab teachers;

    @FXML
    private Tab selectionRules;

    @FXML
    private Tab quizes;

    @FXML
    private Tab classes;

    @FXML
    private Tab subjects;

    @FXML
    private Tab gameConfig;

    public void onAlumnsSelected() {
        this.select(this.alumns, "alumns.fxml");
    }

    public void onTeachersSelected() {
        this.select(this.teachers, "teachers.fxml");
    }

    public void onSelectionRulesSelected() {
        this.select(this.selectionRules, "selection_rules.fxml");
    }

    public void onQuizesSelected() {
        this.select(this.quizes, "quizes.fxml");
    }

    public void onClassesSelected() {
        this.select(this.classes, "classes.fxml");
    }

    public void onSubjectsSelected() {
        this.select(this.subjects, "subjects.fxml");
    }

    public void onGameConfigSelected() {
        this.select(this.gameConfig, "game_config.fxml");
    }

    private void select(Tab currentTab, String fxml) {
        URL fxmlUrl = ConfigFxml.get(fxml);

        try {
            TabLoader.load(new RenderizableHandler(), currentTab, fxmlUrl);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
