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
        displayBoard(cardValue, cardState);
        String userSelection1 = getChoice(cardValue);
        String userSelection2= getChoice(cardValue);
        System.out.println(userSelection1);
        System.out.println(userSelection2);

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
        int num = 0;
        while(num != 16){
            for(int i = 0; i < 4; i++){
                System.out.println("+----+   +----+   +----+   +----+");
                for(int j = 0; j < 4; j++){
                    if(array2[i][j]){
                        System.out.print(array[i][j]);
                        System.out.println(" ");
                    }
                    else{
                        num++;
                        System.out.print("|   " +(num)+ " | ");
                    }
                }
                System.out.println();
            }
            System.out.println("+----+   +----+   +----+   +----+");
        }
    }
    public static String getChoice(String[][] array){
        System.out.println("Enter choice: ");
        int userChoice = CheckInput.getIntRange(1,16);
        int row = 0;
        int col = 0;
        if(userChoice <= 4 && userChoice % 4 != 0){
            row = 0; 
            col = (userChoice % 4) - 1; 
        }
        else if(userChoice > 4 && userChoice < 8 && userChoice % 4 != 0){
            row = 1;
            col = (userChoice % 4) - 1;
        }
        else if (userChoice > 8 && userChoice < 12 && userChoice % 4 != 0){
            row = 2;
            col = (userChoice % 4)-1; 
        }
        else if(userChoice > 12 && userChoice < 16 && userChoice % 4 != 0){
            row = 3;
            col = (userChoice % 4)-1; 
        }
        else if(userChoice == 4){
            row = 0;
            col = 3;
        }
        else if(userChoice == 8){
            row = 1;
            col = 3;
        }
        else if(userChoice == 12){
            row = 2;
            col = 3;
        }
        else if(userChoice == 16){
            row = 3;
            col = 3;
        }
        String location = array[row][col];
        return location;
    }
    public static void flipChoice(String selection1, String selection2, boolean[][] array2, String[][] array, boolean flip){
        for(int i = 0; i < array2.length; i++){
            for(int j = 0; j < array2[i].length ;j++ ){
                array2[i][j] = false;
            }
        }
        
    }
    public static boolean isMatch(String selection1, String selection2, String[][]array){
        boolean match = false;
        if(selection1 == selection2){
            match = true;
        }
        return match;
    }
}