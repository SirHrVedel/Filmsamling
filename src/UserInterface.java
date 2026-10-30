import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDate;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Controller controller = new Controller();

    public void startProgram() {
        printWelcome();
        try {
            while (true) {
                String input = awaitInput(scanner, "");

                switch (input.toLowerCase()) {
                    case ("1"):
                        try {
                            addMovie();
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("An error occurred while adding the movie. " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("An error occurred.");
                        }
                        break;
                    case ("2"):
                        // delete movie
                        try {
                            deleteMovie();
                        } catch (Exception e) {
                            System.out.println("An error occurred. " + e.getMessage());
                        }

                        break;
                    case ("3"):
                        System.out.println("Goodbye");
                        return;
                    case ("help"):
                        printWelcome();
                        break;
                    case ("edit movie"):
                        try {
                            editMovie();
                        } catch (Exception e) {
                            System.out.println("An error occurred  " + e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid input!");
                }
            }
        } catch (NoSuchElementException e){
            System.out.println();
            System.out.println("Input closed. Goodbye");
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
        System.out.println("4. edit movie");
        System.out.println("5. loan movie");
        System.out.println("help");
    }

    public void loanOptions() {
        System.out.println("loan");
        System.out.println("return");
        System.out.println("due");
        System.out.println("fine");

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
            }
        }
    }

    private void addMovie() {
        String title = awaitInput(scanner, "Movie title");
        String director = awaitInput(scanner, "director");
        int year = awaitInputInt(scanner, "Year");
        int minutes = awaitInputInt(scanner, "Minutes");
        String genre = awaitInput(scanner, "Genre");
        boolean isColorMovie = decideColor();
        controller.addMovie(title, director, year, minutes, isColorMovie, genre);
        System.out.println("the movie '" + title + "' was added to the collection!");
    }

    private Movie chooseMovie(String prompt) {
        ArrayList<Movie> movies = controller.getMovies();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + " " + movies.get(i).getTitle());
        }
        int chosen = awaitInputInt(scanner, prompt);
        return movies.get(chosen - 1);
    }

    private void deleteMovie() {
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
            for (Movie movie : movies) {
                i++;
                System.out.println(i + ". " + movie.getTitle() + ", " + movie.getInstructor() + ", " + movie.getYear());
            }
            while (true) {
                int inputInt = awaitInputInt(scanner, "Input the number of he movie to be removed:");
                if (inputInt > movies.size() || inputInt <= 0) {
                    System.out.println("That is not a valid decision");
                } else {
                    Movie chosenMovie = movies.get(inputInt - 1);
                    boolean result = controller.deleteMovie(chosenMovie);
                    if (result){
                        System.out.println("The movie '" + chosenMovie.getTitle() +  "' was deleted");
                    } else System.out.println("The movie couldn't be deleted.");
                    return;
                }
            }

        }
    }

    private void editMovie() {
        ArrayList<Movie> movies = controller.getMovies();
        int count = 0;
        if (movies.isEmpty()){
            System.out.println("No movies to edit.");
            return;
        }

        for (Movie movie : movies) {
            count++;
            System.out.println(count + ". " + movie.getTitle());

        }

        while (true){
            int chosen = awaitInputInt(scanner, "Which movie would you like to edit?");

            if (chosen > movies.size() || chosen <= 0){
                System.out.println("That is not a valid decision");
            } else {
                Movie chosenMovie = movies.get(chosen - 1);
                String title = awaitInput(scanner, "Movie title");
                String director = awaitInput(scanner, "director");
                int year = awaitInputInt(scanner, "Year");
                int minutes = awaitInputInt(scanner, "Minutes");
                String genre = awaitInput(scanner, "Genre");
                boolean isColorMovie = decideColor();
                boolean result = controller.editMovie(chosenMovie, title, director, year, minutes, isColorMovie, genre);
                if (result) {
                    System.out.println("The movie was edited with success");
                } else System.out.println("The movie wasn't edited due to an error");
                return;
            }
        }
    }

}
