import java.io.*;
import java.util.*;


public class main {     

     
    public static void main(String[] args) {
    //TODO:build the hash table and insert keys using the insertKeyArray function.
    	// Given from HW1_keys.csv file.
    	int[] keys1 = {12, 14, 77, 74, 63, 21, 69, 13, 84, 93, 35, 89, 45, 60, 15, 57, 51, 18, 42, 62};
    	int A1 = 683;
    	int[] keys2 = {52, 71, 34, 95, 68, 25, 44, 38, 47, 77, 92, 84, 94, 12, 61, 9, 89, 56, 28, 75};
    	int A2 = 1018;
    
    	System.out.println(getw(A1));
    	// Number of collision for Chaining 
    	// NOTE: seed = 10 (FAVOURITE NUMBER), because it doesn't matter what the seed is if we are provided A.
    	Chaining test1 = new Chaining(getw(A1),10,A1);
    	Chaining test2 = new Chaining(getw(A2),10,A2);
    	System.out.println("Number of collision for key set 1 for Multiplication method is " + test1.insertKeyArray(keys1));
    	System.out.println("Number of collision for key set 2 for Multiplication method is " + test2.insertKeyArray(keys2));
    	
    	// Number of collision for Open Addressing
    	// NOTE: seed = 10 (FAVOURITE NUMBER), because it doesn't matter what the seed is if we are provided A.
    	Open_Addressing test3 = new Open_Addressing(getw(A1),10,A1);
    	Open_Addressing test4 = new Open_Addressing(getw(A2),10,A2);
    	System.out.println("Number of collision for key set 1 for Open Addressing is " + test3.insertKeyArray(keys1));
    	System.out.println("Number of collision for key set 2 for Open Addressing is " + test4.insertKeyArray(keys2));
    }
    //helper method for calculating w.
	// 2^w-1 < A < 2^w
 	public static int getw(int A) {
		int log2A = (int) (Math.log(A)/Math.log(2));
		int w = log2A +1;
		return w;
	}
}