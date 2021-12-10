/* 
Title: hw4_3.java
Abstract: This program conducts the topological sorting based
on the Kahn algorithm.
Author: Ashley Teraishi
ID: 0917
Date: 11/24/21
*/

import java.util.*;

class Graph {
    
    int num_vertices;
    int count_visited = 0;
    
    // create arraylist of arraylist which contains references to the
    // adjacency list of each vertex
    ArrayList<ArrayList<Integer>> adjacency;
    
    // constructor
    public Graph(int num_vertices) {
        this.num_vertices = num_vertices;
        adjacency = new ArrayList<ArrayList<Integer>>(num_vertices);
        for (int i = 0; i < num_vertices; i++) 
            adjacency.add(new ArrayList<Integer>());
    }
    
    // method to add an edge to the graph
    public void addEdge(int first_vertex, int second_vertex) {
        adjacency.get(first_vertex).add(second_vertex);
    }
    
    // print indegrees and topological sort of the graph
    public void topologicalSort() {
        
        // create array to store indegrees of each vertex
        int indegree[] = new int[num_vertices];
        
        // populate the indegree array
        for (int i = 0; i < num_vertices; i++) {
            ArrayList<Integer> temp = adjacency.get(i);
            for (int node : temp)
                indegree[node]++;
        }
        
        // print indegree value for each vertex
        for (int i = 0; i < num_vertices; i++) {
            System.out.println("In-degree[" + i + "]:" + indegree[i]);
        }
        
        // create a queue, enqueue all vertices that have indegree 0
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < num_vertices; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
    
        // create a vector to store result of sort
        Vector<Integer> topological_order = new Vector<Integer>();
    
        // while the queue is not empty
        while (!q.isEmpty()) {
            // remove element at the front of the queue 
            // add it to the topological order vector
            int front = q.poll();
            topological_order.add(front);
        
            // iterate through the neighboring nodes of front
            // decrease their indegree by 1
            for (int node : adjacency.get(front)) {
                // if the indegree becomes 0, add it to the queue
                if (--indegree[node] == 0)
                    q.add(node);
            }
            count_visited++;
        }
    
        // check if there was a cycle (no topological sort)
        if (count_visited != num_vertices) {
            System.out.println("No Order:");
            return;
        }
    
        // if no cycle exists, print the topological order
        System.out.print("Order:");
        for (int i = 0; i < num_vertices; i++) {
            if (i == 0)
                System.out.print(topological_order.get(i));
            else
                System.out.print("->" + topological_order.get(i));
        }
        System.out.println();
    }
}

class hw4_3 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // get num of vertices and edges from user
        int num_vertices = sc.nextInt();
        int num_edges = sc.nextInt();
        
        // create graph with the num vertices
        Graph g = new Graph(num_vertices);
        
        // add edges to the graph based on user input values
        for (int i = 0; i < num_edges; i++) {
            int first_vertex = sc.nextInt();
            int second_vertex = sc.nextInt();
            
            g.addEdge(first_vertex, second_vertex);
        }
        
        g.topologicalSort();
        
        sc.close();
    }
    
}