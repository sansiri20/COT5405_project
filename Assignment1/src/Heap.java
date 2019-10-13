import java.util.Arrays;

@SuppressWarnings("unchecked")
public class Heap<AnyType extends Comparable<AnyType>> {
	private static final int CAPACITY = 2;

	   private int size;            // Number of elements in heap
	   private AnyType[] heap;     // The heap array

	   public Heap(){
	      size = 0;
	      heap = (AnyType[]) new Comparable[CAPACITY];
	   }

	   public void heapify(AnyType[] array){
	      size = array.length;
	      heap = (AnyType[]) new Comparable[size+1];
	      System.arraycopy(array, 0, heap, 1, size);
	      buildHeap();
	      
	      System.out.println("\n\n-- Initial \"heapify the array\" operation --");
	      System.out.println(Arrays.toString(heap));

	      for (int i = size; i > 0; i--){
	         AnyType tmp = heap[i]; //move top item to the end of the heap array
	         heap[i] = heap[1];
	         heap[1] = tmp;
	         size--;
	         siftUp(1);
	      }
	      for(int k = 0; k < heap.length-1; k++)
	         array[k] = heap[k+1];
	   }
	   
	   private void buildHeap(){
	      for (int k = size/2; k > 0; k--){
	         siftUp(k);
	      }
	   }
	   
	   private void siftUp(int k){
		   AnyType tmp = heap[k];
		      int child;

		      for(; 2*k <= size; k = child){
		         child = 2*k;

		         if(child != size && heap[child].compareTo(heap[child + 1]) < 0) 
		        	 child++;

		         if(tmp.compareTo(heap[child]) < 0)  
		        	 heap[k] = heap[child];
		         else
		                break;
		      }
		      heap[k] = tmp;
	   }
}
