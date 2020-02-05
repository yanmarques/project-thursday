package br.game;

import br.common.models.choices.Choice;
import br.common.models.choices.ChoiceDAO;
import br.common.models.selectionRules.SelectionRule;
import br.game.common.DetermineSubject;
import br.game.common.SelectionCycle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class ChooseImageController {
    final public static double GAUSSION_RADIUS = 15;
    final public static double TRANSITION_DURATION = 500;

    private SelectionCycle selectionCycle;
    private Random random;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane rightPane;

    @FXML
    private SelectionRule leftRule;

    @FXML
    private SelectionRule rightRule;

    public void setSelectionCycles(SelectionCycle selectionCycle) {
        this.selectionCycle = selectionCycle;
        this.startChoice();
    }

    @FXML
    public void initialize() {
        this.random = new SecureRandom();
    }

    @FXML
    public void chooseLeft() {
        this.selectionCycle.getAvailableSelectionRules().add(this.rightRule);
        this.choose(this.leftRule);
    }

    @FXML
    public void onLeftPaneEntered() {
        this.parallelalTransition(leftPane, rightPane);
    }

    @FXML
    public void chooseRight() {
        this.selectionCycle.getAvailableSelectionRules().add(this.leftRule);
        this.choose(this.rightRule);
    }

    @FXML
    public void onRightPaneEntered() {
        this.parallelalTransition(rightPane, leftPane);
    }

    private void startChoice() {
        this.leftRule = this.randomlyPop();
        this.setBackground(leftPane, this.leftRule);

        this.rightRule = this.randomlyPop();
        this.setBackground(rightPane, this.rightRule);
    }

    private void choose(SelectionRule rule) {
        if (this.selectionCycle.choose(rule)) {
            if (this.selectionCycle.isStopped()) {
                new DetermineSubject(this.selectionCycle.getChoices(), this.selectionCycle.getRuleAction()).determine();
            } else {
                this.selectionCycle.startCycle(this.leftPane);
            }
        } else {
            System.out.println("Não foi possível salvar a escolha.");
        }
    }

    private void setBackground(AnchorPane pane, SelectionRule selectionRule) {
        try {
            Image image = new Image(new FileInputStream(new File(selectionRule.getImagePath())));

            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(-1, -1, false,
                    false, true, false));

            pane.setBackground(new Background(backgroundImage));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void setGaussionBlur(AnchorPane pane, double radius) {
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(radius);
        pane.setEffect(gaussianBlur);
    }

    private void parallelalTransition(AnchorPane sourcePane, AnchorPane toPane) {
        FadeTransition sourceTransition = new FadeTransition(Duration.millis(TRANSITION_DURATION), sourcePane);
        sourceTransition.setFromValue(0.3);
        sourceTransition.setToValue(1.0);


        FadeTransition toTransition = new FadeTransition(Duration.millis(TRANSITION_DURATION), toPane);
        toTransition.setFromValue(1.0);
        toTransition.setToValue(0.3);


        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(sourceTransition, toTransition);
        parallelTransition.play();

        this.setGaussionBlur(toPane, GAUSSION_RADIUS);
        this.setGaussionBlur(sourcePane, 0);
    }

    private SelectionRule randomlyPop() {
        int index = random.nextInt(this.selectionCycle.getAvailableSelectionRules().size());
        SelectionRule selectionRule = selectionCycle.getAvailableSelectionRules().get(index);
        selectionCycle.getAvailableSelectionRules().remove(index);
        return selectionRule;
    }
}
