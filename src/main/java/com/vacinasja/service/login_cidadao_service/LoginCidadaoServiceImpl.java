package com.vacinasja.service.login_cidadao_service;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LoginCidadao;
import com.vacinasja.repository.LoginCidadaoRepository;
import com.vacinasja.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginCidadaoServiceImpl implements LoginCidadaoService {
    @Autowired
    LoginCidadaoRepository loginCidadaoRepository;

    @Override
    public LoginCidadao criarLoginCidadao(String cpf, Cidadao cidadao) {
        int length = 10;
        LoginCidadao novoLoginCidadao = new LoginCidadao(cpf, LoginUtils.generatePassword(length), cidadao);
        loginCidadaoRepository.save(novoLoginCidadao);
        return novoLoginCidadao;
    }
}
