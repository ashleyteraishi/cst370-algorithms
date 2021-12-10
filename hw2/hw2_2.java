/* 
Title: hw2_2.java
Abstract: This program reads a number of elements in a set then, 
reads the elements of the set. After that, it displays all 
possible decimal numbers, corresponding binary numbers, and subsets one by one.
Author: Ashley Teraishi
ID: 0917
Date: 11/5/21
*/

import java.util.Scanner;
import java.lang.Math;

class hw2_2 {
    
    public static void main(String[] args) {
        
        // create scanner
        Scanner sc = new Scanner(System.in);
        
        // first, get the number of elements from the user
        int numElems = sc.nextInt();
        
        // if the number of elements is 0, print EMPTY and close the scanner
        if (numElems == 0) 
            System.out.println("0:0:EMPTY");
            
        // else, if the number of elements is not 0, do:
        else {
            // get the line of elements from the user, store as a string
            sc.nextLine();
            String elems = sc.nextLine();
        
            // split this line of input into a String array
            // separate elements by empty space (" ")
            String[] allElems = elems.split(" ", numElems);
            
            // calculate the amount of decimal digits we will be converting
            // this is found by doing 2^numElems
            int nums = (int)Math.pow(2, numElems);
            
            // repeat loop nums times to list all possible subsets
            for (int i = 0; i < nums; i++) {
                // use the toBinary and toSubset functions to find values, then print
                String binary = toBinary(numElems, i);
                String subset = toSubset(allElems, binary);
               
                System.out.println(i + ":" + binary + ":" + subset);
            }
        }
        
        // close scanner
        sc.close();
    }
    
    /*
    This method is used to turn a decimal integer to binary format
    @param numElems This is the first parameter, it specifies the number of digits
    for the binary number
    @param num This is the second parameter, it specifies the decimal number to be converted
    @return String that contains the binary format of the decimal integer
    */
    static String toBinary(int numElems, int num) {
        // create string variable to hold the binary value
        String result = "";
        
        // the size of the integer will be the same as numElems
        for (int i = numElems - 1; i >= 0; i--) {
            int k = num >> i;
            
            if ((k & 1) > 0)
                result += "1";
            else
                result += "0";
        }
            
        return result;
    }
    
    /*
    This method is used to find the subset given an array of elements and a binary number
    @param elements This is the array containing the elements to be used to form the subset
    @param binary This is the binary number to be used to find the subset from the array
    @return String that contains the subset elements
    */
    static String toSubset(String[] elements, String binary) {
        // create string variable to hold the subset
        String result = "";
        
        // repeat loop for each element in the elements array, using index i
        for (int i = 0; i < elements.length; i++) {
            // if the character at i of the binary string is 1, add the current 
            // array element to the result string (this element is active)
            if (binary.charAt(i) == '1') {
                result = result + elements[i] + " ";        
            }
        }
        
        // if the result string (subset) is empty, set the string equal to "EMPTY"
        if (result == "") {
            result = "EMPTY";
        }
        
        return result;
    }
    
}