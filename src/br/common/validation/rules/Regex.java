package br.common.validation.rules;

import br.common.validation.Rule;

import java.util.regex.Pattern;

public class Regex implements Rule {
	private String regex;
	private String message = "Valor invalido.";

	public Regex(String regex) {
		this.regex = regex;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public boolean apply(String data) {
		Pattern pattern = Pattern.compile(this.regex);

		return pattern.matcher(data).matches();
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
