package br.common.validation;

public interface Rule {
	boolean apply(String data);

	String getMessage();
}
