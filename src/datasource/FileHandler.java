package datasource;

import domainmodel.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static final String HEADER = "title;director;yearCreated;lengthInMinutes;inColor;genre;lastWatched";
    private final String fileName;
    private int skippedLineCount = 0;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    // Returns null if the line is an invalid movie
    public Movie parseMovie(String line) {
        // -1 means: keep empty fields on the line, last watched can be empty and is accepted.
        String[] fields = line.split(";", -1);
        if (fields.length != 7) {
            return null;
        }
        try {
            String title = fields[0];
            String director = fields[1];
            int yearCreated = Integer.parseInt(fields[2]);
            boolean inColor = Boolean.parseBoolean(fields[3]);
            int lengthInMinutes = Integer.parseInt(fields[4]);
            String genre = fields[5];

            Movie movie = new Movie(title, director, yearCreated, lengthInMinutes, inColor, genre);
            if (!fields[6].isEmpty()) {
                movie.markAsWatched(LocalDate.parse(fields[6]));
            }
            return movie;
        } catch (IllegalArgumentException e) {
            // Også NumberFormatException, som er en slags IllegalArgumentException
            return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public void saveMovies(ArrayList<Movie> movies) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(fileName));
        output.println(HEADER);
        for (Movie movie : movies) {
            output.println(toCsvLine(movie));
        }
        output.close();
    }

    public String toCsvLine(Movie movie) {
        String lastWatched = "";   // empty -> never watched
        if (movie.hasBeenWatched()) {
            lastWatched = movie.getLastWatched().toString();   // ex 2026-10-30
        }
        return movie.getTitle() + ";"
                + movie.getInstructor() + ";"
                + movie.getYear() + ";"
                + movie.getIsColor() + ";"
                + movie.getMinutes() + ";"
                + movie.getGenre() + ";"
                + lastWatched;
    }

    public int getSkippedLineCount() {
        return skippedLineCount;
    }

    public void countSkippedLine() {
        skippedLineCount++;
    }


    public ArrayList<Movie> load() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(fileName));
        ArrayList<Movie> movies = new ArrayList<>();
        boolean isHeader = true;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            if (isHeader) {
                isHeader = false;
                continue;
            }
            Movie movie = parseMovie(line);
            if (movie != null) {
                movies.add(movie);
            } else {
                countSkippedLine();
            }
        }
        fileScanner.close();
        return movies;
    }
}
