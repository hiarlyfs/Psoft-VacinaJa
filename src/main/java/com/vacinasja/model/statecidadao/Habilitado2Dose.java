package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Habilitado2Dose extends Estado {

    @Override
    public Boolean tentaAlterar(Cidadao cidadao, Integer idade, String profissao, String comorbidade) { // faz nada, pois já está habilitado.
        return false;
    }

    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, pois já tomou a primeira dose.
        return false;
    }

    @Override
    public Boolean tentaAlterar(Cidadao cidadao, Vacina vacina) {
        cidadao.setNewEstagioVacinacao(new Vacinado());
        return true;
    }

    @Override
    public int getIntEstado() {
        return 4;
    }

    @Override
    public String toString() {
        return "Habilitado para tomar a 2o dose";
    }
}
