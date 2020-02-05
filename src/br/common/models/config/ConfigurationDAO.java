package br.common.models.config;

import br.common.database.DataBuilder;
import br.common.database.connection.ModelDAO;

public class ConfigurationDAO extends ModelDAO<Configuration> {
    @Override
    public DataBuilder<Configuration> getDataBuilder() {
        return new ConfigurationDataBuilder();
    }
}
