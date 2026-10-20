import java.util.Scanner;

import java.util.ArrayList;

public class Main {
    static void main(String[] args) {

        ArrayList<Movie> movieList = new ArrayList<>();

        Movie movie = new Movie("Lykken", "Jaden", 2008,58,"Horror", "black");
        movieList.add(movie);

        System.out.println("Hello World!");

        UserInterface ui = new UserInterface();
        Scanner scanner = new Scanner(System.in);

        while (true){
            ui.printWelcome();
            String input = UserInterface.awaitInput(scanner, "");

            switch (input.toLowerCase()){
                case ("1"):
                    System.out.println("Not implemented");
                case ("2"):
                    System.out.println("Goodbye");
                    return;
            }

        }
    }
}
