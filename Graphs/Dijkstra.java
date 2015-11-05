/* Dijkstra
 * Finds the shortest paths from one vertex to every other vertex in the graph (SSSP).
 * For negative weights, add |min|+1 to each edge, later subtract from result.\\
 * To get a different shortest path when edges are ints, add an
 * $\epsilon=\frac{1}{k+1}$ on each edge of the shortest path of length $k$, run again. \\
 * Input: A source vertex $s$ and an adjacency list $G$. \\
 * Output: Modified adj. list with distances from s and predcessor vertices set.
 ** |E|\log|V|
 */
import java.util.*;

//START
class Vertex implements Comparable<Vertex> {
	public int id;
	public HashMap<Integer,Double> next=new HashMap<>();
	public Vertex(int i){id=i;}

	public double dist;
	public int pred;
	public boolean removed;

	public int compareTo(Vertex v) {
		if (dist < v.dist) return -1;
		else if (dist > v.dist) return 1;
		return 0;
	}
}
//END
class Main {
//START
public static void dijkstra(int s, Vertex[] G) {
	PriorityQueue<Vertex> H = new PriorityQueue<Vertex>();

	Vertex start = G[s];
	start.pred = start.id;
	for (int i=0; i<G.length; i++) { // init dists with infty
		if (G[i]==null) continue;
		Vertex ivert = G[i];
		ivert.dist = Double.MAX_VALUE;
		ivert.pred = -1;
		ivert.removed = false;
	}
	start.pred = start.id; //init start vertex
	start.dist = 0;
	H.add(G[s]);

	while (!H.isEmpty()) {
		Vertex u = H.poll();
		if (u.removed) continue;
		u.removed = true;

		for (int v : u.next.keySet()) {
			Vertex vert = G[v];
			double utov = u.next.get(v);
			if (vert.dist > u.dist + utov) {
				vert.dist = u.dist + utov;
				vert.pred = u.id;
				H.add(vert);
			}
		}
	}
}
//END
public static void main(String[] args) {
	String names = "CBIGFHDEAJ";
	Vertex[] G = new Vertex[names.length()+1];
	for (int i=0; i<G.length-1; i++)
		G[i] = new Vertex(i);

	G[2].next.put(1, 3.0); G[1].next.put(2, 3.0);
	G[1].next.put(0, 6.0); G[0].next.put(1, 6.0);
	G[0].next.put(3, 2.0); G[3].next.put(0, 2.0);
	G[0].next.put(5, 7.0); G[5].next.put(0, 7.0);
	G[1].next.put(4, 5.0); G[4].next.put(1, 5.0);
	G[2].next.put(8, 10.0); G[8].next.put(2, 10.0);
	G[3].next.put(7, 5.0); G[7].next.put(3, 5.0);
	G[7].next.put(8, 7.0); G[8].next.put(7, 7.0);
	G[7].next.put(5, 3.0); G[5].next.put(7, 3.0);
	G[4].next.put(6, 1.0); G[6].next.put(4, 1.0);
	G[5].next.put(6, 2.0); G[6].next.put(5, 2.0);
	dijkstra(2,G);
	for (int i=0; i<G.length; i++){
		Vertex v = G[i];
		if (v==null) continue;
		if (v.pred>-1)
			System.out.println(names.charAt(v.id)+": "+v.dist+" over "+names.charAt(v.pred));
		else
			System.out.println(names.charAt(v.id)+" unreachable");
	}
}
/*expected output:
C: 9.0 over B
B: 3.0 over I
I: 0.0 over I
G: 11.0 over C
F: 8.0 over B
H: 11.0 over D
D: 9.0 over F
E: 14.0 over H
A: 10.0 over I
J unreachable
*/

}
