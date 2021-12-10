/* 
Title: hw3_3.java
Abstract: This program implements the Depth-First Search (DFS) 
algorithm using a stack and a mark array as we learned in the class.
Author: Ashley Teraishi
ID: 0917
Date: 11/11/21
*/

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class hw3_3 {
    
    static int count = 0;
    
    // create hashmap to store tree data
    // key is a vertex, value is list of child neighbors
    static HashMap<Integer, ArrayList<Integer>> edges = new HashMap<>();
    
    // create arraylist to store neighbors for each vertex
    static ArrayList<Integer> neighbors = new ArrayList<>();
    
    // create hashmap to store vertex data
    // key is vertex, value is int denoting if vertex has been visited
    static HashMap<Integer, Integer> mark = new HashMap<>();
    
    /*
    public static void DFS(HashMap<Integer, Integer> mark) {
        for (HashMap.Entry<Integer, Integer> V : mark.entrySet()) {
            int v = V.getKey();
            if (V.getValue() == 0) 
                dfs(v);
        }
    }
    */
    
    public static void DFS(HashMap<Integer, ArrayList<Integer>> edges) {
        for (HashMap.Entry<Integer, ArrayList<Integer>> V : edges.entrySet()) {
            int v = V.getKey();
            if (mark.get(v) == 0) 
                dfs(v);
        }
    }
    
    public static void dfs(Integer v) {
        count += 1;
        mark.put(v, count);
        
        neighbors = edges.get(v);
        
        //System.out.println(v);
        
        /* 
        for (int i = 0; i < neighbors.size(); i++) {
            int w = neighbors.get(i);
            if (mark.containsKey(w) && mark.get(w) == 0)
                dfs(w);
        }
        */
        for (int neighbor : neighbors) {
            if (mark.containsKey(neighbor) && mark.get(neighbor) == 0)
                dfs(neighbor);
        }
        
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int num_vertices = sc.nextInt();
        int num_edges = sc.nextInt();
        
        int start_vertex = 0;
        int end_vertex = 0;
        
        
        for (int i = 0; i < num_edges; i++) {
            start_vertex = sc.nextInt();
            end_vertex = sc.nextInt();
            
            if (edges.containsKey(start_vertex)) {
                neighbors = edges.get(start_vertex);
                neighbors.add(end_vertex);
                Collections.sort(neighbors);
                edges.put(start_vertex, neighbors);
            }
            else {
                neighbors = new ArrayList<>();
                neighbors.add(end_vertex);
                edges.put(start_vertex, neighbors);
                mark.put(start_vertex, 0);
            }
            
            
        }
        
        /*
        for (HashMap.Entry<Integer, ArrayList<Integer>> vertex : edges.entrySet()) {
            System.out.print("Vertex: " + vertex.getKey());
            System.out.print(" Neighbors:");
            
            neighbors = vertex.getValue();
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(" " + neighbors.get(i));
            }
            System.out.println();
        }
        */
        
        //DFS(mark);
        
        DFS(edges);
        
        int final_vertex = num_vertices - 1;
        mark.put(final_vertex, count + 1);
        
        for (int i = 0; i < num_vertices; i++) {
            System.out.println("Mark[" + i + "]:" + mark.get(i));
        }
    }
    
}