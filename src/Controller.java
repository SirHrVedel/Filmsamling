import java.util.Scanner;

public class Controller {
    private Scanner scanner = new Scanner(System.in);
    private MovieCollection movieCollection = new MovieCollection();
    UserInterface ui = new UserInterface();

    public void loop(){
        while (true) {
            ui.printWelcome();
            String input = UserInterface.awaitInput(scanner, "");


            switch (input.toLowerCase()) {
                case ("1"):
                    String title = UserInterface.awaitInput(scanner, "Movie title");
                    String director = UserInterface.awaitInput(scanner, "director");
                    int year = UserInterface.awaitInputInt(scanner, "Year");
                    int minutes = UserInterface.awaitInputInt(scanner, "Minutes");
                    String genre = UserInterface.awaitInput(scanner, "Genre");
                    boolean isColorMovie = decideColor();

                    movieCollection.addMovie(title, director, year, isColorMovie, minutes, genre);
                    System.out.println("the book '" + title + "' was added to the collection!");
                    break;
                case ("2"):
                    System.out.println("Goodbye");
                    return;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    private boolean decideColor() {
        while (true) {
            String colorMovieDecide = UserInterface.awaitInput(scanner, "Is it a black and white movie?");
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
}
