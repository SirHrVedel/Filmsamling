package ui;

import domainmodel.Controller;
import domainmodel.Movie;

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
        try {
            while (true) {
                try {
                    // beginning of loop
                    printWelcome();
                    String input = awaitInput(scanner, "").toLowerCase();
                    switch (input) {
                        case ("1") -> doMovieAction(1);
                        case ("2") -> deleteMovie();
                        case ("3") -> doMovieAction(2);
                        case ("4") -> markMovieAsWatched();
                        case ("5") -> makeLoan();
                        case ("6") -> printMovieList();
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
        } catch (Exception e) { // all other error, cus why not
            System.out.println("An error occurred. " + e.getMessage());
        }
    }

    private void printMovieList() {
        ArrayList<Movie> movies = controller.getMovies();
        if (movies.isEmpty()){
            System.out.println("Wow, it's empty here. Create a movie to start tracking! :-)");
            return;
        }
        int i = 0;
        for (Movie movie : movies){
            i++;
            System.out.println(i + ". " + movie.getTitle() + ", " + movie.getInstructor() + ", " + movie.getYear());
        }
    }


    private boolean decideColor() {
        while (true) {
            String colorMovieDecide = awaitInput(scanner, "Is it a black and white movie? (yes/no)");
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
        System.out.println();
        System.out.println("1. Create movie.");
        System.out.println("2. Delete movie.");
        System.out.println("3. Edit a movie");
        System.out.println("4. Mark movie as watched");
        System.out.println("5. domainmodel.Loan movie");
        System.out.println("6. Show all movies.");
        System.out.println("help - Possible commands");
        System.out.println("exit - End program");
    }

    public void loanOptions() {
        System.out.println("domainmodel.Loan");
        System.out.println("Return");
        System.out.println("Due");
        System.out.println("Fine");
    }

    public static String awaitInput(Scanner scanner, String text) {
        // Await player input
        System.out.println();
        // don't write the message line if an empty text string is provided
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
                System.out.println("That is not a whole number! Enter a whole number ex: 1, 2, 3 or 4");
            }
        }
    }

    private Movie chooseMovie(String actionMsg) {
        ArrayList<Movie> movies = controller.getMovies();
        if (!actionMsg.isEmpty()) {
            System.out.println(actionMsg);
        }
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            System.out.println((i + 1) + ". " + movie.getTitle() + ", " + movie.getInstructor() + ", " + movie.getYear());
        }
        System.out.println("Type 0 to cancel movie choice.");
        while (true) {
            int chosen = awaitInputInt(scanner, "Which movie?");
            if (chosen == 0){
                System.out.println("Cancelled choice");
                return null;
            }
            if (chosen < 1 || chosen > movies.size()) {
                System.out.println("Invalid decision!");
            } else return movies.get(chosen - 1);
        }
    }

    private void deleteMovie() {
        if (controller.getMovies().isEmpty()) {
            System.out.println("Theres no movies to delete.");
            return;
        }
        String inputString = awaitInput(scanner, "What is the name of the movie you want to remove?");
        ArrayList<Movie> movies = controller.searchMovies(inputString);

        if (movies.isEmpty()) {
            System.out.println("No movies of that name was found");
        } else {
            Movie chosenMovie = chooseMovie("Which movie do you want to delete?");
            if (chosenMovie == null){
                return;
            }
            boolean result = controller.deleteMovie(chosenMovie);
            if (result) {
                System.out.println("The movie '" + chosenMovie.getTitle() + "' was deleted");
            } else System.out.println("The movie couldn't be deleted.");

        }
    }


    public void markMovieAsWatched() {
        ArrayList<Movie> moviesNow = controller.getMovies();
        if (moviesNow.isEmpty()) {
            System.out.println("The are no movies to mark as watched");
            return;
        }
        ;
        Movie chosenMovie = chooseMovie("Which movie do you want to mark as watched?");
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
        Movie loanMovie = chooseMovie("Which movie do you want to loan?");
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
                System.out.println("domainmodel.Movie returned");
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
        if (saveResult) {
            System.out.println("Saved movies to file");
        } else System.out.println("No changes was made, save not needed.");
        System.out.println("Goodbye!");
    }

    /*
        doMovieAction()
        Supports creating and editing a movie. validates input on the fly and reprompts on invalid input at the current step.
        action
            1 = create movie
            2 = edit movie
     */
    // spaghetti code lamo
    public void doMovieAction(int action) {

        // start prompting for which movie immediately when editing
        Movie chosenMovie = null;
        if (action == 2) {
            ArrayList<Movie> movies = controller.getMovies();
            if (movies.isEmpty()) {
                System.out.println("No movies to edit.");
                return;
            } else {
                chosenMovie = chooseMovie("Which movie do you want to edit?");
                if (chosenMovie == null){
                    return;
                }
            }
        }

        Movie validatorMovie = new Movie("test", "test", 1889, 1, true, "test");
        String title;
        String director;
        int year;
        int minutes;
        String genre;
        boolean isColorMovie;


        // loops for each parameter (movie info) so we reprompt when the input is invalid

        // title
        while (true) {
            try {
                title = awaitInput(scanner, "domainmodel.Movie title");
                validatorMovie.setTitle(title);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // director
        while (true) {
            try {
                director = awaitInput(scanner, "Director");
                validatorMovie.setInstructor(director);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // year
        while (true) {
            try {
                year = awaitInputInt(scanner, "Year");
                validatorMovie.setYear(year);

                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // minutes
        while (true) {
            try {
                minutes = awaitInputInt(scanner, "Minutes");
                validatorMovie.setMinutes(minutes);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // genre
        while (true) {
            try {
                genre = awaitInput(scanner, "Genre");
                validatorMovie.setGenre(genre);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // color
        while (true) {
            try {
                isColorMovie = decideColor();
                validatorMovie.setIsColor(isColorMovie);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // create movie
        if (action == 1) {
            controller.addMovie(title, director, year, minutes, isColorMovie, genre);
            System.out.println("the movie '" + title + "' was added to the collection!");
        }

        // edit movie
        else if (action == 2) {
            boolean result = controller.editMovie(chosenMovie, title, director, year, minutes, isColorMovie, genre);
            if (result) {
                System.out.println("The movie was edited with success");
            } else System.out.println("The movie wasn't edited due to an error");
        }
    }

    public void printMoviesAlphabetical(){


    }
}


