package com.maximilianboehm.scheme4mongo.java;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.google.code.morphia.annotations.AlsoLoad;
import com.google.code.morphia.annotations.Entity;
import com.maximilianboehm.hsregensburg.bachelor.Employee;

public class ReadJavaFile {
   
   public static void main(String[] args) throws Exception {

      
//      Employee.class.getDeclaredFields()
      
      for(Annotation ann:Employee.class.getAnnotations()){
         if(ann instanceof Entity){
            Entity entity = (Entity)ann;
            System.out.println(entity.value());
            System.out.println(entity.getClass());
            System.out.println(entity.annotationType());
            System.out.println(entity);
            
            inspectMethodAnnotation(Employee.class);
         }
      }
      
   }
   
   private static void inspectMethodAnnotation(Class cls) {

      for(Field field:cls.getDeclaredFields()){
         Annotation[] annotations = field.getDeclaredAnnotations();
         
         for(Annotation annotation : annotations){
            if(annotation instanceof AlsoLoad){
               AlsoLoad alsoLoad = (AlsoLoad) annotation;
               System.out.println("value: " + alsoLoad.value()[0]);
            } else 
               System.out.println("NO IDEA: "+annotation);
         }
         
      }
      
      
   }
   

}
