package com.vacinasja.repository;

import com.vacinasja.model.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {
    Optional<Cidadao> findByCpf(String cpf);
    Optional<Cidadao> findByCartaoSus(String cartaoSus);
    List<Cidadao> findByProfissao(String profissao);
    List<Cidadao> findByComorbidade(String comorbidade);
    List<Cidadao> findByDataNascimentoLessThanEqual(Date idade);
}
