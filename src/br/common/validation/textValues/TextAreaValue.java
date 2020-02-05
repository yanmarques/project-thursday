package br.common.validation.textValues;

import br.common.validation.InputTextValue;
import javafx.scene.control.TextArea;

public class TextAreaValue implements InputTextValue {
    private TextArea textArea;

    public TextAreaValue(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public String getText() {
        return this.textArea.getText();
    }
}
