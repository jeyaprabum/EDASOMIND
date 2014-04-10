package com.maximilianboehm.git.model;

import java.io.File;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class RepoManager implements AutoCloseable{

   Repository rep = null;
   
   public Repository getRepository() throws Exception {
      return rep;
   }
   
   public RepoManager() throws Exception {
      FileRepositoryBuilder builder = new FileRepositoryBuilder();
      rep = builder.setGitDir(new File("C:/MBRepository/.git"))
            .readEnvironment() // scan environment GIT_* variables
            .findGitDir() // scan up the file system tree
            .build();
   }
   
   public static RepoManager getManager() throws Exception {
      return new RepoManager();

   }

   @Override
   public void close() throws Exception {
      rep.close();
   }

}
