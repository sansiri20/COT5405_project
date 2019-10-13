import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Linkedlist {

	private Map<Integer, List<Map<Integer,Integer>>> adjacencyList;
	 
	//Create list of vertices and initialize empty
    public Linkedlist(int v) 
    {
        adjacencyList = new HashMap<Integer, List<Map<Integer,Integer>>>();
        for (int i = 0; i < v; i++){
            adjacencyList.put(i, new LinkedList<Map<Integer,Integer>>());
        }
    }
    
  
	public void setEdge(Node to, Node from, int weight, ArrayList<Edge> edgeList) 
    {
        if (to.getIndex() > adjacencyList.size() || from.getIndex() > adjacencyList.size()){
            System.out.println("The vertices does not exists");
           System.exit(0);
        }
 
        else{
        	//check if the node is already contained here
	        List<Map<Integer, Integer>> sls = adjacencyList.get(to.getIndex());
	        HashMap<Integer, Integer> temp_to = new HashMap<Integer, Integer>();
	        temp_to.put(from.getIndex(), weight);
	        if(sls.size()==0 ||(!checkRepeat(sls,temp_to)))
	        	sls.add(temp_to);
	        
	        List<Map<Integer, Integer>> dls = adjacencyList.get(from.getIndex());
	        HashMap<Integer, Integer> temp_from = new HashMap<Integer, Integer>();
	        temp_from.put(to.getIndex(), weight);
	        if(dls.size()==0 ||(!checkRepeat(dls,temp_from)))
	        	dls.add(temp_from);
	        
	        edgeList.add(new Edge(to,from,weight));
        }
    }
	
	public boolean checkRepeat(List<Map<Integer, Integer>> inputList, HashMap<Integer, Integer> key){
		boolean exist = false; //default as no repeat
		Object[] toCheck_key = key.keySet().toArray();
		for(int i=0;i<inputList.size();i++){
			if(inputList.get(i).containsKey(toCheck_key[0]))
				exist = true;
		}
		return exist;
	}
    
    public Map<Integer, List<Map<Integer,Integer>>> getAdjacencyList(){
    	return adjacencyList;
    }
    
    
   public void getWeight(int key){
	   for(int i=0;i<adjacencyList.get(key).size();i++){
		   System.out.println(adjacencyList.get(key).get(i).values().toString());
	   }
   }
   
   public ArrayList<Integer> getUniqueWeight(int key){
	   ArrayList<Integer> temp = new ArrayList<Integer>();
	   for(int i=0;i<adjacencyList.get(key).size();i++){
		   if(key < getKey(key,i)){
			   Object[] value = adjacencyList.get(key).get(i).values().toArray();
			   temp.add((int)value[0]);
		   }
	   }
	   return temp;
   }
   
   public int getKey(int key, int index){
	   Object[] keys =  adjacencyList.get(key).get(index).keySet().toArray();
	   return (int) keys[0];
   }
}
