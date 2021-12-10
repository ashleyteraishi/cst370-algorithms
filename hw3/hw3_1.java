/* 
Title: hw3_1.java
Abstract: This program reads input values from the user. After that, it displays them in the ascending order. 
When it displays the numbers, it uses a short representation if there are consecutive numbers.
Author: Ashley Teraishi
ID: 0917
Date: 11/11/21
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class hw3_1 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // create array list to store input ints
        ArrayList<Integer> al = new ArrayList<Integer>();
        
        // get the number of input values from the user
        int num = sc.nextInt();
        
        // get the input values from the user
        for (int i = 0; i < num; i++) {
            al.add(sc.nextInt());
        }
        
        // sort the array list
        Collections.sort(al);
        
        // start index for consecutive ints
        int start = 0;
        boolean consecutive = false;
        
        // print the values
        for (int i = 0; i < num; i++) {
            if (i == 0) {
                if (al.get(i) != al.get(i + 1) - 1)
                    System.out.print(al.get(i) + " ");
            }
            else if (al.get(i) != (al.get(i-1) + 1)) {
                if (consecutive)
                    System.out.print(al.get(start) + "-" + al.get(i - 1) + " ");
                else if (i == num - 1) 
                    System.out.print(al.get(i) + "\n");
                    
                start = i;
                consecutive = false;
                
                if (i < num - 1 && (al.get(i) != al.get(i+1) - 1))
                    System.out.print(al.get(i) + " ");
            }
            else {
                consecutive = true;
                
                if (i == num - 1) 
                    System.out.print(al.get(start) + "-" + al.get(i) + "\n");
            }
        }
        
        sc.close();
    }
    
}