package br.ufla.dcc.todolistspringboot.core.shared.exceptions.causes;

import br.ufla.dcc.todolistspringboot.core.shared.exceptions.DomainException;

public class RequiredFieldException extends DomainException {
    public static final String PATTERN = "Field %s is required.";

    public RequiredFieldException(String fieldName) {
        super(String.format(PATTERN, fieldName));
    }
}
