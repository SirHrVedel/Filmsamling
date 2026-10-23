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
}