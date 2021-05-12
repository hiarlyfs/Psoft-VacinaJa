package com.vacinasja.error.vacina_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VacinaInexistente extends Exception {
    static final String VACINA_INEXISTENTE = "O id %s de vacina não está cadastrado.";

    public VacinaInexistente(Long vacinaId) {
        super(String.format(VACINA_INEXISTENTE, vacinaId));
    }
}
