package com.vacinasja.utils.validacoes;

import com.vacinasja.error.verificacoes_error.TelefoneInvalido;

public class ValidaTelefone {

    public ValidaTelefone(String telefone) throws TelefoneInvalido {
        if (telefone.isEmpty() || telefone.length() != 9 || containsDigit(telefone)) {
            throw new TelefoneInvalido(telefone);
        }
    }
    public final boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = !Character.isDigit(c)) {
                    break;
                }
            }
        }
        return containsDigit;
    }
}
