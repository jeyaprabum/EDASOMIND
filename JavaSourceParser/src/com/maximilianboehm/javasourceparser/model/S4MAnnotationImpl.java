package com.maximilianboehm.javasourceparser.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.source.tree.ExpressionTree;
import com.sun.tools.corba.se.idl.constExpr.Expression;
import com.sun.tools.javac.tree.JCTree.JCAssign;
import com.sun.tools.javac.tree.JCTree.JCExpression;

public class S4MAnnotationImpl {

      public S4MAnnotationImpl(String sName, List<? extends ExpressionTree> listExpr) {
         name = sName;
         mapAttr = new HashMap<String, String>();
         for(ExpressionTree expr:listExpr){
//            mapAttr.put(expr., arg1)
            JCAssign assign = (JCAssign)expr;
            System.out.println(expr.getClass());
            System.out.println(expr);
         }
      }
      private String name;
      private Map<String, String> mapAttr;
   
   
}
