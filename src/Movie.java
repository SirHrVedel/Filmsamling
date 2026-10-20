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
   public String getTitle(){
        return title;
   }
   public String getInstructor(){
        return instructor;
   }
   public int getYear(){
        return year;
   }
   public int getMinutes(){
        return minutes;
   }
   public String getGenre(){
        return genre;
   }
   public boolean isColorBlack(){
        return isColorBlack;
   }
}
