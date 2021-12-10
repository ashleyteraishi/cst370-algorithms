/* 
Title: hw4_1.java
Abstract: This program reads a number of input values and the values themselves. 
Then, the program should put all negative numbers in front of all positive 
numbers.
Author: Ashley Teraishi
ID: 0917
Date: 11/24/21
*/

import java.util.Scanner;

class hw4_1 {
    
    public static void main (String[] args) {
        
        // create scanner
        Scanner sc = new Scanner(System.in);
        
        // get the number of values from the user
        int num_values = sc.nextInt();
        
        // create an array to store the input values
        int[] arr_values = new int[num_values];
        
        // populate the array of input values
        for (int i = 0; i < num_values; i++) {
            arr_values[i] = sc.nextInt();
        }
        
        // first approach
        int[] first_approach = arr_values.clone();
        int i = 0;
        int j = num_values - 1;
        
        while (i <= j - 1) {
            // if the i value is positive, check the j value
            if (first_approach[i] >= 0) {
                // if the j value is positive, shift the j index to the left
                if (first_approach[j] >= 0) {
                    j--;
                }
                // if the j value is negative, swap the i and j values
                if (first_approach[j] < 0) {
                        int temp = first_approach[i];
                        first_approach[i] = first_approach[j];
                        first_approach[j] = temp;
                    }
            }
            // else, if the i value is negative, shift to the right
            else {
                i++;
            }
        } 
        
        for (int index = 0; index < num_values; index++) {
            System.out.print(first_approach[index] + " ");
        }
        System.out.println();
        
        // second approach
        int[] second_approach = arr_values.clone();
        i = 0;
        j = 0;
        
        while (j < num_values) {
            if (second_approach[i] < 0) {
                i++;
                j++;
            }
            else if (second_approach[j] < 0) {
                int temp = second_approach[i];
                second_approach[i] = second_approach[j];
                second_approach[j] = temp;
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        
        for (int k = 0; k < num_values; k++) {
            System.out.print(second_approach[k] + " ");
        }
        System.out.println();
        
        sc.close();
    }
    
}