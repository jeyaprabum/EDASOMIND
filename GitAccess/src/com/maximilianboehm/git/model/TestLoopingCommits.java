package com.maximilianboehm.git.model;

import java.util.Iterator;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.junit.Test;

public class TestLoopingCommits {

   // http://stackoverflow.com/questions/4777453/looping-over-commits-for-a-file-with-jgit

   @Test
   public void test()throws Exception {
      try(RepoManager repManager = RepoManager.getManager();){
         Repository repository = repManager.getRepository();

         Git git = new Git(repository);
         RevWalk walk = new RevWalk(repository);
         RevCommit commit = null;

         // Add all files
         // AddCommand add = git.add();
         // add.addFilepattern(".").call();

         // Commit them
         // CommitCommand commit = git.commit();
         // commit.setMessage("Commiting from java").call();

         Iterable<RevCommit> logs = git.log().call();
         Iterator<RevCommit> i = logs.iterator();

         while (i.hasNext()) {
            commit = walk.parseCommit( i.next() );
            System.out.println( commit.getFullMessage() );

         }
      }
   }

}
