

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PerceptronLearning {
   
   Random random = new Random();
   double dWeightX      = 0;
   double dWeightY      = 0;
   double dThreshold    = 0.1;
   double dLearningRate = 0.2;
   
   public void startPerceptron(String sTrainingData, String sTestdata1, String sTestdata2, Double dWX, Double dWY) {
      // If given, take given Weights
      // else Random value for Weights between -0,5 and 0.5
      if(dWX!=null) dWeightX = dWX;
      else          dWeightX = random.nextDouble()-0.5;
      
      if(dWY!=null) dWeightY = dWY;
      else          dWeightY = random.nextDouble()-0.5;
      
      
      
      System.out.println("Files: "+sTrainingData+", "+sTestdata1+", "+sTestdata2);
      if(dWX == null)
         System.out.print("Random-Value for Weight: ");
      
      System.out.println("Weight X:"+dWeightX+", Weight Y:"+dWeightY);
      
      // Read Files
      HashSet<Point> setTraining = DataSet.readSet(sTrainingData);
      HashSet<Point> setTest1    = DataSet.readSet(sTestdata1);
      HashSet<Point> setTest2    = DataSet.readSet(sTestdata2);
      
      learn(setTraining);
      
      System.out.println(test(setTest1)+" Fehler im Test-Set A");
      System.out.println(test(setTest2)+" Fehler im Test-Set B");
      
      System.out.println();
      System.out.println();
   }
   
   public int test(Set<Point> setTest){
      int nErrorCounter = 0;
      for(Point p:setTest){
         int nValue = calculateValue(p);
         if(p.getValue() != nValue)
            nErrorCounter++;
      }
      return nErrorCounter;
   }
   
   public void learn(Set<Point> setTraining) {
      boolean bHasError = true;
      int nCounter = 0;
      
      // Is there an Error? Continue Training
      while(bHasError){
         // Initialize: No Error on the beginning
         bHasError = false;
         
         // Debugging
         // System.out.println("Iteration: "+nCounter);
         
         // Iterate through the Training-Set
         for(Point p:setTraining){
            // Calculate the Error for one output
            double dValue = calculateValue(p);
            // Is there an error?
            Double dDesiredValue = (double) p.getValue();
            if((dDesiredValue-dValue) != 0){
               // Yes?
               // -> Update Weights according the error
               updateWeights(p, dValue);
               // Remember that there has been an error and we need to do more iterations
               bHasError = true;
            }
         }
         nCounter++;
      }
      System.out.println("Stop Learning at Epoche "+nCounter);
      System.out.println("Final Weights: X="+dWeightX+", Y="+dWeightY);
   }
   
   public int calculateValue(Point p) {
      // Calculate the value
      double dValue = p.getX() * dWeightX + p.getY() * dWeightY - dThreshold;
      
      // STEP-Function
      return dValue >= 0 ? 1 : 0;
   }
   
   public void updateWeights(Point p, double dValue){
      double dDeltaX = dLearningRate * p.getX() * (((double)p.getValue()) - dValue);
      dWeightX = dWeightX + dDeltaX;
      
      double dDeltaY = dLearningRate * p.getY() * (((double)p.getValue()) - dValue);
      dWeightY = dWeightY + dDeltaY;
      
      // DEBUG
      System.out.println("Weight X: "+(dWeightX-dDeltaX)+" + "+dDeltaX+" = "+dWeightX);
      System.out.println("Weight Y: "+(dWeightY-dDeltaY)+" + "+dDeltaY+" = "+dWeightY);
   }
   
}
