/* 
Title: hw2_3.java
Abstract: This program checks if an input string is a palindrome or not. 
Author: Ashley Teraishi
ID: 0917
Date: 11/5/21
*/

import java.util.Scanner;

class hw2_3 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // read input string from user
        String input = sc.nextLine();
        
        // remove all white spaces and punctuation. make all chars uppercase
        input = input.replaceAll("\\s+", "");
        input = input.replaceAll("\\p{Punct}", "");
        input = input.toUpperCase();
        
        // print the results of the recursive function. pass the input string, 
        // 0 as the starting index, and string length - 1 for the end index
        System.out.println(isPalindrome(input, 0, input.length() - 1));
    }
    
    /*
    This method is used to recursively check if a string is a palindrome
    Start at the first and last chars of the string, if they do not match then 
    return false, otherwise, recursively call the method again but shift
    the front and end indexes one unit closer to the center of the string
    @param str This is the first parameter, it specifies the string to be checked
    @param frontIndex This is the second parameter, it specifies the current front index
    @param endIndex This is the third parameter, it specifies the currend end index
    @return String that contains either "TRUE" or "FALSE" to be printed to the user
    */
    static String isPalindrome(String str, int frontIndex, int endIndex) {
        
        // if the string is only 1 char, or if the center of the string 
        // has been reached, the string is a palindrome
        if (frontIndex == endIndex || frontIndex > endIndex) 
            return "TRUE";
            
        // if the front and end characters are not the same,
        // then the string is not a palindrome
        if (str.charAt(frontIndex) != str.charAt(endIndex)) 
            return "FALSE";
            
        // otherwise, call the function again. increase the front index by 1
        // and decrease the end index by 1 until they meet in the middle.
        return (isPalindrome(str, frontIndex + 1, endIndex - 1));
    }
}