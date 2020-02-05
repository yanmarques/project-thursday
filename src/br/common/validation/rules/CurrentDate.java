package br.common.validation.rules;

import br.common.validation.Rule;

import java.time.LocalDate;

public class CurrentDate implements Rule {
	private int reason;

	@Override
	public String getMessage() {
		switch (reason) {
			case 1: return "Formato de data invalido.";
			case 2: return "Ano invalido.";
			case 3: return "Mes invalido.";
			case 4: return "Dia invalido.";
		}

		return "Data invalida.";
	}

	@Override
	public boolean apply(String data) {
		if (! new Regex("\\d{4}\\-\\d{2}\\-\\d{2}").apply(data)) {
			this.reason = 1;
			return false;
		}

		String currentDate = LocalDate.now().toString();
		int year = this.extractYear(currentDate);
		int month = this.extractMonth(currentDate);
		int day = this.extractDay(currentDate);

		if (this.extractYear(data) > year) {
			this.reason = 2;
			return false;
		}

		if (this.extractMonth(data) > month) {
			this.reason = 3;
			return false;
		}

		if (this.extractDay(data) > day) {
			this.reason = 4;
			return false;
		}

		return true;
	}

	private int extractYear(String date) {
		return java.lang.Integer.parseInt(date.split("-")[0]);
	}

	private int extractMonth(String date) {
		return java.lang.Integer.parseInt(date.split("-")[1]);
	}

	private int extractDay(String date) {
		return java.lang.Integer.parseInt(date.split("-")[2]);
	}
}
