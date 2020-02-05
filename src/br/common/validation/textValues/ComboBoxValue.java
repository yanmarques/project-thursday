package br.common.validation.textValues;

import br.common.validation.InputTextValue;
import javafx.scene.control.ComboBox;

public class ComboBoxValue implements InputTextValue {
    private ComboBox comboBox;

    public ComboBoxValue(ComboBox comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public String getText() {
        Object item = this.comboBox.getSelectionModel().getSelectedItem();

        if (item != null) {
            return item.toString();
        }

        return null;
    }
}
