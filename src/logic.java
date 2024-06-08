package src;
import java.util.ArrayList;
import java.util.Scanner;

public class logic {

    public static ArrayList<Integer> playerMoveTracker = new ArrayList<Integer>();
    public static ArrayList<Integer> computerMoveTracker = new ArrayList<Integer>();


    public static void generateTable(char[] moves) {
        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println(String.format("|   %c   |   %c   |   %c   |",moves[0],moves[1],moves[2]));
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println(String.format("|   %c   |   %c   |   %c   |",moves[3],moves[4],moves[5]));
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println(String.format("|   %c   |   %c   |   %c   |",moves[6],moves[7],moves[8]));
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
    }

    public static void playerMove(Scanner scanner, char[] table) {
        
    }

    public static void computerMove(char[] table) {

    }

    public static boolean checkWinner(char[] spaces,char player) {
        int[][] pattern = {
            {0,1,2},
            {3,4,5},
            {6,7,8},

            {0,3,6},
            {1,4,7},
            {2,5,8},

            {0,4,8},
            {2,4,6}
        };

        for (int i = 0; i < 8; i++) {
            int a = pattern[i][0];
            int b = pattern[i][1];
            int c = pattern[i][2];

            if(spaces[a] != ' ' && spaces[a] == spaces[b] && spaces[a] == spaces[c]) {
                if(spaces[a] == player) {
                    System.out.println("Player Won!");
                }else {
                    System.out.println("Computer Won!");
                }

                return true;
            }
        }

        return false;
    }
}
