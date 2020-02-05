package br.common.validation.textValues;

import br.common.validation.InputTextValue;
import javafx.scene.control.TextInputControl;

public class TextFieldValue implements InputTextValue {
	private TextInputControl inputControl;

	public TextFieldValue(TextInputControl inputControl) {
		this.inputControl = inputControl;
	}

	@Override
	public String getText() {
		return inputControl.getText();
	}
}
