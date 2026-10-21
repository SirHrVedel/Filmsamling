public class Movie {
    private String title;
    private String instructor;
    private int year;
    private int minutes;
    private String genre;
    private boolean isColor;

    public Movie(String title, String instructor, int year, int minutes, String genre, boolean isColor){
        this.title = title;
        this.instructor = instructor;
        this.year = year;
        this.minutes = minutes;
        this.genre = genre;
        this.isColor = isColor;
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
   public boolean getIsColorBlack(){
        return isColor;
   }
   public void setGenre(String genre){
        this.genre = genre;
   }

    public void setColorBlack(boolean isColor) {
        this.isColor = isColor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setYear(int year){
        this.year = year;
    }
    public void setMinutes(int minutes){
        this.minutes = minutes;
    }
}
