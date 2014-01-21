import java.util.Collections;



public class Chromosome implements Comparable<Chromosome>, Cloneable {
   
   // members
   private GeneticAlgorithm ga;
   private Generation generation;
   private boolean[] Genes = null;

   /**
    * @param gen
    */
   public void setGeneration(Generation gen){
      generation = gen;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#clone()
    */
   @Override
   protected Chromosome clone() throws CloneNotSupportedException {
      Chromosome cloned = (Chromosome) super.clone();
      cloned.setGenes(this.getGenes().clone());
      return cloned;
   }
   
   public void print() {
      System.out.println(this);
   }
   
   /**
    * @return
    */
   public double getFitnessRatio() {
      double dFitness = (double) getFitness();
      double dTotalFitness = (double) generation.getTotalFitness();
      System.out.println(dFitness+" / "+dTotalFitness);
      return dFitness / dTotalFitness;
   }

   /**
    * @return
    */
   public Integer getFitness() {
      return ga.getCnf().countTrueClauses(getGenes());
   }

   /**
    * @return
    */
   public boolean[] getGenes() {
      return Genes;
   }

   /**
    * @param genes
    */
   public void setGenes(boolean[] genes) {
      Genes = genes;
   }

   /**
    * @param nSize
    * @param ga
    */
   public Chromosome(int nSize, GeneticAlgorithm ga) {
      this.ga = ga;
      Genes = new boolean[nSize];
      
      for (int i = 0; i < Genes.length; i++) 
         Genes[i] = ga.getRandom().nextBoolean();
   }
   
   /**
    * @param GivenGenes
    */
   public Chromosome(boolean[] GivenGenes) {
      Genes = GivenGenes;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      String s = getFitness()+": ";
      s += toStringWOFitness();
      return s;
   }
   
   public String toStringWOFitness() {
      String s = "";
      for(boolean b:getGenes())
         if(b)
            s+= "true  ";
         else
            s+= "false ";
      
      return s;
   }

   
   // Compares this object with the specified object for order.
   // Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object. 
   
   @Override
   public int compareTo(Chromosome arg0) {
      int n2Compare = arg0.getFitness();
      int nFitness  = getFitness();
      
      if(nFitness < n2Compare) return -1;
      else if(nFitness > n2Compare) return +1;
      else {
         // Special Behaviour for equal fitness
         // Use Hashcode as second criteria otherwise the treeset won't accept chromosomes (returning zero means equal objects)
         return Integer.compare(arg0.hashCode() , hashCode());
      }
   }
   
}
