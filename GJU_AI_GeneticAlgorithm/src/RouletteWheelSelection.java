import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RouletteWheelSelection {
   
   // members
   private Random     r   = null;
   private Generation gen = null;
   private Generation genOriginal = null;
   private GeneticAlgorithm ga = null;
   
   /**
    * @param generation
    * @param random
    * @param genalgo
    */
   public RouletteWheelSelection(Generation generation, Random random, GeneticAlgorithm genalgo) {
      r   = random;
      // Clone generation so that elements can be removed
      genOriginal = generation.clone();
      ga  = genalgo;
   }
   
   /**
    * @return
    * @throws Exception
    */
   public List<Pair<Chromosome, Chromosome>> getPairs() throws Exception{
      List<Pair<Chromosome, Chromosome>> listPairs = new ArrayList<>();
      
      // Create Pairs so that the new generation has the same size as the old
      for (int i = 0; i < ga.getPopulationSize()/2; i++) {
         gen = genOriginal.clone();

         // new Pair
         Pair<Chromosome, Chromosome> pair = new Pair<Chromosome, Chromosome>(null, null);
         //hlpOutputInfo();
         pair.setFirst(chooseByProbability(null, null));
         //hlpOutputInfo();
         pair.setSecond(chooseByProbability(pair.getFirst(), listPairs));
         // add to return list
         listPairs.add(pair);
         
         ga.verbose("PAIR: "+pair.getFirst()+"\n      "+pair.getSecond());
      }
      // return
      return listPairs;
   }
   
   /**
    * @return
    * @throws Exception
    */
   private Chromosome chooseByProbability(Chromosome chrFirst, List<Pair<Chromosome, Chromosome>> listPairs) throws Exception {
      double dAccu = 0;
      double randomValue = r.nextDouble();
      
      if(listPairs!=null && chrFirst!=null)
      for(Pair<Chromosome, Chromosome> pair:listPairs){
         if(pair.getFirst().equals(chrFirst))  gen.removeChromosome(pair.getSecond());
         if(pair.getSecond().equals(chrFirst)) gen.removeChromosome(pair.getSecond());
      }

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
   
   /**
    * Debug-Output
    */
   private void hlpDebugInfo() {
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