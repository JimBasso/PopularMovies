package land.basso.android.popularmovies;

import java.util.ArrayList;

/**
 * Created by jbasso on 7/9/2015.
 */
public class Movie
{
    public String posterURL;
    public String title;
    public String ID;
    public String releaseDate;
    public String runningTime;
    public String rating;
    public String overview;
    public ArrayList<Trailer> trailers = new ArrayList<Trailer>();
    public ArrayList<Review> reviews = new ArrayList<Review>();
}
