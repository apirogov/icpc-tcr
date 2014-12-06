/* Next Permutation
 * Computes the next lexicographical permutation of the specified array of integers in place,
 * returning whether a next permutation existed. (Returns \emph{false} when the argument is already
 * the last possible permutation.). returns whether the array was permuted to the next permutation.
 */

import java.util.Arrays;
class Main {
public static void main(String[] args) {
	int count = 1; int[]
	arr = {1,2,3,4,5};
	while (nextPermutation(arr)) {
		count++;
		System.out.println(Arrays.toString(arr));
	}
	System.out.println(count);
}

//START
public static boolean nextPermutation(int[] array) {
    // Find non-increasing suffix
    int i = array.length - 1;
    while (i > 0 && array[i - 1] >= array[i])
        i--;
    if (i <= 0)
        return false;

    // Find successor to pivot
    int j = array.length - 1;
    while (array[j] <= array[i - 1])
        j--;
    int temp = array[i - 1];
    array[i - 1] = array[j];
    array[j] = temp;

    // Reverse suffix
    j = array.length - 1;
    while (i < j) {
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        i++;
        j--;
    }
    return true;
}
//END

}
