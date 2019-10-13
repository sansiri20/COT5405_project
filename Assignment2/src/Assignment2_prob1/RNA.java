/*
 * RNA.java
 * Dynamic algorithm implementation on the problem of finding RNA Secondary Structure
 * By: Sansiri Tarnpradab
 */

public class RNA {

	public static int NMAX = 100;
	public int THRESHOLD = 4;
	public int DISPLAY_COMPUTATION = 0;
	public static int globalCounter=0;
	
	public boolean guPair(char x, char y)
	{
	   return ( x=='G'&&y=='U' || x=='U'&&y=='G' );
	}
	
	public boolean auPair(char x, char y)
	{
	   return ( x=='A'&&y=='U' || x=='U'&&y=='A' );
	}

	public boolean cgPair(char x, char y)
	{
	   return ( x=='C'&&y=='G' || x=='G'&&y=='C' );
	}

	public int basePair(char x, char y)
	{
		int toReturn=0;
		if( auPair(x,y) || cgPair(x,y) || guPair(x,y) )
			toReturn = 1;
	   return toReturn;
	}

	public int watsonCrick(char x, char y)
	{
		int toReturn=0;
		if( auPair(x,y) || cgPair(x,y) )
			toReturn = 1;
	   return toReturn;
	}
	
	public int[][] initializeNumBasePairs(int numBasePairs[][], int n){
		   int i,j;
		   for (i=0;i<n;i++) 
		      for (j=0;j<n;j++) 
		         numBasePairs[i][j]=0;
		   return numBasePairs;
	}

	public void printMatrix(int a[][], int n){
	   int i,j;
	   for (i=0;i<n;i++) {
	      for (j=0;j<n;j++) 
	         System.out.print(""+a[i][j]+" ");
	      System.out.println();
	   }
	}
	
	public  void computeBasePairMatrix(int numBasePairs[][],String S,int n){   
		   int i,j,d,k,max = 0,val,index;

		   for (d = THRESHOLD; d < n; d++)
		   {
		     for(i=1; i < n; i++){
		        
		        j=i+d;
		        if (j < n)
				{ 
		          max=0;
		          index=n; 
		           
		          if ( numBasePairs[i][j-1]>max )
				  {
		             max = numBasePairs[i][j-1];
		             index = n;
		            
		          }

		          val = watsonCrick(S.charAt(i),S.charAt(j)) + numBasePairs[i+1][j-1]; 
		          if ( j-i<= THRESHOLD && val > max )
				  {
		             max = val;
		             index=i;
		          }
		          for(k=i; k<=j-THRESHOLD; k++)
				  { 
			             val = watsonCrick(S.charAt(k),S.charAt(j)) + numBasePairs[i][k-1]+ numBasePairs[k+1][j-1];
			             if (val > max)
						 {
			                max = val;
			                index=k;
			             }
		          }
		          numBasePairs[i][j]=max;
		          if (index<n) 
		             numBasePairs[j][i]=index;
		            
		          else
		             numBasePairs[j][i]=-1;
		       }
		     }
		   }
		   System.out.println("Max no of pairs =  "+ (max+1)+", including:");
		}


	public int backtrack(int i, int j, int numBasePairs[][], String S, char paren[]){
		int k;
		 k = numBasePairs[j][i];
		 if(k != -1)  {
		   System.out.println("  #"+(++globalCounter)+": ("+k+","+j+")\t"+S.charAt(k)+"\t"+S.charAt(j));  
		   paren[k] = '('; 
		   paren[j] = ')';
		   if( THRESHOLD <= (j-1)-(k+1) ){
		     backtrack(k+1,j-1,numBasePairs,S,paren);
		   }
		   if (THRESHOLD <= k-1-i  ){
		     backtrack(i,k-1,numBasePairs,S,paren);
		   }
		 } 
		 else{ 
			if( THRESHOLD <= j-1-i ){
				if (RNA.NMAX==0)
				      System.out.println("i="+i+" j="+(j-1)); 
				backtrack(i,j-1,numBasePairs,S,paren);
			}
		 }  
		 return 0;
	}

	
	 
}
