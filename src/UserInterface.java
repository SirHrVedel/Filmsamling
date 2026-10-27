import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();

    public void startProgram() {
        printWelcome();
        while (true) {
            String input = awaitInput(scanner, "");


            switch (input.toLowerCase()) {
                case ("1"):
                    String title = awaitInput(scanner, "Movie title");
                    String director = awaitInput(scanner, "director");
                    int year = awaitInputInt(scanner, "Year");
                    int minutes = awaitInputInt(scanner, "Minutes");
                    String genre = awaitInput(scanner, "Genre");
                    boolean isColorMovie = decideColor();

                    controller.addMovie(title, director, year, minutes, isColorMovie, genre);
                    System.out.println("the movie '" + title + "' was added to the collection!");
                    break;
                case ("2"):
                    System.out.println("Goodbye");
                    return;
                case ("help"):
                    printWelcome();
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private boolean decideColor() {
        while (true) {
            String colorMovieDecide = awaitInput(scanner, "Is it a black and white movie?");
            switch (colorMovieDecide.toLowerCase()) {
                case "yes":
                    return false;
                case "no":
                    return true;
                default:
                    System.out.println("Invalid decision. Only yes/no is accepted");
            }
        }
    }

    public void printWelcome() {
        System.out.println("1. Create book.");
        System.out.println("2. Exit.");
    }

    public static String awaitInput(Scanner scanner, String text) {
        // Await player input
        System.out.println();
        // don't write the message line if an empty string is provided
        if (!text.isEmpty()) {
            System.out.println(text);
        }
        System.out.print("> ");
        return scanner.nextLine();
    }

    public static int awaitInputInt(Scanner scanner, String text) {
        // Keep asking until the player enters a valid integer
        while (true) {
            try {
                // reuse await input and parse it to an integer. Reprompts if the input isn't an integer.
                return Integer.parseInt(awaitInput(scanner, text).trim());
            } catch (NumberFormatException e) {
                System.out.println("That is not a number!");
            }
        }
    }
}
