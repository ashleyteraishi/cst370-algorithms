/* 
Title: hw1_3.java
Abstract: This program converts a directed graph data from a user 
into a corresponding adjacency list format.
Author: Ashley Teraishi
ID: 0917
Date: 10/29/21
*/

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class hw1_3 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // get the number of vertices and edges from the user
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        
        // create hash map to store the directed graph data, key is the start vertex 
        // and value is an array list containing the end vertex of the edge(s)
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
        
        // create array list to store the end vertices of each directed edge
        ArrayList<Integer> al = new ArrayList<Integer>();
        
        // create temp variables to store the vertices listed on each line
        int from = Integer.MAX_VALUE, to = Integer.MAX_VALUE;
        
        // read input for each line (the number of lines equals the number of edges)
        for (int i = 0; i < edges; i++) {
            // the starting vertex will be the first int, ending vertexx is second int
            from = sc.nextInt();
            to = sc.nextInt();
            
            // if the starting vertex already exists as a key:
            if (hm.containsKey(from)) {
                // retrieve the existing array list containing ending vertices
                al = hm.get(from);
            }
            // else, if the starting vertex does not yet exist in the map as a key:
            else {
                // create a new array list to store this vertex's ending vertices
                al = new ArrayList<Integer>();
            }
            
            // add the current ending vertex to the list and put this list in map
            al.add(to);
            Collections.sort(al);
            hm.put(from, al);

        }
        
        // loop for the number of vertices
        for (int i = 0; i < vertices; i++) {
            System.out.print(i);
            
            // if the vertex exists as a key in the map:
            if (hm.containsKey(i)) {
                // loop through the value list to get each ending vertex
                for (int j = 0; j < hm.get(i).size(); j++) {
                    System.out.print("->" + hm.get(i).get(j));
                }
            }
            
            System.out.println();
        }
        
        // close the scanner
        sc.close();
    }
    
}
