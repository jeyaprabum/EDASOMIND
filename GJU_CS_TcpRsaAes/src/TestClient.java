import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class TestClient {

    /**
     * @param args
     */
    public static void main(String[] args) {

        final int sPort = 4322;

        Socket sock = null;
        Key serverPubKey = null;
        BufferedReader clientIn = null;

        // Initialise server connection
        try{
            sock = new Socket(InetAddress.getLocalHost(), sPort);
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
            String[] line = clientIn.readLine().split(",");
            System.out.println(Integer.parseInt(line[0]));
            int len = Integer.parseInt(line[1]);
            byte[] servPubKeyBytes = new byte[len];
            sock.getInputStream().read(servPubKeyBytes,0,len);
            System.out.println(new String(servPubKeyBytes));
            X509EncodedKeySpec ks = new X509EncodedKeySpec(servPubKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            serverPubKey = kf.generatePublic(ks);
            System.out.println(serverPubKey.getEncoded());
        } catch (IOException e) {
            System.out.println("Error obtaining server public key 1.");
            e.printStackTrace();
            System.exit(0);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error obtaining server public key 2.");
            System.exit(0);
        } catch (InvalidKeySpecException e) {
            System.out.println("Error obtaining server public key 3.");
            System.exit(0);
        }

    }

}