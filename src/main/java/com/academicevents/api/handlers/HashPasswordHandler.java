package com.academicevents.api.handlers;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class HashPasswordHandler {
    public static String hashPassword(String originalStringPassword) {
        String encodedPassword;
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(originalStringPassword.getBytes(StandardCharsets.UTF_8));
            encodedPassword = Base64.getEncoder().encodeToString(hashbytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return encodedPassword;
    }
}
