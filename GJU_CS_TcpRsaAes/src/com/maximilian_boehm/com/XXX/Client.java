package com.maximilian_boehm.com.XXX;
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

       // Initialise server connection
       try{
           sock = new Socket(InetAddress.getLocalHost(), Codes.Port);
       } catch (UnknownHostException e) {
           System.out.println("Unknown host.");
           System.exit(1);
       } catch  (IOException e) {
           System.out.println("No I/O");
           System.exit(1);
       }

       // Get server pub key
       try{
          System.out.println("Beginn Connection");
          TCPRequest req = new TCPRequest(Codes.STATUS_RSA_KEY, "OK".getBytes());
          req.send(sock);
          System.out.println("Request sent");
          System.out.println("Waiting on Response");
          TCPResponse res = new TCPResponse(sock);
          System.out.println(req.getStatusCode()+": "+new String(res.getData()));
          X509EncodedKeySpec ks = new X509EncodedKeySpec(res.getData());
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