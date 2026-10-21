import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    @Test
    void addMovie() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("test", "test", 1000, 200, "test", false));
        assertNotNull(movies);
    }
}