package com.vacinasja.error.cidadao_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CidadaoNaoEncontradoCpf extends Exception {
    static final String CIDADAO_NAO_ENCONTRADO = "O cidadao de cpf %s não foi encontrado no sistema.";

    public CidadaoNaoEncontradoCpf(String cpf) {
        super(String.format(CIDADAO_NAO_ENCONTRADO, cpf));
    }
}
