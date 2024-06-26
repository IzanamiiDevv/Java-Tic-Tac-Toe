package src;
import java.util.Scanner;
import src.Style.Colors;
import java.util.Queue;
import java.util.Random;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;

public class logic {

    protected static char[] table = {'1','2','3','4','5','6','7','8','9'};

    protected static Queue<Integer> playerMoveTracker = new LinkedList<>();
    protected static Queue<Integer> computerMoveTracker = new LinkedList<Integer>();

    protected static class ComputerAI {

        public char computer;
        public int moved;
        public String difficulty;
        public static int playerMove;
        public int recentMove;
        private static ArrayList<Integer> playerMoves = new ArrayList<>();

        public ComputerAI(char computer, String difficulty) {
            this.computer = computer;
            this.difficulty = difficulty;
        }
        
        protected void calculateTurn(){
            //Get Available Moves
            ArrayList<Integer> available = new ArrayList<Integer>();
            for (char move : table) {
                if(!(move == 'X' || move == 'O')) {
                    available.add(Character.getNumericValue(move) - 1);
                }
            }

            int position = -1;

            if(difficulty == "easy"){
                Random rand = new Random();
                position = available.get(rand.nextInt(0, available.size()));
            }else {
                position = calculateADVTurn(available, table);
            }

            this.moved = position;

            table[position] = computer;
            computerMoveTracker.offer(position);
        }

        private int calculateADVTurn(ArrayList<Integer> moves, char[] spaces) {
            int playerRecentMove = playerMove;
            int playerWillLost = -1;
            if(playerMoveTracker.size() > 3) playerWillLost = playerMoveTracker.peek();
            int[][] pattern = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
            if(playerWillLost != -1) playerMoves.removeFirst();
            playerMoves.add(playerRecentMove);

            ArrayList<int[]> enemyPossiblemoves = filterSubArrays(pattern, playerMoves);
            ArrayList<Integer> shouldIGo = calculate(enemyPossiblemoves, moves);

            if(shouldIGo.isEmpty()){
                Random rand = new Random();
                return moves.get(rand.nextInt(0, moves.size()));
            }

            Random rand = new Random();
            return shouldIGo.get(rand.nextInt(0, shouldIGo.size()));
        }

        private ArrayList<int[]> filterSubArrays(int[][] x, ArrayList<Integer> y) {
            ArrayList<int[]> z = new ArrayList<>();
            outerLoop:
            for (int[] subArray : x) {
                for (int value : y) {
                    boolean contains = false;
                    for (int element : subArray) {
                        if (element == value) {
                            contains = true;
                            break;
                        }
                    }
                    if (!contains) {
                        continue outerLoop;
                    }
                }
                z.add(subArray);
            }
            
            return z;
        }

        private ArrayList<Integer> calculate(ArrayList<int[]> possiblemove, ArrayList<Integer> available) {
            HashSet<Integer> temp = new HashSet<>();
            ArrayList<Integer> result = new ArrayList<>();
            
            possiblemove.forEach((int[] items) -> {
                for(int item : items) {
                    temp.add(item);
                }
            });
            
            for (Integer number : available) {

                if (temp.contains(number)) {
                    result.add(number);
                }
            }
            return result;
        }
        
    }
    
    protected static void generateTable(char[] track) {
        String[] moves = new String[track.length];
        
        for (int i = 0; i < track.length; i++) moves[i] = String.valueOf(track[i]);

        if(playerMoveTracker.size() == 3) moves[playerMoveTracker.peek()] = Style.ChangeColor(moves[playerMoveTracker.peek()], Colors.RED);

        if (computerMoveTracker.size() == 3) moves[computerMoveTracker.peek()] = Style.ChangeColor(moves[computerMoveTracker.peek()], Colors.RED);

        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println(String.format("|   %s   |   %s   |   %s   |",moves[0],moves[1],moves[2]));
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println(String.format("|   %s   |   %s   |   %s   |",moves[3],moves[4],moves[5]));
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println(String.format("|   %s   |   %s   |   %s   |",moves[6],moves[7],moves[8]));
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
    }

    protected static void playerMove(Scanner scanner, char player) {
        if(playerMoveTracker.size() == 3) System.out.println("Next Move you will  Lose: " + (playerMoveTracker.peek() + 1));

        if(computerMoveTracker.size() == 3) System.out.println("Next Move Computer will  Lose: " + (computerMoveTracker.peek() + 1));

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
        ComputerAI.playerMove = move - 1;

        if(playerMoveTracker.size() == 4) {
            int temp = playerMoveTracker.poll();
            table[temp] = Integer.toString(temp + 1).charAt(0);
            
        }
    }

    protected static void computerMove(ComputerAI computer) {
        computer.calculateTurn();
        if(computerMoveTracker.size() == 4) {
            int temp = computerMoveTracker.poll();
            table[temp] = Integer.toString(temp + 1).charAt(0);
        }

        generateTable(table);
        System.out.println("Computer Choosed: " + (computer.moved + 1));
    }

    protected static boolean checkWinner(char[] spaces,char player) {
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
                    generateTable(spaces);
                    System.out.println("Player Won!");
                }else {
                    generateTable(spaces);
                    System.out.println("Computer Won!");
                }
                return true;
            }
        }
        return false;
    }
}