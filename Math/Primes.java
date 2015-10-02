/* Prime Testing
 ** k\log^3n
 * Better use \texttt{new BigInteger(""+n).isProbablePrime(k)}! \\
 * Input: Number $n$ and security $k$ \\
 * Output: false if composite, true with error probability of $\frac{1}{2^k}$
 */
import java.util.*;
import java.math.*;
public class Primes {
	static Random rnd = new Random(42);

	public static void main(String[] args) {
		BigInteger n = BigInteger.ONE;
		for (int i=0; i<10000000; i++) {
			if (millerrabin(i,50) != new BigInteger(""+i).isProbablePrime(50))
				System.out.println("Fail!");
		}
	}

	public static long modexp(long a, long k, long n) {
		long b = 1;
		while (k != 0) {
			b = ((k&1)==1) ? b*a % n : b;
			k >>= 1;
			a = a*a % n;
		}
		return b;
	}
//START
public static boolean witness(long a, long n) {
	long u=n-1;
	long t=0;
	while(u%2 == 0) {u >>= 1;t++;}
	long x0 = modexp(a,u,n);
	long xim1 = x0;
	long xi = 0;
	for (int i=1; i<=t; i++) {
		xi = modexp(xim1,2,n);
		if (xi == 1 && xim1 != 1 && xim1 != (n-1))
			return true;
		xim1 = xi;
	}
	if (xi!=1)
		return true;
	return false;
}

public static boolean millerrabin(long n, int s) {
	if (n==2) return true;
	if (n%2==0 || n<2) return false;
	for (int j=1; j<=s; j++) {
		long a = (Math.abs(rnd.nextLong()) % (n-2)) +2;
		if (witness(a,n))
			return false;
	}
	return true;
}
//END
}

