package br.common.properties;

import java.util.Properties;

public class RawConfiguration {
	private Properties properties;

	public RawConfiguration(Properties properties) {
		this.properties = properties;
	}

	public String getDatabasePath() {
		return this.getProperties().getProperty("database_path");
	}

	public String getImagesPath() {
		return this.getProperties().getProperty("images_path");
	}

	public Properties getProperties() {
		return properties;
	}
}
