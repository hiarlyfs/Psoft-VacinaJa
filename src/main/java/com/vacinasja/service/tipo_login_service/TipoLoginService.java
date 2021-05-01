package com.vacinasja.service.tipo_login_service;

import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.TipoLogin;

public interface TipoLoginService {
    TipoLogin findByTipoLogin(String tipoLogin) throws TipoLoginInvalido;

}
