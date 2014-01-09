package de.xabe;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.xabe.music.ID3Toolset;
import de.xabe.music.youtube.YoutubeMetaRetriever;
import de.xabe.struct.YTElement;

public class DownloadChannel {
   
   private YoutubeMetaRetriever ytMetaRetriever = new YoutubeMetaRetriever();
   private ID3Toolset id3 = new ID3Toolset();

   @Test
   public void testMerge() throws Exception{
      String sChannel = "majesticcasual";
      
      //getLinks(sChannel);
      merge(sChannel);
      
   }
   
   private void getLinks(String sChannel) throws Exception{
      // Lade alle Metadaten inklusive Bilder herunter
      Map<String, YTElement> mapYT = ytMetaRetriever.getIDAndTitleByChannel(sChannel);
      
      // Ermittle alle bisherigen IDS
      List<String> listDownloadedMusic = id3.getAllDownloadedYTIDs();
      
      System.out.println("TO-DOWNLOAD");
      System.out.println("#########################################");
      for(String s:mapYT.keySet()){
         YTElement elm = mapYT.get(s);
         
         if(listDownloadedMusic.contains(elm.getID()))continue;
         
         System.out.println("http://www.youtube.com/watch?v="+elm.getID());
      }
   }
   
   private void merge(String sChannel) throws Exception {
      // Lade alle Metadaten inklusive Bilder herunter
      Map<String, YTElement> mapYT = ytMetaRetriever.getIDAndTitleByChannel(sChannel);
      
      File fDir = new File("T:/Musik/WORKDIR/DL");
      // Laufe die Dateien im Ordner ab
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
         }
      }
   }

}
