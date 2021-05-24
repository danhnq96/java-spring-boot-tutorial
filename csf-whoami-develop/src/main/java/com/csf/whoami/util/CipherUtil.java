package com.csf.whoami.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherUtil {

    private final static String cipherKey = "csf-whoami";

    public static String Encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = cipherKey.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }

        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(results);
    }

    public static String Decrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = cipherKey.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }

        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
    }
}
