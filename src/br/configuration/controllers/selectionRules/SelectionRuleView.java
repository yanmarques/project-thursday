package br.configuration.controllers.selectionRules;

import br.common.models.selectionRules.SelectionRule;
import br.configuration.controllers.ModelView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SelectionRuleView extends ModelView<SelectionRule> {
    private SimpleIntegerProperty subjectsCount;
    private ImageView imageView;

    public SelectionRuleView(SelectionRule model) {
        super(model);
        this.subjectsCount = new SimpleIntegerProperty(model.getSubjectsCount());

        try {
            this.imageView = new ImageView(new Image(new FileInputStream(new File(model.getImagePath()))));
            this.imageView.setFitWidth(100);
            this.imageView.setPreserveRatio(true);
            this.imageView.setSmooth(true);
            this.imageView.setCache(true);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public int getSubjectsCount() {
        return subjectsCount.get();
    }

    public SimpleIntegerProperty subjectsCountProperty() {
        return subjectsCount;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
