package com.vacinasja.error.verificacoes_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public class TelefoneInvalido extends Exception {

        static final String TELEFONE_INVALIDO = "O Telefone %s é inválido.";

        public TelefoneInvalido(String telefone) {
            super(String.format(TELEFONE_INVALIDO, telefone));
        }
    }

