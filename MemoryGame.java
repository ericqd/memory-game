import java.util.*;
import java.util.Scanner;
import java.io.*;
/**
 * Author: Eric Dao
 * Date:3/2/2020
 */
public class MemoryGame{
    public static void main(String[] args){
        String[][] cardValue = new String[4][4]; 
        boolean[][] cardState = new boolean[4][4];
        String fileChoice = getFileChoice();
        readFile(fileChoice,cardValue);
        shuffleDeck(cardValue);
        int faceDown = 16;
        boolean isTrue = false;
        int userSelection1=0;
        int userSelection2=0;
        while(faceDown > 0){
            while(!isTrue){
                userSelection1 = getChoice(); //asks user for input
                if(!checkFlipped(userSelection1, cardState)){ //if its not flipped 
                    flipChoice(userSelection1,cardState);//flips over the card 
                    displayBoard(cardValue, cardState);//displays board 
                    isTrue = true;//exits loop
                }
                else{
                    System.out.println("Card is already flipped");
                }
            }
            isTrue= false;
    
            while(!isTrue){
                userSelection2 = getChoice();
                if(!checkFlipped(userSelection2,cardState)){
                    flipChoice(userSelection2,cardState);
                    displayBoard(cardValue, cardState);
                    isTrue = true;
                }
                else{
                    System.out.println("Card is already flipped");
                }
            }
            isTrue = false;
            if(isMatch(userSelection1,userSelection2,cardValue)){
                System.out.println("Match!");
                faceDown -=2;
            }
            else if(isMatch(userSelection1,userSelection2,cardValue) == false){
                System.out.println("Don't Match");
                flipChoice(userSelection1,cardState);
                flipChoice(userSelection2,cardState);
            }
        }
}
/**
 * Displays menu for files and asks user for the text file
 * @return fileChoice
 */
    public static String getFileChoice() {
        System.out.println("Memory Game"); //display menu for file choices
        System.out.println("1. Letters");
        System.out.println("2. Numbers");
        System.out.println("3. Animals");
        System.out.println("4. Objects");
        System.out.println("Enter Choice: ");
        int userInput = CheckInput.getIntRange(1,4); //user input
        String fileChoice = "";
        if (userInput == 1){
            fileChoice = "letters.txt";
        }
        else if(userInput == 2){
            fileChoice = "numbers.txt";
        }
        else if(userInput == 3){
            fileChoice = "animals.txt";
        }
        else if(userInput == 4){
            fileChoice = "objects.txt";
        }
        return fileChoice;
    }
    /**
     * Reads the file and places the element in the text file into the 2d array
     * @param fileChoice
     * @param array
     * @return array
     */
    public static String[][] readFile(String fileChoice, String [][] array){
        int row = 4;
        int col = 4;
        try{
            Scanner read = new Scanner(new File(fileChoice)); //declare scanner
            for(int i = 0; i < row && read.hasNextLine(); i++){  // nested for loop to read in file
                for(int j = 0; j < col && read.hasNextLine(); j++){
                    String card = read.nextLine(); //reads element from file
                    array[i][j] = card; //add card to 2d array
                    j++;
                    array[i][j] = card; // add duplicate
                }
            } 
            read.close();
        } catch(FileNotFoundException fnf){
            System.out.println("File was not found");
        }
        return array;
    }
    /**
     * Shuffles the 4x4 array of elements
     * @param array
     * @return array
     */
    public static String[][] shuffleDeck(String [][] array){
        int count = 0;
        Random rand = new Random();
        while(count != 100){ //shuffles cards until reaches 100
            count++;
            for(int i = array.length - 1; i > 0; i-- ){
                for(int j = array[i].length -1 ; j > 0; j--){
                    int m = rand.nextInt(i+1);
                    int n = rand.nextInt(j+1);
                    String temp = array[i][j];
                    array[i][j] = array[m][n]; // swap elements in 2d array
                    array[m][n] = temp;
                }
            }
        }
        //for(int i=0;i < row; i++){
            //for(int j=0; j < col; j++){
                //System.out.print(array[i][j] + " ");
            //}
            //System.out.println();
        //}
        return array;
    }
    /**
     * Displays a 4x4 grid of cards from 1-16 if card is flipped display the value
     * @param array
     * @param array2
     */
    public static void displayBoard(String[][] array, boolean[][] array2){
        int num = 1;
            for(int i = 0; i < 4; i++){
                System.out.println("+- - - -+" + " " + "+- - - -+" + " " + "+- - - -+" + " " + "+- - - -+");
                System.out.println("|       |" + " " + "|       |" + " " + "|       |" + " " + "|       |");
                for(int j = 0; j < 4; j++){
                    String size = Integer.toString(num); // length of number
                    if(array2[i][j]){
                        System.out.print("| " +array[i][j]+ "  |" + " ");
                        num++;
                    }
                    else if(size.length() >=2){//if number is larger than a single digit
                        System.out.print("|   " +(num++)+ "  |" + " ");
                    }
                    else{
                        System.out.print("|   " +(num++)+ "   |" + " ");
                    }
                }
                System.out.println("");
                System.out.println("|       |" + " " + "|       |" + " " + "|       |" + " " + "|       |");
                System.out.println("|       |" + " " + "|       |" + " " + "|       |" + " " + "|       |");
                System.out.println("+- - - -+" + " " + "+- - - -+" + " " + "+- - - -+" + " " + "+- - - -+");

            }
            System.out.println("");
        }
    /**
     * Gets the user input to choose card
     * @return userChoice
     */
    public static int getChoice(){
        System.out.println("Enter choice: ");
        int userChoice = CheckInput.getIntRange(1,16);
        return userChoice;
    }
    public static void flipChoice(int input, boolean[][] array2){
        int row = 0;
        int col = 0;
        //user input conversion(1-16) to a location in the array
        if(input <= 4 && input % 4 != 0){
            row = 0; 
            col = (input % 4) - 1; 
        }
        else if(input > 4 && input < 8 && input % 4 != 0){
            row = 1;
            col = (input % 4) - 1;
        }
        else if (input > 8 && input < 12 && input % 4 != 0){
            row = 2;
            col = (input % 4)-1; 
        }
        else if(input > 12 && input < 16 && input % 4 != 0){
            row = 3;
            col = (input % 4)-1; 
        }
        else if(input == 4){
            row = 0;
            col = 3;
        }
        else if(input == 8){
            row = 1;
            col = 3;
        }
        else if(input == 12){
            row = 2;
            col = 3;
        }
        else if(input == 16){
            row = 3;
            col = 3;
        }
        if(array2[row][col] == false){ //if the location in the array is false flip it to true
            array2[row][col] = true;
        } 
        else{
            array2[row][col] = false; 
        }
    }
    /**
     * Converts both user choice into a location in the card value 2d array and compares the location to see if location is the same
     * @param input1
     * @param input2
     * @param array
     * @return false
     */
    public static Boolean isMatch(int input1, int input2, String[][]array){
        int row = 0;
        int col = 0;
        int row2 = 0;
        int col2 = 0;
        if(input1 <= 4 && input1 % 4 != 0){
            row = 0; 
            col = (input1 % 4) - 1; 
        }
        else if(input1 > 4 && input1 < 8 && input1 % 4 != 0){
            row = 1;
            col = (input1 % 4) - 1;
        }
        else if (input1 > 8 && input1 < 12 && input1 % 4 != 0){
            row = 2;
            col = (input1 % 4)-1; 
        }
        else if(input1 > 12 && input1 < 16 && input1 % 4 != 0){
            row = 3;
            col = (input1 % 4)-1; 
        }
        else if(input1 == 4){
            row = 0;
            col = 3;
        }
        else if(input1 == 8){
            row = 1;
            col = 3;
        }
        else if(input1 == 12){
            row = 2;
            col = 3;
        }
        else if(input1 == 16){
            row = 3;
            col = 3;
        }
        String location1 = array[row][col];
        if(input2 <= 4 && input2 % 4 != 0){
            row2 = 0; 
            col2 = (input2 % 4) - 1; 
        }
        else if(input2 > 4 && input2 < 8 && input2 % 4 != 0){
            row2 = 1;
            col2 = (input2 % 4) - 1;
        }
        else if (input2 > 8 && input2 < 12 && input2 % 4 != 0){
            row2 = 2;
            col2 = (input2 % 4)-1; 
        }
        else if(input2 > 12 && input2 < 16 && input2 % 4 != 0){
            row2 = 3;
            col2 = (input2 % 4)-1; 
        }
        else if(input2 == 4){
            row2 = 0;
            col2 = 3;
        }
        else if(input2 == 8){
            row2 = 1;
            col2 = 3;
        }
        else if(input2 == 12){
            row2 = 2;
            col2 = 3;
        }
        else if(input2 == 16){
            row2 = 3;
            col2 = 3;
        }
        String location2 = array[row2][col2];
        if(location1.equals(location2)){ //compares two locations based on user input and if they are the same return true
            return true;
        }
        return false;
    }
    /**
     * Check if the card the user chooses has already been flipped 
     * @param input
     * @param array2
     * @return false
     */
    public static Boolean checkFlipped(int input,boolean[][] array2){
        int row = 0;
        int col = 0;
        if(input <= 4 && input % 4 != 0){
            row = 0; 
            col = (input % 4) - 1; 
        }
        else if(input > 4 && input < 8 && input % 4 != 0){
            row = 1;
            col = (input % 4) - 1;
        }
        else if (input > 8 && input < 12 && input % 4 != 0){
            row = 2;
            col = (input % 4)-1; 
        }
        else if(input > 12 && input < 16 && input % 4 != 0){
            row = 3;
            col = (input % 4)-1; 
        }
        else if(input == 4){
            row = 0;
            col = 3;
        }
        else if(input == 8){
            row = 1;
            col = 3;
        }
        else if(input == 12){
            row = 2;
            col = 3;
        }
        else if(input == 16){
            row = 3;
            col = 3;
        }
        if(array2[row][col]){
            return true;
        }
        return false;
        }
}