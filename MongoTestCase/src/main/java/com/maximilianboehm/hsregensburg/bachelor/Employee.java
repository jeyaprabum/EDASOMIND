package com.maximilianboehm.hsregensburg.bachelor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.Key;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.NotSaved;
import com.google.code.morphia.annotations.Property;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Transient;

@Entity("employees")
class Employee {
   
   
   public Employee(String firstName, String lastName, Key<Employee> manager, long salary) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.manager = manager;
      this.salary = salary;
   }
   public Employee() {
      // TODO Auto-generated constructor stub
   }
   
  // auto-generated, if not set (see ObjectId)
   @Id private ObjectId id;

  // value types are automatically persisted
  String firstName, lastName;

  // only non-null values are stored
  Long salary = null;

  //references can be saved without automatic loading
  Key<Employee> manager;

  //refs are stored**, and loaded automatically
  @Reference List<Employee> underlings = new ArrayList<Employee>();

  //fields can be renamed
  @Property("started") Date startDate;
  @Property("left") Date endDate;

  //fields can be indexed for better performance
  @Indexed boolean active = false;

  //fields can loaded, but not saved
  @NotSaved String readButNotStored;

  //fields can be ignored (no load/save)
  @Transient int notStored;

  //not @Transient, will be ignored by Serialization/GWT for example.
  transient boolean stored = true;

}
