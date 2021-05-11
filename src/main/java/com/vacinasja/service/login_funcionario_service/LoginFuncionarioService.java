package com.vacinasja.service.login_funcionario_service;

import com.vacinasja.dto.login_funcionario.AuthorizeFuncionarioDto;
import com.vacinasja.error.login_error.AguardandoAprovacao;
import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Funcionario;
import com.vacinasja.model.Login;
import com.vacinasja.model.LoginFuncionario;

import java.util.List;
import java.util.Map;

public interface LoginFuncionarioService {
    LoginFuncionario save(Funcionario funcionario) throws TipoLoginInvalido, StatusInvalido;
    LoginFuncionario findByLogin(Login login);
    void checkAuthorizeToLogin(Login login) throws AguardandoAprovacao;
    List<Map<String, Object>> getLoginsToAuthorize() throws StatusInvalido;
    void authorizeFuncionarios(List<AuthorizeFuncionarioDto> authorizeFuncionariosDto) throws StatusInvalido;
}
