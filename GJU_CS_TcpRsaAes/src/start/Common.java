package start;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Common {
   
   public static void sendBytes(byte[] myByteArray, DataOutputStream dos) throws IOException {
      sendBytes(myByteArray, 0, myByteArray.length, dos);
   }

   public static void sendBytes(byte[] myByteArray, int start, int len, DataOutputStream dos) throws IOException {
      if (len < 0)
         throw new IllegalArgumentException("Negative length not allowed");
      if (start < 0 || start >= myByteArray.length)
         throw new IndexOutOfBoundsException("Out of bounds: " + start);

      dos.writeInt(len);
      if (len > 0)
         dos.write(myByteArray, start, len);
   }
   
   public byte[] readBytes(DataInputStream inFromServer)throws Exception {
      //Step 1 read length
      int nb = inFromServer.readInt();
      byte[] digit = new byte[nb];
      //Step 2 read byte
      for(int i = 0; i < nb; i++)
         digit[i] = inFromServer.readByte();
      
      return digit;
    }

}
