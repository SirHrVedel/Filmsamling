package domainmodel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void getDaysSinceLastWatched(){
        Movie movie = new Movie("test", "test", 2001, 200, true, "test");

        movie.markAsWatched(LocalDate.now());
        long result = movie.getDaysSinceWatched(LocalDate.now());
        assertEquals(0, result);
    }

    @Test
    void daysSinceWatchedCountsAcrossMonths() {
        // Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, 124, true, "Thriller");
        movie.markAsWatched(LocalDate.of(2025, 9, 30));

        // Act
        long days = movie.getDaysSinceWatched(LocalDate.of(2025, 10, 26));

        // Assert
        assertEquals(26, days);
    }

    @Test
    void testTypeRejectionThrow() {
        Movie movieTest = new Movie("Jaws", "Steven Spielberg", 1975, 124, true, "Thriller");

        assertThrows(IllegalArgumentException.class, () -> movieTest.setTitle(""));
        assertThrows(IllegalArgumentException.class, () -> movieTest.setInstructor(""));
        assertThrows(IllegalArgumentException.class, () -> movieTest.setYear(1800));
        assertThrows(IllegalArgumentException.class, () -> movieTest.setMinutes(0));
        assertThrows(IllegalArgumentException.class, () -> movieTest.setGenre(""));
    }

}