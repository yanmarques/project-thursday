package br.common.application;

import br.common.database.connection.Connector;
import br.common.properties.PropertyReader;
import br.common.properties.RawConfiguration;
import javafx.application.Application;

import java.sql.SQLException;

abstract public class DatabaseAdapter extends Application {
    final private static String conf = "../../conf.properties";
    private static RawConfiguration config;

    public static RawConfiguration getConfig() {
        return config;
    }

    @Override
    public void init() throws Exception {
        config = PropertyReader.read(getClass().getResource(conf));
        Connector.connect(config.getDatabasePath());
    }

    @Override
    public void stop() {
        try {
            Connector.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
