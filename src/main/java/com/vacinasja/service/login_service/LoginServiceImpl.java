package com.vacinasja.service.login_service;

import com.vacinasja.error.login_error.LoginInvalido;
import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Login;
import com.vacinasja.model.TipoLogin;
import com.vacinasja.repository.LoginRepository;
import com.vacinasja.service.tipo_login_service.TipoLoginService;
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

    @Override
    public Login criarLogin(String login, String tipoLogin) throws TipoLoginInvalido {
        TipoLogin tipoLoginEncontrado = tipoLoginService.findByTipoLogin(tipoLogin);
        Login novoLogin = new Login(login, LoginUtils.generatePassword(), tipoLoginEncontrado);
        loginRepository.save(novoLogin);
        return novoLogin;
    }

    @Override
    public Login login(String login, String password) throws LoginInvalido {
        Optional<Login> loginEncontrado = loginRepository.findByLoginAndPassword(login, password);

        if (!loginEncontrado.isPresent()) {
            throw new LoginInvalido();
        }

        return loginEncontrado.get();
    }

    @Override
    public Login findByUserLogin(String login) {
        Optional<Login> loginEncontrado = loginRepository.findByLogin(login);
        return loginEncontrado.orElse(null);
    }


}
