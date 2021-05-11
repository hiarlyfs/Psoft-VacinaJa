package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class NaoHabilitado extends Estado {

    @Override
    public void tentaAlterar(Cidadao cidadao, Integer idade, String profissao, String comorbidade) {
        if (cidadao.getIdade() >= idade || cidadao.getProfissao().equals(profissao) || cidadao.getComorbidades().contains(comorbidade)) {
            cidadao.setNewEstagioVacinacao(new Habilitado1Dose());
        }
    }

    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, pois não tomou a dose.
        return false;
    }

    @Override
    public void tentaAlterar(Cidadao cidadao, Vacina vacina) { // faz nada, pois não está habilitado.
    }

    @Override
    public String toString() {
        return "Não habilitado para tomar a vacina";
    }
}
