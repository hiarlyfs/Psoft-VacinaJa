package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Habilitado2Dose extends Estado {

    @Override
    public void tentaAlterar(Cidadao cidadao, Integer idade, String profissao, String comorbidade) { // faz nada, pois já está habilitado.
    }

    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, pois já tomou a primeira dose.
        return false;
    }

    @Override
    public void tentaAlterar(Cidadao cidadao, Vacina vacina) {
        cidadao.setNewEstagioVacinacao(new Vacinado());
    }

    @Override
    public String toString() {
        return "Habilitado para tomar a 2o dose";
    }
}
