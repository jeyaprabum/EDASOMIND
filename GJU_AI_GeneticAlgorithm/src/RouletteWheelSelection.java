import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;


public class RouletteWheelSelection {
   
   // members
   private Random     r   = null;
   private Generation gen = null;
   private GeneticAlgorithm ga = null;
   
   /**
    * @param generation
    * @param random
    * @param genalgo
    */
   public RouletteWheelSelection(Generation generation, Random random, GeneticAlgorithm genalgo) {
      r   = random;
      // Clone generation so that elements can be removed
      gen = generation.clone();
      ga  = genalgo;
   }
   
   /**
    * @return
    * @throws Exception
    */
   public List<Pair<Chromosome, Chromosome>> getPairs() throws Exception{
      // init
      List<Pair<Chromosome, Chromosome>> listPairs = new ArrayList<>();
      
      // Create Pairs so that the new generation has the same size as the old
      for (int i = 0; i < ga.getPopulationSize()/2; i++) {
         // hold a copy of available chromosomes for each iteration
         TreeSet<Chromosome> setChr = (TreeSet<Chromosome>)gen.getChromosomes().clone();

         // new Pair
         Pair<Chromosome, Chromosome> pair = new Pair<Chromosome, Chromosome>();
         pair.setFirst(chooseByProbability(setChr));
         pair.setSecond(chooseSecondByProbability(setChr, listPairs, pair.getFirst()));
         
         // add to return list
         listPairs.add(pair);
         
         ga.verbose("PAIR: "+pair.getFirst()+"\n      "+pair.getSecond());
      }
      
      for(Pair<Chromosome, Chromosome> pair:listPairs)
         // CLONE
         pair.set(pair.getFirst().clone(), pair.getSecond().clone());
      
      
      // return
      return listPairs;
   }
   
   /**
    * @param setChr
    * @param listPairs
    * @param chrFirst
    * @return
    * @throws Exception
    */
   private Chromosome chooseSecondByProbability(TreeSet<Chromosome> setChr, List<Pair<Chromosome, Chromosome>> listPairs, Chromosome chrFirst) throws Exception {
    // Secure, that no pair will be choosen for two times!
    for(Pair<Chromosome, Chromosome> pair:listPairs){
       if(pair.getFirst() .equals(chrFirst)) setChr.remove(pair.getSecond());
       if(pair.getSecond().equals(chrFirst)) setChr.remove(pair.getFirst());
    }
    return chooseByProbability(setChr);
   }
   
   /**
    * @return
    * @throws Exception
    */
   private Chromosome chooseByProbability(TreeSet<Chromosome> setChr) throws Exception {
      double dAccu = 0;
      double randomValue = r.nextDouble();

      for(Chromosome chr:setChr){
         double dFitness = chr.getFitness();
         double dTotalFitness = getTotalFitness(setChr);
         dAccu += (dFitness / dTotalFitness) ;
         if(randomValue < dAccu){
            setChr.remove(chr);
            return chr;
         }
      }
      throw new Exception("Nothing choosen");
   }
   
   /**
    * Calculate the fitness of the current treeset
    * @param setChr
    * @return
    */
   public int getTotalFitness(TreeSet<Chromosome> setChr){
      int nTotalFitness = 0;
      
      for(Chromosome chr:setChr)
         nTotalFitness += chr.getFitness();
   
      return nTotalFitness;
   }
}