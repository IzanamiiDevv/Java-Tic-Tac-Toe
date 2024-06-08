import java.util.Scanner;
import src.Style;

import src.logic;

class Program extends logic {
    public static void main(String[] args) {
        Scanner req = new Scanner(System.in);
        System.out.println("What is your Name?");
        System.out.print("[+] ");
        String name = req.nextLine();
        System.out.println();

        char player;
        do {
            System.out.println("Choose Your Character (X/O)?");
            System.out.print("[+] ");
            player = req.next().charAt(0);
            req.nextLine();
        }while(!(player == 'X' || player == 'O'));

        char computer = player == 'X' ? 'O':'X';

        char[] moves = {' ',' ',' ',' ',' ',' ',' ',' ',' '};

        Style.SetColor(Style.Colors.RED);
        System.out.println("Hello World");
        Style.ClearColor();

        generateTable(moves);
        playerMove(req, moves, player);
        checkWinner(moves, player);
    }
}