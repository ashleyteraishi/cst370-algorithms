/* 
Title: hw4_2.java
Abstract: This program displays the biggest number in an array
with n integer numbers using a divide-and-conquer technique.
Author: Ashley Teraishi
ID: 0917
Date: 11/24/21
*/

import java.util.Scanner;

class hw4_2 {
    
    static int max = Integer.MIN_VALUE;
    
    static int max_div_n_conq(int[] arr, int start, int end) {
        if (start == end)
            return arr[start];
        else {
            int max_1 = max_div_n_conq(arr, start, (start + end) / 2);
            int max_2 = max_div_n_conq(arr, (start + end) / 2 + 1, end);
            if (max_1 > max_2 && max_1 > max)
                max = max_1;
            else if (max_2 > max_1 && max_2 > max) 
                max = max_2;
                
            return max;
        }
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // get number of input values
        int num_values = sc.nextInt();
        
        // set up array to store the input values
        int[] arr_values = new int[num_values];
        
        // populate array with the input values
        for (int i = 0; i < num_values; i++) {
            arr_values[i] = sc.nextInt();
        }
        
        max = max_div_n_conq(arr_values, 0, (num_values - 1));
        
        System.out.println(max);
        
        sc.close();
    }
    
}