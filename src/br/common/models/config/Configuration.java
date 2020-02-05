package br.common.models.config;

import br.common.database.DataModel;

import java.util.ArrayList;

public class Configuration extends DataModel {
    final public static String RULE_ACTION = "rule_action";
    final public static String HIDE_TEACHERS = "hide_teachers";
    final public static String DISPLAY_RULES_BY_CLASS = "display_rules_by_class";

    @Override
    protected void allowedColumns(ArrayList<String> keys) {
        keys.add(RULE_ACTION);
        keys.add(HIDE_TEACHERS);
        keys.add(DISPLAY_RULES_BY_CLASS);
    }

    @Override
    public String getTableName() {
        return "configuration";
    }

    public void setRuleAction(String action) {
        this.setString(RULE_ACTION, action);
    }

    public void setHideTeachers(boolean status) {
        this.setBoolean(HIDE_TEACHERS, status);
    }

    public void setDisplayRulesByClass(boolean status) {
        this.setBoolean(DISPLAY_RULES_BY_CLASS, status);
    }

    public String getRuleAction() {
        return this.getString(RULE_ACTION);
    }

    public Boolean getHideTeachers() {
        return this.getBoolean(HIDE_TEACHERS);
    }

    public Boolean getDisplayRulesByClass() {
        return this.getBoolean(DISPLAY_RULES_BY_CLASS);
    }
}
