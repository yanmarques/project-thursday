package br.common.validation.rules;

import br.common.validation.Rule;

public class LowerEquals implements Rule {
	private double top;

	public LowerEquals(double top) {
		this.top = top;
	}

	@Override
	public String getMessage() {
		return "Valor deve ser menor ou igual a "+ this.top;
	}

	@Override
	public boolean apply(String data) {
		boolean isDouble = new Double().apply(data);

		if (! isDouble) {
			return true;
		}

		return java.lang.Double.parseDouble(data) <= top;
	}
}
