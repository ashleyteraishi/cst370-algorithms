/* 
Title: hw6_1.java
Abstract: This program collects the maximum number of coins on an n x m board 
Author: Ashley Teraishi
ID: 0917
Date: 12/10/21
*/

import java.util.*;

class hw6_1 {
    
    // global variables
    static int num_rows;
    static int num_cols;
    static int[][] board;
    static int[][] final_result;
    static ArrayList<int[]> backtrack_path;
    
    // method to initialize the final result matrix with zeros
    static void initializeWithZeros() {
        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                final_result[row][col] = 0;
            }
        }
    }
    
    // method to visit the board row by row and identify the
    // number of coins to collect in each cell.
    // the numbers are stored in the final_result matrix
    static void visitBoard() {
        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                int num_from_top = 0;
                int num_from_left = 0;
                
                // find num of coins from the top and left cells
                if (col == 0)
                    num_from_left = 0;
                else 
                    num_from_left = final_result[row][col - 1];
                if (row == 0) 
                    num_from_top = 0;
                else 
                    num_from_top = final_result[row - 1][col];
                
                // compare the num of coins of adjacent cells
                if (num_from_left != 0 || num_from_top != 0) {
                    if (num_from_left >= num_from_top)
                        final_result[row][col] = num_from_left;
                    else
                        final_result[row][col] = num_from_top;
                } 
                
                // if current cell contains a coin, increment final result
                if (board[row][col] == 1)
                    final_result[row][col]++;
            }
        }
    }
    
    // method to backtrack through the final_result matrix 
    // in order to find the optimal path. prioritizing path from the left
    static void backtrack() {
        // first add the final cell to the arraylist
        backtrack_path = new ArrayList<>();
        int[] cell = new int[]{num_rows - 1, num_cols - 1};
        backtrack_path.add(cell);
        
        // initialize row and col to point to the bottom right cell
        int row = num_rows - 1;
        int col = num_cols - 1;
        
        // iterate through the matrix while both row and col are positive
        while (row >= 0 && col >= 0) {
            
            int num_from_top = 0;
            int num_from_left = 0;
            int curr_num = final_result[row][col];
            
            // find num of coins from the top and left cells
            if (col == 0)
                num_from_left = 0;
            else 
                num_from_left = final_result[row][col - 1];
                
            if (row == 0) 
                num_from_top = 0;
            else 
                num_from_top = final_result[row - 1][col];
            
            // if we have reached the leftmost column, only move is up
            if (col == 0 && row > 0) {
                cell = new int[]{row - 1, col};
                backtrack_path.add(cell);
                row--;
            }
            // if we have reached the top row, the only move is left
            else if (row == 0 && col > 0) {
                cell = new int[]{row, col - 1};
                backtrack_path.add(cell);
                col--;
            }
            // if row and col are 0, we have reached the end (top left)
            else if (row == 0 && col == 0) {
                break;
            }
            
            // compare the current num with adjacent nums
            // prioritize nums on the left and nums that are equal to current num
            else if (num_from_left == curr_num) {
                cell = new int[]{row, col - 1};
                backtrack_path.add(cell);
                col--;
            }
            else if (num_from_top == curr_num) {
                cell = new int[]{row - 1, col};
                backtrack_path.add(cell);
                row--;
            }
            // if no adjacent nums are equal to the current num, look for num
            // that is one less than the current num, still prioritizing nums on left
            else if (num_from_left == curr_num - 1) {
                cell = new int[]{row, col - 1};
                backtrack_path.add(cell);
                col--;
            }
            else if (num_from_top == curr_num - 1) {
                cell = new int[]{row - 1, col};
                backtrack_path.add(cell);
                row--;
            }
        }
    }
    
    // method to display the optimal path
    static void displayPath() {
        System.out.print("Path:");
        // iterate through the arraylist backwards to get the correct order
        for (int i = backtrack_path.size() - 1; i >= 0; i--) {
            int[] cell = backtrack_path.get(i);
            // increase the row and col valuese by 1 to get the
            // row and col number rather than index
            cell[0]++;
            cell[1]++;
            
            // print path
            if (i == 0)
                System.out.print("(" + cell[0] + "," + cell[1] + ")");
            else
                System.out.print("(" + cell[0] + "," + cell[1] + ")->");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // get the number of rows and columns from the user
        num_rows = sc.nextInt();
        num_cols = sc.nextInt();
        
        // create board and final_result matrices based on
        // the number of columns and rows entered by the user
        board = new int[num_rows][num_cols];
        final_result = new int[num_rows][num_cols];
        
        // initialize the board matrix with input values
        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                board[row][col] = sc.nextInt();
            }
        }
        
        // initialize the final_result matrix, visit the board, 
        // and find the optimal path using methods
        initializeWithZeros();
        visitBoard();
        backtrack();
        
        // print results
        System.out.println("Max coins:" + final_result[num_rows - 1][num_cols - 1]);
        displayPath();
    }
    
}