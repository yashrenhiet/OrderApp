package com.adobe.prj.util;

public class ArrayUtil {
	
	public static int getSum(int[] elems) {
		int total = 0;
		for(int e : elems) {
			total += e;
		}
		return total;
	}
	
	public static int getCount(int[]elems, int no) {
		int total = 0;
		for(int e : elems) {
			if(e == no) total++;
		}
		return total;
	}
	 
}
