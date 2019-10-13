package details;

import rentals.Movie;
import rentals.MovieRepository;

import java.util.Arrays;
import java.util.HashMap;

import static rentals.MovieType.*;

public class HardCodedMovieRepository implements MovieRepository {
    private final HashMap<String, Movie> movies = new HashMap<>();

    public HardCodedMovieRepository() {
        final Movie[] movies = {
                new Movie("F001", "You've Got Mail", regular),
                new Movie("F002", "Matrix", regular),
                new Movie("F003", "Cars", forChildren),
                new Movie("F004", "Fast & Furious X", newRelease)
        };

        for (Movie movie : movies)
            this.movies.put(movie.getId(), movie);
    }

    @Override
    public Movie getMovie(String id) {
        return movies.get(id);
    }
}
