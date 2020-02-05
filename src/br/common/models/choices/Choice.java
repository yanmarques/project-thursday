package br.common.models.choices;

import br.common.database.DataModel;
import br.common.models.alumn.Alumn;
import br.common.models.selectionRules.SelectionRule;

import java.util.ArrayList;

public class Choice extends DataModel {
    final public static String ALUMN_ID = "alumn_id";
    final public static String SELECTION_RULE_ID = "selection_rule_id";

    private SelectionRule selectionRule;
    private Alumn alumn;

    @Override
    public String getTableName() {
        return "choice";
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(SELECTION_RULE_ID);
    }

    @Override
    public boolean isAutoIncrement() {
        return false;
    }

    @Override
    public String getPrimaryKeyAttribute() {
        return ALUMN_ID;
    }

    public void setAlumnId(int alumnId) {
        this.setInt(ALUMN_ID, alumnId);
    }

    public Integer getAlumnId() {
        return this.getInt(ALUMN_ID);
    }

    public void setSelectionRuleId(int selectionRuleId) {
        this.setInt(SELECTION_RULE_ID, selectionRuleId);
    }

    public Integer getSelectionRuleId() {
        return this.getInt(SELECTION_RULE_ID);
    }

    public SelectionRule getSelectionRule() {
        return selectionRule;
    }

    public void setSelectionRule(SelectionRule selectionRule) {
        this.setSelectionRuleId(selectionRule.getPrimaryKey());
        this.selectionRule = selectionRule;
    }

    public Alumn getAlumn() {
        return alumn;
    }

    public void setAlumn(Alumn alumn) {
        this.setAlumnId(alumn.getPrimaryKey());
        this.alumn = alumn;
    }
}
