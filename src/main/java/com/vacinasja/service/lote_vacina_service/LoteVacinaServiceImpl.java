package com.vacinasja.service.lote_vacina_service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinasja.dto.lote_vacina.LoteVacinaDto;
import com.vacinasja.error.lotevacina_error.LoteVacinaInexistente;
import com.vacinasja.error.vacina_error.VacinaInexistente;
import com.vacinasja.model.LoteVacina;
import com.vacinasja.model.Vacina;
import com.vacinasja.repository.LoteVacinaRepository;
import com.vacinasja.repository.VacinaRepository;

@Service
public class LoteVacinaServiceImpl implements LoteVacinaService{
	
	@Autowired
	LoteVacinaRepository loteVacinaRepository;
	
	@Autowired
	VacinaRepository vacinaRepository;

	@Override
	public LoteVacina cadastrarLoteVacina(LoteVacinaDto loteVacinaDto) throws ParseException, VacinaInexistente{
		Date dataValidade = new SimpleDateFormat("yyyy-MM-dd").parse(loteVacinaDto.getDataValidade());
		
		Optional<Vacina> vacina = vacinaRepository.findById(loteVacinaDto.getVacinaId());
		if (!vacina.isPresent()) {
			throw new VacinaInexistente(loteVacinaDto.getVacinaId());
		}
		
		LoteVacina novoLoteVacina = new LoteVacina(dataValidade, loteVacinaDto.getQtdDoses(), vacina.get());
		loteVacinaRepository.save(novoLoteVacina);
		return novoLoteVacina;
	}

	@Override
	public List<LoteVacina> allLotesVacina() {
		return loteVacinaRepository.findAll();
	}

	@Override
	public LoteVacina getLoteVacinaById(Long id) throws LoteVacinaInexistente {
		Optional<LoteVacina> loteVacinaEncontrado = loteVacinaRepository.findById(id);
		if (!loteVacinaEncontrado.isPresent()) throw new LoteVacinaInexistente(id);
		return loteVacinaEncontrado.get();
	}


}
