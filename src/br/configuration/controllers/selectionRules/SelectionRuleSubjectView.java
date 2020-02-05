package br.configuration.controllers.selectionRules;

import br.common.models.selectionRuleSubjects.SelectionRuleSubject;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleStringProperty;

public class SelectionRuleSubjectView extends ModelView<SelectionRuleSubject> {
    private SimpleStringProperty subject;

    public SelectionRuleSubjectView(SelectionRuleSubject model) {
        super(model);
        this.subject= new SimpleStringProperty(model.getSubject().getName());
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }
}
