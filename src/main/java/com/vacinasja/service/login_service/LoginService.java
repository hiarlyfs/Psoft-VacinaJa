package com.vacinasja.service.login_service;

import com.vacinasja.error.login_error.LoginInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Login;

public interface LoginService {
    Login criarLogin(String login, String tipoLogin) throws TipoLoginInvalido;
    Login login(String login, String password) throws TipoLoginInvalido, LoginInvalido;
    Login findByUserLogin(String login);
}
