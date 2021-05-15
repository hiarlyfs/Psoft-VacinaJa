package com.vacinasja.error.verificacoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VerificaEmail extends Exception {

    static final String EMAIL_INVALIDO = "O E-Mail %s é inválido.";

    public VerificaEmail(String email) {
        super(String.format(EMAIL_INVALIDO, email));
    }

}

