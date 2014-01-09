package de.xabe.struct;

import de.xabe.music.FileMetaRetriever;

public class YTElement {
   
   private FileMetaRetriever retriever = new FileMetaRetriever();
   private String ID;
   private String Title;
   private String ImageURL;
   private String Comment;
   
   public ID3Tag getID3Tag() throws Exception{
      ID3Tag tag = retriever.getTagByString(getTitle());
      tag.setComment(getComment());
      tag.setImage(getImageURL());
      tag.setYoutubeID(ID);
      return tag;
   }
   
   public String getID() {
      return ID;
   }
   public void setID(String iD) {
      ID = iD;
   }
   public String getTitle() {
      return Title;
   }
   public void setTitle(String title) {
      Title = title;
   }
   public String getImageURL() {
      return ImageURL;
   }
   public void setImageURL(String imageURL) {
      ImageURL = imageURL;
   }
   public String getComment() {
      return Comment;
   }
   public void setComment(String comment) {
      Comment = comment;
   }

}
