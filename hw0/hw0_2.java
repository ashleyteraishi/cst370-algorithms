/* 
Title: hw0_2.java
Abstract: This program calculates the min, max, and median of five numbers
Author: Ashley Teraishi
ID: 0917
Date: 10/25/21
*/

import java.util.Scanner;
import java.util.Arrays;

class hw0_2 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // create scanner object
        
        // create integer array
        int[] arr = new int[5];
        
        // get integer input and add to array
        for (int i = 0; i < 5; i++) {
            arr[i] = scanner.nextInt();
        }
        
        // sort array
        Arrays.sort(arr);
        
        // find and print min
        System.out.println("MIN:" + arr[0]);
        
        // find and print max
        System.out.println("MAX:" + arr[4]);
        
        // find and print median 
        // (because there are 5 values in the sorted array, 
        // the integer at index 2 will be the median)
        System.out.println("MEDIAN:" + arr[2]);
    }
}