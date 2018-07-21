package ru.itis.grant.service.utils.generators.interfaces;

public interface HashGenerator {

    String encode(String password);

    boolean match(String rawPassword, String encodedPassword);
}
