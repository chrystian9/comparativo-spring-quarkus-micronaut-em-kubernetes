package br.ufla.dcc.todolistspringboot.core.shared.exceptions;

public class DomainException extends Exception {
    public DomainException(String message) {
        super(message);
    }
}
