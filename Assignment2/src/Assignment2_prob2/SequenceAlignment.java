/*
 * SequenceAlignment.java
 * Dynamic algorithm implementation on the problem of sequence alignment
 * By: Sansiri Tarnpradab
 */

public class SequenceAlignment {
	
	    public String[] align(String sequenceA, String sequenceB) {
	        int[][] T = new int[sequenceA.length() + 1][sequenceB.length() + 1];

	        for (int i = 0; i <= sequenceA.length(); i++)
	            T[i][0] = i;

	        for (int i = 0; i <= sequenceB.length(); i++)
	            T[0][i] = i;

	        for (int i = 1; i <= sequenceA.length(); i++) {
	            for (int j = 1; j <= sequenceB.length(); j++) {
	                if (sequenceA.charAt(i - 1) == sequenceB.charAt(j - 1))
	                    T[i][j] = T[i - 1][j - 1];
	                else
	                    T[i][j] = Math.min(T[i - 1][j], T[i][j - 1]) + 1;
	            }
	        }

	        StringBuilder aa = new StringBuilder(), bb = new StringBuilder();

	        for (int i = sequenceA.length(), j = sequenceB.length(); i > 0 || j > 0; ) {
	            if (i > 0 && T[i][j] == T[i - 1][j] + 1) {
	                aa.append(sequenceA.charAt(--i));
	                bb.append("-");
	            } else if (j > 0 && T[i][j] == T[i][j - 1] + 1) {
	                bb.append(sequenceB.charAt(--j));
	                aa.append("-");
	            } else if (i > 0 && j > 0 && T[i][j] == T[i - 1][j - 1]) {
	                aa.append(sequenceA.charAt(--i));
	                bb.append(sequenceB.charAt(--j));
	            }
	        }

	        return new String[]{aa.reverse().toString(), bb.reverse().toString()};
	    }
	    
	    public void matrixGenerator(String sequenceA, String sequenceB){
	    	
	    	//reverse the input sequences first
	    	sequenceA = new StringBuilder(sequenceA).reverse().toString(); //sequenceA
	    	sequenceB = new StringBuilder(sequenceB).reverse().toString(); //sequenceB
	    	
	    	// penalties
	    	int gap = 1, mismatch = 2, match = 0;

	    	int[][] opt = new int[sequenceA.length() + 1][sequenceB.length() + 1];

	    	// compute insertions and deletions at 1st row/column
	    	for (int i = 1; i <= sequenceA.length(); i++)
	    	    opt[i][0] = opt[i - 1][0] + gap;
	    	for (int j = 1; j <= sequenceB.length(); j++)
	    	    opt[0][j] = opt[0][j - 1] + gap;

	    	for (int i = 1; i <= sequenceA.length(); i++) {
	    	    for (int j = 1; j <= sequenceB.length(); j++) {
	    	        int scoreDiag = opt[i - 1][j - 1] +
	    	                (sequenceA.charAt(i-1) == sequenceB.charAt(j-1) ?
	    	                    match : 
	    	                    	mismatch); 
	    	        int scoreLeft = opt[i][j - 1] + gap; 
	    	        int scoreUp = opt[i - 1][j] + gap; 
	    	        opt[i][j] = Math.min(Math.min(scoreDiag, scoreLeft), scoreUp);
	    	    }
	    	}

	    	for (int i = 0; i <= sequenceA.length(); i++) {
	    	    for (int j = 0; j <= sequenceB.length(); j++){
	    	    	if(i==sequenceA.length() && j == sequenceB.length())
	    	    		System.out.print("["+opt[i][j]+"]" + "\t");
	    	    	else
	    	    		System.out.print(opt[i][j] + "\t");
	    	    }
	    	    System.out.println();
	    	}
	    }
	    
	    public int computerScore(String input1, String input2){
	    	// penalties
	    	int gap = 1, mismatch = 2;
	    	int finalScore=0;
	    	for(int i=0;i<input1.length();i++){
	    		if(input1.charAt(i) == '-' ||input2.charAt(i) == '-')
	    			finalScore = finalScore+gap;
	    		else if(input1.charAt(i) != input2.charAt(i))
	    			finalScore = finalScore+mismatch;
	    	}
	    	return finalScore;
	    }
	    
	}

