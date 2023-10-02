package br.ufla.dcc.todolistspringboot.core.shared.utils;

public class UtilsFactory {
    private final ValidationUtils validationUtils = new ValidationUtils();

    public ValidationUtils getValidationUtils() {
        return validationUtils;
    }
}
