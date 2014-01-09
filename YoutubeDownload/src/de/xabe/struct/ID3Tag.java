package de.xabe.struct;

public class ID3Tag {
   
   private String Title;
   private String Artist;
   private String Remix;
   private String Image;
   private String Comment;
   private String YoutubeID;
   
   public String getYoutubeID() {
      return YoutubeID;
   }
   public void setYoutubeID(String youtubeID) {
      YoutubeID = youtubeID;
   }
   public String getImage() {
      return Image;
   }
   public void setImage(String image) {
      Image = image;
   }
   public String getComment() {
      return Comment;
   }
   public void setComment(String comment) {
      Comment = comment;
   }
   public String getTitle() {
      return Title;
   }
   public void setTitle(String title) {
      Title = title;
   }
   public String getArtist() {
      return Artist;
   }
   public void setArtist(String artist) {
      Artist = artist;
   }
   public String getRemix() {
      return Remix;
   }
   public void setRemix(String remix) {
      if(remix!=null)
         Remix = remix;
   }

}
