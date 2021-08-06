/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.util.logging.*;
import java.io.*;
/**
 * The purpose of this class is to read the numbers in an array from a file.
 * The class then sorts the array in ascending order.
 * The class will also calculate the frequency for each number.
 * The class then creates a new file displaying the scores and the frequency.
 * @author Tom Meehan
 * @version 08/04/19
 */
public class NumberFileExplorer {  // main method
    private static String STR = ""; //the output string
    public static void main(String args[]) {
        int[] numbers = readFile(); //reads file
        sortArr(numbers); //sorts numbers
        STR += calcFreq(numbers); //calculates frequencies and outputs a string 
        writeFile("frequency.txt", STR); //write string to new file
    }
    public static int[] readFile()
    {
        try 
        {
            // An easy way to read text files is to create a scanner to the file
            Scanner input = new Scanner(new File("numbers.txt"));
            
            String desc = input.nextLine(); //String representing the description
            String author = input.nextLine(); //String representing the author
            int[] numbers = new int[input.nextInt()]; // array declariing the set of numbers
            String str = String.format("Tom Meehan 18220975\nJoseph Corbett 18238807 \n\n%s\n%s\n%d scores\n", desc, author,numbers.length); //Output string
            STR += str;
            
            
            int i = 0;
            while( input.hasNext() ) // sets variables inside the array from the file
            {
                numbers[i] = input.nextInt();
                i++;
            }

            // Close the file (actually the scanner and the file it's reading).
            input.close();
            return numbers;
        } catch (FileNotFoundException ex) // Checked exception for File()
            
        {
            System.err.println("No such file: numbers.txt");
            Logger.getLogger(NumberFileExplorer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } // end method
    public static void swap(int[] arr, int num1, int num2) { // swaps 2 elements in an array
        int temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;
    }
    public static void sortArr(int[] arr) { // sorts the array 
        
        for(int i = 0; i<arr.length;i++) {  // sets the smallest value to index 0 
            int smallest = 0;
            for(int j = i; j<(arr.length);j++) {
                if(arr[j] <= arr[smallest]) { 
                    smallest = j; //gets minimum of the unsorted elements
                }
            }
            
            swap(arr,i,smallest); //swaps current index value with next smallest index value
        }
    
    }
    public static String calcFreq(int[] arr) {  // calculates the frequency and outputs it as a string
        
        
        int[] frequency = new int[arr.length];
        String str = "";
        int freqLength = 0;
        boolean display;
        for(int i = 0; i < arr.length; i++  ){
            display = true;
            for(int j = i; j < arr.length; j++ ){
                if (arr[i] == arr[j]) { //if i==j then increments frequency
                    frequency[j]++;
                    if(i < j) { //if there are no more of the same value, i == j. If it finds more, i < j
                        display = false;
                    } 
                }
            }
            if(display) { //if display is true, then output the value and its frequency
                freqLength++;
                str = str + String.format("%6d %6d\n",arr[i], frequency[i]);
                
            }
        }
        str = String.format("%d frequency entries\n%6s %6s\n",freqLength,"Scores","Freq")+ str;
        //System.out.println(str);
        return str;
        
    }
    public static void writeFile(String fileName, String str) // writes the file and creates a new file frequency.txt
    {
        try 
        {
            // An easy way to write to text files is to use a formatter
            java.util.Formatter output;
            
            // Open the formatter to the file fileName
            output = new java.util.Formatter( fileName);
            output.format(str);

            output.close();
        } 
        catch (FileNotFoundException ex)   // Checked exception for Formatter
        {
            System.err.println("Unable to create file: " + fileName);
            Logger.getLogger(NumberFileExplorer.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // end method
}
