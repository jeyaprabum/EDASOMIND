package com.maximilianboehm.git.model;

import java.io.File;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class MRepositoryManager implements AutoCloseable{

   Repository rep = null;

   public Repository getRepository() throws Exception {
      return rep;
   }

   public MRepositoryManager(File f) throws Exception {
      FileRepositoryBuilder builder = new FileRepositoryBuilder();
      rep = builder.setGitDir(f)
            .readEnvironment() // scan environment GIT_* variables
            .findGitDir() // scan up the file system tree
            .build();
   }

   public static MRepositoryManager getManager(File f) throws Exception {
      return new MRepositoryManager(f);
   }

   @Override
   public void close() throws Exception {
      rep.close();
   }

}
