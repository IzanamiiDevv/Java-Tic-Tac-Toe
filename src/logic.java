package src;

public class logic {
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
}
