/* 
Title: hw1_1.java
Abstract: This program reads input numbers from a user and displays 
   the closest distance between two numbers among all input numbers.
Author: Ashley Teraishi
ID: 0917
Date: 10/29/21
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

class hw1_1 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // get the number of inputs 
        int length = sc.nextInt();
        
        // create integer array for input
        int[] arr = new int[length];
        
        // create array list of integer arrays for result pairs
        ArrayList<Integer[]> result = new ArrayList<Integer[]>();
        
        // initialize the difference variable as max value
        int difference = Integer.MAX_VALUE;
        
        // get user input
        for (int i = 0; i < length; i++) {
            arr[i] = sc.nextInt();
        }
        
        // sort user input
        Arrays.sort(arr);
        
        // traverse through the sorted array and compare each
        // element to the previous element, looking for the smallest difference
        for (int i = 0; i < length; i++) {
            // starting at the first index (second integer)
            if (i > 0) {
                // calculte the difference by comparing to previous element
                int temp = arr[i] - arr[i - 1];
                // if the current difference is less than the saved difference:
                if (temp < difference) {
                    // update the new lowest difference
                    difference = temp;
                    // reset result array and add the new pair
                    result.clear();
                    result.add(new Integer[]{arr[i - 1], arr[i]});
                }
                // else, if the current difference is the lowest difference:
                else if (temp == difference) {
                    // add the current pair to the list of lowest difference pairs
                    result.add(new Integer[]{arr[i - 1], arr[i]});
                }
            }
        }
        
        // print results
        System.out.println("Min Distance:" + difference);
        
        // iterate through result array list
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Pair:" + result.get(i)[0] + " " + result.get(i)[1]);
        }
        
        // close the scanner
        sc.close();
    }
}