package com.vacinasja.repository;

import com.vacinasja.model.Login;
import com.vacinasja.model.LoginFuncionario;
import com.vacinasja.model.StatusCadastro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoginFuncionarioRepository extends JpaRepository<LoginFuncionario, Long> {
    Optional<LoginFuncionario> findByLogin(Login login);
    List<LoginFuncionario> findByStatusCadastro(StatusCadastro statusCadastro);
}
