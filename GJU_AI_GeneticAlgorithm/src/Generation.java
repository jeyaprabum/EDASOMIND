import java.util.TreeSet;


public class Generation {

   
   TreeSet<Chromosome> Chromosomes = new TreeSet<Chromosome>();

   public TreeSet<Chromosome> getChromosomes() {
      return Chromosomes;
   }

   public void setChromosomes(TreeSet<Chromosome> chromosomes) {
      Chromosomes = chromosomes;
   }
   
   public void addChromosome(Chromosome chr) {
      getChromosomes().add(chr);
   }
   
   public int getTotalFitness(){
      int nTotalFitness = 0;
      
      for(Chromosome chr:getChromosomes())
         nTotalFitness += chr.getFitness();
   
      return nTotalFitness;
   }

   
}
