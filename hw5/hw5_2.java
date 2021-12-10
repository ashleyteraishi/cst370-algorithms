/* 
Title: hw5_2.java
Abstract: This program displays the performance of two different
sorting algorithms (merge sort and quick sort)
Author: Ashley Teraishi
ID: 0917
Date: 12/02/21

Merge Sort Program - https://www.geeksforgeeks.org/java-program-for-merge-sort/
Quick Sort Program - https://www.geeksforgeeks.org/java-program-for-quicksort/
*/

import java.util.*;

/* Java program for Merge Sort */
class MergeSort
{
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
 
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}

// Java program for implementation of QuickSort
class QuickSort
{
	/* This function takes last element as pivot,
	places the pivot element at its correct
	position in sorted array, and places all
	smaller (smaller than pivot) to left of
	pivot and all greater elements to right
	of pivot */
	int partition(int arr[], int low, int high)
	{
		int pivot = arr[high];
		int i = (low-1); // index of smaller element
		for (int j=low; j<high; j++)
		{
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] <= pivot)
			{
				i++;

				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;

		return i+1;
	}


	/* The main function that implements QuickSort()
	arr[] --> Array to be sorted,
	low --> Starting index,
	high --> Ending index */
	void sort(int arr[], int low, int high)
	{
		if (low < high)
		{
			/* pi is partitioning index, arr[pi] is
			now at right place */
			int pi = partition(arr, low, high);

			// Recursively sort elements before
			// partition and after partition
			sort(arr, low, pi-1);
			sort(arr, pi+1, high);
		}
	}
}

class hw5_2 {
    
    // method to populate an array with random integers
    public static void createRandom(int[] arr) {
        // create random object
        Random rd = new Random();
        
        // populate the array with random integers
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt();
        }
    }
    
    public static void main(String[] args) {
        // time variables
        long ms_start_time;
        long ms_end_time;
        float ms_time;
        long qs_start_time;
        long qs_end_time;
        float qs_time;
        
        // create scanner
        Scanner sc = new Scanner(System.in);
        
        // get input size from user
        System.out.print("Enter input size: ");
        int input_size = sc.nextInt();
        
        // create empty array of input size
        int[] arr = new int[input_size];
        
        // populate array with random integers
        createRandom(arr);
        int[] ms_arr = arr.clone();
        int[] qs_arr = arr.clone();
    
        // calculate elapsed time for merge sort
        ms_start_time = System.nanoTime();
        MergeSort ms = new MergeSort();
        ms.sort(ms_arr, 0, arr.length - 1);
        ms_end_time = System.nanoTime();
        ms_time = (ms_end_time - ms_start_time) / 1000000.0f;
        
        // calculate elapsed time for quick sort
        qs_start_time = System.nanoTime();
        QuickSort qs = new QuickSort();
        qs.sort(qs_arr, 0, arr.length - 1);
        qs_end_time = System.nanoTime();
        qs_time = (qs_end_time - qs_start_time) / 1000000.0f;
        
        
        // display results to the user
        System.out.println();
        System.out.println("===================== Execution Time ====================");
        System.out.println("Merge sort:   " + ms_time + " milliseconds");
        System.out.println("Quick sort:   " + qs_time + " milliseconds");
        System.out.println("=========================================================");
        
    }
    
}