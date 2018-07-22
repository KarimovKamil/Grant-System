package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.ActivationKey;

public interface ActivationKeyDao {

    void addActivationKey(ActivationKey activationKey);

    ActivationKey getActivationKey(String key);

    void deleteUserActivationKeys(long userId);

    boolean verifySendingKeyPossibility(long userId);

    boolean activationKeyExistence(String key);
}
