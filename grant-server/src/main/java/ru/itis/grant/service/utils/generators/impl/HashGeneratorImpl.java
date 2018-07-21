package ru.itis.grant.service.utils.generators.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.grant.service.utils.generators.interfaces.HashGenerator;

@Component
public class HashGeneratorImpl implements HashGenerator {

    private PasswordEncoder encoder;

    public HashGeneratorImpl() {
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean match(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
