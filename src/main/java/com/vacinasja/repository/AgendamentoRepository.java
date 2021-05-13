package com.vacinasja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacinasja.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

}
