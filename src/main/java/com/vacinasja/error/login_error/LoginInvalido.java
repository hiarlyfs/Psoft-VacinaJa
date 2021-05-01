package com.vacinasja.error.login_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class LoginInvalido extends Exception{
    static final String LOGIN_INVALIDO = "Login inválido ou inexistente";

    public LoginInvalido() {
        super(LOGIN_INVALIDO);
    }

}
