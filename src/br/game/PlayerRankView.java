package br.game;

import br.common.models.alumn.Alumn;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleStringProperty;

public class PlayerRankView extends ModelView<Alumn> {
    private SimpleStringProperty rank;
    private SimpleStringProperty className;

    public PlayerRankView(Alumn alumn) {
        super(alumn);
        this.rank = new SimpleStringProperty(alumn.getName() + " - " + alumn.getScore());
        this.className = new SimpleStringProperty(alumn.getClassModel().getName());
    }

    public String getRank() {
        return rank.get();
    }

    public SimpleStringProperty rankProperty() {
        return rank;
    }

    public String getClassName() {
        return className.get();
    }

    public SimpleStringProperty classNameProperty() {
        return className;
    }
}
