package br.common.validation.rules;

import br.common.validation.Rule;

public class Double implements Rule {
	@Override
	public boolean apply(String data) {
		try {
			java.lang.Double.parseDouble(data);
			return true;
		} catch (NullPointerException | NumberFormatException e) {
			return false;
		}
	}

	@Override
	public String getMessage() {
		return "Valor deve ser numerico.";
	}
}
