package com.vacinasja.model.statecidadao;

import com.vacinasja.model.Cidadao;
import com.vacinasja.model.Vacina;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public abstract class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public abstract void tentaAlterar(Cidadao cidadao, Integer idade, String profissao, String comorbidade);

    public abstract void tentaAlterar(Cidadao cidadao, LocalDate data);

    public abstract void tentaAlterar(Cidadao cidadao, Vacina vacina);
}
