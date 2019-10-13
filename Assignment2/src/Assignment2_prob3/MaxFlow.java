/*
 * MaxFlow.java
 * Maxflow algorithm implementation on the given input
 * By: Sansiri Tarnpradab
 */
import java.util.LinkedList;
 
class MaxFlow
{
    static final int V = 8; //Number of vertices in graph
    static final int resultGraph[][] =new int[V][V];
 
    /* Returns true if there is a path from source 's' to sink
      't' in residual graph.*/
    public boolean bfs(int rGraph[][], int s, int t, int parent[]){
        // A visited array and mark all vertices as not visited
        boolean visited[] = new boolean[V];
        for(int i=0; i<V; ++i)
            visited[i]=false;
 
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s]=-1;
 
        // BFS
        while (queue.size()!=0){
            int u = queue.poll();
            for (int v=0; v<V; v++){
                if (visited[v]==false && rGraph[u][v] > 0){
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
        // When reaching sink in BFS, return true, else false
        return (visited[t] == true);
    }
 
    // Returns the maximum flow from s to t
    public int fordFulkerson(int graph[][], int s, int t){
        int u, v;
 
        // Create a residual graph with given capacities 
        int rGraph[][] = new int[V][V];
 
        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];
 
        int parent[] = new int[V];
        int max_flow = 0;  //no flow initially
 
        // Augment the flow if there is path from source to sink
        while (bfs(rGraph, s, t, parent)){
            int path_flow = Integer.MAX_VALUE;
            for (v=t; v!=s; v=parent[v]){
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }
 
            // update residual capacities 
            for (v=t; v != s; v=parent[v]){
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
                resultGraph[u][v] = rGraph[v][u];
            }
            max_flow += path_flow;
        }
        return max_flow;
    }
    
    //function to return the result graph
    public int returnResultGraph(int u, int v){
    	return resultGraph[u][v];
    }
}