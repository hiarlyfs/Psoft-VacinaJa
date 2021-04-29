package com.vacinasja.error.tipo_login_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TipoLoginInvalido extends Exception {
    static final String STATUS_INVALIDO = "O login do tipo %s não existe";

    public TipoLoginInvalido(String tipoLogin) {
        super(String.format(STATUS_INVALIDO, tipoLogin));
    }
}
