/* Fenwick trees
 ** \log n
 * Efficient updating and retrieval of prefix sums. Initialize a regular array
 * of n elements. Update single values at some index using increase,
 * retrieve prefixsums/range sums with sum/getsum.
 */
class Fenwick {
//START
static void increase(int[] tree, int i, int delta) {
	for (; i < (int)tree.length; i |= i + 1)
		tree[i] += delta;
}

static int getsum(int[] tree, int left, int right) {
	return sum(tree, right) - sum(tree, left-1);
}

static int sum(int[] tree, int ind) {
	int sum = 0;
	while (ind>=0) {
		sum += tree[ind];
		ind &= ind + 1;
		ind--;
	}
	return sum;
}
//END
}
