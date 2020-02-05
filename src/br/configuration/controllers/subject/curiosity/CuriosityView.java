package br.configuration.controllers.subject.curiosity;

import br.common.models.curiosity.Curiosity;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleStringProperty;

public class CuriosityView extends ModelView<Curiosity> {
    private SimpleStringProperty description;

    public CuriosityView(Curiosity model) {
        super(model);
        this.description = new SimpleStringProperty(model.getDescription());
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }
}
