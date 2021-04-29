package com.vacinasja.service.login_service;

import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Login;
import com.vacinasja.model.TipoLogin;

public interface LoginService {
    Login criarLogin(String login, String tipoLogin) throws TipoLoginInvalido;

}
