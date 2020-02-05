package br.configuration.controllers.selectionRules;

import br.common.models.selectionRules.SelectionRule;
import br.common.models.selectionRules.SelectionRuleDAO;
import br.configuration.interactions.controller.InteractsWithDatabase;
import br.configuration.interactions.table.ClickInteractiveController;
import br.configuration.window.ConfigFxml;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.function.Function;

public class ListController extends ClickInteractiveController<SelectionRule, SelectionRuleView, SelectionRuleDAO> {
    @FXML
    private TableView<SelectionRuleView> selectionRulesView;

    @FXML
    private TableColumn<SelectionRuleView, ImageView> imageColumn;

    @FXML
    private TableColumn<SelectionRuleView, Number> subjectsCountColumn;

    @Override
    protected SelectionRuleDAO getNewlyDatabase() {
        return new SelectionRuleDAO();
    }

    @Override
    protected TableView<SelectionRuleView> getTable() {
        return this.selectionRulesView;
    }

    @Override
    protected Function<SelectionRule, SelectionRuleView> getSyncerFunction() {
        return SelectionRuleView::new;
    }

    @Override
    protected InteractsWithDatabase<SelectionRule, SelectionRuleView, SelectionRuleDAO> getRowUpdateController(SelectionRuleView view) {
        return new UpdateController(view);
    }

    @Override
    protected InteractsWithDatabase<SelectionRule, SelectionRuleView, SelectionRuleDAO> getStoreController() {
        return new StoreController();
    }

    @Override
    protected URL getFXMLForm() {
        return ConfigFxml.get("forms/selection_rule.fxml");
    }

    @Override
    protected String getUpdateTitle() {
        return "Atualize a regra de seleção";
    }

    @Override
    protected String getCreateTitle() {
        return "Cria uma nova regra de seleção";
    }

    @Override
    public void initialize() {
        super.initialize();
        this.imageColumn.setCellValueFactory(new PropertyValueFactory<>("imageView"));
        this.subjectsCountColumn.setCellValueFactory(cellData -> cellData.getValue().subjectsCountProperty());
    }
}
