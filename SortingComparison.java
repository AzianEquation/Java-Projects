//coded by John Esco
import java.util.*;
public class SortingComparison {
    public static final int ARRAY_SIZE = 1000;
    //need to take average so I will run this test for 100 iterations..
    public static final int ITERATIONS = 100;
    //global variables for counters
    public static int selectionCounter = 0;
    public static int bubbleCounter = 0;
    public static int mergeCounter = 0;
    public static int quickCounter = 0;
    public static void main(String[] args) {
        for(int i=0;i<ITERATIONS;i++) {
            Random r = new Random();
            //initialize arrays
            int[] selectionArray = new int[ARRAY_SIZE];
            int[] bubbleArray = new int[ARRAY_SIZE];
            int[] mergeArray = new int[ARRAY_SIZE];
            int[] quickArray = new int[ARRAY_SIZE];
            //create array populated with random values
            for (int j = 0; j < ARRAY_SIZE; j++) {
                int randomValue = r.nextInt(ARRAY_SIZE); //random value created
                //stored in each array so the arrays are identical per iteration
                selectionArray[j] = randomValue;
                bubbleArray[j] = randomValue;
                mergeArray[j] = randomValue;
                quickArray[j] = randomValue;
            }
            selectionSort(selectionArray);
            bubbleSort(bubbleArray);
            mergeSort(mergeArray);
            quickSort(quickArray,0,ARRAY_SIZE-1);
            //show that sorts worked
            if(i==0) {
                System.out.println("This is a test of each sorting algorithm displaying the first and last 25 integers. Each sorted array should have the same result...");
                System.out.println("Selection Sort: Checks = " +selectionCounter);

                for (int k = 0; k < 25; k++) {
                    System.out.print(selectionArray[k] + " ");

                }
                System.out.print("... ");
                for (int k = ARRAY_SIZE-26; k < ARRAY_SIZE-1; k++) {
                    System.out.print(selectionArray[k] + " ");

                }
                System.out.println();
                System.out.println("Bubble Sort: Checks = " +bubbleCounter);
                for (int k = 0; k < 25; k++) {
                    System.out.print(bubbleArray[k] + " ");
                }
                System.out.print("... ");
                for (int k = ARRAY_SIZE-26; k < ARRAY_SIZE-1; k++) {
                    System.out.print(bubbleArray[k] + " ");

                }
                System.out.println();
                System.out.println("Merge Sort: Checks = " +mergeCounter);
                for (int k = 0; k < 25; k++) {
                    System.out.print(mergeArray[k] + " ");
                }
                System.out.print("... ");
                for (int k = ARRAY_SIZE-26; k < ARRAY_SIZE-1; k++) {
                    System.out.print(mergeArray[k] + " ");

                }
                System.out.println();
                System.out.println("Quick Sort: Checks = " + quickCounter);
                for (int k = 0; k < 25; k++) {
                    System.out.print(quickArray[k] + " ");
                }
                System.out.print("... ");
                for (int k = ARRAY_SIZE-26; k < ARRAY_SIZE-1; k++) {
                    System.out.print(quickArray[k] + " ");

                }
            }
        }
        System.out.println();
        System.out.println("After 100 iterations, the averages are as follows:");
        int selectionAverage = selectionCounter/ITERATIONS;
        int bubbleAverage = bubbleCounter/ITERATIONS;
        int mergeAverage = mergeCounter/ITERATIONS;
        int quickAverage = quickCounter/ITERATIONS;
        System.out.println("Selection Sort: "+ selectionAverage);
        System.out.println("Bubble Sort: "+bubbleAverage);
        System.out.println("Merge Sort: "+mergeAverage);
        System.out.println("Quick Sort: "+quickAverage);
    }
    //selection sort
    public static void selectionSort(int[] a){
        for(int i=0;i<a.length;i++){
            int smallIndex = i;
            for(int j=i+1;j<a.length;j++){
                selectionCounter = selectionCounter+1;
                if(a[j] < a[smallIndex]){
                    //found a smaller value
                    smallIndex=j;
                }
            }
            if(smallIndex != i){ //swap because found a smaller value
                int temp = a[i];
                a[i] = a[smallIndex];
                a[smallIndex] = temp;
            }
        }
    }
    //bubble sort
    public static void bubbleSort(int[] a){
        boolean hasSwapped = true;
        while(hasSwapped){
            hasSwapped = false;
            for(int i=0;i<a.length-1;i++){
                bubbleCounter=bubbleCounter+1;
                if(a[i]>a[i+1])///swap
                {
                    int temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    hasSwapped = true;
                }
            }
        }
    }
    //merge sort
    public static void mergeSort(int[] a){
        //done in non-inline version
        int size = a.length;
        if(size<2)
            return;
        int mid = size/2;
        int leftSize = mid;
        int rightSize = size-mid; //cant guarantee if this is symmetric
        int [] left = new int[leftSize]; //first half
        int[] right = new int[rightSize]; //second half
        //handwaving these out and just assuming it does not add to complexity
        for(int i=0;i<mid;i++){ //populate left array
            left[i] = a[i];
        }
        for(int i=mid;i<size; i++){
            right[i-mid] = a[i]; //making sure not to get an index out of bounds exceptions
        }
        mergeSort(left);
        mergeSort(right);
        merge(left,right,a);
    }
    //merge left and right into array a (final array)
    private static void merge(int[] left, int[] right, int[] a){
        //create helper variables
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0; //left array index
        int j = 0; //right array index
        int k = 0; //sorted array a index
        while(i<leftSize && j<rightSize){
            mergeCounter=mergeCounter+1;
            if(left[i] <= right[j]){ //element is smaller or equal
                a[k] = left[i];
                i++;
                k++;
            }
            else{ //right hand element is strictly less than left
                a[k] = right[j];
                j++;
                k++;
            }
        }
        while(i<leftSize){  ///one of these must run. left overs in left
            a[k] = left[i];
            i++;
            k++;
        }
        while(j<rightSize){ //left overs in right
            a[k] = right[j];
            j++;
            k++;
        }
    }
    public static void quickSort(int[] a, int leftIndex, int rightIndex){
        int index = partition(a,leftIndex,rightIndex);
        if(leftIndex<index-1)
            quickSort(a,leftIndex,index-1);
        if(index<rightIndex)
            quickSort(a,index,rightIndex);
    }
    private static int partition(int[] a, int leftIndex, int rightIndex){
        int i = leftIndex;
        int j= rightIndex;
        int pivot = a[(leftIndex+rightIndex)/2]; //pivot starts at midpoint
        while(i<=j){
            while(a[i]<pivot) //moves forward til it finds element out of place
            {
                quickCounter=quickCounter+1; //left counter
                i++;
            }
            while(a[j]>pivot) {
                quickCounter=quickCounter+1; //right counter
                j--;
            }
            if(i<=j) //found values out of order so swap
            {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }
        return i; //could be i or j
    }
}
