package br.ufla.dcc.todolist.core.shared.exceptions.causes;

import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;

public class RequiredFieldException extends DomainException {
    public static final String PATTERN = "Field %s is required.";

    public RequiredFieldException(String fieldName) {
        super(String.format(PATTERN, fieldName));
    }
}
