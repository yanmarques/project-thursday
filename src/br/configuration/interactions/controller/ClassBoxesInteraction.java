package br.configuration.interactions.controller;

import br.common.database.TableMapping;
import br.common.models.classes.ClassDAO;
import br.configuration.controllers.ModelView;
import br.configuration.controllers.classes.ClassView;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

public class ClassBoxesInteraction<T extends TableMapping> {
    private ComboBox<ClassView> classesBox;
    private DatabaseInteraction<T, ? extends ModelView<T>, ? extends ClassFilterDAO<T>> filterDAO;

    public ClassBoxesInteraction(ComboBox<ClassView> classesBox, DatabaseInteraction<T, ? extends ModelView<T>, ? extends ClassFilterDAO<T>> filterDAO) {
        this.classesBox = classesBox;
        this.filterDAO = filterDAO;
        this.registerBoxSelection();
        this.registerBoxDbInteraction();
    }

    public void onClassSelected(ActionEvent event) {
        ClassView view = this.classesBox.getSelectionModel().getSelectedItem();

        if (view == null) {
            this.registerTable();
        } else {
            this.registerTableByClass(view);
        }
    }

    private void registerBoxSelection() {
        this.classesBox.setOnAction(this::onClassSelected);
    }

    private void registerTable() {
        this.filterDAO.syncAll();
    }

    private void registerTableByClass(ClassView view) {
        this.filterDAO.selectWith(db -> db.allByClass(view.getModel()));
    }

    private void registerBoxDbInteraction() {
        new DatabaseInteraction<>(new ClassDAO(), this.classesBox, ClassView::new).syncAll();
        this.classesBox.getItems().add(0, null);
    }
}
