package br.common.validation;

import sun.security.validator.ValidatorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Validator {
	private ArrayList<RuleMapper> mappings = new ArrayList<>();
	private Map<String, ArrayList<String>> lastFaileds;

	public Map<String, ArrayList<String>> getLastFaileds() {
		return this.lastFaileds;
	}

	public RuleMapper enroleAttribute(InputTextValue node, String alias) {
		RuleMapper mapper = new RuleMapper(node, alias);
		this.mappings.add(mapper);
		return mapper;
	}

	public RuleWrapper wrapRules() {
		return new RuleWrapper(this);
	}

	public void validate() throws ValidatorException {
		Map<String, ArrayList<String>> fails = new HashMap<>();

		for (RuleMapper mapper: this.mappings) {
			String data = mapper.getNode().getText();
			ArrayList<String> messages = new ArrayList<>();

			for (Rule rule: mapper.getRules()) {
				if (! rule.apply(data)) {
					messages.add(rule.getMessage());
				}
			}

			if (! messages.isEmpty()) {
				fails.put(mapper.getAlias(), messages);
			}
		}

		if (! fails.isEmpty()) {
			this.lastFaileds = fails;
			throw new ValidatorException("Input data is not valid.");
		}
	}
}
