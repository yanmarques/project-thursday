package br.common.validation;

import br.common.validation.textValues.TextFieldValue;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class RuleWrapper {
	private Validator validator;
	private ArrayList<Rule> rules = new ArrayList<>();

	public RuleWrapper(Validator validator) {
		this.validator = validator;
	}

	public RuleMapper createRule(InputTextValue node, String alias) {
		RuleMapper mapper = this.validator.enroleAttribute(node, alias);
		this.rules.forEach(mapper::add);
		return mapper;
	}

	public RuleMapper createRule(TextField node, String alias) {
		return this.createRule(new TextFieldValue(node), alias);
	}

	public br.common.validation.RuleWrapper add(Rule rule) {
		this.rules.add(rule);
		return this;
	}

	public br.common.validation.RuleWrapper add(RuleNames rule) {
		try {
			this.rules.add(RuleFactory.findByName(rule));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}
}
