package com.vacinasja.service.tipo_login_service;

import com.vacinasja.error.tipo_login_error.TipoLoginInvalido;
import com.vacinasja.model.TipoLogin;
import com.vacinasja.repository.TipoLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoLoginServiceImpl implements TipoLoginService {

    @Autowired
    TipoLoginRepository tipoLoginRepository;

    @Override
    public TipoLogin findByTipoLogin(String tipoLogin) throws TipoLoginInvalido {
        Optional<TipoLogin> tipoLoginEncontrado = tipoLoginRepository.findByTipoLogin(tipoLogin);

        if (!tipoLoginEncontrado.isPresent()) {
            throw new TipoLoginInvalido(tipoLogin);
        }

        return tipoLoginEncontrado.get();
    }
}
