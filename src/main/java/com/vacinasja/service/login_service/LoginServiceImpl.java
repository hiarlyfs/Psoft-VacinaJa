package com.vacinasja.service.login_service;

import com.vacinasja.dto.login.LoginDto;
import com.vacinasja.error.login_error.AguardandoAprovacao;
import com.vacinasja.error.login_error.LoginInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Login;
import com.vacinasja.model.TipoLogin;
import com.vacinasja.repository.LoginRepository;
import com.vacinasja.service.login_funcionario_service.LoginFuncionarioService;
import com.vacinasja.service.tipo_login_service.TipoLoginService;
import com.vacinasja.service.token_authenticate_service.TokenAuthenticateService;
import com.vacinasja.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    TipoLoginService tipoLoginService;

    @Autowired
    LoginFuncionarioService loginFuncionarioService;

    @Autowired
    TokenAuthenticateService tokenAuthenticateService;

    @Override
    public Login criarLogin(String login, String tipoLogin) throws TipoLoginInvalido {
        TipoLogin tipoLoginEncontrado = tipoLoginService.findByTipoLogin(tipoLogin);
        Login novoLogin = new Login(login, LoginUtils.generatePassword(), tipoLoginEncontrado);
        loginRepository.save(novoLogin);
        return novoLogin;
    }

    @Override
    public String login(LoginDto loginDto) throws LoginInvalido, AguardandoAprovacao {
        Optional<Login> loginEncontrado = loginRepository.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());

        if (!loginEncontrado.isPresent()) {
            throw new LoginInvalido();
        }

        Login login = loginEncontrado.get();

        if (login.getTipoLogin().equals("FUNCIONARIO")) {
            loginFuncionarioService.checkAuthorizeToLogin(login);
        }

        return tokenAuthenticateService.createToken(loginDto.getLogin());
    }

    @Override
    public Login findByUserLogin(String login) {
        Optional<Login> loginEncontrado = loginRepository.findByLogin(login);
        return loginEncontrado.orElse(null);
    }
}
