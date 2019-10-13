import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dijkstras {
	
	private static String sNode;
	private static Map<Integer, List<Map<Integer,Integer>>> linkedList;
	private static ArrayList<Node> nodeList;
	private static int[] D;
	private static String[] D_S;

	public Dijkstras(String _sNode, Map<Integer, List<Map<Integer,Integer>>> _linkedList, ArrayList<Node> _nodeList){
		sNode = _sNode;
		linkedList = _linkedList;
		nodeList = _nodeList;
	}
	
	public void DijkstraManager(){
		boolean flag = true;
		ArrayList<Integer> S = new ArrayList<Integer>();	//S = visited list
		int shortest_D_index = 0;							//D = smallest distance in S (priority queue)
		int shortest_D_val =0;
		D = new int[nodeList.size()];						//D' = intermediate distance to start node
		D_S = new String[nodeList.size()];
		initializeArray(true, sNode);
		initializeArray(false, sNode);
		
		//Initialization
		S.add((Integer) nodeLookup(sNode,false));	//add start node
		List<Map<Integer,Integer>> connectNodes = findAdjacency(sNode,false);
		for(int i=0;i<connectNodes.size();i++){
			Object[] key = connectNodes.get(i).keySet().toArray();
			Object[] value = connectNodes.get(i).values().toArray();
			D[(int)key[0]] = Math.min(D[(int)key[0]],(int)value[0]); //(old,new)
		}
		
		//Loop
		while(S.size()!=nodeList.size()){
			shortest_D_index = getMin(D, S, true);			//Choose the minimum distance node that is not in S
			shortest_D_val = getMin(D, S, false);			//return shortest distance value
			S.add(shortest_D_index);						//add to S(that index must not already be contained)
			if(flag){
				D_S[shortest_D_index] = D_S[shortest_D_index].concat((String) nodeLookup(shortest_D_index,true)); 
				flag = false;  //concatenate here just one time
			}
			
			connectNodes = findAdjacency(shortest_D_index,true);
			for(int i=0;i<connectNodes.size();i++){
				Object[] key = connectNodes.get(i).keySet().toArray();
				Object[] value = connectNodes.get(i).values().toArray();
				if(!(S.contains((int)key[0]))){
					D[(int)key[0]] = Math.min(D[(int)key[0]],(int)value[0]+shortest_D_val); //(old,new)
					if(D[(int)key[0]] == (int)value[0]+shortest_D_val){ //NEW value is the minimum value
						D_S[(int)key[0]] = D_S[shortest_D_index].concat((String) nodeLookup((int)key[0],true)); 
					}
					else{ //OLD value is the minimum value
						if(D_S[(int)key[0]].contains((CharSequence) nodeLookup(key[0],true))==false)
							D_S[(int)key[0]] = D_S[(int)key[0]].concat((String) nodeLookup(key[0],true)); 
					}
				}
			}
		}
		DijkstrasPrinter();
	}
	
	
	public static <T> void initializeArray(boolean inputType, String startNode){
		if(inputType == true){
			for(int i=0;i<D.length;i++)
				D[i] = Integer.MAX_VALUE;
		}
		else{
			for(int i=0;i<D_S.length;i++)
				D_S[i] = sNode;
		}
	}
	
	public static <T> Object nodeLookup(T input, boolean inputType){
		Object toReturn = null;
		if(inputType==true){ //inputType = Integer
			for(int i=0;i<nodeList.size();i++){
				if(input.equals(nodeList.get(i).getIndex())){
					toReturn = nodeList.get(i).getName(); //get name corresponding to node index
					break;
				}
			}
		}
		else{ //inputType = String
			for(int i=0;i<nodeList.size();i++){
				if(input.equals(nodeList.get(i).getName())){
					toReturn = nodeList.get(i).getIndex(); //get index corresponding to node name
					break;
				}
			}
		}
		return toReturn;
	}
	
	public <T> List<Map<Integer,Integer>> findAdjacency(T input, boolean inputType){ 
		List<Map<Integer,Integer>> toReturn = null;
		if(inputType == true) //Integer
			toReturn = linkedList.get(input);
		else	//String
			toReturn = linkedList.get(nodeLookup(input, inputType)); //list of hash map of <node, distance> connecting to node[index]
		return toReturn;
	}
	
	public int getMin(int[] input, ArrayList<Integer> S, boolean index){
		int toReturn = 0;
		int min = input[0];
		int min_index = 0;
		for (int ktr = 0; ktr < input.length; ktr++) {
			if (input[ktr] < min && S.contains(ktr)==false) {
				min = input[ktr];
				min_index = ktr;
			}
		}
		if(index)
			toReturn = min_index;
		else
			toReturn = min;
		return toReturn;
	}
	
	public void DijkstrasPrinter(){
		for(int i=1;i<D.length;i++){
			System.out.print("Destination Node "+nodeLookup(i,true)+": path value = "+D[i]+", path is: ");
			for(int j=0;j<D_S[i].length();j++){
				System.out.print(D_S[i].charAt(j));
				if(!(j==D_S[i].length()-1))
					System.out.print(" -> ");
			}
			System.out.println();
		}
	}
	
}
