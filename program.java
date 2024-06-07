import java.util.Scanner;

import src.logic;

class Program {
    public static void main(String[] args) {
        Scanner req = new Scanner(System.in);
        System.out.println("What is your Name?");
        System.out.print("[+] ");
        String name = req.nextLine();
        System.out.println();

        char player;
        do {
            player = req.next().charAt(0);
        }while(!(player == 'X' || player == 'O'));
        req.close();
        char computer = player == 'X' ? 'O':'X';
        char[] moves = {player,computer,' ',' ',' ',' ',' ',' ',' '};
        logic.generateTable(moves);
    }
}