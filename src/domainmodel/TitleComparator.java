package domainmodel;

import java.util.Comparator;

public class TitleComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie movie1, Movie movie2){

        return CharSequence.compare(movie1.getTitle(), movie2.getTitle());
    }
}
