package com.vacinasja.repository;

import com.vacinasja.model.LoginCidadao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginCidadaoRepository extends JpaRepository<LoginCidadao, Long> {
    Optional<LoginCidadao> findByLogin(String login);
}
