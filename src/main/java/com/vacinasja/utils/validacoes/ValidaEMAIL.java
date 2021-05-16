package com.vacinasja.utils.validacoes;

import com.vacinasja.error.verificacoes_error.EmailInvalido;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEMAIL {
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static void validaEMAIL(String email) throws EmailInvalido {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) throw new EmailInvalido(email);
    }

}
