
public class Program {

   public static void main(String[] args) throws Exception{
      GeneticAlgorithm ga = new GeneticAlgorithm();
      ga.learn(ga.createInitGeneration());
   }

}
