import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;


public class GeneticAlgorithm {
   
   private int PopulationSize = 4;
   private int MaxGenerations = 50;
   private double MutationProbability = 0.01;
   private double CrossoverProbebaility = 0.7;
   private Random r = new Random();
   private CNF cnf = null;
   
   public void init() throws Exception {
      
      setCnf(InputReader.readInputFile("def.txt"));
      
      TreeSet<Chromosome> setChromosome = new TreeSet<Chromosome>();
      
      // Step #1: Create Chromosomes with randomized Genes
      for (int i = 0; i < getPopulationSize(); i++) {
         Chromosome chr = new Chromosome(cnf.getLength(), this);
         // Step #2: Caculate Fitness of each chromosome
         setChromosome.add(chr);
      }      
      
      int nTotalFitness = 0;
      
      // Step #3: Caculate Fitness-Ratio 
      for(Chromosome chr:setChromosome)
         nTotalFitness += chr.getFitness();
      for(Chromosome chr:setChromosome)
         chr.setFitnessRatio((double)(chr.getFitness()/nTotalFitness));
      
      
      List<Pair<Chromosome, Chromosome>> listPair = new ArrayList<>();
      while(listPair.size()!=getPopulationSize()/2){
         
         Pair<Chromosome, Chromosome> pair = new Pair<Chromosome, Chromosome>(null, null);
         
         
      }
      
   }
   
   private boolean hlpChoose(double dProbability){
      return r.nextDouble() <= dProbability;
   }

   public int getPopulationSize() {
      return PopulationSize;
   }

   public void setPopulationSize(int populationSize) {
      PopulationSize = populationSize;
   }

   public int getMaxGenerations() {
      return MaxGenerations;
   }

   public void setMaxGenerations(int maxGenerations) {
      MaxGenerations = maxGenerations;
   }

   public double getMutationProbability() {
      return MutationProbability;
   }

   public void setMutationProbability(double mutationProbability) {
      MutationProbability = mutationProbability;
   }

   public double getCrossoverProbebaility() {
      return CrossoverProbebaility;
   }

   public void setCrossoverProbebaility(double crossoverProbebaility) {
      CrossoverProbebaility = crossoverProbebaility;
   }

   public Random getRandom() {
      return r;
   }

   public void setR(Random r) {
      this.r = r;
   }

   public CNF getCnf() {
      return cnf;
   }

   public void setCnf(CNF cnf) {
      this.cnf = cnf;
   }

   
   
}
