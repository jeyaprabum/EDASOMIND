package com.maximilianboehm.git.model._OLD;

import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.junit.Test;

public class TestGitAPI {

   @Test
   public void testBranch() throws Exception{
      try(RepoManager repManager = RepoManager.getManager();){
         String head = repManager.getRepository().getFullBranch();
         if (head.startsWith("refs/heads/")) {
            // Print branch name with "refs/heads/" stripped.
            System.out.println("Current branch is " + repManager.getRepository().getBranch());
         }
      }
   }


   @Test
   public void testGetBlob() throws Exception{
      try(RepoManager repManager = RepoManager.getManager();){

         Repository repo = repManager.getRepository();

         String revSpec = repManager.getRepository().getFullBranch();
         String path = "MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/Employee.java";


         // Resolve the revision specification
         final ObjectId id = repo.resolve(revSpec);

         // Makes it simpler to release the allocated resources in one go
         ObjectReader reader = repo.newObjectReader();

         try {
            // Get the commit object for that revision
            RevWalk walk = new RevWalk(reader);
            RevCommit commit = walk.parseCommit(id);

            // Get the revision's file tree
            RevTree tree = commit.getTree();
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
         } finally {
            reader.release();
         }
      }
   }


   @Test
   public void testGetBlob2() throws Exception{
      try(RepoManager repManager = RepoManager.getManager();){

         Repository repository = repManager.getRepository();

         String path = "MongoTestCase/src/main/java/com/maximilianboehm/hsregensburg/bachelor/Employee.java";

         ObjectId lastCommitId = repository.resolve(Constants.HEAD);
         // now we have to get the commit
         RevWalk revWalk = new RevWalk(repository);
         RevCommit commit = revWalk.parseCommit(lastCommitId);
         // and using commit's tree find the path
         RevTree tree = commit.getTree();
         TreeWalk treeWalk = new TreeWalk(repository);
         treeWalk.addTree(tree);
         treeWalk.setRecursive(true);
         treeWalk.setFilter(PathFilter.create(path));
         if (!treeWalk.next()) {
            //return null;
         }
         ObjectId objectId = treeWalk.getObjectId(0);
         ObjectLoader loader = repository.open(objectId);

         // and then one can use either
         //         InputStream in = loader.openStream();
         // or
         //               loader.copyTo(out);
         System.out.println(new String(loader.getBytes(), "utf-8"));
      }
   }




}
