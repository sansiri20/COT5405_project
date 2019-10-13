import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputManager {

	private ArrayList<Node> nodeList;
	public  ArrayList<Edge> edgeList;
	private Linkedlist glist;	//linked list to store vertices, edge, and weight
	private String fileName; 	//Input filename
	private int numV; 			//number of total vertices
	private int numE; 			//number of total edges
	
	InputManager(String _fileName){
		fileName = _fileName;
	}
	
	public void ReadFile(){
		BufferedReader br = null;
		glist = null;
		Node to = null, from = null;
		int weight;

		try {
			ArrayList<String> nodeList_name = new ArrayList<String>();
			nodeList = new ArrayList<Node>();
			edgeList = new ArrayList<Edge>();
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			int countLine =0, count=0;
			while ((sCurrentLine = br.readLine()) != null) {
				if(countLine==0){
					numV = Integer.parseInt(sCurrentLine);
					glist = new Linkedlist(numV);
				}
				//else if(countLine==1){
					//numE = Integer.parseInt(sCurrentLine);
				//}
				else{
					while(count<numV){
						 String[] elements = sCurrentLine.split(" ");
						 
						 if(!(nodeList_name.contains(elements[0]))){
							 from = new Node(elements[0],nodeList.size());
							 nodeList.add(from);
							 nodeList_name.add(from.getName());
						 }
						 else{
							 from = new Node(elements[0],nodeList);
						 }
						 
						 //for loop for each adjacency edge
						 for(int i=1;i<elements.length;i++){
							 String[] adjEdge = elements[i].split(",");
							 if(!(nodeList_name.contains(adjEdge[0]))){
								 to = new Node(adjEdge[0],nodeList.size());
								 nodeList.add(to);
								 nodeList_name.add(to.getName());
							 }
							 else{
								 to = new Node(adjEdge[0],nodeList);
							 }
							 weight = Integer.parseInt(adjEdge[1]);
				             glist.setEdge(to, from, weight,edgeList);
						 }
			             count++;
			             break;
					}
				}
				countLine++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public int getV(){
		return numV;
	}
	
	/*public int getE(){
		return numE;
	}*/
	
	public Linkedlist getGlist(){
		return glist;
	}
	
	public ArrayList<Node> getNodeList(){
		return nodeList;
	}
	
	public ArrayList<Edge> getEdgeList(){
		return edgeList;
	}
	
}
