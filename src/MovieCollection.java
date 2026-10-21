import java.util.ArrayList;

public class MovieCollection {
    private ArrayList<Movie> movies;

    public MovieCollection(){
       movies = new ArrayList<>();
    }

    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre){
        //todo
    }

    public ArrayList<Movie> getMovies() {
        return movies; // todo
    }

    public int getNumberOfMovies(){
        return movies.size();
    }

    public ArrayList<Movie> searchMovies(String text){

        return null; //todo
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
    if(movies.remove(movie)){
        return true;
    }
    else{
        return false;
    }

    }

}
