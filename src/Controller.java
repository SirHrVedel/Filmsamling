import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private MovieCollection movieCollection = new MovieCollection();

    public ArrayList<Movie> getMovies(){
        return movieCollection.getMovies();
    }

    public void addMovie(String title, String director, int year, int lengthInMinutes, Boolean isColor, String genre){
        movieCollection.addMovie(title, director, year,lengthInMinutes,isColor, genre);
    }

    public ArrayList<Movie> searchMovies(String text){

        return movieCollection.searchMovies(text);
    }

    public boolean editMovie(Movie movie, String title, String director, int year, int lengthInMinutes, boolean isColor, String genre){

        return movieCollection.editMovie(movie, title, director, year, isColor, lengthInMinutes, genre);
    }

    public boolean deleteMovie(Movie movie){
        return movieCollection.deleteMovie(movie);
    }

    public void markAsWatched(Movie movie){
        movie.markAsWatched(LocalDate.now());
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days){
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.getMoviesNotWatchedSince(limit);
    }
}

