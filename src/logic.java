package src;
import java.util.Scanner;
import java.util.Queue;
import java.util.Random;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ArrayList;

public class logic {

    protected static char[] table = {'1','2','3','4','5','6','7','8','9'};

    protected static Queue<Integer> playerMoveTracker = new LinkedList<Integer>();
    protected static Queue<Integer> computerMoveTracker = new LinkedList<Integer>();
    
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

    public static void playerMove(Scanner scanner, char player) {
        System.out.println("Your Turn What is Your Next Move?");
        System.out.print("[+] ");
        
        int move = -1;
        boolean validInput = false;
        
        do {
            try {
                move = scanner.nextInt();
                scanner.nextLine();
                if (move > 0 && move <= table.length && !(table[move - 1] == 'X' || table[move - 1] == 'O')) {
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
        playerMoveTracker.offer(move - 1);

        if(playerMoveTracker.size() == 4) {
            int temp = playerMoveTracker.poll();
            table[temp] = Integer.toString(temp + 1).charAt(0);
        }
    }

    public static void computerMove(char computer) {
        ArrayList<Integer> available = new ArrayList<Integer>();
        for (char move : table) {
            System.out.println(table);
            if(!(move == 'X' || move == 'O')) {
                available.add(Character.getNumericValue(move) - 1);
            }
        }

        Random rand = new Random();
        int position = available.get(rand.nextInt(0, available.size()));

        table[position] = computer;
        computerMoveTracker.offer(position);

        if(computerMoveTracker.size() == 4) {
            int temp = computerMoveTracker.poll();
            table[temp] = Integer.toString(temp + 1).charAt(0);
        }

        System.out.println(available);
        generateTable(table);
        System.out.println("Computer Choosed: " + (position + 1));
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
