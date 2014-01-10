import java.util.List;
import java.util.Random;
import java.util.TreeSet;


public class RouletteWheelSelection {
   
   TreeSet<Chromosome> set = new TreeSet<Chromosome>();
   Random r = null;
   
   public RouletteWheelSelection(List<Chromosome> listCHR, Random ran) {
      r = ran;
      for(Chromosome chr:listCHR)
         set.add(chr);
   }

   public Pair<Chromosome, Chromosome> getPair() {
      Double randomValue = r.nextDouble();
      Pair<Chromosome, Chromosome> pair = new Pair<Chromosome, Chromosome>(null, null);
      
      for(Chromosome chr:set){
         if(chr.getFitnessRatio()<randomValue)
            pair.setFirst(chr);
      }
      
      for(Chromosome chr:set){
         if(chr.getFitnessRatio()<randomValue && !chr.equals(pair.getFirst()))
            pair.setFirst(chr);
      }
      
      return null;
   }
   
}
