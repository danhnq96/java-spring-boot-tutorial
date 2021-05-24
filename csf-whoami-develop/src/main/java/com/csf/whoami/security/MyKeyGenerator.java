package com.csf.whoami.security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class MyKeyGenerator {
    public static String getKey() throws NoSuchAlgorithmException {
        byte[] keyStart = "this is a museum".getBytes(StandardCharsets.UTF_8);
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(keyStart);
        keygen.init(256, random);
        SecretKey originalKey = keygen.generateKey();
        byte[] raw = originalKey.getEncoded();
        return Base64.getEncoder().encodeToString(raw);
    }
}