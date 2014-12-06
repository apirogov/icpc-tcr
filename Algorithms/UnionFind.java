/* Kruskal / Union Find (for MST)
** m\log n
 * Usage: Initialize with number of vertices $n$. Pre-sort edges (ascending by weight, if given).
 * Run through edges and apply $union$, keep edges where $union$ returns $true$.
 */
import java.util.Scanner;
//START
class UnionFind {
	private int[] p = null;
	private int[] r = null;
	private int count = 0;

	public int count(){ return count; } //number of sets

	public UnionFind(int n) {
		count = n;      //every node is its own set
		r = new int[n]; //every node is its own tree with height 0
		p = new int[n];
		for (int i=0; i<n; i++)
			p[i] = -1;  //no parent = -1
	}

	public int find(int x) {
		int root = x;
		while (p[root]>=0) { //find root
			root = p[root];
		}
		while (p[x]>=0) {	 //path compression
			int tmp = p[x];
			p[x] = root;
			x = tmp;
		}
		return root;
	}

	//return true, if sets merged and false, if already from same set
	public boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px==py) return false;   //same set -> reject edge
		if (r[px] < r[py]) {        //swap so that always h[px]>=h[py]
			int tmp = px; px = py; py = tmp;
		}
		p[py] = px; //hang flatter tree as child of higher tree
		r[px] = Math.max(r[px], r[py]+1); //update (worst-case) height
		count--;
		return true;
	}

	//Example
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //Nodes
		int m = sc.nextInt(); //Edges
		UnionFind uf = new UnionFind(n);

		for (int i=0; i<m; i++) {
			//here edges assumed already sorted
			int x = sc.nextInt();
			int y = sc.nextInt();
			if (uf.union(x,y)) {
				System.out.println("Use "+x+" "+y); //this edge is in MST
			}
		}
	}
}
//END
