import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Kruskal {

	private Integer[] sortedEdge;	//The already sorted edges in ascending weight order
	private ArrayList<Node> nodeList;
	private ArrayList<Edge> edgeList;
	public Kruskal(Integer[] a, ArrayList<Node> _nodeList, ArrayList<Edge> _edgeList){
		setSortedEdge(a);
		setNodeList(_nodeList);
		setEdgeList(_edgeList);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void KruskalManager(){
		
		ArrayList<Edge> T = new ArrayList<Edge>();
		ArrayList<Set> S = new ArrayList<Set>();
		
		//For each node, make a set containing each individual node
		Set<Node> set;
		for(int i=0;i<nodeList.size();i++){
			set = new HashSet<Node>();
			set.add(nodeList.get(i));
			S.add(set);
		}
		
		for(int j=0;j<sortedEdge.length;j++){
			//get pair (u,v) for the edge
			Edge edge_current = getNodePair(sortedEdge[j]);
			
			Set u = getSet(edge_current.getFromEdge(), S);	//get set u
			Set v = getSet(edge_current.getToEdge(), S);	//get set v
			
			
			if(!(u.equals(v))){
			//if u and v are in different sets
				T.add(edge_current);	//add e to Tree
				union(u,v,S);	//merge the sets containing u and v
			}
		}
		KruskalPrinter(T,S);
	}
	
	 @SuppressWarnings("rawtypes")
	public static <T> void union(Set<T> setA, Set<T> setB, ArrayList<Set> totalSets) {
		    Set<T> merged = new HashSet<T>(setA);
		    merged.addAll(setB);
		    totalSets.add(merged);
		    
		    //remove set A and B from the ArrayList
		    totalSets.remove(setA);
		    totalSets.remove(setB);
	 }

	public Integer[] getSortedEdge() {
		return sortedEdge;
	}

	public void setSortedEdge(Integer[] a) {
		this.sortedEdge = a;
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(ArrayList<Edge> edgeList) {
		this.edgeList = edgeList;
	}
	
	public Edge getNodePair(int weight){
		Edge toReturn = null;
		for(int i=0;i<edgeList.size();i++){
			if(weight==edgeList.get(i).getWeight()){
				toReturn = edgeList.get(i);
			}
		}
		return toReturn;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Set getSet(Node inputNode, ArrayList<Set> S){
		Set toReturn = null;
		for(int i=0;i<S.size();i++){
			  for (Iterator<Set> it = S.get(i).iterator(); it.hasNext(); ) {
			        Node f = (Node) it.next();
			        if(f.getName().equals(inputNode.getName())){
			        	toReturn = S.get(i);
			        	break;
			        }
			    }
			  }
		return toReturn;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void KruskalPrinter(ArrayList<Edge> edgeSet, ArrayList<Set> nodeSet){
		int totalWeight=0;
		for(int i=0;i<edgeSet.size();i++)
			totalWeight = totalWeight+edgeSet.get(i).getWeight();
		System.out.println("Minimum Spanning Tree: Total weights on MST edges = "+totalWeight);
		
		System.out.print("Node Set = {");
		for(int i=0;i<nodeSet.size();i++){
		  for (Iterator<Set> it = nodeSet.get(i).iterator(); it.hasNext(); ) {
		        Node f = (Node) it.next();
		        System.out.print(f.getName());
		        if(!(it.hasNext()==false))
		        	System.out.print(", ");
		    }
		  }
		System.out.println("}");
		
		System.out.print("Edge Set = {");
		for(int i=0;i<edgeSet.size();i++){
			System.out.print(edgeSet.get(i).getFromEdge().getName()+"-"+edgeSet.get(i).getToEdge().getName());
			if(i!=edgeSet.size()-1)
				System.out.print(", ");
		}
		
		System.out.println("}");
	}
}
