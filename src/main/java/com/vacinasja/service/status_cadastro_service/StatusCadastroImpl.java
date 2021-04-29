package com.vacinasja.service.status_cadastro_service;

import com.vacinasja.error.status_cadastro_error.StatusInvalido;
import com.vacinasja.model.StatusCadastro;
import com.vacinasja.repository.StatusCadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class StatusCadastroImpl implements StatusCadastroService {

    @Autowired
    StatusCadastroRepository statusCadastroRepository;

    @Override
    public StatusCadastro findByStatus(String status) throws StatusInvalido {
        Optional<StatusCadastro> statusCadastrado = statusCadastroRepository.findByStatus(status);

        if (!statusCadastrado.isPresent()) {
            throw new StatusInvalido(status);
        }

        return statusCadastrado.get();
    }
}
