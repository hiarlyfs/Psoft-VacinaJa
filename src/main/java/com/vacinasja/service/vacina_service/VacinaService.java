package com.vacinasja.service.vacina_service;

import java.util.List;

import com.vacinasja.dto.vacina.VacinaDto;
import com.vacinasja.error.vacina_error.VacinaInexistente;
import com.vacinasja.model.Vacina;

public interface VacinaService {
    Vacina criarTipoVacina(VacinaDto vacinaDto);
    List<Vacina> allTiposVacina();
    Vacina getVacinaById(Long id) throws VacinaInexistente;
}
