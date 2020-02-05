package br.common.validation.rules;

import br.common.validation.Rule;

public class Integer implements Rule {
	@Override
	public boolean apply(String data) {
		try {
			java.lang.Integer.parseInt(data);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public String getMessage() {
		return "Valor deve ser um numero inteiro.";
	}
}
