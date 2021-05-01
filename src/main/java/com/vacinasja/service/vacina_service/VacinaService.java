package com.vacinasja.service.vacina_service;

import java.util.List;
import java.util.Optional;

import com.vacinasja.dto.vacina.VacinaDto;
import com.vacinasja.model.Vacina;

public interface VacinaService {
    Vacina criarTipoVacina(VacinaDto vacinaDto);
    List<Vacina> allTiposVacina();
    Optional<Vacina> getVacinaById(Long id);
}
