package com.vacinasja.service.login_cidadao_service;

import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Login;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.repository.LoginCidadaoRepository;
import com.vacinasja.service.login_service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginCidadaoServiceImpl implements LoginCidadaoService {

    @Autowired
    LoginService loginService;

    @Autowired
    LoginCidadaoRepository loginCidadaoRepository;

    @Override
    public LoginCidadao criarLoginCidadao(Cidadao cidadao) throws TipoLoginInvalido {
        Login novoLogin = loginService.criarLogin(cidadao.getCartaoSus(), "CIDADAO");
        LoginCidadao novoLoginCidadao = new LoginCidadao(novoLogin, cidadao);
        loginCidadaoRepository.save(novoLoginCidadao);
        return novoLoginCidadao;
    }
}
