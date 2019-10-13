
public class Edge {

	private Node start;
	private Node end;
	private int weight;
	public Edge(Node _from, Node _to, int _weight){
		setFromEdge(_from);
		setToEdge(_to);
		setWeight(_weight);
	}
	public Node getFromEdge() {
		return start;
	}
	public void setFromEdge(Node start) {
		this.start = start;
	}
	public Node getToEdge() {
		return end;
	}
	public void setToEdge(Node end) {
		this.end = end;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
