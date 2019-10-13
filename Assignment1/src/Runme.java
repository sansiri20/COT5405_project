import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Runme {
	
	
	public static Map<Integer, List<Integer>> adjacencyList;

	public static void main(String args[]) throws FileNotFoundException, IOException{
		
		final long startTime = System.currentTimeMillis();
		
		if(!(Checker.checkInput(args[0]))){
			System.out.println("Bad input");
			System.exit(0);
		}
		
		InputManager in = new InputManager(args[0]); //read input from Input.txt
		in.ReadFile();
		
		//Display input in Adjacency List format
		System.out.println("-- Adjacency List of Input --");
		for(int i=0;i<in.getGlist().getAdjacencyList().size();i++)
			System.out.println("vertex "+i+": "+in.getGlist().getAdjacencyList().get(i));
		
		//get all the weight values
		ArrayList<Integer> listWeight = new ArrayList<Integer>();
		for(int i=0;i<in.getGlist().getAdjacencyList().size();i++)
			listWeight.addAll(in.getGlist().getUniqueWeight(i)); //all edges value
		
		
		//Problem 2) Heap sort the edge values
		Heap<Integer> h = new Heap<Integer>();
		Integer[] a = listWeight.toArray(new Integer[listWeight.size()]);
		h.heapify(a);
		System.out.println("\n-- The final sorted result list --");
		System.out.println(Arrays.toString(a));
		
		
		//Problem 3) Shortest path using Dijkstra's Algorithm 
		Dijkstras d = new Dijkstras("A",in.getGlist().getAdjacencyList(),in.getNodeList()); //"A" is the start node
		System.out.println("\n\n-- Dijkstra's algorithm --");
		d.DijkstraManager();
		
		
		//Problem 4) Minimum spanning tree using Kruskal's Algorithm 
		Kruskal k = new Kruskal(a, in.getNodeList(), in.getEdgeList());
		System.out.println("\n\n-  Kruskal's algorithm --");
		k.KruskalManager();
		
		System.out.println("\n\nDone");
		
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) +"Milliseconds");

	}
}
