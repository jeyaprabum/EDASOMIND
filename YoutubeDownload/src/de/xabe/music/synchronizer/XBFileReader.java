package de.xabe.music.synchronizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.xabe.DownloadSingleMusic;

public class XBFileReader {
   
   @Test
   public void test() throws Exception {
      for(String s:readfile(DownloadSingleMusic.class, "2DL.txt"))
         System.out.println(s);
   }
   
   public java.util.List<String> readfile(Class<DownloadSingleMusic> cls, String sFile) throws Exception {
      List<String> listID = new ArrayList<>();
      BufferedReader br = new BufferedReader(new InputStreamReader(cls.getResourceAsStream(sFile)));
      String line = br.readLine();

      while(line != null){
         listID.add(line);
         line = br.readLine();
      }
      
      return listID;
      
   }

}
