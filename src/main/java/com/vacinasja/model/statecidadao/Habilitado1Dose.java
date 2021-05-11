package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Habilitado1Dose extends Estado {

    @Override
    public void tentaAlterar(Cidadao cidadao, Integer idade, String profissao, String comorbidade) { // faz nada, pois já está habilitado.
    }

    @Override
    public void tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, pois ainda não tomou a dose.
    }

    @Override
    public void tentaAlterar(Cidadao cidadao, Vacina vacina) {
        if (vacina.getDosesNecessarias() == 2) {
            cidadao.setIntervaloDoses(vacina.getIntervaloDoses());
            cidadao.setDiaTomouDose(new Date());
            cidadao.setNewEstagioVacinacao(new Tomou1Dose());
        } else
            cidadao.setNewEstagioVacinacao(new Vacinado());
    }

    @Override
    public String toString() {
        return "Habilitado para tomar a 1o dose";
    }
}
