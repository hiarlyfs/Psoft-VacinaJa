package com.vacinasja.error.lotevacina_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LoteVacinaInexistente extends Exception {
    static final String LOTE_VACINA_INEXISTENTE = "O lote de id %s não está cadastrado.";

    public LoteVacinaInexistente(Long loteVacinaId) {
        super(String.format(LOTE_VACINA_INEXISTENTE, loteVacinaId));
    }
}
