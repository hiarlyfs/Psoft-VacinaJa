package com.vacinasja.repository;

import com.vacinasja.model.TipoLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoLoginRepository extends JpaRepository<TipoLogin, Long> {
    Optional<TipoLogin> findByTipoLogin(String tipoLogin);

}
