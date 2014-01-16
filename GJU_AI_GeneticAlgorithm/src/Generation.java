import java.util.TreeSet;


public class Generation implements Cloneable {

   // members
   private TreeSet<Chromosome> Chromosomes = new TreeSet<Chromosome>();
   private Generation parentGeneration = null;
   private int nGenerationCounter = 0;
   
   public int getGenerationCounter() {
      return nGenerationCounter;
   }
   
   public Generation(int nGenerationCounter) {
      setGenerationCounter(nGenerationCounter);
   }


   public void setGenerationCounter(int generationCounter) {
      nGenerationCounter = generationCounter;
   }

   
   public void print() {
      System.out.println(this);
   }

   /**
    * @return
    */
   public Generation getParentGeneration() {
      return parentGeneration;
   }


   /**
    * @param parentGeneration
    */
   public void setParentGeneration(Generation parentGeneration) {
      this.parentGeneration = parentGeneration;
   }


   /* (non-Javadoc)
    * @see java.lang.Object#clone()
    */
   public Generation clone() {
      Generation ga = new Generation(getGenerationCounter());
      ga.setChromosomes((TreeSet<Chromosome>)getChromosomes().clone());
      return ga;
   };
   

   /**
    * @return
    */
   public TreeSet<Chromosome> getChromosomes() {
      return Chromosomes;
   }

   /**
    * @param chromosomes
    */
   public void setChromosomes(TreeSet<Chromosome> chromosomes) {
      Chromosomes = chromosomes;
   }
   
   /**
    * @param chr
    */
   public void addChromosome(Chromosome chr) {
      getChromosomes().add(chr);
      chr.setGeneration(this);
   }
   
   /**
    * @param chr
    */
   public void removeChromosome(Chromosome chr){
     getChromosomes().remove(chr);
   }
   
   /**
    * @return
    */
   public int getTotalFitness(){
      int nTotalFitness = 0;
      
      for(Chromosome chr:getChromosomes())
         nTotalFitness += chr.getFitness();
   
      return nTotalFitness;
   }

   
   @Override
   public String toString() {
      String s = "";
      s+= "------------------------------------------------------------------\n";
      for(Chromosome chr:getChromosomes())
         s += chr.toString()+"\n";
      s+= "------------------------------------------------------------------";
      
      return s;
   }
   
   
}
