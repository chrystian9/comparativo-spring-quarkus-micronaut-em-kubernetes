package br.ufla.dcc.todolist.core.shared.exceptions.causes;

import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;

public class DomainValidationException extends CoreException {
    public DomainValidationException() {
    }

    public DomainValidationException(String message) {
        super(message);
    }
}
