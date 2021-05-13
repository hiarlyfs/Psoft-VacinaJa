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

    public abstract Boolean tentaAlterarByIdade(Cidadao cidadao, Integer idade);
    public abstract Boolean tentaAlterarByProfissao(Cidadao cidadao, String profissao);
    public abstract Boolean tentaAlterarByComorbidade(Cidadao cidadao, String comorbidade);
    		


    public abstract boolean tentaAlterar(Cidadao cidadao, LocalDate data);

    public abstract Boolean tentaAlterar(Cidadao cidadao, Vacina vacina);
}
