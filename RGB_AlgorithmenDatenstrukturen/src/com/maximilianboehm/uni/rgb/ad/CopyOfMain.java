package com.maximilianboehm.uni.rgb.ad;

import java.util.Arrays;

public class CopyOfMain
{
	public final static int M = 115;
	public final static int N = 6;
	
	public static void main(String[] args)	{
	   
		int[] naehrwert = new int[] { 11, 15, 20, 28, 9, 3 };
		int[] groesse = new int[] { 8, 12, 13, 20, 7, 4 };
		
		
		//index = m
		//result[m] = optimaler Nährwert bei Größe m
		int[] result_value = new int[M + 1]; 
		int[] result_i = new int[M + 1];
		
		int[] partResult;
		
		for (int m = 1; m <= M; m++){
			System.out.println("m = " + m);
			
			partResult = new int[N];
			for (int i = 0; i < N; i++)
				partResult[i] = w(m, i, groesse, naehrwert, result_value);

			// Determine max
			for (int i = 0; i < partResult.length; i++){
				if(result_value[m] < partResult[i]){
					result_value[m] = partResult[i];
					result_i[m] = i;
				}
			}			
		}
		
		printArr(result_value);
		
		explain(M, groesse, naehrwert, result_value, result_i);
	}
	
	public static void explain(int m, int[]s, int[]v, int[] results_value, int[] results_i)
	{
		System.out.println();
		System.out.println("EXPLAIN: m = " + m);
		int tmp = m;
		while(tmp > 0)
		{
			System.out.println("tmp = " + tmp);
			System.out.println("1x Fruit: " + results_i[tmp]);
			
			tmp -= s[results_i[tmp]];
		}
		
		System.out.println("tmp = " + tmp);
	}
	
	public static int w(int m, int i, int[] groesse, int[] naehrwert, int[] results){		
		if(m >= groesse[i]){
			int res = naehrwert[i];
			if(m > groesse[i])
				res += results[m - groesse[i]];

			return res;
		}
		
		return 0;
	}
	
	public static void printArr(int[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			System.out.println(String.format("[%d] %d", i, arr[i]));
		}
		
	}
}
