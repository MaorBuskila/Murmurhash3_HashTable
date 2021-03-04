package ie.ucd.murmur;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
Main file used for running the task.

Get input from the user for M and K values for the hash table and creates it,
read input file values and insert them into the hash table,
read check file values and test their existence in the hash table while showing results
of existence for each value.
 */

public class Main {
	
	static HashTable hashtable = new HashTable();
	static Scanner scan = new Scanner(System.in);
	static List<Integer> inputArray;
	static List<Integer> checkArray;
	static List<Integer> notInTheinputArray;
	
	
	/**
	Reads a file which separate values by spaces and perform action on each value.
	 */
	
	public static void readInputFile() throws FileNotFoundException {
		File inputFile = new File ("input.txt");
		Scanner s = new Scanner(inputFile);
		inputArray = new ArrayList<>();
		while(s.hasNextInt()) {
			inputArray.add(s.nextInt());
		}
	}
	public static void readCheckFile() throws FileNotFoundException {
		File checkFile = new File ("check.txt");
		Scanner s = new Scanner(checkFile);
		checkArray = new ArrayList<>();
		while(s.hasNextInt()) {
			checkArray.add(s.nextInt());
		}
	}
	
	/**
    Insert action which perform insert of given value to hash table instance and
    current values list.
    */
	
	
	public static void insertAction () {
		for (int i =0; i<inputArray.size() ; i++) {
			hashtable.insert(inputArray.get(i));
		}
	}
	/**
    Check action which perform check of given value to hash table instance and
    current values list.
    */
	public static void checkAction () {
		notInTheinputArray = new ArrayList<>();
		for (int i =0; i<checkArray.size() ; i++) {
			if(!hashtable.checkExistence(checkArray.get(i))) {
				notInTheinputArray.add(checkArray.get(i));

			}
		}
		
	}
		
		
	/**
	Printing functions for the hash table and lists
	*/
	public static void printHashTable() {
		 for(int i=0; i< hashtable.byteTable.length ; i++) {
	         System.out.print(hashtable.byteTable[i]+ " ");
		 }
	}
		 
	public static void printList(List<Integer> list) {
		 for(int i = 0; i < list.size(); i++) {
	            System.out.print(list.get(i)+ " ");
	        }
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		 System.out.println("Enter m value for the hash table (Size of the hash table)");
		   int m = scan.nextInt(); 
		   
		   System.out.println("Enter k value for the hash table (Number of hash functions)");
		   int k = scan.nextInt(); 
		     
	    
		hashtable.init(m, k);
		readInputFile();
		insertAction();
		System.out.println("The hash Table is: ");
		printHashTable();
		readCheckFile();
		checkAction();
		System.out.println();
		System.out.println("Printing the numbers that do not exist");
		printList(notInTheinputArray);
		
		
	
			
			
		
		
	}
}
