package br.ufla.dcc.todolist.core.shared.exceptions.causes;

import br.ufla.dcc.todolist.core.shared.exceptions.CoreException;

public class OutputPortException extends CoreException {
    public OutputPortException() {
    }

    public OutputPortException(String message) {
        super(message);
    }
}
