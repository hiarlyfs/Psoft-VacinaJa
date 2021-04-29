package com.vacinasja.service.login_service;

import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Login;
import com.vacinasja.model.TipoLogin;
import com.vacinasja.repository.LoginRepository;
import com.vacinasja.service.tipo_login_service.TipoLoginService;
import com.vacinasja.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
