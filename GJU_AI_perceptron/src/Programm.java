

public class Programm {

   public static void main(String[] args) {
      PerceptronLearning learning = new PerceptronLearning();
//      learning.startPerceptron("TrainingDataA", "TestDataA1", "TestDataA2", null, null);
//      learning.startPerceptron("TrainingDataB", "TestDataB1", "TestDataB2", null, null);
//      
//      learning.startPerceptron("TrainingDataA", "TestDataA1", "TestDataA2", 0.5, -0.5);
      learning.startPerceptron("TrainingDataB", "TestDataB1", "TestDataB2", 0.5, -0.5);

   }

}
