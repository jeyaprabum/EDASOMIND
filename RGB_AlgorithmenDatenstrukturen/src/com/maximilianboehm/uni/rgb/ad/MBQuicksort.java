package com.maximilianboehm.uni.rgb.ad;
import java.util.Stack;


public class MBQuicksort {

   public static void main(String[] args) {
      int[] liste = { 0, 9, 4, 6, 2, 8, 5, 1, 7, 3 };
      QuicksortIterativ(0, liste.length - 1, liste);
//      Quicksort(0, liste.length - 1, liste);
      for (int i = 0; i < liste.length; i++)
         System.out.print(liste[i] + " ");
   }

   public static void QuicksortIterativ(int l, int r, int f[]) {
      
      Stack<Integer> stack = new Stack<Integer>();

      stack.push(0);
      stack.push(f.length - 1);

      while(!stack.empty())
      {
         r = stack.pop();
         l = stack.pop();

         if(r>l)
         {
            int c = Partition(l, r, f);
            stack.push(l);
            stack.push(c-1);
            stack.push(c+1);
            stack.push(r);
         }
      }
   }
   
   
   
   public static void Quicksort(int l, int r, int f[]) {
      if (l < r) { // Mindestens zwei Elemente?
         // Feld Partitionieren
         int c = Partition(l, r, f);
         // ab jetzt gilt:
         // f[l] .. f[c-1] <= f[c] und
         // f[c] >= f[c+1] .. f[r]
         System.out.println("RECHTS: "+l+" : "+(c - 1));
         Quicksort(l, c - 1, f); // linkes Feld sortieren
         System.out.println("LINKS: "+(c+1)+" : "+l);
         Quicksort(c + 1, r, f); // rechtes Feld sortieren
      }
   }

   public static int Partition(int l, int r, int feld[]) {
      if (l < r) { // Gibt es was zu tun?
         int pivot = feld[r]; // Äusserst rechtes Element ist Pivot
         int lh = l;
         int rh = r; // Zeiger initialisieren
         do {
            // suche von links nach größerem Element
            while ((lh < rh) && (feld[lh] <= pivot))
               lh = lh + 1;
            // suche von rechts nach kleinerem Element
            while ((rh > lh) && (feld[rh] >= pivot))
               rh = rh - 1;
            if (lh < rh) { // nicht gekreuzt? Dann tauschen!
               int t = feld[lh];
               feld[lh] = feld[rh];
               feld[rh] = t;
            }
         } while (lh < rh);
         feld[r] = feld[lh];
         feld[lh] = pivot; // Pivot-Element an Zielposition
         return lh; // Neue Pivot-Position an Aufrufer
      } else {
         return r;
      }
   }

}
