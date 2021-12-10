/* 
Title: hw3_2.java
Abstract: This program reads an input graph data from a user. 
Then, it should present a path for the travelling salesman problem (TSP).
Author: Ashley Teraishi
ID: 0917
Date: 11/11/21
*/

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class hw3_2 {
    
    public static ArrayList<ArrayList<Integer>> permute(int[] vertices) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<>();
        Permute(vertices, 0, permutations);
        return permutations;
    }
    
    private static void Permute(int[] vertices, int start_index, ArrayList<ArrayList<Integer>> permutations) {
        
        int size = vertices.length;
        
        if (size == start_index + 1) {
            ArrayList<Integer> permutation = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                permutation.add(vertices[i]);
            }
            permutations.add(permutation);
        }
        else {
            for (int i = start_index; i < size; i++) {
                int temp = vertices[i];
                vertices[i] = vertices[start_index];
                vertices[start_index] = temp;

                Permute(vertices, start_index + 1, permutations);
                temp = vertices[i];
                vertices[i] = vertices[start_index];
                vertices[start_index] = temp;
            }
        }
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // create hashmap to store graph data. key is an array start and end vertices 
        // for each edge. value is the cost for the edge
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        
        // create array of length 2 (start and end vertices) for the hashmap
        ArrayList<Integer> arr = new ArrayList<Integer>();
        
        // read the number of vertices and edges from user
        int num_vertices = sc.nextInt();
        int num_edges = sc.nextInt();
        
        // temp variables to hold input data for each edge
        int start_vertex = 0;
        int end_vertex = 0;
        int cost = 0;
        
        // get input data for each edge
        for (int i = 0; i < num_edges; i++) {
            start_vertex = sc.nextInt();
            end_vertex = sc.nextInt();
            cost = sc.nextInt();
            
            // add start and end vertices to the arraylist
            // start at index 0, end at index 1
            arr.add(start_vertex);
            arr.add(end_vertex);
            
            // add arraylist and corresponding cost to the hashmap
            hm.put(arr.toString(), cost);
            
            // clear the arraylist to prepare for the next edge's data
            arr.clear();
        }
        
        int starting_vertex = sc.nextInt(); 
        
        // create array with each edge to find permutations
        int[] vertices = new int[num_vertices - 1];
        
        // populate vertices array
        int value = 0;
        for (int i = 0; i < num_vertices - 1; i++) {
            if (i != starting_vertex) {
                vertices[i] = value;
            }
            else {
                vertices[i] = ++value;
            }
            value++;
        }
        
        // get permutations
        ArrayList<ArrayList<Integer>> permutations = permute(vertices);
        
        //ArrayList<Integer> costs = new ArrayList<Integer>();
        HashMap<ArrayList<Integer>, Integer> path_values = new HashMap<>();
        Integer total_cost = 0;
        
        for (int i = 0; i < permutations.size(); i++) {
            ArrayList<Integer> temp_vertices = permutations.get(i);
            ArrayList<Integer> temp_key = new ArrayList<Integer>();
            
            for (int j = -1; j < temp_vertices.size(); j++) {
                if (j == -1) {
                    temp_key.add(starting_vertex);
                    temp_key.add(temp_vertices.get(j + 1));
                }
                else if (j == temp_vertices.size() - 1) {
                    temp_key.add(temp_vertices.get(j));
                    temp_key.add(starting_vertex);
                }
                else {
                    temp_key.add(temp_vertices.get(j));
                    temp_key.add(temp_vertices.get(j + 1));
                }
                
                if (hm.containsKey(temp_key.toString())) {
                    if (total_cost != -1)
                        total_cost += hm.get(temp_key.toString());
                }
                else {
                    total_cost = -1;
                }
                //System.out.println(temp_key + " " + total_cost);
                temp_key.clear();
            }
            //costs.add(total_cost);
            path_values.put(temp_vertices, total_cost);
            total_cost = 0;
            //System.out.println("Completed Path " + i);
        }
        
        Integer min_cost = Integer.MAX_VALUE;
        ArrayList<Integer> min_path = new ArrayList<>();
        for (HashMap.Entry<ArrayList<Integer>, Integer> path : path_values.entrySet()) {
            if (path.getValue() < min_cost && path.getValue() != -1) {
                min_cost = path.getValue();
                min_path = path.getKey();
            }
        }
        
        //System.out.println("Path: " + min_path.toString());
        if (min_cost == Integer.MAX_VALUE) {
            System.out.println("Path:");
            System.out.println("Cost:-1");
        }
        else {
            System.out.print("Path:" + (starting_vertex));
            for (int i = 0; i < min_path.size(); i++) {
                System.out.print("->" + min_path.get(i));
            }
            System.out.print("->" + (starting_vertex));
            System.out.println();
            System.out.println("Cost:" + min_cost);
        }
        
    }
    
}