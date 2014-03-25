package com.maximilianboehm.hsregensburg.bachelor;

import com.google.code.morphia.Datastore;

public class Action {
   
   public void doIt(Datastore ds) {
      ds.save(new Employee("Mister", "GOD", null, 0));
      
      for (Employee e :ds.find(Employee.class))
         System.out.println(e.firstName+" "+e.lastName+" "+e.salary);
      
//      ds.delete(ds.createQuery(Employee.class));
      
   }

}
