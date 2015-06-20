/* Dijkstra
 * Finds the shortest paths from one vertex to every other vertex in the graph (SSSP). \\
 * Input: A source vertex $s$ and an adjacency list $G$. \\
 * Output: Modified adj. list with distances from s and predcessor vertices set.
 ** |E|\log|V|
 */
import java.util.*;

//START
class Vertex implements Comparable<Vertex> {
	public int id;
	public double dist;
	public int pred;
	public HashMap<Integer,Double> next=new HashMap<>();
	public Vertex(int i){id=i;}
	public int compareTo(Vertex v) {
		if (dist < v.dist) return -1;
		else if (dist > v.dist) return 1;
		return 0;
	}
}
//END
class Main {
//START
public static void dijkstra(int s, Map<Integer,Vertex> G) {
	PriorityQueue<Vertex> H = new PriorityQueue<Vertex>();

	Vertex start = G.get(s);
	start.pred = start.id;
	for (int i : G.keySet()) { // init dists with infty
		Vertex ivert = G.get(i);
		ivert.id = i;
		ivert.dist = Double.MAX_VALUE;
		ivert.pred = -1;
	}
	start.pred = start.id; //init start vertex
	start.dist = 0;
	H.add(G.get(s));
	while (!H.isEmpty()) {
		Vertex u = H.poll();
		for (int v : u.next.keySet()) {
			Vertex vert = G.get(v);
			double utov = u.next.get(v);
			if (vert.dist > u.dist + utov) {
				vert.dist = u.dist + utov;
				vert.pred = u.id;
				H.remove(vert);
				H.add(vert);
			}
		}
	}
}
//END
public static void main(String[] args) {
	HashMap<Integer,Vertex> G = new HashMap<>();
	String names = "CBIGFHDEAJ";
	for (int i=0; i<10; i++) {
		G.put(i,new Vertex(i));
	}
	G.get(2).next.put(1, 3.0); G.get(1).next.put(2, 3.0);
	G.get(1).next.put(0, 6.0); G.get(0).next.put(1, 6.0);
	G.get(0).next.put(3, 2.0); G.get(3).next.put(0, 2.0);
	G.get(0).next.put(5, 7.0); G.get(5).next.put(0, 7.0);
	G.get(1).next.put(4, 5.0); G.get(4).next.put(1, 5.0);
	G.get(2).next.put(8, 10.0); G.get(8).next.put(2, 10.0);
	G.get(3).next.put(7, 5.0); G.get(7).next.put(3, 5.0);
	G.get(7).next.put(8, 7.0); G.get(8).next.put(7, 7.0);
	G.get(7).next.put(5, 3.0); G.get(5).next.put(7, 3.0);
	G.get(4).next.put(6, 1.0); G.get(6).next.put(4, 1.0);
	G.get(5).next.put(6, 2.0); G.get(6).next.put(5, 2.0);
	dijkstra(2,G);
	for (int i=0; i<10; i++){
		Vertex v = G.get(i);
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
