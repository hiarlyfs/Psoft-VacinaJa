package com.vacinasja.utils.validacoes;

import com.vacinasja.error.verificacoes.VerificaEmail;

public class ValidaEMAIL {
    
    public ValidaEMAIL(String email) throws VerificaEmail {
        if (email.isEmpty() || !(email.contains("@")) || !(email.contains("."))) throw new VerificaEmail(email);
    }

}
