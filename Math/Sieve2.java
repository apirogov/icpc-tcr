/* Extended Sieve of Eratosthenes
 ** n\log n
 * Calculates factorizations of all numbers up to n. Usable for max. up to $10^8$!
 * Use isPrime and primeFactors to retrieve this information.
 */
import java.util.*;
//goes well up to 10^8 (<4s), but larger -> out of heap
class Primes {
	public static void main(String[] args) {
		calcPrimes(Integer.parseInt(args[0]));
		for (int i=bigdiv.length-1000; i<bigdiv.length; i++) {
			System.out.println(i+": "+bigdiv[i]+" "+Arrays.toString(primeFactors(i)));
		}
	}

//START
static int[] bigdiv;
static void calcPrimes(int N) {
	bigdiv = new int[N+1];
	boolean[] notPrime = new boolean[N+1];
	for (int i = 2; i*i <= N; i++) {
		if (!notPrime[i]) {
			bigdiv[i] = 1;
			for (int j = i*i; j <= N; j+=i) {
				notPrime[j] = true;
				if (bigdiv[j]==0)
					bigdiv[j] = j/i;
			}
		}
	}
	for (int i = 2; i <= N; i++)
		if (bigdiv[i] == 0) bigdiv[i] = 1;
}
static boolean isPrime(int n) { return bigdiv[n]==1; }
static Integer[] primeFactors(int n) {
	if (n<2) return new Integer[0];
	ArrayList<Integer> l = new ArrayList<Integer>();
	int j = n;
	do {
		int f = j/bigdiv[j];
		l.add(f);
		if (j==f) break;
		j = bigdiv[j];
	} while (true);
	return l.toArray(new Integer[0]);
}
//END
}
