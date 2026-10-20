import java.util.Scanner;

public class Main {
    static void main(String[] args) {
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
