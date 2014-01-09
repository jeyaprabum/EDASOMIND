package de.xabe.music;

import java.io.File;

import org.junit.Test;

import de.xabe.struct.ID3Tag;

public class FileMetaRetriever {
   
   @Test
   public void test() throws Exception{
      File fDir = new File("T:/XXXTESTORDNER");
      for(File f:fDir.listFiles()){
         ID3Tag tag = getTagByFile(f);
         System.out.println(tag.getArtist()+"::"+tag.getTitle()+"::"+tag.getRemix());
      }
   }
   
   public ID3Tag getTagByFile(File f) throws Exception{
      String sName = f.getName();
      sName = sName.substring(0, sName.lastIndexOf("."));
      return getTagByString(sName);
   }
   
   public ID3Tag getTagByString(String sName) throws Exception{
      if(!sName.contains("-"))
         throw new Exception("Kein Separator zwischen Title und Artist");
      
      String[] sepArtistTitle = splitByFirstChar(sName, "-");
      String sInterpret = sepArtistTitle[0];
      String sTitle = sepArtistTitle[1];
      
      String sRemix = null;
      if(sTitle.contains("(")){
         String[] sepTitleRemix = splitByFirstChar(sTitle, "(");
         sTitle = sepTitleRemix[0];
         sRemix = sepTitleRemix[1];
         sRemix = sRemix.replace(")", "");
         sRemix = sRemix.replace("(", "");
      }
      
      // CLEANUP
      sInterpret.trim();
      sTitle.trim();
      if(sRemix!=null)
         sRemix.trim();
      
      ID3Tag tag = new ID3Tag();
      tag.setArtist(sInterpret);
      tag.setTitle(sTitle);
      tag.setRemix(sRemix);
      
      return tag;
   }
   
   private String[] splitByFirstChar(String sSource, String sChar){
      String[] aa = new String[2];
      aa[0] = sSource.substring(0, sSource.indexOf(sChar));
      aa[1] = sSource.substring((sSource.indexOf(sChar)+1), sSource.length());
      return aa;
   }

}
