import java.util.Random;


public class GeneticAlgorithm {
   
   private int PopulationSize = 4;
   private int MaxGenerations = 50;
   private double MutationProbability = 0.01;
   private double CrossoverProbebaility = 0.7;
   private Random r = new Random();
   private CNF cnf = null;
   private int GenerationCounter = 0;
   
   public GeneticAlgorithm(String sInputFile) {
      // Read Input
      try {
         setCnf(InputReader.readInputFile(sInputFile));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public Generation createInitGeneration() throws Exception{
      Generation generation = new Generation();
      
      // Step #1: Create Chromosomes with randomized Genes
      for (int i = 0; i < getPopulationSize(); i++) {
         Chromosome chr = new Chromosome(cnf.getLength(), this);
         // Step #2: Caculate Fitness of each chromosome
         generation.addChromosome(chr);
      }      
      return generation;
   }
   
   public void learn(Generation parentGeneration) throws Exception {
      GenerationCounter++;
      if(GenerationCounter == MaxGenerations) {
         System.out.println("MaxGenerations reached");
         return;
      }
      
      for(Chromosome chr:parentGeneration.getChromosomes())
      if(cnf.countTrueClauses(chr.getGenes()) == cnf.getNbOfClauses()){
         System.out.println("Solution found");
         return;
      }
         
      Generation childGeneration = new Generation();
      childGeneration.setParentGeneration(parentGeneration);
      
      
      RouletteWheelSelection pairSelection = new RouletteWheelSelection(parentGeneration, getRandom(), this);
      for(Pair<Chromosome, Chromosome> pair:pairSelection.getPairs()){
         // Add Chromosomes to Generation
         Chromosome firstChr = pair.getFirst();
         Chromosome seconChr = pair.getSecond();
         childGeneration.addChromosome(firstChr);
         childGeneration.addChromosome(seconChr);
         
         // Do Crossover?
         if(hlpChoose(CrossoverProbebaility)){
            // Crossover!
            int nSplitPoint = r.nextInt(getPopulationSize());
            for (int i = 0; i < getPopulationSize(); i++) {
               boolean bFirst = firstChr.getGenes()[i];
               boolean bSecond = seconChr.getGenes()[i];
               firstChr.getGenes()[i] = i < nSplitPoint ? bFirst : bSecond;
               seconChr.getGenes()[i] = i < nSplitPoint ? bSecond : bFirst;
            }
         } {
            // No? Just clonse
            // Nothing to do because pair has already cloned chromosomes
         }
         
         // Crossover?
         if(hlpChoose(MutationProbability)){
            
         }
      }
      
      // set correct association (Chromosome to generation
      
      //learn(childGeneration);
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
