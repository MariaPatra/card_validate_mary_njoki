/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card_validate_mary;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author master
 */
public class Card_validate_Mary {

   
    public static void main(String a[]) {
        //scanner object
        
        
        Scanner input = new Scanner(System.in);
        //variable to store the users card number
        String cardNumber = "";
        //fetch the input of the card number
        System.out.println("Please enter your card number. e.g, 10006-12342-00081-99993");
        cardNumber = input.nextLine();
        //card verification method
        validateCardNumber(cardNumber);
    }

    public static void validateCardNumber(String cardNumber) {
        boolean validCardStatus = true;
        //  validate card number
        
        //checks if the card number is an integer
//        String text = "abc"; 
//        String number; 
//        if (Pattern.matches("[a-zA-Z]+", text) == false && text.length() > 2) {
//    number = text; 
//}
        //check if the card has dash or spaced card number sets
        
        if (cardNumber.contains(" ") || cardNumber.contains("-") && !(cardNumber.contains(" ") && cardNumber.contains("-"))) {
            //split the card number into four sets
            String cardNumberSets[] = new String[4];
            //card has spaces
            if (cardNumber.contains(" ")) {
                cardNumberSets = cardNumber.split(" ");
            }
            //card has dashes 
            if (cardNumber.contains("-")) {
                cardNumberSets = cardNumber.split("-");
            }
            //validate whether the card has four sets of numbers
            if (cardNumberSets.length != 4) {
                System.out.println("Invalid card number. Please type all the card numbers correctly.");
                validCardStatus = false;
                return;
            }
            //proceed to loop the four sets validating each set at a time.
            for (int i = 0; i < 4; i++) {
                //get the card set
                String currentCardNumberSet = cardNumberSets[i];
                String originalSetNumber = currentCardNumberSet;
                //check the card set size. must be five digits
                if (currentCardNumberSet.length() != 5) {
                    System.out.println("Invalid card number. You have entered an invalid card number. Please try again.");
                    validCardStatus = false;
                    break;
                }
                //checkSumValue expected after summation
                int checkSumValue = Integer.parseInt("" + (currentCardNumberSet.toCharArray())[4]);
                //pick the first four digits
                currentCardNumberSet = "" + (currentCardNumberSet.toCharArray())[0] + (currentCardNumberSet.toCharArray())[1] + (currentCardNumberSet.toCharArray())[2] + (currentCardNumberSet.toCharArray())[3];
                int cardNumberSetValue;
                //check if they are all integers
                try {
                    cardNumberSetValue = Integer.parseInt(currentCardNumberSet);
                    // is an integer!
                } catch (NumberFormatException e) {
                    System.out.println("Invalid card number. please input number. Please try again.");
                    validCardStatus = false;
                    break;
                    // not an integer!
                }
                //convert the set to octal value
                String cardNumberOctalValue = Integer.toOctalString(cardNumberSetValue);
                //get the summation of the digits
                int sum = Integer.parseInt(octalSummation(cardNumberOctalValue));
                //validate the card
                //new correct set number is
                String newCardNumberSet = currentCardNumberSet + sum;
                if (!(newCardNumberSet.trim().equals(originalSetNumber))) {
                    validCardStatus = false;
                    break;
                }

            }
            if (validCardStatus) {
                System.out.println("Bravo! Your Card Number is valid.");
            } else {
                System.out.println("Sorry! The card Number that you entered is invalid. Please try again.");
            }

        } else {
            validCardStatus = false;
            //invalid card number
            System.out.println("You have entered a wrong card format");
        }

    }

    public static String octalSummation(String octalDigits) {
        int sum = 0;
        for (int y = 0; y < octalDigits.toCharArray().length; y++) {
            sum = sum + Integer.parseInt("" + (octalDigits.toCharArray())[y], 8);
        }
        String newSum = Integer.toOctalString(sum);

        if (newSum.toCharArray().length > 1) {
            newSum = octalSummation(newSum);
        }
        return newSum;
    }

}
