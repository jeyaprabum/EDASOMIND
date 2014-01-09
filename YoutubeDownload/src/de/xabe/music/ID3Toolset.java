package de.xabe.music;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;

import de.xabe.struct.ID3Tag;

public class ID3Toolset {
   
   public void setID3Tag(File f, ID3Tag tag) throws Exception{
      
      // used: https://github.com/mpatric/mp3agic
      
      Mp3File mp3file = new Mp3File(f.getAbsolutePath());
      
      ID3v2 id3v2Tag;
      if (mp3file.hasId3v2Tag()) {
        id3v2Tag = mp3file.getId3v2Tag();
      } else {
        // mp3 does not have an ID3v2 tag, let's create one..
        id3v2Tag = new ID3v24Tag();
        mp3file.setId3v2Tag(id3v2Tag);
      }
      
      id3v2Tag.setTitle(tag.getTitle());
      id3v2Tag.setArtist(tag.getArtist());
      
      if(tag.getRemix()!=null)
         id3v2Tag.setComposer(tag.getRemix());
      
      id3v2Tag.setComment(tag.getComment());
      id3v2Tag.setEncoder("YT:"+tag.getYoutubeID());
      
      RandomAccessFile file = new RandomAccessFile(new File(tag.getImage()), "r");
      byte[] bytes = new byte[(int) file.length()];
      file.read(bytes);
      file.close();

      id3v2Tag.setAlbumImage(bytes, "image/jpeg");
      
      mp3file.save("T:/Musik/WORKDIR/TAGGED/"+f.getName());
      
      f.delete();
   }
   
   public List<String> getAllDownloadedYTIDs() throws Exception{
      List<String> listIDs = new ArrayList<>();
      
      File fDir = new File("T:/Musik/_SORTIERT/_YOUTUBE");
      // Laufe die Dateien im Testordner ab
      for(File f:fDir.listFiles()){
         String sID = getYTIDByFile(f);
         if(!listIDs.contains(sID))
            listIDs.add(sID);
      }
      return listIDs;
   }
   
   public String getYTIDByFile(File f) throws Exception{
      Mp3File mp3file = new Mp3File(f.getAbsolutePath());
      if (mp3file.hasId3v2Tag()) {
        ID3v2 id3v2Tag = mp3file.getId3v2Tag();
        String sID = id3v2Tag.getEncoder();
        return sID.substring(3);
      } else
         throw new Exception("KEINE ID GEFUNDEN? "+f.getAbsolutePath());
   }
   
   
   
   public void removeID3Tag(File f)throws Exception {
      // NOCH NICHT SO WIE ES SOLL, aber ungefähr :D
      
      
      Mp3File mp3file = new Mp3File(f.getAbsolutePath());
      if (mp3file.hasId3v1Tag()) {
        mp3file.removeId3v1Tag();
      }
      if (mp3file.hasId3v2Tag()) {
        mp3file.removeId3v2Tag();
      }
      if (mp3file.hasCustomTag()) {
        mp3file.removeCustomTag();
      }
      mp3file.save("T:/NO_ID3/"+f.getName());
      
   }
   
}
