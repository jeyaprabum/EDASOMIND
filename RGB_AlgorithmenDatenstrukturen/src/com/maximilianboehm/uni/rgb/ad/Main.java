package com.maximilianboehm.uni.rgb.ad;
public class Main{
   
   // statics
	public final static int M = 115;
	public final static int N = 6;
	
	public static void main(String[] args)	{
	   // Stammdaten
		int[] naehrwert = new int[] { 11, 15, 20, 28, 9, 3 };
		int[] groesse   = new int[] {  8, 12, 13, 20, 7, 4 };
		
		// Halter für Ergebnisse
		int[] result_naehrwert = new int[M + 1]; 
		int[] result_groesse   = new int[M + 1];
		
		// Laufe alle möglichen Gemüse-Positionen ab
		for (int m = 1; m <= M; m++){
		   
		   // Speichere pro Position die möglichen Ergebnisse
		   int[] partResult = new int[N];
			
			// Laufe alle Gemuese-Sorten ab
			for (int i = 0; i < N; i++)
			   // Ermittle ein Ergbnis für eine Gemüse-Sorte
				partResult[i] = kostenfunktionW(m, i, groesse, naehrwert, result_naehrwert);

			// Laufe die Ergebnisse der Gemüse-Sorten ab
			for (int i = 0; i < partResult.length; i++){
			   // Wenn der gespeicherte Naehrwert kleiner als das Teilergebnis ist
				if(result_naehrwert[m] < partResult[i]){
				   // Setze das Teilergebnis
					result_naehrwert[m] = partResult[i];
					// Speichere die Groesse des Teilergebnisses
					result_groesse[m] = i;
				}
			}			
		}
	}
	
	public static int kostenfunktionW(int m, int i, int[] groesse, int[] naehrwertArray, int[] results){
	   // Passt die Groeße in das M?
		if(m >= groesse[i]){
		   // Ermittle Naehrwert für Gemuese i
			int naehrwert = naehrwertArray[i];
			// Ist zu viel Platz übrig?
			if(m > groesse[i])
			   // Dann verschiebe es nach links...
				naehrwert += results[m - groesse[i]];

			// naehrwert zurückgeben
			return naehrwert;
		}
		
		return 0;
	}
}
