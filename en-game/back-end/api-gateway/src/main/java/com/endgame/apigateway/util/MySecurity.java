package com.endgame.apigateway.util;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

/**
 * @author: Doan Thanh Nhan
 * @created: 2021/03/19
 * @project: order-service
 */
public class MySecurity {

  public static String signSHA256RSA(String input, String privateKey) throws NoSuchAlgorithmException,
    InvalidKeySpecException,
    InvalidKeyException, UnsupportedEncodingException, SignatureException {
    // Remove markers and new line characters in private key
    String realPK = privateKey.replaceAll("-----BEGIN PRIVATE KEY-----", "")
      .replaceAll("-----END PRIVATE KEY-----", "")
      .replaceAll("\n", "");

    byte[] b1 = Base64.getDecoder().decode(realPK);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
    KeyFactory kf = KeyFactory.getInstance("RSA");

    Signature privateSignature = Signature.getInstance("SHA256withRSA");
    privateSignature.initSign(kf.generatePrivate(spec));
    privateSignature.update(input.getBytes("UTF-8"));
    byte[] s = privateSignature.sign();
    return Base64.getEncoder().encodeToString(s);
  }

  public static String getRandomPassword(int length) {
    String validChars = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*?_-";
    Random random = new Random();

    char[] chars = new char[length];
    for (int i = 0; i < length; i++) {
      chars[i] = validChars.charAt(random.nextInt(length));
    }

    return new String(chars);
  }
}
