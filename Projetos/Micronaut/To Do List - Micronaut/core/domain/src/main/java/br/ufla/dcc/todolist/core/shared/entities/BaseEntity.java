package br.ufla.dcc.todolist.core.shared.entities;

import br.ufla.dcc.todolist.core.shared.exceptions.DomainException;

public interface BaseEntity {
    void validate() throws DomainException;
}
