package com.vacinasja.repository;

import com.vacinasja.model.StatusCadastro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusCadastroRepository extends JpaRepository<StatusCadastro, Long> {
    Optional<StatusCadastro> findByStatus(String status);

}
