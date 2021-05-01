package com.vacinasja.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class LoginCidadao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidadao_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cidadao cidadao;

    public LoginCidadao(Login login, Cidadao cidadao) {
        this.login = login;
        this.cidadao = cidadao;
    }

    public LoginCidadao() {}

    public Login getLogin() {
        return login;
    }
}
