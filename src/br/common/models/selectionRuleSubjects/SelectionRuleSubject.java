package br.common.models.selectionRuleSubjects;

import br.common.database.DataModel;
import br.common.models.selectionRules.SelectionRule;
import br.common.models.subject.Subject;

import java.util.ArrayList;

public class SelectionRuleSubject extends DataModel {
    final public static String SELECTION_RULE_ID = "selection_rule_id";
    final public static String SUBJECT_ID = "subject_id";

    private SelectionRule selectionRule;
    private Subject subject;

    @Override
    public String getTableName() {
        return "selection_rule_subject";
    }

    @Override
    public String getPrimaryKeyAttribute() {
        return SELECTION_RULE_ID;
    }

    @Override
    public boolean isAutoIncrement() {
        return false;
    }

    @Override
    protected void allowedColumns(ArrayList<String> columns) {
        columns.add(SUBJECT_ID);
    }

    public void setSelectionRuleId(int selectionRuleId) {
        this.setInt(SELECTION_RULE_ID, selectionRuleId);
    }

    public Integer getSelectionRuleId() {
        return this.getInt(SELECTION_RULE_ID);
    }

    public void setSubjectId(int subjectId) {
        this.setInt(SUBJECT_ID, subjectId);
    }

    public Integer getSubjectId() {
        return this.getInt(SUBJECT_ID);
    }

    public SelectionRule getSelectionRule() {
        return selectionRule;
    }

    public void setSelectionRule(SelectionRule selectionRule) {
        this.setSelectionRuleId(selectionRule.getPrimaryKey());
        this.selectionRule = selectionRule;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.setSubjectId(subject.getPrimaryKey());
        this.subject = subject;
    }
}
