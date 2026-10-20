import java.util.Scanner;

import java.util.ArrayList;

public class Main {
    static void main(String[] args) {

        ArrayList<Movie> movieList = new ArrayList<>();

        Movie movie = new Movie("Lykken", "Jaden", 2008,58,"Horror", false);
        movieList.add(movie);


        UserInterface ui = new UserInterface();
        Scanner scanner = new Scanner(System.in);

        while (true){
            ui.printWelcome();
            String input = UserInterface.awaitInput(scanner, "");

            switch (input.toLowerCase()){
                case ("1"):
                    System.out.println("Not implemented");
                    break;
                case ("2"):
                    System.out.println("Goodbye");
                    return;
                default:
                    System.out.println("Invalid input!");
                    break;
            }

        }
    }
}
