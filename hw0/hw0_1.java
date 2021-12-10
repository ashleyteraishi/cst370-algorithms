/* 
Title: hw0_1.java
Abstract: This program calculates the sum and difference of two numbers
Author: Ashley Teraishi
ID: 0917
Date: 10/25/21
*/

import java.util.Scanner;

class hw0_1 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // create scanner object
        
        // get integer input
        int first = scanner.nextInt();
        int second = scanner.nextInt();
        
        // find and print sum of integers
        int sum = first + second;
        System.out.println("SUM:" + sum);
        
        // find and print difference of integers
        int diff = first - second;
        System.out.println("DIFF:" + diff);
    }
}