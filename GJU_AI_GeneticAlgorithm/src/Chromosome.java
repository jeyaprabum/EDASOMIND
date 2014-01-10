import java.util.Random;


public class Chromosome implements Comparable<Chromosome> {
   
   private GeneticAlgorithm ga;
   private boolean[] Genes = null;
   private Double dFitnessRatio = null;

   public Double getFitnessRatio() {
      return dFitnessRatio;
   }

   public void setFitnessRatio(Double fitnessRatio) {
      dFitnessRatio = fitnessRatio;
   }

   public Integer getFitness() {
      return ga.getCnf().countTrueClauses(getGenes());
   }

   public boolean[] getGenes() {
      return Genes;
   }

   public void setGenes(boolean[] genes) {
      Genes = genes;
   }

   public Chromosome(int nSize, GeneticAlgorithm ga) {
      this.ga = ga;
      Genes = new boolean[nSize];
      
      for (int i = 0; i < Genes.length; i++) 
         Genes[i] = ga.getRandom().nextBoolean();
   }
   
   public Chromosome(boolean[] GivenGenes) {
      Genes = GivenGenes;
   }

   
   // Compares this object with the specified object for order.
   // Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object. 
   
   @Override
   public int compareTo(Chromosome arg0) {
      int n2Compare = arg0.getFitness();
      int nFitness  = getFitness();
      
      if(nFitness < n2Compare) return -1;
      else if(nFitness > n2Compare) return +1;
      else return 0;
   }
   
}