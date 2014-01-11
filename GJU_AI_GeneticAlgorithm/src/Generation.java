import java.util.TreeSet;


public class Generation implements Cloneable {

   
   public Generation clone() {
      Generation ga = new Generation();
      ga.setChromosomes((TreeSet<Chromosome>)getChromosomes().clone());
      return ga;
   };
   
   TreeSet<Chromosome> Chromosomes = new TreeSet<Chromosome>();

   public TreeSet<Chromosome> getChromosomes() {
      return Chromosomes;
   }

   public void setChromosomes(TreeSet<Chromosome> chromosomes) {
      Chromosomes = chromosomes;
   }
   
   public void addChromosome(Chromosome chr) {
      System.out.println("BEFORE "+getChromosomes().size()+" "+getChromosomes().hashCode());
      System.out.println(getChromosomes().add(chr));
      System.out.println("AFTER "+getChromosomes().size()+" "+getChromosomes().hashCode());
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
