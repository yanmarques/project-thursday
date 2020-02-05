package br.common.validation.textValues;

import br.common.validation.InputTextValue;

public class RawTextValue implements InputTextValue {
	private String value;

	public RawTextValue(String value) {
		this.value = value;
	}

	@Override
	public String getText() {
		return this.value;
	}
}
