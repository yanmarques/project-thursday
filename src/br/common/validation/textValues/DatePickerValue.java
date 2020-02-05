package br.common.validation.textValues;

import br.common.validation.InputTextValue;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DatePickerValue implements InputTextValue {
	private DatePicker datePicker;

	public DatePickerValue(DatePicker datePicker) {
		this.datePicker = datePicker;
	}

	@Override
	public String getText() {
		LocalDate date = datePicker.getValue();

		if (date == null) {
			return "";
		}

		return date.toString();
	}
}
