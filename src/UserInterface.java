import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller = new Controller();

    public void startProgram() {
        loadMovies();
        printWelcome();
        try {
            while (true) {
                try {
                    String input = awaitInput(scanner, "").toLowerCase();
                    switch (input) {
                        case ("1") -> addMovie();
                        case ("2") -> deleteMovie();
                        case ("3") -> editMovie();
                        case ("4") -> markMovieAsWatched();
                        case ("5") -> makeLoan();
                        case ("help") -> printWelcome();
                        case ("exit") -> {
                            exitMsg();
                            return;
                        }
                        default -> System.out.println("Unknown command: " + input + ".");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("Input closed. Goodbye!");
        } catch (Exception e) {
            System.out.println("An error occurred. " + e.getMessage());
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
        System.out.println("3. Edit a movie");
        System.out.println("4. Mark movie as watched");
        System.out.println("5. loan movie");

        System.out.println("help - possible commands");
        System.out.println("exit - end program");
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
                    if (result) {
                        System.out.println("The movie '" + chosenMovie.getTitle() + "' was deleted");
                    } else System.out.println("The movie couldn't be deleted.");
                    return;
                }
            }

        }
    }

    private void editMovie() {
        ArrayList<Movie> movies = controller.getMovies();
        int count = 0;
        if (movies.isEmpty()) {
            System.out.println("No movies to edit.");
            return;
        }

        for (Movie movie : movies) {
            count++;
            System.out.println(count + ". " + movie.getTitle());

        }

        while (true) {
            int chosen = awaitInputInt(scanner, "Which movie would you like to edit?");

            if (chosen > movies.size() || chosen <= 0) {
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

    public void markMovieAsWatched() {
        ArrayList<Movie> moviesNow = controller.getMovies();
        if (moviesNow.isEmpty()) {
            System.out.println("The are no movies to mark as watched");
            return;
        }
        int number = 0;
        for (Movie movies : moviesNow) {
            number++;
            System.out.println(number + " " + movies.getTitle());
        }
        int seen = awaitInputInt(scanner, "which movie would you like to mark as seen");
        if(seen >= moviesNow.size()){
            System.out.println("that is not a valid choice");
            return;
        }
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
            if (date.isAfter(LocalDate.now())) {
                System.out.println("the date is in the future");
            } else {
                controller.markAsWatched(chosenMovie, date);
            }
            break;
        }
    }

    public void makeLoan() {
        //Todo, gør så der kan printes en liste af de lånte bøger og ret så
        // at man ikke skal vælge loan option og bog på ny når man ville returnere
        // eller en anden option

        loanOptions();
        Movie loanMovie = chooseMovie("which movie?");
        System.out.println("What action do you want to do?");
        String action = awaitInput(scanner, "loan / return / due / fine");
        LocalDate today = LocalDate.now();
        switch (action.toLowerCase()) {
            case "loan" -> {
                loanMovie.loanOut(today);
                System.out.println("Loaned out. Due: " + loanMovie.getLoan().getDueDate());
            }
            case "return" -> {
                loanMovie.returnMovie();
                System.out.println("Movie returned");
            }
            case "due" -> {
                if (loanMovie.isLoaned()) {
                    System.out.println("Due date: " + loanMovie.getLoan().getDueDate());
                } else {
                    System.out.println("There is no loan for this movie");
                }
            }
            case "fine" -> {
                if (loanMovie.isLoaned()) {
                    System.out.println("Fine: " + loanMovie.getLoan().getFine(today) + " Kr.");
                } else {
                    System.out.println("There is no fine");
                }
            }
            default -> System.out.println("Invalid loan action!");
        }
    }

    private void loadMovies() {
        try {
            int count = controller.loadMovies();
            System.out.println(count + " movies are loaded.");
            int skipped = controller.getSkippedLineCount();
            if (skipped > 0) {
                System.out.println("Warning: " + skipped + " line(s) could not be read, and was skipped.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("There's no saved movie collection yet, you begin with an empty collection");
        }
    }

    private void exitMsg() throws FileNotFoundException {
        boolean saveResult = controller.saveMoviesIfChanged();
        if (saveResult){
            System.out.println("Saved movies to file");
        } else System.out.println("No changes was made, save not needed.");
        System.out.println("Goodbye!");

    }
}
