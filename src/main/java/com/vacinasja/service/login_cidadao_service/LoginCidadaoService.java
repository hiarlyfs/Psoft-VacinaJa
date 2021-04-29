package com.vacinasja.service.login_cidadao_service;

import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.Cidadao;
import com.vacinasja.model.LoginCidadao;

public interface LoginCidadaoService {
    LoginCidadao criarLoginCidadao(Cidadao cidadao) throws TipoLoginInvalido;
}
