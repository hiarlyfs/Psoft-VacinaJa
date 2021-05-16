package com.vacinasja.error.cidadao_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CidadaoNaoHabilitado extends Exception {
    static final String CIDADAO_NAO_HABILITADO = "O cidadão %s não está habilitado para tomar a vacina.";

    public CidadaoNaoHabilitado(String nomeCidadao) {
        super(String.format(CIDADAO_NAO_HABILITADO, nomeCidadao));
    }
}
