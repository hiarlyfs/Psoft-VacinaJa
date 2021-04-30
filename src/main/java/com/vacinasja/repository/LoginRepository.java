package com.vacinasja.repository;

import com.vacinasja.model.Login;
import com.vacinasja.model.TipoLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByLoginAndPasswordAndTipoLogin(String login, String password, TipoLogin tipoLogin);
}
