import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Movie {
    private String title;
    private String instructor;
    private int year;
    private int minutes;
    private String genre;
    private boolean isInColor;
    private LocalDate lastWatched;

    public Movie(String title, String instructor, int year, int minutes, boolean isColor, String genre){
        setTitle(title);
        setInstructor(instructor);
        setYear(year);
        setMinutes(minutes);
        setGenre(genre);
        this.isInColor = isColor;
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
   public boolean getIsColor(){
        return isInColor;
   }
   public void setGenre(String genre){
        if (genre.isEmpty()){
            throw new IllegalArgumentException(
                    "A genre must be assigned!"
            );
        }
        this.genre = genre;
   }

    public void setIsColor(boolean isColor) {
        this.isInColor = isColor;
    }

    public void setInstructor(String instructor) {
        if (instructor.isEmpty()){
            throw new IllegalArgumentException(
                    "A director must be assigned!"
            );
        }
        this.instructor = instructor;
    }
    public void setTitle(String title){
        if (title.isEmpty()){
            throw new IllegalArgumentException(
                    "A title must be assigned!"
            );
        }
        this.title = title;
    }
    public void setYear(int year){
        int magicLimit = 1888;
        int currentYear = LocalDate.now().getYear() + 1;
        if (year <= magicLimit || year > currentYear) {
            throw new IllegalArgumentException(
                    "The year must be between " + magicLimit + " and " + currentYear + "."
            );
        }
        this.year = year;
    }
    public void setMinutes(int minutes){
        if (minutes < 1){
            throw new IllegalArgumentException(
                    "The movie's runtime must be 1 minute or longer!"
            );
        }
        this.minutes = minutes;
    }
    public void markAsWatched(LocalDate date) {
        lastWatched = date;
    }
    public void markAsNotWatched() {
        lastWatched = null;
    }

    public boolean hasBeenWatched() {
        return lastWatched != null;
    }

    public LocalDate getLastWatched() {
        return lastWatched;
    }

    public long getDaysSinceWatched(LocalDate today){
        return ChronoUnit.DAYS.between(lastWatched, today);
    }
}
