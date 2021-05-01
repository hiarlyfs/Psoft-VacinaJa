package com.vacinasja.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoLogin;

    public TipoLogin() {
    }

    public String getTipoLogin() {
        return tipoLogin;
    }
}
