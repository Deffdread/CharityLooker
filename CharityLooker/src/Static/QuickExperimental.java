package Static;

import java.util.Arrays;
import java.util.Collections;

public class QuickExperimental {
	

	    // This class should not be instantiated.
	    private QuickExperimental() { }

	    /**
	     * Rearranges the array in ascending order, using the natural order.
	     * @param a the array to be sorted
	     */
	    public static void sort(Charity[] a){ //, Comparator comparator) {
	        Collections.shuffle(Arrays.asList(a)); //Protects against worst case
	        sort(a, 0, a.length - 1);
	    }

	    // quicksort the subarray from a[lo] to a[hi]
	    private static void sort(Charity[] a, int lo, int hi) { 
	        if (hi <= lo) return;
	        int j = partition(a, lo, hi);
	        sort(a, lo, j-1);
	        sort(a, j+1, hi);
	    }

	    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
	    // and return the index j.
	    private static int partition(Charity[] a, int lo, int hi){
	        int i = lo;
	        int j = hi + 1;
	        Charity v = a[lo];
	        while (true) { 

	            // find item on lo to swap
	            while (less(a[++i], v)) {
	                if (i == hi) break;
	            }

	            // find item on hi to swap
	            while (less(v, a[--j])) {
	                if (j == lo) break;      // redundant since a[lo] acts as sentinel
	            }

	            // check if pointers cross
	            if (i >= j) break;

	            exch(a, i, j);
	        }

	        // put partitioning item v at a[j]
	        exch(a, lo, j);

	        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
	        return j;
	    }


	   /***************************************************************************
	    *  Helper sorting functions.
	    ***************************************************************************/
	    
	    // is v < w ?
	    private static boolean less(Charity v, Charity w) {
	        if (v == w) return false;   // optimization when reference equals
	        return v.compareTo(w,"bnum") < 0;
	    }
	    
	    
	    /*private static boolean less(Object v, Object w) 
	    {
	        return comparator.compare(v, w) < 0;
	    }*/
	        
	    // exchange a[i] and a[j]
	    private static void exch(Object[] a, int i, int j) {
	        Object swap = a[i];
	        a[i] = a[j];
	        a[j] = swap;
	    }
	    


	   /***************************************************************************
	    *  Check if array is sorted - useful for debugging.
	    ***************************************************************************/
	    private static boolean isSorted(Charity[] a) {
	        return isSorted(a, 0, a.length - 1);
	    }

	    private static boolean isSorted(Charity[] a, int lo, int hi) {
	        for (int i = lo + 1; i <= hi; i++)
	            if (less(a[i], a[i-1])) return false;
	        return true;
	    }


	    // print array to standard output
	    private static void show(Charity[] a) {
	        for (int i = 0; i < a.length; i++) {
	            System.out.println(a[i]);
	        }
	    }
}
