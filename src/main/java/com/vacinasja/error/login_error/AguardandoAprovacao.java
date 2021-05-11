package com.vacinasja.error.login_error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class AguardandoAprovacao extends Exception {
    static final String LOGIN_INVALIDO = "Aguardando aprovação do administrador do sistema";

    public AguardandoAprovacao() {
        super(LOGIN_INVALIDO);
    }

}
