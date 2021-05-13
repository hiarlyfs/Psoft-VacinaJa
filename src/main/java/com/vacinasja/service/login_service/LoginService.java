package com.vacinasja.service.login_service;

import com.vacinasja.dto.login.LoginDto;
import com.vacinasja.error.login_error.AguardandoAprovacao;
import com.vacinasja.error.login_error.LoginInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Login;

public interface LoginService {
    Login criarLogin(String login, String tipoLogin) throws TipoLoginInvalido;
    String login(LoginDto loginDto) throws LoginInvalido, AguardandoAprovacao;
    Login findByUserLogin(String login);
}
