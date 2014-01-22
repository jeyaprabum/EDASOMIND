package com.maximilian_boehm.com.tcp;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
   
   public static byte[] generateAESKey() throws Exception{
      KeyGenerator keyGen = KeyGenerator.getInstance("AES");
      SecureRandom random = new SecureRandom(); // cryptograph. secure random 
      keyGen.init(random); 
      SecretKey secretKey = keyGen.generateKey();
      
      return secretKey.getEncoded();
   }
   
   public static byte[] encrypt(byte[] value, byte[] bKey) {
      byte[] encrypted = null;
      try {

          Key skeySpec = new SecretKeySpec(bKey, "AES");
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          byte[] iv = new byte[cipher.getBlockSize()];

          IvParameterSpec ivParams = new IvParameterSpec(iv);
          cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivParams);
          encrypted  = cipher.doFinal(value);
          System.out.println("encrypted string:" + encrypted.length);

      } catch (Exception ex) {
          ex.printStackTrace();
      }
      return encrypted;
  }

  public static  byte[] decrypt(byte[] encrypted, byte[] bKey) {
       byte[] original = null;
       Cipher cipher = null;
      try {
          Key key = new SecretKeySpec(bKey, "AES");
          cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          //the block size (in bytes), or 0 if the underlying algorithm is not a block cipher
          byte[] ivByte = new byte[cipher.getBlockSize()];
          //This class specifies an initialization vector (IV). Examples which use
          //IVs are ciphers in feedback mode, e.g., DES in CBC mode and RSA ciphers with OAEP encoding operation.
          IvParameterSpec ivParamsSpec = new IvParameterSpec(ivByte);
          cipher.init(Cipher.DECRYPT_MODE, key, ivParamsSpec);
          original= cipher.doFinal(encrypted);
      } catch (Exception ex) {
          ex.printStackTrace();
      }
      return original;
  }  

}
