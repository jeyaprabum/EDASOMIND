package com.maximilianboehm.uni.rgb.ad;


import java.util.Stack;

public class Quicksort
{
	public static void main(String[] args)
	{
		int[] toSort = new int[] { 14, 23, 7, 12, 49, 47, 30, 27, 4, 11, 19, 39 };
		printArr(toSort, "INPUT");

		Quicksort_it(toSort);
		//Quicksort(0, toSort.length -1, toSort);
		printArr(toSort, "SORTIERT");
	}

	/*
	 * Angepasster Code für JAVA von Palm
	 */
	public static void Quicksort_it(int[] field)
	{
		Stack<Integer> stack = new Stack<Integer>();

		stack.push(0);
		stack.push(field.length - 1);

		while(!stack.empty())
		{
			int r = stack.pop();
			int l = stack.pop();

			if(r>l)
			{
				int c = Partition(l, r, field);
				stack.push(l);
				stack.push(c-1);
				stack.push(c+1);
				stack.push(r);
			}
		}
	}

	/*
	 *
	 * ORIGINAL CODE (C++) von Palm
	 *
	 *
	public static void Quicksort_it(int size, int[] field)
	{
		Stack stack = new Stack();

		stack.push(0);
		stack.push(size);

		while(!stack.empty())
		{
			int r = stack.top();
			stack.pop();
			int l = stack.top();
			stack.pop();

			if(r>l)
			{
				int c = Partition(l, r, field);
				stack.push(l);
				stack.push(c-1);
				stack.push(c+1);
				stack.push(r);
			}
		}
	}*/

	/*
	 * Rekursiver Quicksort lt. Vorlesung
	 */
	public static void Quicksort(int l,int r,int f[])
	{
		// Mindestens zwei Elemente?
	    if (l < r)
	    {
	    	// Feld Partitionieren
	    	int c = Partition(l,r,f);

	    	// ab jetzt gilt:
	    	// f[l] .. f[c-1] <= f[c] und f[c] <= f[c+1] .. f[r]
	    	Quicksort(l, c-1, f); // linkes Feld sortieren
	    	Quicksort(c+1, r, f); // rechtes Feld sortieren
	    }
	  }

	 /*
	 * Partition Funktion (mit Debug-Ausgabe) lt. Vorlesung
	 */
	public static int Partition(int l,int r,int feld[])
	{
		// Gibt es was zu tun?
		if (l<r)
		{
			System.out.println(String.format("l = %d, r = %d", l, r));
			printArrSmall(feld);

			int pivot = feld[r];  // Äusserst rechtes Element ist Pivot
			int lh=l ; int rh=r;  // Zeiger initialisieren
			do {
				// suche von links nach größerem Element
				while ((lh<rh) && (feld[lh]<=pivot)) lh=lh+1;
				// suche von rechts nach kleinerem Element
				while ((rh>lh) && (feld[rh]>=pivot)) rh=rh-1;
				if (lh<rh) { // nicht gekreuzt? Dann tauschen!
					int t = feld[lh] ; feld[lh]=feld[rh]; feld[rh]=t;
				}
			}
			while (lh<rh);
			feld[r]  = feld[lh]; feld[lh]=pivot; // Pivot-Element an Zielposition

			printArrSmall(feld);

			return lh; // Neue Pivot-Position an Aufrufer
	    }
		else
			return r;
	}

	public static void printArrSmall(int[] arr, String heading)
	{
		System.out.println("================ " + heading + " ==================");
		printArrSmall(arr);
	}

	public static void printArrSmall(int[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			System.out.print(String.format("  %2d  |", arr[i]));
		}
		System.out.println();
	}

	public static void printArr(int[] arr, String heading)
	{
		System.out.println("================ " + heading + " ==================");
		printArr(arr);
	}

	public static void printArr(int[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			System.out.println(String.format("[%d]\t%d", i, arr[i]));
		}
		System.out.println();
	}
}
