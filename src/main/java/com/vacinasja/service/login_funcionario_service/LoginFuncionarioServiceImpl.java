package com.vacinasja.service.login_funcionario_service;

import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.*;
import com.vacinasja.repository.LoginFuncionarioRepository;
import com.vacinasja.service.login_service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginFuncionarioServiceImpl implements LoginFuncionarioService{

    @Autowired
    LoginService loginService;

    @Autowired
    LoginFuncionarioRepository loginFuncionarioRepository;

    @Override
    public LoginFuncionario save(Funcionario funcionario) throws TipoLoginInvalido {
        Login novoLogin = loginService.criarLogin(funcionario.getCpf(), "FUNCIONARIO");
        LoginFuncionario novoLoginFuncionario = new LoginFuncionario(novoLogin, funcionario);
        loginFuncionarioRepository.save(novoLoginFuncionario);
        return novoLoginFuncionario;
    }

    @Override
    public LoginFuncionario findByLogin(Login login) {
        Optional<LoginFuncionario> loginFuncionario = loginFuncionarioRepository.findByLogin(login);
        return loginFuncionario.orElse(null);
    }
}
