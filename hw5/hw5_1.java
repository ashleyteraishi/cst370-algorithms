/* 
Title: hw5_1.java
Abstract: This program conducts heap operations.
Author: Ashley Teraishi
ID: 0917
Date: 12/02/21
*/

import java.util.*;

class hw5_1 {
    
    // create arraylist to store heap data
    // using arraylist instead of array to easily add new nodes
    static ArrayList<Integer> heap = new ArrayList<>();
    
    // variables
    static int left_child_index = Integer.MAX_VALUE;
    static int left_child_value = Integer.MAX_VALUE;
    static int right_child_index = Integer.MAX_VALUE;
    static int right_child_value = Integer.MAX_VALUE;
    static int current_node_value = Integer.MAX_VALUE;
    
    // method to check if the arraylist is a max heap,
    // if not, make it into a max heap
    static void isMaxHeap() {
        // check if the value of each non-leaf node is >= its children
        for (int i = ((heap.size() - 1) / 2); i >= 1; i--) {
            
            // get the indexes of the left and right child nodes
            left_child_index = i * 2;
            right_child_index = (i * 2) + 1;
            
            // get the value of i
            current_node_value = heap.get(i);
            
            // get the values of the left child node
            left_child_value = heap.get(left_child_index);
            
            // check if the right child exists
            if (right_child_index < heap.size()) {
                right_child_value = heap.get(right_child_index);
                // check if i is less than it's childrens' values
                // if so, then the list is NOT a max heap, make it a max heap
                if (current_node_value < left_child_value || current_node_value < right_child_value) {
                    System.out.println("This is NOT a heap.");
                    createMaxHeap();
                    break;
                }
                // else, the list IS a max heap
                else {
                    System.out.println("This is a heap.");
                    break;
                }
            }
            else {
                // check if i is less than the left child's value
                // if so, then the list is NOT a max heap, make it a max heap
                if (current_node_value < left_child_value) {
                    System.out.println("This is NOT a heap.");
                    createMaxHeap();
                    break;
                }
                // else, the list IS a max heap
                else {
                    System.out.println("This is a heap.");
                    break;
                }
            }
            
        }
    }
    
    // method to create max heap from a list
    static void createMaxHeap() {
        // iterate through all non-leaf nodes, starting at the last non-leaf
        for (int i = ((heap.size() - 1) / 2); i >= 1; i--) {
            maxHeapify(i);
        } 
    }
    
    // helper method for createMaxHeap, swaps nodes that are out of order
    static void maxHeapify(int node) {
        // get the indexes of the left and right child nodes
        left_child_index = node * 2;
        right_child_index = (node * 2) + 1;
        
        // get the value of the current node
        current_node_value = heap.get(node);
        
        // check if the left child node exists
        if (left_child_index < heap.size() && right_child_index >= heap.size()) {
            // get the values of the left child node
            left_child_value = heap.get(left_child_index);
            
            // if the current node value is less than the left child's value
            // swap the current node with the left node
            if (current_node_value < left_child_value) {
                Collections.swap(heap, node, left_child_index);
                maxHeapify(left_child_index);
            }
        }
        // check if the right child node exists
        else if (left_child_index < heap.size() && right_child_index < heap.size()) {
            // get the values of the left child node
            left_child_value = heap.get(left_child_index);
            
            right_child_value = heap.get(right_child_index);
            // if the current node value is less than it's childrens' values, 
            // swap the current node with the bigger child
            if (left_child_value > right_child_value && current_node_value < left_child_value) {
                Collections.swap(heap, node, left_child_index);
                maxHeapify(left_child_index);
            }
            else if (right_child_value > left_child_value && current_node_value < right_child_value) {
                Collections.swap(heap, node, right_child_index);
                maxHeapify(right_child_index);
            }
        }
        // else, no swaps are necessary
    }
    
    // method to display the max value
    static void displayMax() {
        // the root node (index = 1) is the max
        System.out.println(heap.get(1));
    }
    
    // method to insert a new node
    static void insertNode(int node) {
        // add the insert value to the end of the heap
        heap.add(node);
        
        // create max heap
        createMaxHeap();
    }
    
    // method to delete the max value
    static void deleteMax() {
        // get the last leaf node index and value
        int last_leaf_index = heap.size() - 1;
        int last_leaf_value = heap.get(last_leaf_index);
        
        // replace root node value (index = 1) with the last leaf value
        heap.set(1, last_leaf_value);
        heap.remove(last_leaf_index);
        
        // create max heap
        createMaxHeap();
    }
    
    // method to display the heap
    static void displayHeap() {
        for (int i = 1; i < heap.size(); i++) {
            System.out.print(heap.get(i) + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int num_nodes = sc.nextInt();
        
        // first value of arraylist should be null (index 0)
        heap.add(null);
        
        // temp variable to store input values
        int input = Integer.MAX_VALUE;
        
        // get input values and add them to the heap arraylist
        for (int i = 0; i < num_nodes; i++) {
            input = sc.nextInt();
            heap.add(input);
        }
        
        int num_commands = sc.nextInt();
        sc.nextLine();
        
        // create arraylist to store input commands to be executed
        ArrayList<String> commands = new ArrayList<>();
        
        // temp variable to store input commands
        String command = "";
        
        // get input commands and add them to the commands arraylist
        for (int i = 0; i < num_commands; i++) {
            command = sc.nextLine();
            commands.add(command);
        }
        
        // first check if the numbers are a max heap
        isMaxHeap();
        
        // execute input commands
        for (int i = 0; i < num_commands; i++) {
            // get command from the commands list
            command = commands.get(i);
            
            // string tokenizer for "insert ... commands"
            StringTokenizer st = new StringTokenizer(command);
            
            if (st.countTokens() == 2) {
                String insert_command = st.nextElement().toString();
                int insert_value = Integer.parseInt(st.nextElement().toString());
                insertNode(insert_value);
            }
        
            // switch statement to execute other commands
            switch (command) {
                case  "displayMax":
                    displayMax();
                    break;
                case "deleteMax":
                    deleteMax();
                    break;
                case "display":
                    displayHeap();
                    break;
            }
        }
    }
    
}
