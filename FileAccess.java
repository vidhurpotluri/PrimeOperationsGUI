// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;

public class FileAccess {
  
  public static boolean loadPrimes(Primes primes, String filename) {
	  try {
		  File primeLoadFile = new File(Config.DATAPATH + filename);
		  primes.clearPrimes();
		  Scanner psc = new Scanner(primeLoadFile);
		  while (psc.hasNextBigInteger()) {
			  primes.addPrime(psc.nextBigInteger());
		  }
		  psc.close();
		  return true;
	  }
	  
	  catch (FileNotFoundException Fex) {
		  return false;
	  }

  }
  
  public static boolean loadCrosses(Primes primes, String filename) {
	  try {
		  File crossLoadFile = new File(Config.DATAPATH + filename);
		  primes.clearCrosses();
		  Scanner csc = new Scanner(crossLoadFile);
		  csc = csc.useDelimiter(", ");
		  while (csc.hasNextLine()) {
			  String line = csc.nextLine();
			  String[] s = line.split(", ");
			  BigInteger left = new BigInteger(s[0]);
			  BigInteger right = new BigInteger(s[1]);
			  Pair<BigInteger> cross = new Pair<BigInteger>(left,right);
			  primes.addCross(cross);
		  }
		  csc.close();
		  return true;
	  }
	  
	  catch (FileNotFoundException Fex) {
		  return false;
	  }
	}
  
  public static boolean savePrimes(Primes primes, String filename)
  {
	try {
		File primeSaveFile = new File(Config.DATAPATH + filename);
		FileWriter psf = new FileWriter(primeSaveFile);
		Iterator<BigInteger> primos = primes.iteratePrimes().iterator();

		while (primos.hasNext()) {
			String s = (primos.next()).toString();
			//System.out.println(s);
			psf.write(s+'\n');
		}
		
		psf.close();
		return true;
	}
	
	catch (IOException io) {
		return false;
	}
	
  }
 
  public static boolean saveCrosses(Primes primes, String filename)
  {
		try {
			File crossSaveFile = new File(Config.DATAPATH + filename);
			FileWriter psf = new FileWriter(crossSaveFile);
			Iterator<Pair<BigInteger>> crosses = primes.iterateCrosses().iterator();

			while (crosses.hasNext()) {
				Pair<BigInteger> pair = crosses.next();
				String left = pair.left().toString();
				String right = pair.right().toString();
				//System.out.println(s);
				psf.write(left + ", " + right + '\n');
			}
			
			psf.close();
			return true;
		}
		
		catch (IOException io) {
			return false;
		}
  }
}