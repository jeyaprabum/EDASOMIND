
public class Program {

   public static void main(String[] args) throws Exception{
      doGenLearning("def.txt", true);
//      doGenLearning("def.txt", false);
//      doGenLearning("def1.txt", true);
//      doGenLearning("def1.txt", false);
   }
   
   private static void doGenLearning(String sFile, boolean bVerbose)  throws Exception{
      System.err.println(sFile+", Verbose: "+bVerbose);
      GeneticAlgorithm ga = new GeneticAlgorithm(sFile, bVerbose);
      ga.learn(ga.createInitGeneration());
      
      
      System.out.println();
      System.out.println();
      System.out.println();
   }

}
