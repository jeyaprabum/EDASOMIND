package com.maximilianboehm.javasourceparser.model;


import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.maximilianboehm.scheme4mongo.java.VBB.ClassVisitor;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.SimpleTreeVisitor;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;


// Taken from http://fw-geekycoder.blogspot.de/2012/09/how-to-parse-java-source-code-using.html

public class JavaSourceReader {

   private JavaCompiler compiler;
   private StandardJavaFileManager fileManager;

   public JavaSourceReader() {
      System.setProperty("java.home", "C:\\\\\\\\Program Files\\\\\\\\Java\\\\\\\\jdk1.8.0");
      compiler = ToolProvider.getSystemJavaCompiler();
      fileManager = compiler.getStandardFileManager(null, null, null);
   }


   public void parseJavaSourceFile(S4MClassImpl source) throws Exception {
      Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(source.getFile());
      JavacTask javac = (JavacTask) compiler.getTask(null, fileManager, null, null, null, fileObjects);
      Iterable<? extends CompilationUnitTree> trees = javac.parse();
      for (CompilationUnitTree tree : trees)
         tree.accept(new ClassVisitor(source), null);
   }

   class ClassVisitor extends SimpleTreeVisitor<Void, Void> {
      S4MClassImpl javaClass;
      public ClassVisitor(){}
      public ClassVisitor(S4MClassImpl source) {
         javaClass = source;
      }

      @Override
      public Void visitCompilationUnit(CompilationUnitTree cut, Void p) {
         System.out.println("Package name: " + cut.getPackageName());
         for (Tree t : cut.getTypeDecls()) {
            if (t instanceof ClassTree) {
               ClassTree ct = (ClassTree) t;
               ct.accept(this, null);
            }
         }
         return super.visitCompilationUnit(cut, p);
      }
      
      
      @Override
      public Void visitClass(ClassTree ct, Void p) {
         
         javaClass.setEntityname(ct.getSimpleName().toString());
//         System.out.println("Class name: " + ct.getSimpleName());
         javaClass.setListAnnotations(getAnnotations(ct.getModifiers().getAnnotations()));
         System.out.println(ct.getModifiers().getAnnotations());
         for (Tree t : ct.getMembers()) {

            if(t instanceof JCVariableDecl) {
//               Member member = new Member();
               JCVariableDecl var = (JCVariableDecl)t;

               System.out.println("Typ: "+var.getTag()); // VARDEF means definition of a variable
               System.out.println("Variablennamen: "+var.getName()); // Name der Variablen
               System.out.println("Datentyp: "+var.getType()); // Datentyp (e.g. List<Employee> oder Date)
               getAnnotations(var.getModifiers().getAnnotations());
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
   private List<S4MAnnotationImpl> getAnnotations(List<? extends AnnotationTree> listAnnotation){
      List<S4MAnnotationImpl> listAnno = new ArrayList<S4MAnnotationImpl>();
      for(AnnotationTree ann:listAnnotation){
         System.out.println("Annotation-Type: "+ann.getAnnotationType());
         System.out.println("Annotation-Argumente: "+ann.getArguments());
         listAnno.add(new S4MAnnotationImpl(ann.getAnnotationType().toString(), ann.getArguments()));
      }
      return listAnno.isEmpty() ? null : listAnno;
   }
}