/* 
Title: hw6_2.java
Abstract: This program implements Floyd's Algorithm to display all pairs' shortest path.
Author: Ashley Teraishi
ID: 0917
Date: 12/10/21
*/

import java.util.*;

class hw6_2 {
    
    static int num_vertices;
    static int[][] matrix;
    
    // for this assignment, -1 indicates infinity
    static void floyds() {
        
        // one vertex at a time
        for (int i = 0; i < num_vertices; i++) {
            // compare values in the i row and column
            for (int row = 0; row < num_vertices; row++) {
                for (int col = 0; col < num_vertices; col++) {
                    // if the values are not infinity or 0, calculate the sum
                    if (matrix[i][col] != -1 && matrix[row][i] != -1 
                    && matrix[i][col] != 0 && matrix[row][i] != 0) {
                        int sum = matrix[i][col] + matrix[row][i];
                        // if the path is infinity or if the sum is less than the current path
                        if (matrix[row][col] == -1 || sum < matrix[row][col])
                            matrix[row][col] = sum;
                    }
                }
            }
        }
    }
    
    static void displayMatrix() {
        for (int row = 0; row < num_vertices; row++) {
            for (int col = 0; col < num_vertices; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // get num of vertices from user and create the input matrix
        num_vertices = sc.nextInt();
        matrix = new int[num_vertices][num_vertices];
        
        // populate the adjacency matrix with user input
        for (int row = 0; row < num_vertices; row++) {
            for (int col = 0; col < num_vertices; col++) {
                matrix[row][col] = sc.nextInt();
            }
        }
        
        // implement floyd's algorithm and print the shortest paths
        floyds();
        displayMatrix();
    }
    
}