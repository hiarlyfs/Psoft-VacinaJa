package com.vacinasja.repository;

import com.vacinasja.model.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {
    Optional<Cidadao> findByCpf(String cpf);
}
