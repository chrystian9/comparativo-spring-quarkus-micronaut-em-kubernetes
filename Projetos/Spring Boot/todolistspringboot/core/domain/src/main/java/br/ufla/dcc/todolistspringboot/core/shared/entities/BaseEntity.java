package br.ufla.dcc.todolistspringboot.core.shared.entities;

import br.ufla.dcc.todolistspringboot.core.shared.exceptions.DomainException;

public interface BaseEntity {
    void validate() throws DomainException;
}
