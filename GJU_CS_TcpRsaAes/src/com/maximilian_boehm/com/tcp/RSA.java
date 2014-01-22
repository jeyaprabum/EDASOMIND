package com.maximilian_boehm.com.tcp;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA {
   
   public static Key getKeyByBytes(byte[] b)throws Exception{
      return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(b));
   }
   
   public static byte[] decrypt(byte[] data, Key privKey) throws Exception {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, privKey);
      byte[] cipherData = cipher.doFinal(data);
      return cipherData;
   }
   
   public static byte[] encrypt(byte[] data, Key pubKey) throws Exception {
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, pubKey);
      byte[] cipherData = cipher.doFinal(data);
      return cipherData;
   }

}
