package br.common.validation;

import java.util.ArrayList;
import java.util.Map;

public interface ValidationErrorHandler {
    void handleFailed(Map<String, ArrayList<String>> rules);
}
