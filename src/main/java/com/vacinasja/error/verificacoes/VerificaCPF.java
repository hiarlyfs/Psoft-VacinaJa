package com.vacinasja.error.verificacoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
    public class VerificaCPF extends Exception {

    static final String CPF_INVALIDO = "O CPF %s é inválido.";

    public VerificaCPF(String cpf) {
        super(String.format(CPF_INVALIDO, cpf));
    }

}