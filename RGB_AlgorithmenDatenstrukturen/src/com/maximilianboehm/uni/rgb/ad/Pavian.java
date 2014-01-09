package com.maximilianboehm.uni.rgb.ad;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pavian {

	java.util.List<Gemuese> listGemuese = new ArrayList<Gemuese>();
	
	public static void main(String[] args) {
		new Pavian().start();
	}
	
	public void start() {
		int M = 115;
		
		listGemuese.add(Gemuese.createGemuese(0, 11, 8));
		listGemuese.add(Gemuese.createGemuese(1, 15, 12));
		listGemuese.add(Gemuese.createGemuese(2, 20, 13));
		listGemuese.add(Gemuese.createGemuese(3, 28, 20));
		listGemuese.add(Gemuese.createGemuese(4,  9, 7));
		listGemuese.add(Gemuese.createGemuese(5,  3, 4));
		
		Map<Gemuese, Integer> mapInDerHand = new HashMap<Gemuese, Integer>();
		
		// 115
		for (int m = 1; m == M; m++) {
			
			// Alle möglichen Gemüse ablaufen
			for (Gemuese g:listGemuese) {
				
			}
			
			
			
		}
		
		
	}
	
}
