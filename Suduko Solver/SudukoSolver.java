import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class SudukoSolver{
    public static void main(String[] args) {
        SudukoSolver sudukoSolver = new SudukoSolver();
        sudukoSolver.setUp();
    }
    static int[][] suduko = new int[9][];
    public  void setUp(){
       
        for(int i=0;i<9;i++){
            suduko[i] = new int[9];
            for(int j=0;j<9;j++){
                suduko[i][j] = 0;
            }
        }
        printSuduko();
      userInput();
    }
   static Scanner in = new Scanner(System.in);
   static int row;
static int column;
    public void userInput(){
       
        System.out.println("Please enter number index to set number");
        row = in.nextInt();
       /* if(row==100){
            autoSolver();
            printSuduko();
            return;
        }*/
        column = in.nextInt();
        if(row>9||row<1||column>9||column<1){
            System.out.println("Please enter correct index");
            userInput();
        }
       numberInput();
       
    }
    static int number;
    public void numberInput(){
        System.out.println("Please enter number for the given index ");
        number =in.nextInt();
       
        if(number>9 || number<1){
            System.out.println("The number must be inbetween 1 to 9");
            numberInput();
        }
        suduko[row-1][column-1] = number;
        if(isValidSuduko()){
            printSuduko();
            System.out.println("You entered correct number in correct index");
            userInput();
        }else{
            suduko[row-1][column-1] = 0;
            System.out.println("You enter number not make valid suduko");
            printSuduko();
            userInput();
        }
    }
    public void printSuduko(){
         for(int[] k : suduko){
            System.out.println(Arrays.toString(k));
        }
    }
    ArrayList<Integer> rows = new ArrayList<>();
    ArrayList<Integer> columns = new ArrayList<>();
    ArrayList<Integer> box3 = new ArrayList<>();
    public boolean isValidSuduko(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(suduko[i][j] !=0){
                    if(rows.contains(suduko[i][j])){
                        rows.clear();
                        columns.clear();
                        //box3.clear();
                        return false;
                    }else{
                        rows.add(suduko[i][j]);
                    }
                }
                if(suduko[j][i] !=0){
                    if(columns.contains(suduko[j][i])){
                        rows.clear();
                        columns.clear();
                        //box3.clear();
                        return false;
                    }else{
                        columns.add(suduko[j][i]);
                    }
                }
            }
            rows.clear();
            columns.clear();
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<3;j++){
                if(suduko[i][j] !=0){
                    if(rows.contains(suduko[i][j])){
                        rows.clear();
                        columns.clear();
                        box3.clear();
                        return false;
                    }else{
                        rows.add(suduko[i][j]);
                    }
                }
                if(suduko[i][j+3] !=0){
                    if(suduko[i][j+3] !=0){
                        if(columns.contains(suduko[i][j+3])){
                            rows.clear();
                            columns.clear();
                            box3.clear();
                            return false;
                        }else{
                            columns.add(suduko[i][j+3]);
                        }
                    }
                }
                    if(suduko[i][j+6] !=0){
                        if(box3.contains(suduko[i][j+6])){
                            rows.clear();
                            columns.clear();
                            box3.clear();
                            return false;
                        }else{
                            box3.add(suduko[i][j+6]);
                        }
                    }
            }
            if(i==2||i==5){
                rows.clear();
                columns.clear();
                box3.clear();
            }
        }
        rows.clear();
        columns.clear();
        box3.clear();
        return true;
    }
    public void autoSolver(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(suduko[i][j]==0){
                    for(int k=1;k<=9;k++){
                        suduko[i][j]=k;
                        if(isValidSuduko()){
                            suduko[i][j]=k;
                            break;
                        }else{
                            suduko[i][j]=0;
                        }
                    }
                }
            }
        }
    }
}