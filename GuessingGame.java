/**
 * The purpose of this game is to selecect a random int.
 * The int will be between 100 and 999.
 * You will need to guess a number between this range.
 * If your number is out of range a message will be displayed stating that your guess is too high or low.
 * You will have 10 attempts.
 * 
 * @author (Tom Meehan)
 * @version (25/02/19)
 * ID Number: 18220975
 */
import java.util.Random;
import javax.swing.JOptionPane;
import java.lang.*;
import java.lang.System;
public class GuessingGame
{
    // create random number generator.
    private static final Random randomNumbers = new Random(); 
    // enumeration with constants that represent the game status
    private enum Status { TRYAGAIN, WON, LOST };
    private int guess;
    private int gameRange;
    private int gamesPlayed;
    public static void main(String[] args)
    {

        Random rand = new Random();
        int gameRange = 100 + randomNumbers.nextInt( 899 );  // creates a random int between 100 and 999
        int gamesPlayed = 0;  // sets games played to 0
        boolean win = false;  // sets win to false 
        int maxAttempts = 10;  // sets the maximum number of attempts to 10
        int guess;  // initialises an int called guess

        String informUserString = String.format("You have 10 attempts to guess a number between 100 and 999 "  ); // informs the user how to play the game 
        JOptionPane.showMessageDialog( null, informUserString, 
            "Would you like to play a game? ", JOptionPane.PLAIN_MESSAGE );  // uses JOptionPane to display message

        while (gamesPlayed < maxAttempts && win == false  ){  // start of while loop
            String guessString = 
                JOptionPane.showInputDialog( "You have 10 attempts to guess a number between 100 and 999 " ); // asks the user to input a number between 100 and 999
            try{  // trys for exception
                guess = Integer.parseInt( guessString ); // Convert String to int 
            }  
            catch (Exception E){  // catch the exception if the user inputs a string
                String stringUserString = String.format("You cannot enter a letter ");
                JOptionPane.showMessageDialog( null, stringUserString, "Please enter a NUMBER!!!", JOptionPane.PLAIN_MESSAGE );
                continue;  // contiues the loop
            }

            if (guess < 100){  // determines if the users guess is below 100
                String lowUserString = String.format("The number cannot be less than 100");
                JOptionPane.showMessageDialog( null, lowUserString,
                    "You have 10 atetmpts to guess a number between 100 and 999", JOptionPane.PLAIN_MESSAGE );
            } 

            else if (guess > 999){  // determines if the users guess is greater than 999
                String highUserString = String.format("The number cannot be greater than 999");
                JOptionPane.showMessageDialog( null, highUserString,
                    "You have 10 atetmpts to guess a number between 100 and 999", JOptionPane.PLAIN_MESSAGE );
            } 

            else if (guess == gameRange){  // determines what happens if the user guesses correctly
                String winUserString = String.format("Congratulations you win!!!");
                JOptionPane.showMessageDialog( null,"Congratulations you win!!!", winUserString, JOptionPane.PLAIN_MESSAGE );
                win = true;
            }

            else if ( guess < gameRange){  // determines what happens if the users guess is less than gameRange 
                String higherUserString = String.format("The answer is less than your input ");
                JOptionPane.showMessageDialog( null, "The number you have entered is too low please try again ", higherUserString, JOptionPane.PLAIN_MESSAGE);
            }
            else if ( guess > gameRange){
                String lowerUserString = String.format("The answer is greater than your input ");
                JOptionPane.showMessageDialog( null, "The number you have entered is too high please try again ", lowerUserString, JOptionPane.PLAIN_MESSAGE);
            }

            else if (gamesPlayed == maxAttempts){  // determines what happens if the user runs out of attempts
                String looseUserString = String.format("You ran out of lives");
                JOptionPane.showMessageDialog( null,"Better luck next time!!!", looseUserString, JOptionPane.PLAIN_MESSAGE );

            }

            gamesPlayed++;  // creates a counter for games played
        }

    
    }

}  
