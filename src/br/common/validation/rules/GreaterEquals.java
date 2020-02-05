package br.common.validation.rules;

import br.common.validation.Rule;

public class GreaterEquals implements Rule {
    private int baseline;

    public GreaterEquals(int baseline) {
        this.baseline = baseline;
    }

    @Override
    public boolean apply(String data) {
        boolean isDouble = new Double().apply(data);

        if (! isDouble) {
            return true;
        }

        return java.lang.Double.parseDouble(data) >= this.baseline;
    }

    @Override
    public String getMessage() {
        return "Valor deve ser maior ou igual a " + this.baseline;
    }
}
