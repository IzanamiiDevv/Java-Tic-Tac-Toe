package src;
import java.util.Scanner;
import java.util.Queue;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class logic {

    public static Queue<Integer> playerMoveTracker = new LinkedList<Integer>();
    public static Queue<Integer> computerMoveTracker = new LinkedList<Integer>();
    
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

    public static void playerMove(Scanner scanner, char[] table, char player) {
        System.out.println("Your Turn What is Your Next Move?");
        System.out.print("[+] ");
        
        int move = -1;
        boolean validInput = false;
        
        do {
            try {
                move = scanner.nextInt();
                scanner.nextLine();
                if (move > 0 && move <= table.length && table[move - 1] == ' ') {
                    validInput = true;
                } else {
                    System.out.println("Track is occupied or invalid. Choose another location!");
                    System.out.print("[+] ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be a number between 1 - 9");
                System.out.print("[+] ");
                scanner.nextLine();
            }
        } while (!validInput);

        table[move - 1] = player;
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
