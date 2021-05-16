package com.vacinasja.utils.validacoes;

import com.vacinasja.error.verificacoes_error.TelefoneInvalido;

public class ValidaTelefone {

    public static void validaTelefone(String telefone) throws TelefoneInvalido {
        if (telefone.isEmpty() || telefone.length() != 9 || containsDigit(telefone)) {
            throw new TelefoneInvalido(telefone);
        }
    }
    public static boolean containsDigit(String s) {
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
