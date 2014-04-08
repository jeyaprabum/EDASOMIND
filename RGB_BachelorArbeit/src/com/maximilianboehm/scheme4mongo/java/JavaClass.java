package com.maximilianboehm.scheme4mongo.java;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.tools.javac.tree.JCTree.JCExpression;

public class JavaClass {

   public JavaClass(File f) {file = f;}

   private File file;
   private List<Member> listMember;
   private List<JavaAnnotation> listAnnotations;
   private boolean entity;
   private String entityname;

   public class Member{
      private String name;
      private List<JavaAnnotation> listAnnotation;
   }

   public File getFile() {
      return file;
   }

   public void setFile(File file) {
      this.file = file;
   }

   public List<Member> getListMember() {
      return listMember;
   }

   public void setListMember(List<Member> listMember) {
      this.listMember = listMember;
   }

   public List<JavaAnnotation> getListAnnotations() {
      return listAnnotations;
   }

   public void setListAnnotations(List<JavaAnnotation> listAnnotations) {
      this.listAnnotations = listAnnotations;
   }

   public boolean isEntity() {
      return entity;
   }

   public void setEntity(boolean entity) {
      this.entity = entity;
   }

   public String getEntityname() {
      return entityname;
   }

   public void setEntityname(String entityname) {
      this.entityname = entityname;
   }


}
