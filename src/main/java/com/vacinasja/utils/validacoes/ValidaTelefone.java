package com.vacinasja.utils.validacoes;

import com.vacinasja.error.verificacoes.VerificaTelefone;

public class ValidaTelefone {

    public ValidaTelefone(String telefone) throws VerificaTelefone {
        if (telefone.isEmpty() || telefone.length() != 9) {
            throw new VerificaTelefone(telefone);
        }
    }
}
