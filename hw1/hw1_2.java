/* 
Title: hw1_2.java
Abstract: This program reads input numbers from a user and displays 
   the number(s) that occurs most frequently among all the input numbers.
Author: Ashley Teraishi
ID: 0917
Date: 10/29/21
*/

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class hw1_2 {
    
    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);
        
        // get the number of inputs 
        int length = sc.nextInt();
        
        // create temporary integer for input
        int temp = Integer.MAX_VALUE;
        
        // create hash map
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        
        // get user input and populate the hash map
        for (int i = 0; i < length; i++) {
            temp = sc.nextInt();
            
            // compare integer with hashMap keys
            if (hashMap.containsKey(temp)) {
                // if a match is found, increase the value (frequency)
                hashMap.put(temp, hashMap.get(temp) + 1);
            }
            else {
                // if no match is found, add the new integer to the hash map
                // with the value (frequency) equal to 1
                hashMap.put(temp, 1); 
            }
        }
        
        // find the key, value pair with the largest value (highest frequency)
        Integer largest = Integer.MIN_VALUE;
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        // iterate through the hash map key, value pairs
        for (HashMap.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            // if the value is greater than the largest
            if (entry.getValue() > largest) {
                // largest becomes the current value
                largest = entry.getValue();
                // clear the result list and add the new largest key
                result.clear();
                result.add(entry.getKey());
            }
            // else, if the value is equal to the largest
            else if (entry.getValue() == largest) {
                // add the current key to the array list
                result.add(entry.getKey());
            }
        }
        
        // sort result array list in descending order 
        Collections.sort(result, Collections.reverseOrder());
        
        // print results 
        System.out.println("Frequency:" + largest);
        System.out.print("Number:");
        // iterate through the result array list, print each element
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println();
        
        // close the scanner
        sc.close();
    }
    
}