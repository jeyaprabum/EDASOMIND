import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RouletteWheelSelection {
   
   private Random     r   = null;
   private Generation gen = null;
   private GeneticAlgorithm ga = null;
   
   public RouletteWheelSelection(Generation generation, Random random, GeneticAlgorithm genalgo) {
      r   = random;
      // Klonen, damit Elemente entfernt werden können
      gen = generation.clone();
      ga  = genalgo;
   }
   
   public List<Pair<Chromosome, Chromosome>> getPairs() throws Exception{
      List<Pair<Chromosome, Chromosome>> listPairs = new ArrayList<>();
      
      // Create Pairs so that the new generation has the same size
      for (int i = 1; i < ga.getPopulationSize()/2; i++) {
         Pair<Chromosome, Chromosome> pair = getPair();
         listPairs.add(getPair());
      }
      
      return listPairs;
   }
   

   private Pair<Chromosome, Chromosome> getPair() throws Exception{
      // Instanciate Pair
      Pair<Chromosome, Chromosome> pair = new Pair<Chromosome, Chromosome>(null, null);

      //hlpOutputInfo();
      pair.setFirst(chooseByProbability());
      //hlpOutputInfo();
      pair.setFirst(chooseByProbability());
      
      
      return pair;
   }
   
   private Chromosome chooseByProbability() throws Exception {
      double dAccu = 0;
      double randomValue = r.nextDouble();

      for(Chromosome chr:gen.getChromosomes()){
         double dFitness = chr.getFitness();
         double dTotalFitness = gen.getTotalFitness();
         dAccu += (dFitness / dTotalFitness) ;
         //System.out.println("COMPARE: "+randomValue+" < "+dAccu);
         if(randomValue < dAccu){
            gen.removeChromosome(chr);
            return chr.clone();
         }
      }
      
      throw new Exception("Nothing choosen");
   }
   
   private void hlpOutputInfo() {
      System.out.print("TF: "+gen.getTotalFitness()+" = ");
      for(Chromosome chr:gen.getChromosomes()){
         System.out.print(chr.getFitness()+" + ");
      }
      System.out.println();
      
      double dAccu = 0;
      for(Chromosome chr:gen.getChromosomes()){
         dAccu += chr.getFitnessRatio();
         System.out.println("FR: "+dAccu);
      }
      
      
   }
   
}
