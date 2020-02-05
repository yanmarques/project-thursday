package br.configuration.interactions.database;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;
import br.common.validation.AttributesValidator;
import br.common.window.ModalAdapterController;
import br.common.window.validation.ModalValidationHandler;
import br.configuration.controllers.ModelView;
import br.configuration.interactions.controller.DatabaseInteraction;
import br.configuration.interactions.controller.InteractsWithDatabase;
import javafx.fxml.FXML;

abstract public class InteractiveController<T extends TableMapping, M extends ModelView<T>, D extends ModelDAO<T>> extends ModalAdapterController
            implements InteractsWithDatabase<T, M, D> {
    private AttributesValidator validator = new AttributesValidator(new ModalValidationHandler());
    private DatabaseInteraction<T, M, D> dbInteration;
    protected boolean syncOnSave = true;

    abstract protected void fillModel(T model);

    abstract protected T getModel();

    abstract protected Action getAction();

    protected M getViewToRemove() {
        return null;
    }

    public void setDbInteraction(DatabaseInteraction<T, M, D> dbInteration) {
        this.dbInteration = dbInteration;
    }

    public DatabaseInteraction<T, M, D> getDbInteraction() throws NullPointerException {
        if (this.dbInteration == null) {
            throw new NullPointerException("Database interaction must not be null.");
        }

        return dbInteration;
    }

    public AttributesValidator getValidator() {
        return validator;
    }

    @FXML
    public void save() {
        if (this.getValidator().isValid()) {
            T model = this.getModel();
            this.fillModel(model);

            if (this.handleSave(model)) {
                this.close();
            } else {
                System.out.println("Falha ao realizar ação ou ação inválida.");
            }
        }
    }

    protected boolean handleSave(T model) {
        if (this.getAction() == Action.ADD_TO_VIEW) {
            this.getDbInteraction().addToView(model);
            return true;
        }

        if (this.getAction() == Action.UPDATE_FROM_VIEW) {
            M view = this.getViewToRemove();

            if (view != null) {
                if (this.getDbInteraction().getViewSyncer().remove(view)) {
                    return this.getDbInteraction().addToView(model);
                }

                return false;
            }

            return true;
        }

        if (this.getAction() == Action.STORE) {
            return this.getDbInteraction().create(model, this.syncOnSave);
        }

        if (this.getAction() == Action.UPDATE) {
            return this.updateModel(model) != 0;
        }

        return false;
    }

    protected int updateModel(T model) {
        return this.getDbInteraction().update(model, this.syncOnSave);
    }
}
