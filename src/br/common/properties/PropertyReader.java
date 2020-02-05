package br.common.properties;

import br.common.validation.RuleNames;
import br.common.validation.RuleWrapper;
import br.common.validation.Validator;
import br.common.validation.textValues.RawTextValue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyReader {
	public static RawConfiguration read(URL file) throws Exception {
		try (InputStream inputStream = file.openStream()) {
			if (inputStream == null) {
				throw new Exception("File not found!");
			}

			Properties properties = new Properties();
			properties.load(inputStream);

			RawConfiguration rawConfiguration = new RawConfiguration(properties);

			validate(rawConfiguration);

			return rawConfiguration;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static void validate(RawConfiguration config) throws Exception {
		Validator validator = new Validator();
		RuleWrapper requiredWrapper = validator.wrapRules().add(RuleNames.REQUIRED);

		requiredWrapper.createRule(new RawTextValue(config.getDatabasePath()), "banco de dados");
		requiredWrapper.createRule(new RawTextValue(config.getImagesPath()), "caminho das imagens");

		validator.validate();
	}
}
