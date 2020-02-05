package br.common.validation;

import br.common.validation.rules.Double;
import br.common.validation.rules.Integer;
import br.common.validation.rules.*;

public class RuleFactory {
	public static Rule integer() {
		return new Integer();
	}

	public static Rule required() {
		return new Required();
	}

	public static Rule ruleDouble() {
		return new Double();
	}

	public static Rule positive() {
		return new Positive();
	}

	public static Rule currentDate() {
		return new CurrentDate();
	}

	public static Rule findByName(RuleNames rule) throws IllegalArgumentException {
		switch (rule) {
			case REQUIRED: return required();
			case INTEGER: return integer();
			case DOUBLE: return ruleDouble();
			case POSITIVE: return positive();
			case CURRENT_DATE: return currentDate();
		}

		throw new IllegalArgumentException("Rule with name ["+rule.toString()+"] does not exist.");
	}
}
