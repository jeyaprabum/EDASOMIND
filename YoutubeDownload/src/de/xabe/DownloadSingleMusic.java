package de.xabe;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.xabe.music.ID3Toolset;
import de.xabe.music.youtube.YoutubeMetaRetriever;
import de.xabe.struct.YTElement;

public class DownloadSingleMusic {
   
   private YoutubeMetaRetriever ytMetaRetriever = new YoutubeMetaRetriever();
   private ID3Toolset id3 = new ID3Toolset();

   @Test
   public void testMerge() throws Exception{
      List<String> listDL = readfile("2DL.txt");
      
      for(String sLink:listDL)
         merge(sLink);
   }
   
   
   private void merge(String sLink) throws Exception {
      // Lade alle Metadaten inklusive Bilder herunter
      YTElement elm = ytMetaRetriever.getMetaByLink(sLink);
      
      File fDir = new File("T:/Musik/WORKDIR/DL");
      // Laufe die Dateien im Ordner ab
      for(File f:fDir.listFiles()){
         // Ermittle aktuellen Dateinamen
         String sName = f.getName();
         sName = sName.substring(0, sName.lastIndexOf("."));
      
         // Gehört der Dateiname zum Channel?
         if(elm.getTitle().equals(sName))
            // Meta-Daten setzen
            id3.setID3Tag(f, elm.getID3Tag());
      }
   }

   
   private List<String> readfile(String sFile) throws Exception {
      List<String> listID = new ArrayList<>();
      BufferedReader br = new BufferedReader(new InputStreamReader(DownloadSingleMusic.class.getResourceAsStream(sFile)));
      String line = br.readLine();

      while(line != null){
         listID.add(line);
         line = br.readLine();
      }
      
      return listID;
      
   }
   
   
}
