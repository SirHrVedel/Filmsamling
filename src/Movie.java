public class Movie {
    private String title;
    private String instructor;
    private int year;
    private int minutes;
    private String genre;
    private boolean isColorBlack;

    public Movie(String title, String instructor, int year, int minutes, String genre, boolean isColorBlack){
        this.title = title;
        this.instructor = instructor;
        this.year = year;
        this.minutes = minutes;
        this.genre = genre;
        this.isColorBlack = isColorBlack;
    }
}
