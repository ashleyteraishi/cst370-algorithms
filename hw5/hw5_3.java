/* 
Title: hw5_3.java
Abstract: This program simulates the operations of linear probing.
Author: Ashley Teraishi
ID: 0917
Date: 12/02/21
*/

import java.util.*;

class hw5_3 {
    
    // global variables
    static int size;
    static HashMap<Integer, Integer> hm = new HashMap<>();
    
    // populates the hashmap according to the initial size, values are null
    static void createHashMap() {
        for (int i = 0; i < size; i++) {
            hm.put(i, null);
        }
    }
    
    static void insert(int value) {
        // find the number of populated keys
        int num_keys = 1;
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if (entry.getValue() != null)
                num_keys++;
        }
        
        // calculate the load factor using the number of 
        // populated keys / the size of the table
        float load_factor = (float)num_keys / size;
        
        // if the load factor becomes > 0.5 then rehashing is needed
        if (load_factor > 0.5) {
            rehash();
        }
        // use the hash fcn (key % size) to insert using linear probing
        int index = value % size;
        
        // if the index is not already populated, insert the value
        if (hm.get(index) == null)
            hm.put(index, value);
        // else, find the next empty index and insert the value
        else {
            while (hm.get(index) != null) {
                index++;
            }
            hm.put(index, value);
        }
    }
    
    static void displayStatus(int value) {
        // if the key at index value is null, print empty
        if (hm.get(value) == null) 
            System.out.println("Empty");
        // else, print the key
        else
            System.out.println(hm.get(value));
    }
    
    static void tableSize() {
        System.out.println(hm.size());
    }
    
    static void search(int value) {
        if (hm.containsValue(value))
            System.out.println(value + " Found");
        else
            System.out.println(value + " Not found");
    }
    
    static void rehash() {
        // find the first prime number after 2 * size
        int new_size = size * 2;
        
        // for this assignment we can assume table size is less than 200
        // update the size variable to equal the next prime number
        size = findNextPrime(new_size, 200);
        
        // create arraylist to store keys to be rehashed 
        ArrayList<Integer> keys = new ArrayList<>();
        
        // iterate through existing hash table and get keys
        // add keys to an array list to be entered into the rehashed table
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if (entry.getValue() != null) {
                keys.add(entry.getValue());
            }
        }
        
        // create new empty hash table with the updated size
        createHashMap();
        
        // use the new hash function to rehash previously placed keys
        for (int i = 0; i < keys.size(); i++) {
            insert(keys.get(i));
        }
    }
    
    // helper method for rehash to find the next prime number
    static int findNextPrime(int start, int end) {
        int prime = 0;
        // iterate ints from start to end values
        for (int n = start; n < end; n++) {
            boolean isPrime = true;
            int i = 2;
            while (i <= n / 2) {
                // checking if i is a multiple, if so then n is not prime
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
                i++;
            }
            if (isPrime) {
                prime = n;
                break;
            }
        }
        return prime;
    }
    
    // helper method for debugging
    static void displayTable() {
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        size = sc.nextInt();
        int num_commands = sc.nextInt();
        sc.nextLine();
        
        // create hashmap to serve as our hashtable
        createHashMap();
        
        // create arraylist to store input commands to be executed
        ArrayList<String> commands = new ArrayList<>();
        
        // temp variable to store input commands
        String command = "";
        
        // get input commands and add them to the commands arraylist 
        for (int i = 0; i < num_commands; i++) {
            command = sc.nextLine();
            commands.add(command);
        }
        
        // execute input commands
        for (int i = 0; i < num_commands; i++) {
            // get command from the commands list
            command = commands.get(i);
            
            // string tokenizer for "insert ... commands"
            StringTokenizer st = new StringTokenizer(command);
            
            // if there are two elements in the string (command + value)
            if (st.countTokens() == 2) {
                // get the command and value
                command = st.nextElement().toString();
                int value = Integer.parseInt(st.nextElement().toString());
                
                // switch statement to execute commands
                switch(command) {
                    case "insert":
                        insert(value);
                        break;
                    case "displayStatus":
                        displayStatus(value);
                        break;
                    case "search":
                        search(value);
                        break;
                }
                
            }
            else {
                tableSize();
            }
        }
    }
    
}