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
        int userChoice = getChoice();

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
        for(int i = 0; i < 4; i++){
            System.out.println("+-------+   +-------+   +-------+   +-------+");
            for(int j = 0; j < 4; j++){
                if(array2[i][j]){
                    System.out.print(array[i][j]);
                    System.out.println(" ");
                }
                else{
                    System.out.print("|" + (i+1) + "      |   ");
                }
            }
            System.out.println();
        }
        System.out.println("+-------+   +-------+   +-------+   +-------+");
    }
    public static int getChoice(){
        int userChoice = CheckInput.getIntRange(1,16);
        return userChoice;
    }
    public static void flipChoice(int input, boolean[][] array2){

    }
}