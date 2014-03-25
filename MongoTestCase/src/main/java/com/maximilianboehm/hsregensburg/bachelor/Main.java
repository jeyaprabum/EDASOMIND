package com.maximilianboehm.hsregensburg.bachelor;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.UpdateResults;
import com.mongodb.Mongo;


public class Main {

   public static void main(String[] args)  throws Exception{
      Morphia m = new Morphia();
      Mongo mongo = new Mongo("192.168.1.19");
      Datastore ds = m.createDatastore(mongo, "a");
    
      //at application start map classes before calling with morphia map* methods
      ds.ensureIndexes(); //creates indexes from @Index annotations in your entities
      ds.ensureCaps(); //creates capped collections from @Entity
      
      
      m.map(Employee.class);
      
      while (true) {
         new Action().doIt(ds);
         Thread.sleep(1000);
         
      }
      

//      ds.save(new Employee("Mister", "GOD", null, 0));

      // get an employee without a manager
//      Employee boss = ds.find(Employee.class).field("manager").equal(null).get();
//
//      Key<Employee> scottsKey = ds.save(new Employee("Scott", "Hernandez", ds.getKey(boss), 150*1000));
//
//      //add Scott as an employee of his manager
//      UpdateResults<Employee> res = ds.update(boss,ds.createUpdateOperations(Employee.class).add("underlings", scottsKey));
//
//      // get Scott's boss; the same as the one above.
//      Employee scottsBoss = ds.find(Employee.class).filter("underlings", scottsKey).get();
//
//      for (Employee e : ds.find(Employee.class, "manager", boss))
//         System.out.println(e.firstName+" "+e.lastName);
      
      
   }

}
