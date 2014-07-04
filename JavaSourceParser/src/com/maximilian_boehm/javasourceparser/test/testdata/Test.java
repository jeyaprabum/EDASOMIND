package com.maximilian_boehm.javasourceparser.test.testdata;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.AlsoLoad;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.Embedded;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.Entity;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.Id;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.Indexed;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.Key;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.NotSaved;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.ObjectId;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.Property;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.Reference;
import com.maximilian_boehm.javasourceparser.test.testdata.fake_annotations.Transient;

@Entity(value="hotels", noClassnameStored=true)
public class Test {


   @Id private ObjectId id;

   @AlsoLoad("name")
   String lastName;
   
   // only non-null values are stored
   boolean salary = false; 

   //references can be saved without automatic loading
   Key<Test> manager;

   //refs are stored**, and loaded automatically
   @Reference List<Test> underlings = new ArrayList<Test>();

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

   @Embedded("AAAAAA")
   public class Address {
      private String City;
   }
}

