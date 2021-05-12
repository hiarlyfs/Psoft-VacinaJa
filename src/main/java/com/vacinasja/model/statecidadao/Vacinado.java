package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Vacinado extends Estado {

    @Override
    public void tentaAlterar(Cidadao cidadao, Integer idade, String profissao, String comorbidade) { // faz nada, já está vacinado.
    }

    @Override
    public boolean tentaAlterar(Cidadao cidadao, LocalDate data) { // faz nada, já está vacinado.
        return false;
    }

    @Override
    public void tentaAlterar(Cidadao cidadao, Vacina vacina) { // faz nada, já está vacinado.
    }

    @Override
    public String toString() {
        return "Finalizada vacinação";
    }
}
