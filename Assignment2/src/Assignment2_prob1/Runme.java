
public class Runme {

    public static void main (String[] args) throws java.lang.Exception
    {
    	//Problem 1: RNA Secondary Structure
    	System.out.println("Problem 1: RNA Secondary Structure");
    	System.out.println("a. Find the max number of base pairs for the 'AUGGCUACCGGUCGAUUGAGCGCCAAUGUAAUCAUU'");
    	
    	RNA r = new RNA();
		String S= "AUGGCUACCGGUCGAUUGAGCGCCAAUGUAAUCAUU";
		char[] paren = new char[RNA.NMAX];
		   int i;
		   int[][] numBasePairs = new int[RNA.NMAX][RNA.NMAX];
		   int n = S.length();
		   numBasePairs = r.initializeNumBasePairs(numBasePairs,n);
		   for (i=0;i<n;i++) {
			   paren[i]='.';
			   paren[n]='\0';
		   }
		   System.out.println("Number of Nucleotides: "+n);
	
		   r.computeBasePairMatrix(numBasePairs,S,n);
		   r.backtrack(0,n-1,numBasePairs,S,paren);
    }
  
}
