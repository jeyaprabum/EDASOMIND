package com.maximilianboehm.git.model._OLD;

import java.io.File;
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;


public class ReadGit {
   
   private static Repository rep = null;

   public static void main(String[] args) throws Exception {
      
      
      openRepository();
      System.out.println(rep.getClass());
      // DO STH
      Git git = new Git(rep);
      // TODO Auto-generated method stub
      RevWalk walk = new RevWalk(rep);
      
      Map<String, Ref> mapX = rep.getAllRefs();
      for(String s:mapX.keySet()){
         System.out.println(s+": "+mapX.get(s));
      }
      
      
      ObjectId head = rep.resolve("HEAD");
      System.out.println(head);
      
      closeRepository();
      
   }
   
   
   private static void closeRepository() throws Exception {
      rep.close();
   }
   
   private static void openRepository()throws Exception {
      FileRepositoryBuilder builder = new FileRepositoryBuilder();
      rep = builder.setGitDir(new File("C:/MBRepository/"))
              .readEnvironment() // scan environment GIT_* variables
              .findGitDir() // scan up the file system tree
              .build();

      System.out.println("Having repository: " + rep.getDirectory());


   }
   

}
