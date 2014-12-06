/* Extended Euclidean Algorithm
** \log^2(a+b)
 * Calculates the gcd of $a$ and $b$ (or multiplicative inverse of $a\in\mathbb{Z}_b$, if it exists) \\
 * \textbf{Input:} numbers $a$, $b$ \\
 * \textbf{Output:} array $[gcd, x, y]$ with $x\cdot a + y\cdot b = gcd$. \\
 * If $a \in \mathbb{Z}_b$,
 * then $gcd=1 \Rightarrow \left[ x = a^{-1} \Leftrightarrow a \cdot x \equiv 1 \mod b \right]$. \\
 * Also lcm($a,b$) = $a\cdot b$ / gcd$(a,b)$.
 */
class Main {
	public static void main(String[] args) {
		long a = 11934;
		long b = 8007;
		long[] ret = eea(a, b);
		System.out.println("GCD of " + a + " and " + b + " is: "+ret[0]);
		System.out.println(ret[1]+"*"+a+" + "+ret[2]+"*"+b+" = "+ret[0]);
		a = 4; b = 19;
		System.out.println("a = "+a+", b = "+b);
		ret = eea(a, b);
		System.out.println(a+" * "+ret[1]+" = "+((a*ret[1])%b) + " mod "+ b);
	}

//START
public static long[] eea(long a, long b) {
	if (b==0) return new long[] {a, 1, 0};
	long ret[] = eea(b, a % b);
	long y = ret[2];
	ret[2] = ret[1] - a/b * y;
	ret[1] = y;
	return ret;
}
//END

}
