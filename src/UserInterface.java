import java.util.Scanner;

public class UserInterface {

    public void printWelcome(){
        System.out.println("1. Create book.");
        System.out.println("2. Exit.");
    }

    public static String awaitInput(Scanner scanner, String text){
        // Await player input
        System.out.println();
        // don't write the message line if an empty string is provided
        if (!text.isEmpty()){
            System.out.println(text);
        }
        System.out.print("> ");
        return scanner.nextLine();
    }

    public static int awaitInputInt(Scanner scanner, String text){
        // Keep asking until the player enters a valid integer
        while (true) {
            try {
                // reuse await input and parse it to an integer. Reprompts if the input isn't an integer.
                return Integer.parseInt(awaitInput(scanner, text).trim());
            }
            catch (NumberFormatException e){
                System.out.println("That is not a number!");
            }
        }
    }
}
