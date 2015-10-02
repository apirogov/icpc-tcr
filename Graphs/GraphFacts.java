/* Graph Stuff
 * Max Flow = Min Cut, find edges to cut using rest graph (edges with 0 capacity) \\
 * In bipartite graphs: Max Matching = Min Vertex Cover = Max Flow with unit edges \\
 * Min Path Cover of G=(V,E) (= No. of Vertex-disjoint paths in Graph): \\
 * Construct new graph by cloning all nodes (for each vertex $v_i$ add vertices
 * $x_i,y_i$) and adding $s$ and $t$. Add the edges $(s,x_i)$ and $(y_i,t)$ and
 * for each $(v_i,v_j)$ add $(x_i,y_j)$. Get max. matching in resulting
 * bipartite graph. \\
 * Max. edge-disjoint paths from s to t = flow from s to t with unit edges \\
 * Max. vertex-disjoint paths from s to t = flow from s to t with capacities $c(e)=c(v)=1$ \\
 * Max. flow with vertex capacities: Expand each $v\in V$ to $v_{in},v_{out}$
 * and add $e=(v_{in},v_{out})$ with $c(e) = c(v)$. \\
 * Multi-Source/Sink: Add Master-Source/Sink with infinite capacity.
 */
