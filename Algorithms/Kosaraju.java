/* Kosaraju
 * Indentifies strongly connected components (SCC) in a directed graph, can be used for solving 2SAT in CNF.\\
 * For example for $(x1 \lor x2) \land (\lnot x3 \lor x4)$: \\
 * \texttt{addConstraint(G,1,2); addConstraint(G,-3,4); boolean b = is2SatInstance(G);} \\
 * Input: HashMap representing the adjacency list.  \\
 * Output: An integer array mapping the color of the corresponding SCC to every vertex in the graph (all colors are natural numbers).
 ** |E|+|V|
 */
import java.util.*;

//START
class Vertex {
	int id;
	Set<Vertex> next = new HashSet<Vertex>();
	Set<Vertex> prev = new HashSet<Vertex>();
	public Vertex(int id) { this.id = id; }
}

//END
public class Kosaraju {
//START
public static HashMap<Integer,Integer> kosaraju(HashMap<Integer, Vertex> M) {
	HashMap<Integer,Integer> color = new HashMap<Integer,Integer>();
	Stack<Vertex> stack = new Stack<Vertex>();

	for (Vertex v : M.values())
		if (color.get(v.id) == null)
			sccDFS(stack, color, v);

	color = new HashMap<Integer,Integer>();
	int colorN = 0;
	while (!stack.isEmpty()) {
		Vertex v = stack.pop();
		if (color.get(v.id) == null)
			sccRDFS(stack, color, v, colorN++);
	}

	return color;
}

public static void sccDFS(Stack<Vertex> stack, HashMap<Integer,Integer> color, Vertex v) {
	color.put(v.id,1);
	for (Vertex nxt : v.next) {
		if (color.get(nxt.id) == null) {
			sccDFS(stack, color, nxt);
		}
	}
	stack.push(v);
}

public static void sccRDFS(Stack<Vertex> stack, HashMap<Integer,Integer> color, Vertex v, int colorN) {
	color.put(v.id,colorN);
	for (Vertex prv : v.prev)
		if (color.get(prv.id) == null)
			sccRDFS(stack, color, prv, colorN);
}

public static void addEdge(HashMap<Integer,Vertex> G, int a, int b) {
	if (G.get(a)==null) G.put(a, new Vertex(a));
	if (G.get(b)==null) G.put(b, new Vertex(b));
	G.get(a).next.add(G.get(b));
	G.get(b).prev.add(G.get(a));
}

public static void addConstraint(HashMap<Integer,Vertex> G, int a, int b) {
	addEdge(G,-a,b); addEdge(G,-b,a);
}

//END
//TODO: eine belegung berechnen falls true
//START
public static boolean is2SatInstance(HashMap<Integer,Vertex> G) {
	HashMap<Integer,Integer> color = kosaraju(G);
	for (Vertex v : G.values())
		if (color.get(v.id) == color.get(-v.id))
			return false;
	return true;
}

//END
public static void main(String[] args) {
	HashMap<Integer,Vertex> G = new HashMap<Integer,Vertex>();
	addConstraint(G, 1, 2);
	addConstraint(G, 2, 3);
	addConstraint(G, 3, 4);
	addConstraint(G, -1, -3);
	addConstraint(G, -2, -4);
	System.out.println(is2SatInstance(G)); //true (0 1 1 0)
	G = new HashMap<Integer,Vertex>();
	addConstraint(G, 1, 2);
	addConstraint(G, 2, 4);
	addConstraint(G, -1, 2);
	addConstraint(G, -2, 1);
	addConstraint(G, -1, -2);
	System.out.println(is2SatInstance(G)); //false
	G = new HashMap<Integer,Vertex>();
	addConstraint(G, 1, 2);
	addConstraint(G, 2, 4);
	addConstraint(G, -1, 2);
	addConstraint(G, 2, -1);
	addConstraint(G, -1, -2);
	System.out.println(is2SatInstance(G)); //true (0 1)
}
}
