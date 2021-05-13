package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Vacinado extends Estado {

    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, já está vacinado.
        return false;
    }

    @Override
    public Boolean tentaAlterar(Cidadao cidadao, Vacina vacina) { // faz nada, já está vacinado.
        return false;
    }

    @Override
    public String toString() {
        return "Finalizada vacinação";
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
