import java.math.BigInteger;

public class NaiveTest
{
	public static boolean isPrime(BigInteger candidate)
	{
		if (candidate.compareTo(BigInteger.valueOf(1)) == -1 || candidate.compareTo(BigInteger.valueOf(1)) == 0 ) {
			return false;
		}
		
		for (int i = 2; i < candidate.intValue(); i++) {
			if (candidate.mod(BigInteger.valueOf(i)) == BigInteger.valueOf(0)) {
				return false;
			}
		}
		
		return true;
	}
}