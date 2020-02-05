package br.common.validation.rules;

import br.common.validation.Rule;

public class Required implements Rule {
	@Override
	public boolean apply(String data) {
		return data != null && ! data.trim().equals("");
	}

	public String getMessage() {
		return "Campo obrigatorio.";
	}
}
