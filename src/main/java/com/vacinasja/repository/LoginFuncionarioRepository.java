package com.vacinasja.repository;

import com.vacinasja.model.Funcionario;
import com.vacinasja.model.Login;
import com.vacinasja.model.LoginFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginFuncionarioRepository extends JpaRepository<LoginFuncionario, Long> {
    Optional<LoginFuncionario> findByLogin(Login login);
}
