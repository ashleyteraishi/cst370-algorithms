/* 
Title: hw2_1.java
Abstract: This program reads two timestamps of two events from a 
user and displays the difference between the two timestamps. 
Author: Ashley Teraishi
ID: 0917
Date: 11/5/21
*/

import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class hw2_1 {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // get the input for the two timestamps, save as LocalTime variables
        LocalTime t1 = LocalTime.parse(sc.nextLine());
        LocalTime t2 = LocalTime.parse(sc.nextLine());
        
        // use ChronoUnit to find the difference for hours, minutes, and seconds
        long hours = ChronoUnit.HOURS.between(t1, t2);
        long mins = ChronoUnit.MINUTES.between(t1, t2) % 60;
        long secs = ChronoUnit.SECONDS.between(t1, t2) % 60;
        
        // if secs is a negative number, add 60 to find the true difference
        // and subtract one from mins
        if (secs < 0) {
            secs += 60;
            mins -= 1;
        }
        // if mins is a negative number, add 60 to find the true difference
        // and subtract one from hours
        if (mins < 0) {
            mins += 60;
            hours -= 1;
        }
        // if hours is a negative number, add 24 to find the true difference
        if (hours < 0) {
            hours += 24;
        }
        
        // format the results to display the difference as a string (HH:MM:SS)
        String diff = String.format("%02d:%02d:%02d", hours, mins, secs);
        System.out.println(diff);
        
        // close the scanner
        sc.close();
    }
    
}