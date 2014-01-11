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
   
   public List<Pair<Chromosome, Chromosome>> getPairs() {
      List<Pair<Chromosome, Chromosome>> listPairs = new ArrayList<>();
      
      // Look as long for Pairs, so that a new generation with the same size can come into existence
      while(listPairs.size() < ga.getPopulationSize()/2)
         listPairs.add(getPair());
      
      return listPairs;
   }
   

   private Pair<Chromosome, Chromosome> getPair() {
      // Instanciate Pair
      Pair<Chromosome, Chromosome> pair = new Pair<Chromosome, Chromosome>(null, null);

      // Create randomValue
      Double randomValue = r.nextDouble() * gen.getTotalFitness();
      
      System.out.println(gen.getTotalFitness());
      for(Chromosome chr:gen.getChromosomes())
      System.out.println(chr.getFitness());
      
      // Choose the first
      for(Chromosome chr:gen.getChromosomes()){
         double dFitnessRatio = (chr.getFitness()/gen.getTotalFitness()
         System.out.println()+"<"+randomValue);
         if(chr.getFitness()/gen.getTotalFitness() < randomValue){
            pair.setFirst(chr);
            break;
         }
      }
      // Remove Chromsomes from copied generation so that they cannot be choosen again for pairing
      gen.removeChromosome(pair.getFirst());
      
      randomValue = r.nextDouble();
      
      for(Chromosome chr:gen.getChromosomes()){
         System.out.println((chr.getFitness()/gen.getTotalFitness())+"<"+randomValue);
         if(chr.getFitness()/gen.getTotalFitness() < randomValue  && !chr.equals(pair.getFirst())){
            pair.setSecond(chr);
            break;
         }
      }
      
      // Remove Chromsomes from copied generation so that they cannot be choosen again for pairing
      gen.removeChromosome(pair.getSecond());
      
      return pair;
   }
   
}
