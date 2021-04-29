package com.vacinasja.service.status_cadastro_service;

import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.model.StatusCadastro;

public interface StatusCadastroService {
    StatusCadastro findByStatus(String status) throws StatusInvalido;
}
