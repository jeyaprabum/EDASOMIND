package com.maximilian_boehm.com.tcp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
       Client client = new Client();
       client.start();
    }
    
    private void start() {
       Socket sock = null;
       Key serverPubKey = null;
       BufferedReader clientIn = null;

       // Initialise server connection
       try{
           sock = new Socket(InetAddress.getLocalHost(), Codes.Port);
           clientIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
       } catch (UnknownHostException e) {
           System.out.println("Unknown host.");
           System.exit(1);
       } catch  (IOException e) {
           System.out.println("No I/O");
           System.exit(1);
       }

       // Get server pub key
       try{
          Request req = new Request(clientIn, sock);
          
          
          System.out.println(req.getStatusCode()+": "+new String(req.getData()));
          X509EncodedKeySpec ks = new X509EncodedKeySpec(req.getData());
          
          
           KeyFactory kf = KeyFactory.getInstance("RSA");
           serverPubKey = kf.generatePublic(ks);
           System.out.println(serverPubKey.getEncoded());
       } catch (Exception e) {
           System.out.println("Error obtaining server public key 1.");
           e.printStackTrace();
           System.exit(0);
    }
    }

}