/* Binomial Coefficient
 ** k
 * Calculates $\binom{n}{k}$. \\
 * Input: An integer $n$ and an integer $k$. \\
 * Output: An integer holding the result.
 */

class Main {
	public static void main(String[] args) {
		System.out.println(choose(10,3)); //120
		System.out.println(choose(123,5)); //216 071 394
	}

//START
public static long choose(int n, int k) {
	if (k==0) return 1;
	if (2*k > n) return choose(n, n-k);
	long res = n-k+1;
	for (int i=2; i<=k; i++) res = res*(n-k+i)/i;
	return res;
}
//END

}
