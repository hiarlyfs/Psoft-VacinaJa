package com.vacinasja.service.lote_vacina_service;

import java.text.ParseException;
import java.util.List;

import com.vacinasja.dto.lote_vacina.InsertLoteVacinaDto;
import com.vacinasja.error.lotevacina_error.LoteVacinaInexistente;
import com.vacinasja.error.vacina_error.VacinaInexistente;
import com.vacinasja.model.LoteVacina;

public interface LoteVacinaService{
    LoteVacina cadastrarLoteVacina(InsertLoteVacinaDto loteVacinaDto) throws ParseException, VacinaInexistente;
    List<LoteVacina> allLotesVacina();
	LoteVacina getLoteVacinaById(Long id) throws LoteVacinaInexistente;
}
