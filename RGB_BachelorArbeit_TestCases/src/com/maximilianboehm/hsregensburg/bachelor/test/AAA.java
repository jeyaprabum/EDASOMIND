package com.maximilianboehm.hsregensburg.bachelor.test;


import java.io.*;
import java.net.*;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;

import com.sun.source.tree.*;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.api.JavacTaskImpl;

public class AAA {

  final static TreeScanner<?, ?> methodPrintingTreeVisitor = new TreeScanner<Void, Void>() {
    @Override public Void visitCompilationUnit( CompilationUnitTree unit, Void arg ) {
      System.out.println( "Paket: " + unit.getPackageName() );
      return super.visitCompilationUnit( unit, arg );
    };
    @Override public Void visitClass( ClassTree classTree, Void arg ) {
      System.out.println( "Klasse: " + classTree.getSimpleName() );
      return super.visitClass( classTree, arg );
    }
    @Override public Void visitMethod( MethodTree methodTree, Void arg ) {
      System.out.println( "Methode: " + methodTree.getName() );
      return super.visitMethod( methodTree, arg );
    }
    @Override public Void visitAnnotation(AnnotationTree arg0, Void arg1) {
       System.out.println( "Anno: " + arg0+" "+arg1);
//       System.err.println(arg0.getAnnotationType()); // Which Annotation? returns E.g. entity or property
//       System.err.println(arg0.getArguments()); // Argument e.g. name of the entity
       return super.visitAnnotation(arg0, arg1);
   }
    @Override public Void visitAnnotatedType(AnnotatedTypeTree arg0, Void arg1) {
       System.out.println( "AnnoType: " + arg0+" "+arg1);
       return super.visitAnnotatedType(arg0, arg1);
    };
    @Override public Void visitVariable(VariableTree arg0, Void arg1) {
//       System.out.println( "Var: " + arg0+" "+arg1);
       return super.visitVariable(arg0, arg1);
    };
    @Override public Void visitMemberReference(MemberReferenceTree arg0, Void arg1) {
       System.out.println( "MemRef: " + arg0+" "+arg1);
       return super.visitMemberReference(arg0, arg1);
    };
    @Override public Void visitMemberSelect(MemberSelectTree arg0, Void arg1) {
       System.out.println( "MemSel: " + arg0+" "+arg1);
       return super.visitMemberSelect(arg0, arg1);
    };
    @Override public Void visitAssignment(AssignmentTree arg0, Void arg1) {
       System.out.println("Assign: "+arg0+" "+arg1);
       return super.visitAssignment(arg0, arg1);
    };
    @Override public Void visitTypeParameter(TypeParameterTree arg0, Void arg1) {
       System.out.println("TypeParameter: "+arg0+" "+arg1);
       return super.visitTypeParameter(arg0, arg1);
    };
    @Override public Void visitIdentifier(IdentifierTree arg0, Void arg1) {
       System.out.println("Identifier: "+arg0+" "+arg1);
       return super.visitIdentifier(arg0, arg1);
    };
    @Override public Void visitLiteral(LiteralTree arg0, Void arg1) {
       System.out.println("Literal: "+arg0+" "+arg1);
       return super.visitLiteral(arg0, arg1);
    };
    @Override public Void visitCompoundAssignment(CompoundAssignmentTree arg0, Void arg1) {
       System.out.println("CA: "+arg0+" "+arg1);
       return super.visitCompoundAssignment(arg0, arg1);
    };
   
    
  };

  public static void main( String[] args ) throws IOException, URISyntaxException {
     System.setProperty("java.home", "C:\\\\\\\\Program Files\\\\\\\\Java\\\\\\\\jdk1.8.0");
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    StandardJavaFileManager fileManager = compiler.getStandardFileManager( null, null, null );
//    URI filename = AAA.class.getResource( "AAA.java" ).toURI();
    File f = new File("T:\\Dateien\\Sonstiges\\git\\MBRepository\\MongoTestCase\\src\\main\\java\\com\\maximilianboehm\\hsregensburg\\bachelor\\Employee.java");
    Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects( f );
    CompilationTask task = compiler.getTask( null, null, null, null, null, fileObjects );

    JavacTaskImpl javacTask = (JavacTaskImpl) task;

    for ( CompilationUnitTree tree : javacTask.parse() ){
       tree.accept( methodPrintingTreeVisitor, null );
    }
  }
}
