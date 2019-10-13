
public class Runme {

    public static void main (String[] args) throws java.lang.Exception
    {
    	
    	//Problem 3: Max Flow
    	     					   //s, 2, 3, 4, 5, 6, 7, t
    	int graph[][] =new int[][] {{0, 10, 5, 15, 0, 0, 0, 0},
						            {0, 0, 4, 0, 9, 15, 0, 0},
						            {0, 0, 0, 4, 0, 8, 0, 0},
						            {0, 0, 0, 0, 0, 0, 30, 0},
						            {0, 0, 0, 0, 0, 15, 0, 10},
						            {0, 0, 0, 0, 0, 0, 15, 10},
						            {0, 0, 6, 0, 0, 0, 0, 10},
						            {0, 0, 0, 0, 0, 0, 0, 0}};   
        MaxFlow m = new MaxFlow();
        System.out.println("\nProblem 3: Max Flow");
        printResult3(m, graph);
    }
    
    
    static void printResult3(MaxFlow m, int graph[][]){
        System.out.println("a. The maximum flow value from node (s) to (t) is " +m.fordFulkerson(graph, 0, 7));
        System.out.println("b. The flow values on each link on the directed graph for the maximum flow scenario: (0 = Source 's', 7 = Sink 't') \n");
        
        //print the results in row/column format
        for(int a=0;a<=7;a++){
        	if(a==0)
        		System.out.print("FROM\\TO ||\t");
        	System.out.print(a+"\t");
        }
        System.out.println();
        
        for(int a=0;a<=8;a++)
        	System.out.print("---------");
        System.out.println();
        
        for(int i=0;i<=7;i++){
        	System.out.print("   "+i);
        	System.out.print("    ||\t");
        	for(int j=0;j<=7;j++){
        		System.out.print(m.returnResultGraph(i, j)+"\t");
        	}
        	System.out.println();
        }
    }
}
