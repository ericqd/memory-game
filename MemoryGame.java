import java.util.*;
import java.util.Scanner;
import java.io.*;

public class MemoryGame{
    public static void main(String[] args){
        String[][] cardValue = new String[4][4]; 
        boolean[][] cardState = new boolean[4][4];
        String fileChoice = getFileChoice();
        String[][] array = readFile(fileChoice,cardValue);
        System.out.println(array);
    }
    public static String getFileChoice(){
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
        try{
            Scanner read = new Scanner(new File(fileChoice));
            while(read.hasNextLine()){
                for( int i = 0; i < array.length; i++){
                    String line = read.nextLine();
                    for(int j = 0; j < array.length; j++){
                        array[i][j] = line;
                    }
                }
            }
            read.close();
        } catch(FileNotFoundException fnf){
            System.out.println("File was not found");
        }
        return array;
    }
}