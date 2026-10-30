import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

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
                    System.out.println("the book '" + title + "' was added to the collection!");
                    break;
                case ("2"):
                    System.out.println("Goodbye");
                    return;
                case ("6"): {
                    ArrayList<Movie> moviesNow = controller.getMovies();
                    int number = 0;
                    for (Movie movies : moviesNow) {
                        number++;
                        System.out.println(number + " " + movies.getTitle());
                    }
                    int seen = awaitInputInt(scanner, "which movie would you like to mark as seen");
                    Movie chosenMovie = moviesNow.get(seen - 1);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date;
                    while (true) {
                        String dateInput = awaitInput(scanner, "dd-MM-yyyy");
                        if (dateInput.isEmpty()) {
                            date = LocalDate.now();

                        } else {
                            try {
                                date = LocalDate.parse(dateInput, formatter);
                            } catch (DateTimeParseException c) {
                                System.out.println("date is not valid");
                                continue;
                            }

                        }
                        if(date.isAfter(LocalDate.now())) {
                            System.out.println("the date is in the future");
                        }
                        else {
                            controller.markAsWatched(chosenMovie, date);
                        }
                        break;
                    }
                    break;
                }
                case ("help"):
                    printWelcome();
                    break;
                case ("edit movie"):
                    ArrayList<Movie> movies = controller.getMovies();
                    int count = 0;
                    for (Movie movie : movies) {
                        count++;
                        System.out.println(count + " " + movie.getTitle());
                    }
                    int chosen = awaitInputInt(scanner, "which movie would you like to edit");
                    Movie chosenMovie = movies.get(chosen - 1);
                    String title2 = awaitInput(scanner, "Movie title");
                    String director2 = awaitInput(scanner, "director");
                    int year2 = awaitInputInt(scanner, "Year");
                    int minutes2 = awaitInputInt(scanner, "Minutes");
                    String genre2 = awaitInput(scanner, "Genre");
                    boolean isColorMovie2 = decideColor();
                    controller.editMovie(chosenMovie, title2, director2, year2, minutes2, isColorMovie2, genre2);
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
        System.out.println("6. Marker en film som set");
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
