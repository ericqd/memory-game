import java.util.*;
import java.util.Scanner;
import java.io.*;

public class MemoryGame{
    public static void main(String[] args){
        String[][] cardValue = new String[4][4]; 
        boolean[][] cardState = new boolean[4][4];
        String fileChoice = getFileChoice();
        readFile(fileChoice,cardValue);
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
                }
            } 
            read.close();
        } catch(FileNotFoundException fnf){
            System.out.println("File was not found");
        }
        for(int i=0;i < row; i++){
            for(int j=0; j < col; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        return array;
    }
}