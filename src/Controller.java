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
        //todo
        return null;
    }

    public boolean editMovie(Movie movie, String title, String director, int year, int lengthInMinutes, boolean isColor, String genre){
        //todo
        return false;
    }

    public boolean deleteMovie(Movie movie){
        //todo
        return false;
    }
}

