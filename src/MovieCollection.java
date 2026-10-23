import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection(){
       movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int yearCreated, int lengthInMinutes, boolean inColor, String genre){
        Movie movie = new Movie(title, director, yearCreated, lengthInMinutes, inColor, genre);
        movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movies; // todo
    }

    public int getNumberOfMovies(){
        return movies.size();
    }

    public ArrayList<Movie> searchMovies(String text){
        ArrayList<Movie> searchResults = new ArrayList<>();
        for (Movie current : movies) {
            if (current.getTitle().toLowerCase().contains(text)) {
                searchResults.add(current);
            }
        }
        return searchResults;
    }

    public boolean editMovie(Movie movie, String title, String director, int yearCreated,
                             boolean inColor, int lengthInMinutes, String genre){
        if(movie == null){
            return false;
        }
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setInstructor(director);
        movie.setColorBlack(inColor);
        movie.setMinutes(lengthInMinutes);
        movie.setYear(yearCreated);
        return true;
    }

    public boolean deleteMovie(Movie movie){
        return movies.remove(movie);

    }

    // get the first movie with the inputted title name.
    private Movie getMovieByTitle(String title){
        for (Movie current : movies) {
            if (current.getTitle().equals(title)) {
                return current;
            }
        }
        return null;
    }

}
