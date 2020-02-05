package br.game;

import br.common.models.alumn.AlumnDAO;
import br.common.window.Carrier;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.game.common.GameFxml;
import br.game.common.PlayerGatherer;
import br.game.common.SelectionCycle;
import br.game.common.handlers.PlayerGatheringHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;

public class PlayerHomeController extends PlayerGatherer {

    @FXML
    private TextField playerName;

    @FXML
    private TextField score;

    @FXML
    private ProgressIndicator rankedScore;

    @FXML
    private TableView<PlayerRankView> playersRanking;

    @FXML
    private TableColumn<PlayerRankView, String> rankColumn;

    @FXML
    private TableColumn<PlayerRankView, String> classColumn;

    @Override
    public void render() {
        this.registerColumns();
        this.registerTextFields();

        new DatabaseInteraction<>(new AlumnDAO(), this.playersRanking, PlayerRankView::new)
                    .selectWith(AlumnDAO::allOrderedByScore);

        this.registerScore();
    }

    @FXML
    public void exit() {
        Carrier.close(this.playerName);
    }

    @FXML
    public void newGame() {
        new SelectionCycle(this.getPlayer()).startCycle(this.playerName);
    }

    private void registerColumns() {
        this.rankColumn.setCellValueFactory(cellData -> cellData.getValue().rankProperty());
        this.classColumn.setCellValueFactory(cellData -> cellData.getValue().classNameProperty());
    }

    private void registerTextFields() {
        this.playerName.setText(this.getPlayer().getName());
        this.score.setText(this.getPlayer().getScore().toString());
    }

    private void registerScore() {
        double totalScore = 0;

        for (PlayerRankView playerRankView: this.playersRanking.getItems()) {
            totalScore += playerRankView.getModel().getScore();
        }

        double partialScore = (this.getPlayer().getScore() * 100) / totalScore;
        this.rankedScore.setProgress(partialScore / 100);
    }
}
