import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    @Test
    void addMovie() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 2000, 120, true, "testGenre");
        assertEquals(1, movieCollection.getNumberOfMovies());

        // test values are correct.
        Movie actual = movieCollection.getMovies().getFirst();
        assertEquals("test", actual.getTitle());
        assertEquals("test", actual.getInstructor());
        assertEquals(2000, actual.getYear());
        assertEquals(120, actual.getMinutes());
        assertTrue(actual.getIsColor());
        assertEquals("testGenre", actual.getGenre());

    }

    @Test
    void addMultipleMovies(){
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 2000, 120, true, "testGenre");
        movieCollection.addMovie("test2", "test", 2000, 120, true, "testGenre");
        movieCollection.addMovie("test3", "test", 2000, 120, true, "testGenre");
        assertEquals(3, movieCollection.getNumberOfMovies());
    }

    @Test
    void deleteMovie() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 2000, 120, true, "testGenre");
        ArrayList<Movie> movieList = movieCollection.getMovies();
        assertTrue(movieCollection.deleteMovie(movieList.getFirst()));
    }

    @Test
    void deleteMovieThatDoesntExist(){
        MovieCollection movieCollection = new MovieCollection();
        Movie fakeMovie = new Movie("test3", "test", 2000, 120, true, "testGenre");
        movieCollection.addMovie("test", "test", 2000, 120, true, "testGenre");
        assertFalse(movieCollection.deleteMovie(fakeMovie));
    }

    @Test
    void searchMoviesEmpty() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("test", "test", 2000, 200, true, "horror");
        assertEquals(0, movieCollection.searchMovies("hej").size());

    }

    @Test
    void getNumberOffMovies() {
        MovieCollection movieCollection = new MovieCollection();
        assertEquals(0, movieCollection.getNumberOfMovies());
        MovieCollection movieCollection1 = new MovieCollection();

        movieCollection1.addMovie("test", "test", 2000, 120, true, "testGenre");
        movieCollection1.addMovie("test2", "test2", 2001, 121, false, "testGenre2");
        assertEquals(2, movieCollection1.getNumberOfMovies());

    }



    @Test
    void searchMoviesFindOneMovie() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("help", "help", 2000, 3000, true, "action");
        movieCollection.addMovie("ski", "ski", 2000, 3000, true, "horror");
        movieCollection.addMovie("sommer", "jens", 2000, 200, true, "romantic");
        assertEquals(1, movieCollection.searchMovies("help").size());
    }

    @Test
    void searchMoviesFindMore() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("shush", "lykke", 2000, 40, true, "horror");
        movieCollection.addMovie("shush1", "ole", 2010, 100, true, "romantic");
        movieCollection.addMovie("lost", "peter", 2020, 50, true, "action");
        assertEquals(2, movieCollection.searchMovies("Shush").size());

    }

    @Test
    void editMovieSuccess() {
        MovieCollection movieCollection = new MovieCollection();
        movieCollection.addMovie("ski", "ski", 2000, 120, true, "horror");
        boolean result = movieCollection.editMovie(
                movieCollection.getMovies().getFirst(), "ski", "ski", 2000, true, 120, "horror"
        );

        assertTrue(result);
    }
    @Test
    void editMovieFail() {
        MovieCollection movieCollection = new MovieCollection();

        Movie movie = null;

        boolean result = movieCollection.editMovie(movie, "hello", "hey", 2000, true, 120, "romantic");
        assertFalse(result);
    }

}