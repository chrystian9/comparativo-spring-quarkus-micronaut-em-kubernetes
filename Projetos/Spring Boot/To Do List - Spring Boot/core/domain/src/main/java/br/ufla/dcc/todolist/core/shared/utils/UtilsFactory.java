package br.ufla.dcc.todolist.core.shared.utils;

public class UtilsFactory {
    private final ValidationUtils validationUtils = new ValidationUtils();

    public ValidationUtils getValidationUtils() {
        return validationUtils;
    }
}
