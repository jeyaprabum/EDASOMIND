package com.maximilian_boehm.javasourceparser.model;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.maximilian_boehm.javasourceparser.access.struct.JPAnnotation;
import com.maximilian_boehm.javasourceparser.access.struct.JPClass;
import com.maximilian_boehm.javasourceparser.model.meta.JPAnnotationImpl;
import com.maximilian_boehm.javasourceparser.model.meta.JPClassImpl;
import com.maximilian_boehm.javasourceparser.model.meta.JPFieldImpl;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.SimpleTreeVisitor;
import com.sun.tools.javac.tree.JCTree.JCAssign;
import com.sun.tools.javac.tree.JCTree.JCLiteral;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;


// Taken from http://fw-geekycoder.blogspot.de/2012/09/how-to-parse-java-source-code-using.html

public class JPSourceReader {

    private final JavaCompiler compiler;
    private final StandardJavaFileManager fileManager;

    public JPSourceReader() {
        compiler = ToolProvider.getSystemJavaCompiler();
        fileManager = compiler.getStandardFileManager(null, null, null);
    }


    public JPClass parseJavaSourceFile(File f, JPClassImpl jpClass) throws Exception {
        Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(f);
        JavacTask javac = (JavacTask) compiler.getTask(null, fileManager, null, null, null, fileObjects);

        // TODO: Compare behaviour of analyze and parse
        // javac.analyze

        Iterable<? extends CompilationUnitTree> trees = javac.parse();

        for (CompilationUnitTree tree : trees)
            tree.accept(new ClassVisitor(jpClass), null);

        return jpClass;
    }

    class ClassVisitor extends SimpleTreeVisitor<Void, Void> {
        JPClassImpl jpClass;

        public ClassVisitor(JPClassImpl jpClass) {
            this.jpClass = jpClass;
        }

        @Override
        public Void visitCompilationUnit(CompilationUnitTree cut, Void p) {
            jpClass.setPackageName(cut.getPackageName().toString());
            //         System.out.println("Package name: " + cut.getPackageName());

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

            jpClass.setClassName(ct.getSimpleName().toString());
            jpClass.setAnnotations(getAnnotations(ct.getModifiers().getAnnotations()));

            //         System.out.println(ct.getSimpleName().toString());
            //         System.out.println(ct.getModifiers().getAnnotations());


            for (Tree t : ct.getMembers()) {
                if(t instanceof JCVariableDecl) {
                    JCVariableDecl var = (JCVariableDecl)t;

                    JPFieldImpl field = JPModelFactory.createJPField();
                    field.setName(var.getName().toString());
                    field.setType(var.getType().toString());
                    field.setAnnotations(getAnnotations(var.getModifiers().getAnnotations()));
                    jpClass.addField(field);

                    //               System.out.println("Typ: "+var.getTag()); // VARDEF means definition of a variable
                    //               System.out.println("Variablennamen: "+var.getName()); // Name der Variablen
                    //               System.out.println("Datentyp: "+var.getType()); // Datentyp (e.g. List<Employee> oder Date)
                    //               for(JCAnnotation ann:var.getModifiers().getAnnotations()){
                    //                  System.out.println("Annotation-Type: "+ann.getAnnotationType());
                    //                  System.out.println("Annotation-Argumente: "+ann.getArguments());
                    //               }
                    //               System.out.println("---------");


                }
                //               else
                //               System.err.println(t.getClass());
            }
            return super.visitClass(ct, p);
        }
    }
    private List<JPAnnotation> getAnnotations(List<? extends AnnotationTree> listAnnotation){
        List<JPAnnotation> listAnno = new ArrayList<JPAnnotation>();
        for(AnnotationTree ann:listAnnotation){
            //         System.out.println("Annotation-Type: "+ann.getAnnotationType());
            //         System.out.println("Annotation-Argumente: "+ann.getArguments());
            //         System.out.println("----------------");

            JPAnnotationImpl anno = JPModelFactory.createJPAnnotation();
            anno.setType(ann.getAnnotationType().toString());

            for(ExpressionTree expr:ann.getArguments()){
                if(expr instanceof JCAssign){
                    JCAssign assign = (JCAssign)expr;
                    anno.addAttribute(assign.lhs.toString(), assign.rhs.toString());
                    //               System.out.println("ASSIGN: "+assign.lhs+"="+assign.rhs);
                } else if(expr instanceof JCLiteral){
                    JCLiteral lit = (JCLiteral)expr;
                    anno.addAttribute("value", lit.value.toString());
                    //               System.out.println("LITERAL: "+lit.value);
                } else{}
                //               System.err.println("Error: "+expr);
            }

            listAnno.add(anno);
        }
        return listAnno.isEmpty() ? null : listAnno;
    }
}