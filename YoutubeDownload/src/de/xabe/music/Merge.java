package de.xabe.music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.xabe.music.youtube.YoutubeMetaRetriever;
import de.xabe.struct.YTElement;

public class Merge {
   
   private FileMetaRetriever fMetaRetriever = new FileMetaRetriever();
   private YoutubeMetaRetriever ytMetaRetriever = new YoutubeMetaRetriever();
   private ID3Toolset id3 = new ID3Toolset();
   
   @Test
   public void testMerge() throws Exception{
      // Lade alle Metadaten inklusive Bilder herunter
      Map<String, YTElement> mapYT = ytMetaRetriever.getIDAndTitleByChannel("thesoundyouneed1");
      
      // Liste für aktualisierte Dateien
      List<String> listUpdated = new ArrayList<String>();
      
      File fDir = new File("T:/XXXTESTORDNER");
      // Laufe die Dateien im Testordner ab
      for(File f:fDir.listFiles()){
         // Ermittle aktuellen Dateinamen
         String sName = f.getName();
         sName = sName.substring(0, sName.lastIndexOf("."));
      
         // Gehört der Dateiname zum Channel?
         if(mapYT.containsKey(sName)){
            // Meta-Daten ermitteln
            YTElement elm = mapYT.get(sName);
            // Meta-Daten setzen
            id3.setID3Tag(f, elm.getID3Tag());
            
            listUpdated.add(elm.getID());
         }
      }
      
      System.out.println("TO-DOWNLOAD");
      System.out.println("#########################################");
      for(String s:mapYT.keySet()){
         YTElement elm = mapYT.get(s);
         
         if(listUpdated.contains(elm.getID()))continue;
         
         System.out.println("http://www.youtube.com/watch?v="+elm.getID());
      }
      
   }

}
