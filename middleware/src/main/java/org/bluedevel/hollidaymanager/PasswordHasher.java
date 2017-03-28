package org.bluedevel.hollidaymanager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Robin Engel
 */
@Component
public class PasswordHasher {

    @Value("${hollidaymanager.security.passwords.algorithm}")
    private String algorithm;

    public String hash(String secret) throws NoSuchAlgorithmException {
        if (StringUtils.isEmpty(secret)) {
            throw new IllegalArgumentException("Secret cannot be empty");
        }

        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(secret.getBytes());
        byte[] hashBytes = md.digest();
        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
