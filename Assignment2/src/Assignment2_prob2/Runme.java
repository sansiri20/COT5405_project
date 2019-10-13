
public class Runme {

    public static void main (String[] args) throws java.lang.Exception
    {
    	
    	//Problem 2: Sequence Alignment
    	System.out.println("Problem 2: Sequence Alignment");
    	SequenceAlignment s = new SequenceAlignment();
    	String[] aligned = s.align("AGGCTATCACCTGACCTCCAGGCCGATGCCC", "TAGCTATCACGACCGCGGTCGATTTGCCCGAC");
    	printResult2(s, aligned, "AGGCTATCACCTGACCTCCAGGCCGATGCCC", "TAGCTATCACGACCGCGGTCGATTTGCCCGAC");
    	System.out.println();
    	
    }
    
    
    static void printResult2(SequenceAlignment s, String[] aligned, String sequenceA, String sequenceB){
    	System.out.println("a. Show the final penalty cost of the alignment have been found: "+s.computerScore(aligned[0], aligned[1]));
    	
    	System.out.println("b. Show the alignment of these two strings, in the similar format:");
 	    System.out.println(aligned[0]);
 	    System.out.println(aligned[1]);
 	    
 	    System.out.println("The corresponding matrix is the following table, where resulting penalty cost is at opt["+sequenceA.length()+"]["+sequenceB.length()+"] (result in the [bracket]): \n");
 	    s.matrixGenerator(sequenceA, sequenceB);
    }
}
