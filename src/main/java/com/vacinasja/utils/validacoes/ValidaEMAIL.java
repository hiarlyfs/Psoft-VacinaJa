package com.vacinasja.utils.validacoes;

import com.vacinasja.error.verificacoes_error.EmailInvalido;

public class ValidaEMAIL {

    public static void validaEMAIL(String email) throws EmailInvalido {
        if (email.isEmpty() || !(email.contains("@")) || !(email.contains("."))) throw new EmailInvalido(email);
    }

}
