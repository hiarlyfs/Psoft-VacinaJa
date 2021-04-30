package com.vacinasja.service.login_service;

import com.vacinasja.error.login_error.LoginInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Login;
import com.vacinasja.model.TipoLogin;

public interface LoginService {
    Login criarLogin(String login, String tipoLogin) throws TipoLoginInvalido;
    Login findLoginAndPasswordAndTipoLogin(String login, String password, String tipoLogin) throws TipoLoginInvalido, LoginInvalido;

}
