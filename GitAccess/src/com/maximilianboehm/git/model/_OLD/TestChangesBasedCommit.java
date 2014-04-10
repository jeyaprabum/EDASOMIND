package com.maximilianboehm.git.model._OLD;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.junit.Test;

public class TestChangesBasedCommit {

   // http://stackoverflow.com/questions/4777453/looping-over-commits-for-a-file-with-jgit
   String path = "MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/Employee.java";


   @Test
   public void test()throws Exception {
      try(RepoManager repManager = RepoManager.getManager();){
         Repository repository = repManager.getRepository();

         Git git = new Git(repository);

         LogCommand logCommand = git.log()
               .add(git.getRepository().resolve(Constants.HEAD))
               .addPath(path);

         for (RevCommit revCommit : logCommand.call()) {
            System.out.println(revCommit);
            
            ObjectReader reader = repository.newObjectReader();
            ObjectLoader loader = reader.open(revCommit);
            RevTree tree = revCommit.getTree();
            System.out.println(new String(loader.getBytes(), "utf-8"));
            
            
            // .. and narrow it down to the single file's path
            TreeWalk treewalk = TreeWalk.forPath(reader, path, tree);

            if (treewalk != null) {
               // use the blob id to read the file's data
               byte[] data = reader.open(treewalk.getObjectId(0)).getBytes();
               System.out.println(new String(data, "utf-8"));
               //               return new String(data, "utf-8");
            } else {
               System.out.println("");
               //               return "";
            }
            
            
         }
      }
   }

}
