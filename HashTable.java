package ie.ucd.murmur;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HashTable {
	
	byte[] byteTable;
	int[] randomSeeds;
	int hashkey;
	int numberOfFunction;


	   /**
    Constructor for hash table which initialize the hash table.
    It construct the following properties:
    * Table consisting list of M zero values at first.
    * Randomized seed which is used for creating K different hash functions.
    * K different hash functions which is created by utility function `__initialize_hash_functions`

    @param k: Number of MurmurHash3 functions which the hash table will use.
    @param m: Size of the hash table elements.
    */
		public void init (int m, int k) {
				byteTable = new byte[m];
				Arrays.fill(byteTable, (byte) 0);
				randomSeeds = new int[k];
				numberOfFunction=k;
				Random rand = new Random();
				 for (int i=0; i<k; i++) {
			      randomSeeds[i] = rand.nextInt(8192);
			      
			        }
				 
			}

	//Convert a int value to Byte Array
		public static byte[] IntegerToByte (int value) {
					return ByteBuffer.allocate((int) (32*Math.pow(10,6))).putInt(value).array();
					
		}
			/**
			 *  Initialize K MurmurHash3 hash functions for the hash table.

		        Each function will be different from each other, by using the multiplication of the randomized seed of
		        the hash table with the index of the hash table.
		        (under the assumption the K functions hashes in uniquely fashion)

		        The MurmurHash3 functions then modulo by the table length, because the return value of
		        the hash functions need to be in range [0, table length].

		     
			 * @param k Number of hash functions to initialize.
			 */
		
		public void  initializeHashFunctions(int k, int m) {
		
			for (int i = 0 ; i <= k ; i++) {
			MurmurHash.hash32(byteTable, byteTable.length, randomSeeds[i]);
			}
		}
	
	  /**
    Insert a given value into the hash table.
    For each of the hash functions, calculate the hashed key using given value
    and turn on the hashed key index in the internal table list.

    @param value: Value to insert into the hash table
    */
	
		public void insert(int value) {
			byte[] tmpByte= IntegerToByte(value); 
			for (int i = 0 ; i<randomSeeds.length; i++) {
			 hashkey = Math.abs(MurmurHash.hash32(tmpByte, byteTable.length, randomSeeds[i]))%byteTable.length;
			 byteTable[hashkey] = 1; 
			}
		
		}
	
	 /**
    Check existence of value in the hash table.
    The existence check determined by checking if all hash functions hashed keys are turned on
    in the internal table list.
    If all hashed keys turned on, the value is in the table, Otherwise it is not.

    @param value: Value to check if exists in the hash table.
    @return: True if the hash table contains the given value, Otherwise False.
    */
	
		public boolean checkExistence (int value) {
			byte[] tmpByte= IntegerToByte(value); 
			for (int i = 0 ; i < randomSeeds.length; i++) {
			 int tmpHash = Math.abs(MurmurHash.hash32(tmpByte, byteTable.length, randomSeeds[i]))%byteTable.length;
	            if (byteTable[tmpHash] == 0) {
	                return false;
	            }
			}
			
	        return true;
		}
	
}
