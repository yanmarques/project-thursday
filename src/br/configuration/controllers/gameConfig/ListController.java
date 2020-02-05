package br.configuration.controllers.gameConfig;

import br.common.models.config.Configuration;
import br.common.models.config.ConfigurationDAO;
import br.configuration.controllers.BoxActionsHelper;
import br.configuration.controllers.gameConfig.ruleActions.Actions;
import br.configuration.controllers.gameConfig.ruleActions.EmbracingActionsInterface;
import br.configuration.controllers.ActionsInterface;
import br.configuration.controllers.gameConfig.ruleActions.SpecificActionsInterface;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;

public class ListController {
    @FXML
    private ComboBox<ActionsInterface<Actions>> ruleAction;

    @FXML
    private CheckBox hideTeacher;

    @FXML
    private CheckBox ruleByClass;

    private ConfigurationDAO database;

    private Configuration state;

    private BoxActionsHelper<Actions> boxActions;

    @FXML
    public void initialize() {
        this.registerRuleActions();
        this.registerCheckBoxes();
        this.registerDatabase();
    }

    public void onRuleSelected() {
        ActionsInterface action = this.ruleAction.getSelectionModel().getSelectedItem();
        this.getState().setRuleAction(action.getAction().toString());
        this.updateState();
    }

    public void onHideTeacherSelected() {
        boolean isHiding = this.hideTeacher.isSelected();
        this.getState().setHideTeachers(isHiding);
        this.updateState();
    }

    public void onRuleByClassSelected() {
        boolean isGrouped = this.ruleByClass.isSelected();
        this.getState().setDisplayRulesByClass(isGrouped);
        this.updateState();
    }

    private void registerRuleActions() {
        this.boxActions = new BoxActionsHelper<>(this.ruleAction);
        this.boxActions.add(new EmbracingActionsInterface());
        this.boxActions.add(new SpecificActionsInterface());
        this.boxActions.render();
    }

    private void registerCheckBoxes() {
        this.hideTeacher.setTooltip(new Tooltip("Os professores não ficarão disponíveis para escolha no jogo."));
        this.ruleByClass.setTooltip(new Tooltip("Usa apenas as regras de seleção da turma do aluno."));
    }

    private void registerDatabase() {
        this.database = new ConfigurationDAO();
        this.synchronizeDatabase();
    }

    private void synchronizeDatabase() {
        Configuration configuration = this.database.first();

        if (configuration == null) {
            configuration = new Configuration();
            this.setFirstAction(configuration);
            configuration.setHideTeachers(false);
            configuration.setDisplayRulesByClass(false);

            if (this.database.create(configuration)) {
                this.synchronizeDatabase();
            } else {
                System.out.println("nao foi possivel salvar item no banco de dados.");
            }
        } else {
            ActionsInterface<Actions> foundAction = this.boxActions.findAction(configuration.getRuleAction());

            if (foundAction == null) {
                this.setFirstAction(configuration);
                this.database.update(configuration);
                this.synchronizeDatabase();
                return;
            }

            this.ruleAction.getSelectionModel().select(foundAction);
            this.hideTeacher.setSelected(configuration.getHideTeachers());
            this.ruleByClass.setSelected(configuration.getDisplayRulesByClass());
        }

        this.setState(configuration);
    }

    private void setState(Configuration configuration) {
        this.state = configuration;
    }

    private Configuration getState() {
        return this.state;
    }

    private void updateState() {
        if (this.getState().isDirty()) {
            this.database.update(this.getState());
        } else {
            System.out.println("Calling state update on a clean state.");
        }
    }

    private void setFirstAction(Configuration configuration) {
        ActionsInterface firstAction = this.ruleAction.getItems().get(0);
        configuration.setRuleAction(firstAction.getAction().toString());
    }
}
