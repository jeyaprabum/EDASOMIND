
public class Program {

   public static void main(String[] args) throws Exception{
      doGenLearning("def.txt");
      doGenLearning("def1.txt");
   }
   
   private static void doGenLearning(String sFile)  throws Exception{
      GeneticAlgorithm ga = new GeneticAlgorithm(sFile);
      ga.learn(ga.createInitGeneration());
   }

}
