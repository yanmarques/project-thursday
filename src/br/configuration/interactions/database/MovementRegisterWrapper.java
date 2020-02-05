package br.configuration.interactions.database;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;

public class MovementRegisterWrapper<T extends TableMapping, D extends ModelDAO<T>> {
    private D modelDao;
    private boolean hasMovements = false;

    public MovementRegisterWrapper(D modelDao) {
        this.modelDao = modelDao;
    }

    public boolean create(T model) {
        if (this.modelDao.create(model)) {
            this.registerMovement();
        }

        return false;
    }

    public int update(T model) {
        int rowsUpdated = this.modelDao.update(model);

        if (rowsUpdated > 0) {
            this.registerMovement();
        }

        return rowsUpdated;
    }

    public boolean delete(T model) {
        if (this.modelDao.delete(model)) {
            this.registerMovement();
        }

        return false;
    }

    public boolean hasMovements() {
        return hasMovements;
    }

    private void registerMovement() {
        this.hasMovements = true;
    }
}