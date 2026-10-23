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
    void searchMoviesEmpty() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 2000, 200, true, "horror");
        assertEquals(0, movieCollection.searchMovies("hej").size());

    }

    @Test
    void searchMoviesFindOneMovie() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("help", "help", 300, 3000, true, "action");
        movieCollection.addMovie("ski", "ski", 2000, 3000, true, "horror");
        movieCollection.addMovie("sommer", "jens", 2000, 200, true, "romantic");
        assertEquals(1, movieCollection.searchMovies("help").size());
    }

    @Test
    void searchMoviesFindMore() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("shush", "lykke", 4000, 40, true, "horror");
        movieCollection.addMovie("shush1", "ole", 2000, 100, true, "romantic");
        movieCollection.addMovie("lost", "peter", 1000, 50, true, "action");
        assertEquals(2, movieCollection.searchMovies("Shush").size());

    }

}