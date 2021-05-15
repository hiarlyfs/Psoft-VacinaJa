package com.vacinasja.error.verificacoes_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
    public class CPFInvalido extends Exception {

    static final String CPF_INVALIDO = "O CPF %s é inválido.";

    public CPFInvalido(String cpf) {
        super(String.format(CPF_INVALIDO, cpf));
    }

}