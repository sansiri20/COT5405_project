import java.util.ArrayList;

public class Node {

	private int index;
	private String name;
	Node(String _name,int _index){
		name = _name;
		index = _index;
	}
	
	Node(String _name, ArrayList<Node> nodeList){
		name = _name;
		index = getIndex(_name, nodeList);
	}
	
	public int getIndex(String name, ArrayList<Node> nodeList){
		int index = 0;
		for(int i=0;i<nodeList.size();i++){
			if(nodeList.get(i).getName().equals(name))
				index = nodeList.get(i).getIndex();
		}
		 return index;
	}
	
	public int getIndex(){
		return index;
	}
	
	
	public String getName(){
		return name;
	}
	
}
