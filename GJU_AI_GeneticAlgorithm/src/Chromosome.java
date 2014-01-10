import java.util.Random;


public class Chromosome {
   
   private boolean[] Genes = null;
   private Integer nFitness = null;
   private Double dFitnessRatio = null;

   public Double getFitnessRatio() {
      return dFitnessRatio;
   }

   public void setFitnessRatio(Double fitnessRatio) {
      dFitnessRatio = fitnessRatio;
   }

   public Integer getFitness() {
      return nFitness;
   }

   public void setFitness(Integer fitness) {
      this.nFitness = fitness;
   }
   

   public boolean[] getGenes() {
      return Genes;
   }

   public void setGenes(boolean[] genes) {
      Genes = genes;
   }

   public Chromosome(int nSize, Random r) {
      Genes = new boolean[nSize];
      
      for (int i = 0; i < Genes.length; i++) 
         Genes[i] = r.nextBoolean();
   }
   
   public Chromosome(boolean[] GivenGenes) {
      Genes = GivenGenes;
   }
   
}
