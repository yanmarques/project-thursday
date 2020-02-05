package br.common.validation;

import br.common.validation.textValues.ComboBoxValue;
import br.common.validation.textValues.DatePickerValue;
import br.common.validation.textValues.TextFieldValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextInputControl;
import sun.security.validator.ValidatorException;

public class AttributesValidator {
	private Validator validator = new Validator();
	private ValidationErrorHandler errorHandler;

	public AttributesValidator(ValidationErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public RuleMapper createRule(TextInputControl node, String alias) {
		return this.validator.enroleAttribute(new TextFieldValue(node), alias);
	}

	public RuleWrapper createWraper(Rule rule) {
		return this.validator.wrapRules().add(rule);
	}

	public RuleWrapper createWraper(RuleNames rule) {
		return this.validator.wrapRules().add(rule);
	}

	public RuleMapper createRule(DatePicker node, String alias) {
		return this.validator.enroleAttribute(new DatePickerValue(node), alias);
	}

	public RuleMapper createRule(ComboBox node, String alias) {
		return this.validator.enroleAttribute(new ComboBoxValue(node), alias);
	}

	public boolean isValid() {
		try {
			this.validator.validate();
			return true;
		} catch (ValidatorException e) {
			this.errorHandler.handleFailed(this.validator.getLastFaileds());

			return false;
		}
	}
}
