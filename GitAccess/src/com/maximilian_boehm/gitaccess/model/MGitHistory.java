package com.maximilian_boehm.gitaccess.model;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.TreeWalk;

import com.maximilian_boehm.gitaccess.access.struct.GTHistory;

public class MGitHistory {

    // members
    private static Logger logger = Logger.getLogger("com.maximilian_boehm.gitaccess");

    /**
     * @param f, the most up 2 date file in located in a git-repository
     * @return the history of the given file
     * @throws Exception
     */
    public GTHistory getHistory(File f) throws Exception{
        if(!f.exists())
            throw new Exception("Datei "+f.getAbsolutePath()+" existiert nicht.");

        // Create new instance of history
        GTHistoryImpl gtHistory = GTModelFactory.createHistoryImpl();
        // set given file
        gtHistory.setCurrentFile(f);

        // get a manager for the repository
        try(MRepositoryManager repManager = MRepositoryManager.getManager(f);){
            // get the repository itself
            Repository repository = repManager.getRepository();

            String sAbsolutePath2File = f.getAbsolutePath().replaceAll("\\\\", "/");
            String sPathGitDirectory = repository.getDirectory().getParentFile().getAbsolutePath().replaceAll("\\\\", "/");
            String sRelativePath2File = sAbsolutePath2File.replaceFirst(sPathGitDirectory, "");
            if(sRelativePath2File.startsWith("/"))
                sRelativePath2File = sRelativePath2File.substring(1);


            logger.log(Level.FINEST, "Path to Git-File "+sRelativePath2File);


            Git git = new Git(repository);

            LogCommand logCommand = git.log()
                    .add(git.getRepository().resolve(Constants.HEAD))
                    .addPath(sRelativePath2File);


            for (RevCommit revCommit : logCommand.call()) {
                RevTree tree = revCommit.getTree();

                //            System.out.println(revCommit.getCommitTime());
                //            System.out.println(revCommit.getId());
                //            System.out.println(revCommit.getAuthorIdent().getName());
                //            System.out.println(revCommit.getFullMessage());
                //            System.out.println(tree.getId());


                // use the blob id to read the file's data
                File fTMP = Files.createTempFile(UUID.randomUUID().toString(), ".java").toFile();
                ObjectReader reader = null;
                FileOutputStream fop = null;
                try {
                    fop = new FileOutputStream(fTMP);
                    reader = repository.newObjectReader();

                    TreeWalk treewalk = TreeWalk.forPath(reader, sRelativePath2File, tree);

                    reader.open(treewalk.getObjectId(0)).copyTo(fop);

                    fop.flush();

                    GTHistoryFileImpl fileImpl = GTModelFactory.createHistoryFileImpl();

                    Calendar mydate = Calendar.getInstance();
                    mydate.setTimeInMillis((long)revCommit.getCommitTime()*1000);

                    fileImpl.setFile(fTMP);
                    fileImpl.setCommitDate(mydate);
                    fileImpl.setAuthor(revCommit.getAuthorIdent().getName());
                    fileImpl.setComment(revCommit.getFullMessage());
                    gtHistory.addHistoryFile(fileImpl);

                } finally{
                    reader.release();
                    fop.close();
                }
            }
        }

        return gtHistory;
    }

}
