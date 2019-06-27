package com.example.sweater;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePassportEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return String.format("Secret: '%s'", charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
