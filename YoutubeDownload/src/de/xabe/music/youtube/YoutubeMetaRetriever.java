package de.xabe.music.youtube;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import de.xabe.struct.YTElement;

public class YoutubeMetaRetriever {
   
   @Test
   public void test() throws Exception {
      Map<String, YTElement> mapIDTitle = getIDAndTitleByChannel("majesticcasual");
      
//      for(String s:mapIDTitle.keySet())
//         System.out.println("http://www.youtube.com/watch?v="+mapIDTitle.get(s));
      
      
   }
   public Map<String, YTElement> getIDAndTitleByChannel(String sChannel) throws Exception{
      int nPos = 0;
      int nSize = getSize(sChannel);
      System.out.println(nSize);
      Map<String, YTElement> mapID2Element = new HashMap<String, YTElement>();
      String sOpen = "<entry>";
      String sClose = "</entry>";
      
      while(nPos < nSize){
         String sPos = nPos==0 ? "" : "&start-index="+(nPos);
         String sContent = get("http://gdata.youtube.com/feeds/base/users/"+sChannel+"/uploads?max-results=50"+sPos);
         
         int nPrevCloseIndex = 0;
         
         while(sContent.indexOf(sOpen, nPrevCloseIndex)!=-1){
            int nStartIndex = sContent.indexOf(sOpen, nPrevCloseIndex)+sOpen.length();
            int nCloseIndex = sContent.indexOf(sClose, nStartIndex);
            String sEntry = sContent.substring(nStartIndex, nCloseIndex);
            nPrevCloseIndex = nCloseIndex;
            
            if(sEntry.startsWith("http://gdata.youtube.com/feeds/"))
               continue;
            
            YTElement elm = getMetaByEntryString(sEntry);
            mapID2Element.put(elm.getTitle(), elm);
         }
         nPos = nPos+50;
      }
      
      return mapID2Element;
   }
   
   public YTElement getMetaByLink(String sLink) throws Exception{
      // http://gdata.youtube.com/feeds/base/videos/Je9Kz9dighg
      String sEntry = get(sLink);
      return getMetaByEntryString(sEntry);
   }
   
   private YTElement getMetaByEntryString(String sEntry) throws Exception{
      String sLink = StringUtils.substringBetween(sEntry, "<id>", "</id>");
      sLink = sLink.substring(sLink.lastIndexOf("/")+1);

      String sTitle = StringUtils.substringBetween(sEntry, "<title type='text'>", "</title>");
      String sImageURL = StringUtils.substringBetween(sEntry, "src=\"", ".jpg");
      sImageURL +=".jpg";
      
      String sComment = StringUtils.substringBetween(StringEscapeUtils.unescapeHtml4(sEntry), "<span>", "</span>");
            
      sComment = StringEscapeUtils.unescapeHtml4(sComment);
      
      YTElement elm = new YTElement();
      elm.setComment(sComment);
      elm.setID(sLink);
      elm.setImageURL(downloadImage(sImageURL, sLink));
      elm.setTitle(sTitle);
      return elm;
   }
      
   public int getSize(String sChannel) throws Exception {
      String sContent = get("http://gdata.youtube.com/feeds/base/users/"+sChannel+"/uploads");
      String sOpen = "<openSearch:totalResults>";
      String sClose = "</openSearch:totalResults>";
      int nStartindex = sContent.indexOf(sOpen);
      int nCloseIndex = sContent.indexOf(sClose);
      String sSize = sContent.substring(nStartindex+sOpen.length(), nCloseIndex);
      return Integer.parseInt(sSize);
   }
      
   private String get(String s) throws Exception{
      CloseableHttpClient httpClient = HttpClients.createDefault();
      HttpResponse response = httpClient.execute(new HttpGet(s));
      return new BasicResponseHandler().handleResponse(response);
   }
   
   private String downloadImage(String sUrl, String id) throws Exception{
      String filePath = "T:/Musik/WORKDIR/artwork/"+id+".jpg";
      File fImage = new File(filePath);
      if(fImage.exists())
         return filePath;
      
      CloseableHttpClient httpClient = HttpClients.createDefault();
      HttpGet httpget = new HttpGet(sUrl);
      HttpResponse response = httpClient.execute(httpget);
      HttpEntity entity = response.getEntity();
      if (entity != null) {
         BufferedInputStream bis = new BufferedInputStream(entity.getContent());
         BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fImage));
         int inByte;
         while((inByte = bis.read()) != -1) bos.write(inByte);
         bis.close();
         bos.close();
      }
      return filePath;
   }
   
   
}
