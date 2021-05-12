package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class NaoHabilitado extends Estado {

    @Override
    public Boolean tentaAlterar(Cidadao cidadao, Integer idade, String profissao, String comorbidade) {
        if (cidadao.calculaIdade() >= idade || cidadao.getProfissao().equals(profissao) || cidadao.getComorbidade().contains(comorbidade)) {
            cidadao.setNewEstagioVacinacao(new Habilitado1Dose());
        }
        return true;
    }

    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, pois não tomou a dose.
        return false;
    }

    @Override
    public Boolean tentaAlterar(Cidadao cidadao, Vacina vacina) { // faz nada, pois não está habilitado.
        return false;
    }

    @Override
    public String toString() {
        return "Não habilitado para tomar a vacina";
    }
}
