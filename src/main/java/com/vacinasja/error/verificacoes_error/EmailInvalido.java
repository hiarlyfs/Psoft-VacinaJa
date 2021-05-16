package com.vacinasja.error.verificacoes_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmailInvalido extends Exception {

    static final String EMAIL_INVALIDO = "O E-Mail %s é inválido.";

    public EmailInvalido(String email) {
        super(String.format(EMAIL_INVALIDO, email));
    }

}

