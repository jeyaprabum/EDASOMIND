package com.maximilianboehm.scheme4mongo.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.source.tree.ExpressionTree;
import com.sun.tools.corba.se.idl.constExpr.Expression;
import com.sun.tools.javac.tree.JCTree.JCExpression;

public class JavaAnnotation {

      public JavaAnnotation(String sName, List<? extends ExpressionTree> listExpr) {
         name = sName;
         mapAttr = new HashMap<String, String>();
         for(ExpressionTree expr:listExpr){
//            mapAttr.put(expr., arg1)
            System.out.println(expr);
         }
      }
      private String name;
      private Map<String, String> mapAttr;
   
   
}
