package com.maximilianboehm.git.model;

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
      
      // DO STH
      Git git = new Git(rep);
      
      closeRepository();
      
      // TODO Auto-generated method stub
      
      FileRepositoryBuilder builder = new FileRepositoryBuilder();
      Repository repository = builder.setGitDir(new File(""))
        .readEnvironment() // scan environment GIT_* variables
        .findGitDir() // scan up the file system tree
        .build();
      
      RevWalk walk = new RevWalk(repository);
      
      Map<String, Ref> mapX = repository.getAllRefs();
      for(String s:mapX.keySet()){
         System.out.println(s+": "+mapX.get(s));
      }
      
      
      ObjectId head = repository.resolve("HEAD");
      System.out.println(head);
      
      
   }
   
   
   private static void closeRepository() throws Exception {
      rep.close();
   }
   
   private static void openRepository()throws Exception {
      FileRepositoryBuilder builder = new FileRepositoryBuilder();
      rep = builder.setGitDir(new File("C:/MBRepository/.git"))
              .readEnvironment() // scan environment GIT_* variables
              .findGitDir() // scan up the file system tree
              .build();

      System.out.println("Having repository: " + rep.getDirectory());


   }
   

}
