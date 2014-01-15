import java.util.Random;


public class GeneticAlgorithm {
   
   // members
   private Random r = new Random();
   private CNF    cnf = null;
   
   private int    nGenerationCounter = 0;
   private int    nPopulationSize    = 4;
   private int    nMaxGenerations    = 50;
   private double dMutationProbability   = 0.01;
   private double dCrossoverProbebaility = 0.7;
   
   /**
    * @param sCNFFile
    */
   public GeneticAlgorithm(String sCNFFile) {
      try {
         // Read Input
         setCnf(InputReader.readInputFile(sCNFFile));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public Generation createInitGeneration() throws Exception{
      Generation generation = new Generation();
      
      // Create Chromosomes with randomized Genes
      for (int i = 0; i < getPopulationSize(); i++)
         // Add Chromsome to Generation
         generation.addChromosome(new Chromosome(cnf.getLength(), this));
      return generation;
   }
   
   /**
    * @param parentGeneration
    * @throws Exception
    */
   public void learn(Generation parentGeneration) throws Exception {
      nGenerationCounter++;
      if(nGenerationCounter == nMaxGenerations) {
         System.out.println("MaxGenerations reached");
         // Look for best result
         return;
      }
      
      for(Chromosome chr:parentGeneration.getChromosomes())
      if(cnf.countTrueClauses(chr.getGenes()) == cnf.getNbOfClauses()){
         System.out.println("Solution found");
         // Print solution
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
         
         // ###################################################################
         // Crossover?
         // ###################################################################
         if(trueByProbability(dCrossoverProbebaility)){
            int nSplitPoint = r.nextInt(getPopulationSize());
            for (int i = 0; i < getPopulationSize(); i++) {
               boolean bFirst = firstChr.getGenes()[i];
               boolean bSecond = seconChr.getGenes()[i];
               firstChr.getGenes()[i] = i < nSplitPoint ? bFirst : bSecond;
               seconChr.getGenes()[i] = i < nSplitPoint ? bSecond : bFirst;
            }
         } 
         // ###################################################################
         // Mutation
         // ###################################################################
         if(trueByProbability(dMutationProbability)){
            mutateChromosome(firstChr);
            mutateChromosome(seconChr);
         }
      }
      
      //learn(childGeneration);
   }
   
   private void mutateChromosome(Chromosome chr){
      int nMutationPoint = r.nextInt(getPopulationSize());
      chr.getGenes()[nMutationPoint] = !chr.getGenes()[nMutationPoint];
   }
   
   private boolean trueByProbability(double dProbability){
      return r.nextDouble() <= dProbability;
   }

   public int getPopulationSize() {
      return nPopulationSize;
   }

   public void setPopulationSize(int populationSize) {
      nPopulationSize = populationSize;
   }

   public int getMaxGenerations() {
      return nMaxGenerations;
   }

   public void setMaxGenerations(int maxGenerations) {
      nMaxGenerations = maxGenerations;
   }

   public double getMutationProbability() {
      return dMutationProbability;
   }

   public void setMutationProbability(double mutationProbability) {
      dMutationProbability = mutationProbability;
   }

   public double getCrossoverProbebaility() {
      return dCrossoverProbebaility;
   }

   public void setCrossoverProbebaility(double crossoverProbebaility) {
      dCrossoverProbebaility = crossoverProbebaility;
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
