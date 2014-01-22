package com.maximilian_boehm.com.tcp;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA {
   
   public static Key getKeyByBytes(byte[] b)throws Exception{
      return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(b));
   }
   
   public static byte[] decrypt(byte[] data, Key privKey) throws Exception { return cipher(Cipher.DECRYPT_MODE, data, privKey);}
   public static byte[] encrypt(byte[] data, Key pubKey ) throws Exception { return cipher(Cipher.ENCRYPT_MODE, data, pubKey);}
   
   public static byte[] cipher(int nCipherMode, byte[] data, Key key) throws Exception{
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(nCipherMode, key);
      return cipher.doFinal(data);
   }

}
