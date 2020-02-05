package br.common.validation.rules;

import br.common.validation.Rule;

public class Positive implements Rule {
	@Override
	public boolean apply(String data) {
		return new GreaterEquals(1).apply(data);
	}

	@Override
	public String getMessage() {
		return "Valor deve ser um numero maior ou igual a 1.";
	}
}
