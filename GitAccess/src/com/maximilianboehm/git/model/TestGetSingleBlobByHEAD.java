package com.maximilianboehm.git.model;

import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.junit.Test;

public class TestGetSingleBlobByHEAD {

   String path = "MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/Employee.java";

   @Test
   public void test()throws Exception {
      try(RepoManager repManager = RepoManager.getManager();){
         Repository repository = repManager.getRepository();

         // HEAD
         ObjectId lastCommitId = repository.resolve(Constants.HEAD);

         // Rev-Walk starten
         RevWalk revWalk = new RevWalk(repository);
         RevCommit commit = revWalk.parseCommit(lastCommitId);

         ObjectReader reader = repository.newObjectReader();

         // and using commit's tree find the path
         RevTree tree = commit.getTree();

         TreeWalk treewalk = TreeWalk.forPath(reader, path, tree);

         if (treewalk != null) {
            // use the blob id to read the file's data
            byte[] data = reader.open(treewalk.getObjectId(0)).getBytes();
            System.out.println(new String(data, "utf-8"));

         }
      }

   }
}
