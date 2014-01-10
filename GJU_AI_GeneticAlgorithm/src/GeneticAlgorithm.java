import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GeneticAlgorithm {
   
   int nPopulationSize = 4;
   int nMaxGenerations = 50;
   double dMutationProbability = 0.01;
   double dCrossoverProbebaility = 0.7;
   Random r = new Random();
   
   public void init() throws Exception {
      
      CNF cnf = InputReader.readInputFile("def.txt");
      
      List<Chromosome> listChromosome = new ArrayList<Chromosome>();
      
      // Step #1: Create Chromosomes with randomized Genes
      for (int i = 0; i < nPopulationSize; i++) {
         Chromosome chr = new Chromosome(cnf.getLength(), r);
         listChromosome.add(chr);
      }      
      
      int nTotalFitness = 0;
      
      // Step #2: Caculate Fitness of each chromosome
      for(Chromosome chr:listChromosome)
         chr.setFitness(cnf.countTrueClauses(chr.getGenes()));
      
      // Step #3: Caculate Fitness-Ratio 
      for(Chromosome chr:listChromosome)
         nTotalFitness += chr.getFitness();
      for(Chromosome chr:listChromosome)
         chr.setFitnessRatio((double)(chr.getFitness()/nTotalFitness));
      
      
      List<Pair<Chromosome, Chromosome>> listPair = new ArrayList<>();
      while(listPair.size()!=nPopulationSize/2){
         
         Pair<Chromosome, Chromosome> pair = new Pair<Chromosome, Chromosome>(null, null);
         
         
      }
      
   }
   
   private boolean hlpChoose(double dProbability){
      return r.nextDouble() <= dProbability;
   }

}
