import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FileHandler {
    private static final String HEADER = "title;director;yearCreated;lengthInMinutes;inColor;genre;lastWatched";
    private String fileName;
    private int skippedLineCount = 0;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    // Returnerer null, hvis linjen ikke er en gyldig film
    public Movie parseMovie(String line) {
        // -1 betyder: behold tomme felter i slutningen af linjen (lastWatched kan være tom)
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
        String lastWatched = "";   // tomt felt = aldrig set
        if (movie.hasBeenWatched()) {
            lastWatched = movie.getLastWatched().toString();   // fx 2026-10-30
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
}
