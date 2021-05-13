package com.vacinasja.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacinasja.model.LocalVacinacao;

public interface LocalVacinacaoRepository extends JpaRepository<LocalVacinacao, Long> {
	Optional<LocalVacinacao> findByNomeLocal(String nomeLocal);
}
