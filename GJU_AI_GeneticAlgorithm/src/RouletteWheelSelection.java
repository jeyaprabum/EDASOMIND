import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;


public class RouletteWheelSelection {
   
   Random     r   = null;
   Generation gen = null;
   GeneticAlgorithm ga = null;
   
   public RouletteWheelSelection(Generation generation, Random random, GeneticAlgorithm genalgo) {
      r   = random;
      gen = generation;
      ga  = genalgo;
   }
   
   public List<Pair<Chromosome, Chromosome>> getPairs() {
      List<Pair<Chromosome, Chromosome>> listPairs = new ArrayList<>();
      for (int i = 0; i < ga.getPopulationSize()/2; i++) 
         listPairs.add(getPair());
      
      return listPairs;
   }
   

   private Pair<Chromosome, Chromosome> getPair() {
      Pair<Chromosome, Chromosome> pair = new Pair<Chromosome, Chromosome>(null, null);

      
      Double randomValue = r.nextDouble() * gen.getTotalFitness();
      
      // Choose the first
      for(Chromosome chr:gen.getChromosomes()){
         if(chr.getFitnessRatio()<randomValue)
            pair.setFirst(chr);
      }
      
      randomValue = r.nextDouble() * gen.getTotalFitness();
      
      for(Chromosome chr:gen.getChromosomes()){
         if(chr.getFitnessRatio()<randomValue && !chr.equals(pair.getFirst()))
            pair.setSecond(chr);
      }
      
      return pair;
   }
   
}
