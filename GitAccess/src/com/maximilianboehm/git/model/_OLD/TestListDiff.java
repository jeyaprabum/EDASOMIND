package com.maximilianboehm.git.model._OLD;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.util.io.DisabledOutputStream;
import org.junit.Test;

public class TestListDiff {

   // http://www.eclipse.org/forums/index.php/t/213979/

   @Test
   public void test()throws Exception {
      try(RepoManager repManager = RepoManager.getManager();){

         Repository repository = repManager.getRepository();
         RevWalk rw = new RevWalk(repository);
         ObjectId head = repository.resolve(Constants.HEAD);
         RevCommit commit = rw.parseCommit(head);
         DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
         df.setRepository(repository);
         df.setDiffComparator(RawTextComparator.DEFAULT);
         df.setDetectRenames(true);


         // MODIFIED!
         RevCommit parent = rw.parseCommit(commit.getParent(0).getId());
         while(parent!=null){
            List<DiffEntry> diffs = df.scan(parent.getTree(), commit.getTree());
            for (DiffEntry diff : diffs)
               System.out.println(MessageFormat.format("({0} {1} {2}", diff.getChangeType().name(), diff.getNewMode().getBits(), diff.getNewPath()));

            if(parent.getParentCount()!=0)
               parent = rw.parseCommit(parent.getParent(0).getId());
            else
               parent = null;
         }
      }
   }

}
