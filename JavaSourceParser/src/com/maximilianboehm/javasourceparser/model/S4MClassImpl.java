package com.maximilianboehm.javasourceparser.model;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.tools.javac.tree.JCTree.JCExpression;

public class S4MClassImpl {

   public S4MClassImpl(File f) {file = f;}

   private File file;
   private List<Member> listMember;
   private List<S4MAnnotationImpl> listAnnotations;
   private String entityname;

   public class Member{
      private String name;
      private List<S4MAnnotationImpl> listAnnotation;
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

   public List<S4MAnnotationImpl> getListAnnotations() {
      return listAnnotations;
   }

   public void setListAnnotations(List<S4MAnnotationImpl> listAnnotations) {
      this.listAnnotations = listAnnotations;
   }

   public boolean isEntity() {
      System.out.println("TODO");
      return false;
   }

   public String getEntityname() {
      return entityname;
   }

   public void setEntityname(String entityname) {
      this.entityname = entityname;
   }


}
