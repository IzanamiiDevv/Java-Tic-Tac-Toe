import java.util.Random;
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

        Random rand = new Random();
        int random = rand.nextInt(0,2);
        boolean isFirst = true; // random == 1

        ComputerAI computer = new ComputerAI(player == 'X' ? 'O':'X', "hard");    
        if(isFirst) generateTable(table);
        boolean isRunning = true;
        while (isRunning) {
            if(isFirst){
                playerMove(req, player);
                isRunning = !checkWinner(table, player);
                if(!isRunning) break;
                computerMove(computer);
                isRunning = !checkWinner(table, player);
                
            } else {
                computerMove(computer);
                isRunning = !checkWinner(table, player);
                if(!isRunning) break;
                playerMove(req, player);
                isRunning = !checkWinner(table, player);
            }
        }
    }
}