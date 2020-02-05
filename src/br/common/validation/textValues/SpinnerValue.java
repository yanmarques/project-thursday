package br.common.validation.textValues;

import br.common.validation.InputTextValue;
import javafx.scene.control.Spinner;

public class SpinnerValue implements InputTextValue {
    private Spinner spinner;

    public SpinnerValue(Spinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public String getText() {
        Object value = this.spinner.getValue();

        if (value != null) {
            return value.toString();
        }

        return null;
    }
}
