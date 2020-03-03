import java.util.*;
import java.util.Scanner;
import java.io.*;

public class MemoryGame{
    public static void main(String[] args){
        String[][] cardValue = new String[4][4]; 
        boolean[][] cardState = new boolean[4][4];
        String fileChoice = getFileChoice();
        readFile(fileChoice,cardValue);
        shuffleDeck(cardValue);
        int userSelection1 = getChoice();
        flipChoice(userSelection1,cardValue,cardState, true);
        displayBoard(cardValue, cardState);
        int userSelection2= getChoice();
        flipChoice(userSelection2,cardValue,cardState,true);
        displayBoard(cardValue, cardState);
        boolean match = isMatch(userSelection1,userSelection2,cardValue);
        int faceDown = 16;
        while(faceDown > 16)
            if(match == true){
                System.out.println("Match!");
                faceDown -=2;
            }   
}



    public static String getFileChoice() {
        System.out.println("Memory Game");
        System.out.println("1. Letters");
        System.out.println("2. Numbers");
        System.out.println("3. Animals");
        System.out.println("4. Objects");
        System.out.println("Enter Choice: ");
        int userInput = CheckInput.getIntRange(1,4);
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
    public static String[][] readFile(String fileChoice, String [][] array){
        int row = 4;
        int col = 4;
        try{
            Scanner read = new Scanner(new File(fileChoice));
            for(int i = 0; i < row && read.hasNextLine(); i++){
                for(int j = 0; j < col && read.hasNextLine(); j++){
                    String card = read.nextLine();
                    array[i][j] = card;
                    j++;
                    array[i][j] = card;
                }
            } 
            read.close();
        } catch(FileNotFoundException fnf){
            System.out.println("File was not found");
        }
        return array;
    }
    public static String[][] shuffleDeck(String [][] array){
        int count = 0;
        int row = 4;
        int col = 4;
        Random rand = new Random();
        while(count != 100){
            count++;
            for(int i = array.length - 1; i > 0; i-- ){
                for(int j = array[i].length -1 ; j > 0; j--){
                    int m = rand.nextInt(i+1);
                    int n = rand.nextInt(j+1);
                    String temp = array[i][j];
                    array[i][j] = array[m][n];
                    array[m][n] = temp;
                }
            }
        }
        for(int i=0;i < row; i++){
            for(int j=0; j < col; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        return array;
    }
    public static void displayBoard(String[][] array, boolean[][] array2){
        int num = 1;
            for(int i = 0; i < 4; i++){
                System.out.println("+- - - -+" + " " + "+- - - -+" + " " + "+- - - -+" + " " + "+- - - -+");
                System.out.println("|       |" + " " + "|       |" + " " + "|       |" + " " + "|       |");
                for(int j = 0; j < 4; j++){
                    String size = Integer.toString(num);
                    if(array2[i][j]){
                        System.out.print("| " +array[i][j]+ "  |" + " ");
                        num++;
                    }
                    else if(size.length() >=2){
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
    public static int getChoice(){
        System.out.println("Enter choice: ");
        int userChoice = CheckInput.getIntRange(1,16);
        // String location = array[row][col];
        return userChoice;
    }
    public static void flipChoice(int input,String[][] array, boolean[][] array2, boolean flip){
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
        array2[row][col] = true;
        if(array2[row][col] == false){
            array2[row][col] = flip;
        }
    }
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
        boolean match = false;
        if(location1 == location2){
            match = true;
        }
        else{
            System.out.println("Don't Match");
        }
        return match;
    }
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
        boolean location = array2[row][col];
        if(location){
            return true;
        }
        return false;
        }
        //if(checkFlipped(input,array2) == true){
           // System.out.println("Card is already flipped");
           // flipChoice(input,array,array2,flip);
}