package br.common.validation;

import java.util.ArrayList;

public class RuleMapper {
	private InputTextValue node;
	private String alias;
	private ArrayList<Rule> rules = new ArrayList<>();

	public RuleMapper(InputTextValue node, String alias) {
		this.node = node;
		this.alias = alias;
	}

	public br.common.validation.RuleMapper add(Rule rule) {
		this.getRules().add(rule);
		return this;
	}

	public br.common.validation.RuleMapper add(RuleNames rule) {
		try {
			return this.add(RuleFactory.findByName(rule));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return this;
	}

	public ArrayList<Rule> getRules() {
		return this.rules;
	}

	public InputTextValue getNode() {
		return node;
	}

	public void setNode(InputTextValue node) {
		this.node = node;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
