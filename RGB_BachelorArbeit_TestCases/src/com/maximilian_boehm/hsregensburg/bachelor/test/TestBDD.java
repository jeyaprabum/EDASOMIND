package com.maximilian_boehm.hsregensburg.bachelor.test;

import jdd.bdd.BDD;

import org.junit.Test;

public class TestBDD {

   @Test
   public void test() throws Exception {
      //      File f = new File("C:\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
      //      GTHistory history = GTAccessFactory.getHome().getGitHistoryOfFile(f);
      //
      //      JPHome  jpHome  = JPAccessFactory.getHome();
      //
      //      JPClass jpClassC = jpHome.getParsedClass(history.getCurrentFile());
      //      for(GTHistoryFile historyFile:history.getHistoryFiles()){
      //         JPClass jpClass = jpHome.getParsedClass(historyFile.getFile());
      //      }


      BDD bdd = new BDD(1000, 1000);
      int v1 = bdd.createVar();
      int v2 = bdd.createVar();
      int v3 = bdd.createVar();

      int x = bdd.and(v1,v2);
      int y = bdd.xor(v1,v3);
      int z = bdd.not(v2);

      bdd.ref(x);
      bdd.ref(y);
      bdd.ref(z);

      bdd.printSet(y);
      bdd.printCubes(y);

      int cube = bdd.ref( bdd.and(v1,v2) );

      int x2 = bdd.ref( bdd.forall(x,cube) );

      System.out.println();





   }

}
