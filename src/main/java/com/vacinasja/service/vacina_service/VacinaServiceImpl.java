package com.vacinasja.service.vacina_service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacinasja.dto.vacina.VacinaDto;
import com.vacinasja.model.Vacina;
import com.vacinasja.repository.VacinaRepository;

@Service
public class VacinaServiceImpl implements VacinaService{
	
	@Autowired
	VacinaRepository vacinaRepository;
	
	@Override
	public Vacina criarTipoVacina(VacinaDto vacinaDto) {
		Vacina novaVacina = new Vacina(vacinaDto.getFabricante(), vacinaDto.getDosesNecessarias(), vacinaDto.getIntervaloDoses());
		vacinaRepository.save(novaVacina);
		return novaVacina;
	}

	@Override
	public List<Vacina> allTiposVacina() {
		return vacinaRepository.findAll();
	}

	@Override
	public Optional<Vacina> getVacinaById(Long id) {
		return vacinaRepository.findById(id);
	}

}
