import java.util.Scanner;

public class UserInterface {

    public void printWelcome(){
        System.out.println("1. Create book.");
        System.out.println("2. Exit.");
    }

    public static String awaitInput(Scanner scanner, String text){
        // Await player input
        System.out.println();
        System.out.println(text);
        System.out.print("> ");
        return scanner.nextLine();
    }

    public static int awaitInputInt(Scanner scanner, String text){
        // Await player input
        System.out.println();
        System.out.println(text);
        System.out.print("> ");
        return scanner.nextInt();
    }
}
