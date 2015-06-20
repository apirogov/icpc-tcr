/* Modular Exponentiation
** \log(k)
 * Calculates $a^k \mod n$ for $k<n$, useful e.g. if only last digits are needed.
 */

class Main {
	public static void main(String[] args) {
		System.out.println(modexp(7,113,10000)); //should be 407
		System.out.println(modexp(3,117,100000)); //should be 26163
		System.out.println(modexp(7,20,1000000)); //should be 612001
	}

//START
public static long modexp(long a, long k, long n) {
	long b = 1;
	while (k != 0) {
		b = ((k&1)==1) ? b*a % n : b;
		k >>= 1;
		a = a*a % n;
	}
	return b;
}
//END

}
