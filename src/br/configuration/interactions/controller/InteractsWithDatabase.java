package br.configuration.interactions.controller;

import br.common.database.TableMapping;
import br.common.database.connection.ModelDAO;
import br.configuration.controllers.ModelView;

public interface InteractsWithDatabase<T extends TableMapping, M extends ModelView<T>, D extends ModelDAO<T>> {
    void setDbInteraction(DatabaseInteraction<T, M, D> dbInteration);

    DatabaseInteraction<T, M, D> getDbInteraction() throws NullPointerException;
}
