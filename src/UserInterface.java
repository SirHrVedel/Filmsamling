import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();

    public void startProgram() {
        printWelcome();
        while (true) {
            String title;
            String director;
            int year;
            int minutes;
            String genre;
            boolean isColorMovie;
            String input = awaitInput(scanner, "");
            int inputInt;

            switch (input.toLowerCase()) {
                case ("1"):
                    title = awaitInput(scanner, "Movie title");
                    director = awaitInput(scanner, "director");
                    year = awaitInputInt(scanner, "Year");
                    minutes = awaitInputInt(scanner, "Minutes");
                    genre = awaitInput(scanner, "Genre");
                    isColorMovie = decideColor();
                    try {
                        controller.addMovie(title, director, year, minutes, isColorMovie, genre);
                    } catch (IllegalArgumentException e) {
                        System.out.println("The movie couldn't be created: " + e.getMessage());
                    } catch (Exception e){
                        System.out.println("An error occurred.");
                    }

                    System.out.println("the movie '" + title + "' was added to the collection!");
                    break;
                case ("2"):
                    // delete movie
                    deleteMovie();
                    break;
                case ("3"):
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
        System.out.println("1. Create movie.");
        System.out.println("2. Delete movie.");
        System.out.println("3. Exit.");
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
                System.out.println("That is not a whole number!");
            } catch (Exception e){
                System.out.println("An error occurred.");
            }
        }
    }

    private void deleteMovie(){
        String inputString = awaitInput(scanner, "What is the name of the movie you want to remove?");
        String movieTitle;
        ArrayList<Movie> movies = controller.searchMovies(inputString);

        if (movies.isEmpty()) {
            System.out.println("No movies of that name was found");
        } else if (movies.size() == 1) {
            controller.deleteMovie(movies.getFirst());
            movieTitle = movies.getFirst().getTitle();
            System.out.println("The movie '" + movieTitle + "' was deleted");
        } else {
            System.out.println("Multiple movies of the name: '" + inputString + "' was found.");
            System.out.println("Which one should be removed?");
            int i = 0;
            for (Movie movie : movies){
                i++;
                System.out.println(i + ". " + movie.getTitle() + ", " + movie.getInstructor() + ", " + movie.getYear());
            }
            while (true){
                int inputInt = awaitInputInt(scanner, "Input the number of he movie to be removed:");
                if (inputInt > movies.size() || inputInt <= 0){
                    System.out.println("That is not a valid decision");
                } else {
                    Movie chosenMovie = movies.get(inputInt - 1);
                    controller.deleteMovie(chosenMovie);
                    return;
                }
            }

        }
    }

}
