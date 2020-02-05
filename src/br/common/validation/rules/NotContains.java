package br.common.validation.rules;

import br.common.validation.Rule;

public class NotContains implements Rule {
	private String badChar;
	private String message = "Valor invalido.";

	public NotContains(String badChar) {
		this.badChar = badChar;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public boolean apply(String data) {
		return ! data.contains(this.badChar);
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
