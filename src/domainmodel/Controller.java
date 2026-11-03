package domainmodel;

import datasource.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private MovieCollection movieCollection = new MovieCollection();
    private FileHandler fileHandler = new FileHandler("movies.csv");
    private boolean changed = false;

    public ArrayList<Movie> getMovies(){
        return movieCollection.getMovies();
    }

    public void addMovie(String title, String director, int year, int lengthInMinutes, Boolean isColor, String genre){
        changed = true;
        movieCollection.addMovie(title, director, year,lengthInMinutes,isColor, genre);
    }

    public ArrayList<Movie> searchMovies(String text){

        return movieCollection.searchMovies(text);
    }

    public boolean editMovie(Movie movie, String title, String director, int year, int lengthInMinutes, boolean isColor, String genre){
        changed = true;
        return movieCollection.editMovie(movie, title, director, year, isColor, lengthInMinutes, genre);
    }

    public boolean deleteMovie(Movie movie){
        changed = true;
        return movieCollection.deleteMovie(movie);
    }

    public void markAsWatched(Movie movie, LocalDate date){
        changed = true;
        movie.markAsWatched(date);
    }

    public ArrayList<Movie> getMoviesNotWatchedForDays(int days){
        LocalDate limit = LocalDate.now().minusDays(days);
        return movieCollection.getMoviesNotWatchedSince(limit);
    }

    // Gemmer kun, hvis der er noget at gemme. Returnerer true, hvis filen blev skrevet.
    public boolean saveMoviesIfChanged() throws FileNotFoundException {
        if (!changed) {
            return false;
        }
        fileHandler.saveMovies(movieCollection.getMovies());
        changed = false;
        return true;
    }

    public int loadMovies() throws FileNotFoundException{
        Scanner fileScanner = new Scanner(new File("movies.csv"));
        ArrayList<Movie> movies = new ArrayList<>();
        boolean isHeader = true;
        while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();
            if (isHeader){
                isHeader = false;
                continue;
            }
            Movie movie = fileHandler.parseMovie(line);
            if (movie != null){
                movieCollection.addMovie(movie);
            } else {
                fileHandler.countSkippedLine();
            }
        }
        return getMovies().size();
    }

    public int getSkippedLineCount() {
        return fileHandler.getSkippedLineCount();
    }
}

