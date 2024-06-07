package src;

public class logic {
    public static void generateTable(char[] moves) {
        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println(String.format("|   %c   |   %c   |   %c   |",moves[0],moves[1],moves[3]));
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println("|   X   |   X   |   X   |");
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
        System.out.println("|       |       |       |");
        System.out.println("|   X   |   X   |   X   |");
        System.out.println("|       |       |       |");
        System.out.println("+-------+-------+-------+");
    }
}
