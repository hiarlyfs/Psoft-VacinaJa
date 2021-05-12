package com.vacinasja.error.cidadao_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CidadaoNaoEncontradoCartaoSus extends Exception {
    static final String CIDADAO_NAO_ENCONTRADO = "O cidadao de cartão do SUS %s não foi encontrado no sistema.";

    public CidadaoNaoEncontradoCartaoSus(String cartaoSus) {
        super(String.format(CIDADAO_NAO_ENCONTRADO, cartaoSus));
    }
}
