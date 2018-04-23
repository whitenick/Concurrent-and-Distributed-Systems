import java.util.*;
import java.util.concurrent.*;



public class PSortQ1 extends RecursiveTask<Integer>{
    // QuickSort algorithm
    // input: array A, split into two, and sorted recursively
    // output: array A, sorted
    // max sizeof A = 10,000
    // use ForkJoinPool for parallel processing
    // range of sort from index begin (inclusive) to end (exclusive)
    public static void parallelsort(int[] A, int begin, int end) {
        
    }

    protected Integer compute() {

	
	    
	    
	    return 0;
    }

    // size of array int[] A does not exceed 10,000 elements
    // if size of A[] is less than or equal to 16, use sequential insert sort
    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of processors: "+processors);
        PSortQ1 mainSort = new PSortQ1();
    }


}
