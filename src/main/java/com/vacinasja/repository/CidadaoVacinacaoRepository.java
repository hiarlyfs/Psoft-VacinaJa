package com.vacinasja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.CidadaoVacinacao;

public interface CidadaoVacinacaoRepository extends JpaRepository<CidadaoVacinacao, Long>  {

}
