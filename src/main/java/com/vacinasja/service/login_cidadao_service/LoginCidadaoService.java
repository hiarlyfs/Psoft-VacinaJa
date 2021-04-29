package com.vacinasja.service.login_cidadao_service;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LoginCidadao;

public interface LoginCidadaoService {
    LoginCidadao criarLoginCidadao(String cpf, Cidadao cidadao);
}
