package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Habilitado1Dose extends Estado {


    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, pois ainda não tomou a dose.
        return false;
    }

    @Override
    public Boolean tentaAlterar(Cidadao cidadao, Vacina vacina) {
        if (vacina.getDosesNecessarias() == 2) {
            cidadao.setIntervaloDoses(vacina.getIntervaloDoses());
            cidadao.setDiaTomouDose(new Date());
            cidadao.setNewEstagioVacinacao(new Tomou1Dose());
            cidadao.setFabricanteVacina(vacina.getFabricante());
        } else
            cidadao.setNewEstagioVacinacao(new Vacinado());
        return true;
    }

    @Override
    public int getIntEstado() {
        return 2;
    }

    @Override
    public String toString() {
        return "Habilitado para tomar a 1o dose";
    }

	@Override
	public Boolean tentaAlterarByIdade(Cidadao cidadao, Integer idade) {
		return false;
	}

	@Override
	public Boolean tentaAlterarByProfissao(Cidadao cidadao, String profissao) {
		return false;
	}

	@Override
	public Boolean tentaAlterarByComorbidade(Cidadao cidadao, String comorbidade) {
		return false;
	}
}
