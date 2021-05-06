package com.vacinasja.repository;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCidadao(Cidadao cidadao);
    Optional<Funcionario> findById(Long id);
}
