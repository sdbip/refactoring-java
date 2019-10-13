package details;

import rentals.Movie;
import rentals.MovieRepository;

import java.util.HashMap;

import static rentals.MovieType.*;

public class HardCodedMovieRepository implements MovieRepository {
    private final HashMap<String, Movie> movies = new HashMap<>();

    public HardCodedMovieRepository() {
        movies.put("F001", new Movie("You've Got Mail", regular));
        movies.put("F002", new Movie("Matrix", regular));
        movies.put("F003", new Movie("Cars", forChildren));
        movies.put("F004", new Movie("Fast & Furious X", newRelease));
    }

    @Override
    public Movie getMovie(String id) {
        return movies.get(id);
    }
}
