package br.game.common;

import br.common.models.alumn.Alumn;
import br.common.models.choices.Choice;
import br.common.models.choices.ChoiceDAO;
import br.common.models.classes.ClassDAO;
import br.common.models.config.ConfigurationDAO;
import br.common.models.selectionRules.SelectionRule;
import br.common.models.selectionRules.SelectionRuleDAO;
import br.common.window.Carrier;
import br.configuration.controllers.gameConfig.ruleActions.Actions;
import br.game.common.handlers.CyclicControllerHandler;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SelectionCycle {
    private ArrayList<SelectionRule> choices = new ArrayList<>();
    private Alumn player;
    private Actions ruleAction;
    private int currentCycle = 0;
    private ArrayList<SelectionRule> availableSelectionRules;
    private ChoiceDAO choiceDAO;

    public SelectionCycle(Alumn player) {
        this.player = player;
        this.ruleAction = this.retrieveRuleAction();
        this.availableSelectionRules = new SelectionRuleDAO().all();
        this.choiceDAO = new ChoiceDAO();
    }

    public Alumn getPlayer() {
        return player;
    }

    public Actions getRuleAction() {
        return ruleAction;
    }

    public ArrayList<SelectionRule> getAvailableSelectionRules() {
        return availableSelectionRules;
    }

    public boolean isStopped() {
        return this.currentCycle >= this.getConfiguredCycles();
    }

    public ArrayList<SelectionRule> getChoices() {
        return choices;
    }

    public boolean choose(SelectionRule selectionRule) {
        Choice choice = new Choice();
        choice.setAlumn(this.getPlayer());
        choice.setSelectionRule(selectionRule);

        if (this.choiceDAO.create(choice)) {
            return this.choices.add(selectionRule);
        }

        return false;
    }

    public void startCycle(Node owner) {
        if (isStopped()) {
            throw new RuntimeException("Selection already stopped.");
        }

        URL fxml = GameFxml.get("choose.fxml");
        String title = "Qual imagem mais chama sua atenção?";

        try {
            Carrier.proxyOpen(new CyclicControllerHandler(this), fxml, title);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Carrier.close(owner);

        this.currentCycle++;
    }

    private Actions retrieveRuleAction() {
        String action = new ConfigurationDAO().first().getRuleAction();

        if (action.equals(Actions.EMBRACING.toString())) {
            return Actions.EMBRACING;
        }

        return Actions.SPECIFIC;
    }

    private int getConfiguredCycles() {
        if (this.ruleAction == Actions.EMBRACING) {
            return 3;
        }

        return 6;
    }
}
