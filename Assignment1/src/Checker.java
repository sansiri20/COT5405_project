import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker{
	
	private static String getSubString(String regex, String inputString){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(inputString);
		if (matcher.find()){
		    return matcher.group(0);
		} else {
			return null;
		}
	}
	
	private static boolean isSymmetric(HashSet<String> vertices, String [][] lines){
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		Iterator <String> iter = vertices.iterator();
		int i = 0;
		//every single vertex will now be represented by a number. so if A = 0, then row 0
		// and col 0 will represent A in the adjacency matrix
		while(iter.hasNext()){
			map.put(iter.next(), i);
			i++;
		}
		//System.out.println(map);
		
		int [][] adjMatrix = new int[vertices.size()][vertices.size()];
		int row;
		int col;
		int weight;
		String tok;
		for(int j = 0; j < lines.length; j++){
			row = map.get(lines[j][0]);
			for(int k = 1; k <lines[j].length; k++){
				tok = lines[j][k];
				weight = Integer.parseInt(getSubString("\\d+$",tok));
				col = map.get(getSubString("^[a-zA-z0-9]",tok));
				adjMatrix[row][col] = weight;
			}
		}
		//print2D(adjMatrix);
		//At this point, you have the matrix. Now just check if it's symmetric.
		for(int j = 0; j < adjMatrix.length; j++){
			for(int k = 0; k < adjMatrix[j].length; k++){
				if(adjMatrix[j][k] != adjMatrix[k][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	private static void print2D(int[][] a){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[i].length; j++){
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static boolean checkLine(String [] tokenizedLine, HashSet<String>vertices){
		
		//skip 0, cause that's the vertex
		for(int i = 1; i < tokenizedLine.length; i++){
			//check each token to make sure it's starts with alphabet letters,
			//	followed by a comma, 
			//	then ending with an integer.
			if (tokenizedLine[i].matches("^[a-zA-z0-9],\\d+$") == false){
				return false;
			}
			//make sure that the alpabet letters each token starts with is in vertices set
			Pattern pattern = Pattern.compile("^[a-zA-z0-9]");
			Matcher matcher = pattern.matcher(tokenizedLine[i]);
			if (matcher.find()){
			    if (vertices.contains(matcher.group(0)) == false){
			    	//this word is NOT a valid vertex
			    	return false;
			    }
			} else {
				//this token doesn't start with any letters, so it's not a valid vertex
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkInput(String file) throws FileNotFoundException, IOException{
		HashSet<String> vertices = new HashSet<String>();
		
		//read in the file and store each line into lines array
		ArrayList<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       lines.add(line);
		    }
		}
		
		
		if( lines.get(0).matches("^\\d+$") == false){
			//the first line is not a number
			return false;
		} else if (lines.size() - 1 != (Integer.parseInt(lines.get(0)))){
			//the number of lines in the input do not match the number given in line 1
			return false;
			
		}
		
		String [][] tokenizedLines = new String[lines.size() - 1][];
		
		for(int i = 1; i < lines.size(); i++){
			String [] lineArray = lines.get(i).split(" ");
			vertices.add(lineArray[0]);
			tokenizedLines[i - 1] = lineArray;
		}
		
		for(int i = 0; i < tokenizedLines.length; i++){
			if(checkLine(tokenizedLines[i], vertices) == false){
				return false;
			}
		}
		if(isSymmetric(vertices, tokenizedLines) == false)
			return false;
		return true;
		
	}
	/*
	public static void main(String [] args) throws FileNotFoundException, IOException{
		
		System.out.println( "Check:" + checkInput("dist.txt"));
		
	}
	*/
}