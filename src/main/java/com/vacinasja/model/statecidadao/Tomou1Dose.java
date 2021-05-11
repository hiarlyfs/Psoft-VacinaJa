package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Entity
public class Tomou1Dose extends Estado {

    @Override
    public void tentaAlterar(Cidadao cidadao, Integer idade, String profissao, String comorbidade) { // faz nada, pois já está habilitado.
    }

    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) {
        // compara se a diferença entre o dia que tomou a dose e o dia atual é igual (ou maior) ao tempo de espera para se tornar habilitado, se for, então muda o estado para habilitado.
        if (Period.between(cidadao.getDiaTomouDose().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), data).getDays() >= cidadao.getIntervaloDoses()) {
            cidadao.setNewEstagioVacinacao(new Habilitado2Dose());
        }
        return true;
    }

    @Override
    public void tentaAlterar(Cidadao cidadao, Vacina vacina) { // faz nada, esperando estar habilitado.
    }

    @Override
    public String toString() {
        return "Esperando a 2o dose";
    }
}