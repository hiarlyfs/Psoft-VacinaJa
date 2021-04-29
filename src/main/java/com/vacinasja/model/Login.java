package com.vacinasja.model;

import javax.persistence.*;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String login;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private TipoLogin tipoLogin;

    public Login() {
    }

    public Login(String login, String password, TipoLogin tipoLogin) {
        this.login = login;
        this.password = password;
        this.tipoLogin = tipoLogin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
