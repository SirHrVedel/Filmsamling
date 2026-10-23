import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    @Test
    void addMovie() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 1000, 120, true, "testGenre");
        assertEquals(1, movieCollection.getNumberOfMovies());

        // test values are correct.
        Movie actual = movieCollection.getMovies().getFirst();
        assertEquals("test", actual.getTitle());
        assertEquals("test", actual.getInstructor());
        assertEquals(1000, actual.getYear());
        assertEquals(120, actual.getMinutes());
        assertTrue(actual.getIsColor());
        assertEquals("testGenre", actual.getGenre());

    }

    @Test
    void addMultipleMovies(){
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 1000, 120, true, "testGenre");
        movieCollection.addMovie("test2", "test", 1000, 120, true, "testGenre");
        movieCollection.addMovie("test3", "test", 1000, 120, true, "testGenre");
        assertEquals(3, movieCollection.getNumberOfMovies());
    }

    @Test
    void deleteMovie() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 1000, 120, true, "testGenre");
        ArrayList<Movie> movieList = movieCollection.getMovies();
        assertTrue(movieCollection.deleteMovie(movieList.getFirst()));
    }

    @Test
    void deleteMovieThatDoesntExist(){
        MovieCollection movieCollection = new MovieCollection();

        movieCollection.addMovie("test", "test", 1000, 120, true, "testGenre");
        ArrayList<Movie> movieList = movieCollection.getMovies();
        assertFalse(movieCollection.deleteMovie(movieList.getFirst()));
    }
}