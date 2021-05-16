package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Habilitado2Dose extends Estado {


    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, pois já tomou a primeira dose.
        return false;
    }

    @Override
    public Boolean tentaAlterar(Cidadao cidadao, Vacina vacina) {
        if (cidadao.pegaFabricanteVacina().equals(vacina.getFabricante()) && cidadao.pegaIntervaloDoses().equals(vacina.getIntervaloDoses())) {
            cidadao.setNewEstagioVacinacao(new Vacinado());
        return true;
        } else {
            return false;
        }
    }

    @Override
    public int getIntEstado() {
        return 4;
    }

    @Override
    public String toString() {
        return "Habilitado para tomar a 2o dose";
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
