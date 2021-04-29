package com.vacinasja.error.status_cadastro_error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class StatusInvalido extends Exception{
    static final String STATUS_INVALIDO = "O %s não existe";

    public StatusInvalido(String status) {
        super(String.format(STATUS_INVALIDO, status));
    }

}
