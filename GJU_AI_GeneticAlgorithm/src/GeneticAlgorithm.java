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
   private boolean bVerbose = false;
   

   private void setVerbose(boolean b){
      bVerbose = b;
   }
   
   /**
    * @param sCNFFile
    */
   public GeneticAlgorithm(String sCNFFile, boolean bVerbose) {
      setVerbose(bVerbose);
      try {
         // Read Input
         setCnf(InputReader.readInputFile(sCNFFile));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public Generation createInitGeneration() throws Exception{
      Generation generation = new Generation(0);
      
      // Create Chromosomes with randomized Genes
      for (int i = 0; i < getPopulationSize(); i++)
         // Add Chromsome to Generation
         generation.addChromosome(new Chromosome(getCnf().getLength(), this));
      
      // Printout first Generation
      System.out.println("Initial Population");
      generation.print();
      
      return generation;
   }
   
   
   /**
    * @param parentGeneration
    * @throws Exception
    */
   public void learn(Generation parentGeneration) throws Exception {
      setGenerationCounter(getGenerationCounter()+1);
      verbose("==========================================================================================================================================");
      verbose("Enter Generation "+getGenerationCounter());
      verbose("==========================================================================================================================================");
      if(getGenerationCounter() == getMaxGenerations()) {
         System.out.println("MaxGenerations ("+getMaxGenerations()+") reached");
         System.out.println("------------------------------------------------------------------");
         // Look for best result
         Chromosome chrBest = parentGeneration.getChromosomes().last();
         Generation genBest = parentGeneration;
         
         System.out.println("Last Generation");
         parentGeneration.print();
         
         while(parentGeneration!=null){
            Chromosome chr = parentGeneration.getChromosomes().last();
            if(chr.getFitness() > chrBest.getFitness()){
               chrBest = chr;
               genBest = parentGeneration;
            }
            parentGeneration = parentGeneration.getParentGeneration();
         }
         
         System.out.println("Best Solution is from Generation "+genBest.getGenerationCounter());
         chrBest.print();
         
         
         
         return;
      }
      
      for(Chromosome chr:parentGeneration.getChromosomes())
         if(getCnf().countTrueClauses(chr.getGenes()) == getCnf().getNbOfClauses()){
            System.out.println("Solution found in Generation "+nGenerationCounter);
            parentGeneration.print();
            
            System.out.println("Solution");
            chr.print();
            
            return;
         }
         
      Generation childGeneration = new Generation(getGenerationCounter());
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
         if(trueByProbability(getCrossoverProbebaility())){
            String s = firstChr.toStringWOFitness() + "&& " + seconChr.toStringWOFitness();
            int nSplitPoint = r.nextInt(firstChr.getGenes().length);
            for (int i = 0; i < firstChr.getGenes().length; i++) {
               boolean bFirst  = firstChr.getGenes()[i];
               boolean bSecond = seconChr.getGenes()[i];
               firstChr.getGenes()[i] = i <= nSplitPoint ? bFirst  : bSecond;
               seconChr.getGenes()[i] = i <= nSplitPoint ? bSecond : bFirst;
            }
            s += "\n   "+firstChr.toStringWOFitness() + "&& " + seconChr.toStringWOFitness();
            verbose("CROSSOVER at "+nSplitPoint+"\n   "+s);
         } 
         // ###################################################################
         // Mutation
         // ###################################################################
         if(trueByProbability(getMutationProbability()))
            mutateChromosome(firstChr);
         
         if(trueByProbability(getMutationProbability()))
            mutateChromosome(seconChr);
      }
      verbose("------------------------------------------------------------------");
      verbose("Result Generation "+getGenerationCounter());
      verbose(childGeneration);
      learn(childGeneration);
   }
   
   public void verbose(Object o) {
      if(bVerbose)
         System.out.println(o);
   }
   
   private void mutateChromosome(Chromosome chr){
      String s = chr.toStringWOFitness();
      int nMutationPoint = r.nextInt(chr.getGenes().length);
      chr.getGenes()[nMutationPoint] = !chr.getGenes()[nMutationPoint];
      verbose("Mutate "+nMutationPoint+": "+s+" \n          "+chr.toStringWOFitness());
   }
   
   private boolean trueByProbability(double dProbability){
      return r.nextDouble() <= dProbability;
   }

   
   
   
   /** ******************************************
    * GETTER / SETTER
    ****************************************** **/
   
   /**
    * @return
    */
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
   
   public int getGenerationCounter() {
      return nGenerationCounter;
   }

   public void setGenerationCounter(int generationCounter) {
      nGenerationCounter = generationCounter;
   }


   
   
}
