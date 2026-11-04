import domainmodel.Controller;
import domainmodel.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;


    class MarkMovieAsWatchedTest {
        private Movie movie;
        private Controller controller;


        @BeforeEach
        void setup(){ movie = new Movie("Jaws", "Steven Spielberg", 1975, 124, true, "Thriller");
        }
        @Test
        void markMovieAsWatchedWithDate(){
            LocalDate date = LocalDate.of(2025, 9,10);
            movie.markAsWatched(date);
            assertEquals(date, movie.getLastWatched());
        }

    }

