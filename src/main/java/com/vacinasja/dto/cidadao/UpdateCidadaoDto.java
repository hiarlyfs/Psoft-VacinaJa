package com.vacinasja.dto.cidadao;

import java.util.List;

public class UpdateCidadaoDto {

    private String nomeCompleto;
    private String endereco;
    private String email;
    private String telefone;
    private String profissao;
    private List<String> cormobidades;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getProfissao() {
        return profissao;
    }

    public List<String> getCormobidades() {
        return cormobidades;
    }
}
