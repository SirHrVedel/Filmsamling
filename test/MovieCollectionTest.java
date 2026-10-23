import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    @Test
    void addMovie() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 1000, 120, true, "testGenre");
        assertNotNull(movieCollection.getMovies());
    }

    @Test
    void deleteMovie() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 1000, 120, true, "testGenre");
        ArrayList<Movie> movieList = movieCollection.getMovies();
        assertTrue(movieCollection.deleteMovie(movieList.getFirst()));

    }

    @Test
    void getNumberOffMovies() {
        MovieCollection movieCollection = new MovieCollection();
        assertEquals(0, movieCollection.getNumberOfMovies());
        MovieCollection movieCollection1 = new MovieCollection();

        movieCollection1.addMovie("test", "test", 1000, 120, true, "testGenre");
        movieCollection1.addMovie("test2", "test2", 1001, 121, false, "testGenre2");
        assertEquals(2, movieCollection1.getNumberOfMovies());

    }


}