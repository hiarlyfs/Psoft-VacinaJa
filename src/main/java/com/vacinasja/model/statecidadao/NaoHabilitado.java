package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class NaoHabilitado extends Estado {

    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, pois não tomou a dose.
        return false;
    }

    @Override
    public Boolean tentaAlterar(Cidadao cidadao, Vacina vacina) { // faz nada, pois não está habilitado.
        return false;
    }

    @Override
    public int getIntEstado() {
        return 1;
    }

    @Override
    public String toString() {
        return "Não habilitado para tomar a vacina";
    }

	@Override
	public Boolean tentaAlterarByIdade(Cidadao cidadao, Integer idade) {
		if(cidadao.calculaIdade() >= idade) {
			cidadao.setNewEstagioVacinacao(new Habilitado1Dose());
		}
		return true;
	}

	@Override
	public Boolean tentaAlterarByProfissao(Cidadao cidadao, String profissao) {
		if(cidadao.getProfissao() == profissao) {
			cidadao.setNewEstagioVacinacao(new Habilitado1Dose());
		}
		return true;
	}

	@Override
	public Boolean tentaAlterarByComorbidade(Cidadao cidadao, String comorbidade) {
		if(cidadao.getComorbidade().contains((comorbidade))) {
			cidadao.setNewEstagioVacinacao(new Habilitado1Dose());
		}
		return true;
	}
}
