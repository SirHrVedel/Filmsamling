package datasource;

import domainmodel.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    private static final String TEST_FILE = "test-movies.csv";

    @AfterEach
    void deleteTestFile() {
        new File(TEST_FILE).delete();
    }

    @Test
    void savedRunsCanBeLoadedAgain() throws FileNotFoundException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Jaws", "Steven Spielberg", 1975, 124, true, "Thriller"));
        movies.add(new Movie("Jaws2", "Steven Spielberg", 1976, 124, true, "Thriller"));

        // Act
        fileHandler.saveMovies(movies);
        ArrayList<Movie> loaded = fileHandler.load();

        // Assert
        assertEquals(2, loaded.size());
        Movie first = loaded.getFirst();
        assertEquals(1975, first.getYear());
        assertEquals(124, first.getMinutes());
        assertEquals(1976, loaded.get(1).getYear());
    }

    @Test
    void emptyListGivesEmptyFile() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler(TEST_FILE);

        fileHandler.saveMovies(new ArrayList<>());

        assertTrue(fileHandler.load().isEmpty());
    }

    @Test
    void missingFileThrowsFileNotFoundException() {
        FileHandler fileHandler = new FileHandler("findes-ikke.csv");

        assertThrows(FileNotFoundException.class, () -> fileHandler.load());
    }

    @Test
    void brokenLinesAreSkippedAndCounted() throws FileNotFoundException {
        // Arrange: en fil med to gode og tre ødelagte linjer
        PrintStream output = new PrintStream(new File(TEST_FILE));
        output.println("title;director;yearCreated;lengthInMinutes;inColor;genre;lastWatched");
        output.println("2026-10-25;5.2;31");     // wrong
        output.println("25-10-2026;5.2;31");     // wrong
        output.println("2026-10-26;fem;31");     // wrong
        output.println("2026-10-27;5.2");        // wrong
        output.println("2026-10-28;10.0;58");    // wrong
        output.close();
        FileHandler fileHandler = new FileHandler(TEST_FILE);

        // Act
        ArrayList<Movie> loaded = fileHandler.load();

        // Assert
        assertEquals(0, loaded.size());
        assertEquals(5, fileHandler.getSkippedLineCount());
    }

    @Test
    void invalidRunInFileIsSkipped() throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(TEST_FILE));
        output.println("title;director;yearCreated;lengthInMinutes;inColor;genre;lastWatched");
        output.println("2026-10-25;0;31");       // wrong
        output.close();
        FileHandler fileHandler = new FileHandler(TEST_FILE);

        assertTrue(fileHandler.load().isEmpty());
        assertEquals(1, fileHandler.getSkippedLineCount());
    }

}