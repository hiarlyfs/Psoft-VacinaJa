package com.vacinasja.error.cidadao_error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CidadaoInvalido extends Exception {
    static final String CIDADAO_INVALIDO = "Loginão existe um cidadao com esse cpf";

    public CidadaoInvalido() { super(CIDADAO_INVALIDO); }
}
