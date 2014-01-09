package de.xabe.music;

import java.io.File;

import org.junit.Test;

public class TestMe {

   private ID3Toolset toolset = new ID3Toolset();
   private FileMetaRetriever retriever = new FileMetaRetriever();
   
   @Test
   public void test() throws Exception{
      
      File fDir = new File("T:/XXXTESTORDNER");
      for(File f:fDir.listFiles()){
         editFile(f);
      }
   }
   
   private void editFile(File f) throws Exception{
      toolset.setID3Tag(f, retriever.getTagByFile(f));
   }
}
