package br.common.database.connection;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connection connection;
    private static String lastDatabasePath;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        Connector.connection = connection;
    }

    public static void close() throws SQLException {
        if (isConnected()) {
            connection.close();
        }
    }

    public static void checkConnection() throws Exception {
        if (! isConnected() && getLastDatabasePath() != null) {
            // Reconnect.
            connect(getLastDatabasePath());
        }
    }

    public static boolean isConnected() {
        try {
            return connection != null && ! connection.isClosed();
        } catch (SQLException exception) {
            return false;
        }
    }

    public static void connect(String databasePath) throws ClassNotFoundException, SQLException, IOException {
        if (! new File(databasePath).exists()) {
            throw new IOException("Could not find database at [" + databasePath + "].");
        }

        if (! isConnected()) {
            Class.forName("org.sqlite.JDBC");
            setLastDatabasePath(databasePath);
            setConnection(DriverManager.getConnection("jdbc:sqlite:" + databasePath));
        }
    }

    public static String getLastDatabasePath() {
        return lastDatabasePath;
    }

    private static void setLastDatabasePath(String path) {
        lastDatabasePath = path;
    }
}
