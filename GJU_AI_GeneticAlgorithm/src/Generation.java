import java.util.TreeSet;


public class Generation implements Cloneable {

   private TreeSet<Chromosome> Chromosomes = new TreeSet<Chromosome>();
   private Generation parentGeneration = null;
   
   
   
   public Generation getParentGeneration() {
      return parentGeneration;
   }


   public void setParentGeneration(Generation parentGeneration) {
      this.parentGeneration = parentGeneration;
   }


   public Generation clone() {
      Generation ga = new Generation();
      ga.setChromosomes((TreeSet<Chromosome>)getChromosomes().clone());
      return ga;
   };
   

   public TreeSet<Chromosome> getChromosomes() {
      return Chromosomes;
   }

   public void setChromosomes(TreeSet<Chromosome> chromosomes) {
      Chromosomes = chromosomes;
   }
   
   public void addChromosome(Chromosome chr) {
      getChromosomes().add(chr);
      chr.setGeneration(this);
   }
   
   public void removeChromosome(Chromosome chr){
     getChromosomes().remove(chr);
   }
   
   public int getTotalFitness(){
      int nTotalFitness = 0;
      
      for(Chromosome chr:getChromosomes())
         nTotalFitness += chr.getFitness();
   
      return nTotalFitness;
   }

   
}
