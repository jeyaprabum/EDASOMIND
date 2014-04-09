package com.maximilianboehm.javasourceparser.model._old;


import java.io.File;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ReturnTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.SimpleTreeVisitor;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;


// Taken from http://fw-geekycoder.blogspot.de/2012/09/how-to-parse-java-source-code-using.html

public class VBB {
   public static void main(String[] args) throws Exception {
      System.setProperty("java.home", "C:\\\\\\\\Program Files\\\\\\\\Java\\\\\\\\jdk1.8.0");

      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

      File f = new File("T:\\Dateien\\Sonstiges\\git\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
      Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects( f );
      JavacTask javac = (JavacTask) compiler.getTask(null, fileManager, null, null, null, fileObjects);
      Iterable<? extends CompilationUnitTree> trees = javac.parse();
      for (CompilationUnitTree tree : trees)
         tree.accept(new CompilationUnitVisitor(), null);
   }

   static class CompilationUnitVisitor extends SimpleTreeVisitor<Void, Void> {
      @Override
      public Void visitCompilationUnit(CompilationUnitTree cut, Void p) {
         System.out.println("Package name: " + cut.getPackageName());
         for (Tree t : cut.getTypeDecls()) {
            if (t instanceof ClassTree) {
               ClassTree ct = (ClassTree) t;
               ct.accept(new ClassVisitor(), null);
            }
         }
         return super.visitCompilationUnit(cut, p);
      }
   }

   static class ClassVisitor extends SimpleTreeVisitor<Void, Void> {
      @Override
      public Void visitClass(ClassTree ct, Void p) {
         System.out.println("Class name: " + ct.getSimpleName());
         System.out.println(ct.getModifiers().getAnnotations());
         for (Tree t : ct.getMembers()) {

            if(t instanceof MethodTree){
               MethodTree mt = (MethodTree) t;
               mt.accept(new MethodVisitor(), null);
            } else if(t instanceof JCVariableDecl) {
               JCVariableDecl var = (JCVariableDecl)t;

               System.out.println("Typ. "+var.getTag()); // VARDEF means definition of a variable
               System.out.println("Variablennamen: "+var.getName()); // Name der Variablen
               System.out.println("Datentyp: "+var.getType()); // Datentyp (e.g. List<Employee> oder Date)
               for(JCAnnotation ann:var.getModifiers().getAnnotations()){
                  System.out.println("Annotation-Type: "+ann.getAnnotationType());
                  System.out.println("Annotation-Argumente: "+ann.getArguments());
               }

               System.out.println("---------");
            } else
               System.out.println(t.getClass());
         }
         return super.visitClass(ct, p);
      }

   }

   static class MethodVisitor extends SimpleTreeVisitor<Void, Void> {
      @Override
      public Void visitMethod(MethodTree mt, Void p) {
         System.out.println("Method name: " + mt.getName());
         for (StatementTree st : mt.getBody().getStatements()) {
            if (st instanceof ReturnTree) {
               ReturnTree rt = (ReturnTree) st;
               rt.accept(new ReturnTreeVisitor(), null);
            }
         }
         return super.visitMethod(mt, p);
      }
   }

   static class ReturnTreeVisitor extends SimpleTreeVisitor<Void, Void> {
      @Override
      public Void visitReturn(ReturnTree rt, Void p) {
         System.out.println("Return statement: " + rt.getExpression());
         return super.visitReturn(rt, p);
      }
   }
}