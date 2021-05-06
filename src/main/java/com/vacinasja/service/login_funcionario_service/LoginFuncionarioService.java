package com.vacinasja.service.login_funcionario_service;

import com.vacinasja.error.cidadao_error.CidadaoInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Funcionario;
import com.vacinasja.model.Login;
import com.vacinasja.model.LoginFuncionario;

public interface LoginFuncionarioService {
    LoginFuncionario save(Funcionario funcionario) throws TipoLoginInvalido;
    LoginFuncionario findByLogin(Login login);
}
