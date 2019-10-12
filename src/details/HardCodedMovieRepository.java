package details;

import rentals.MovieRepository;
import rentals.MovieType;

import java.util.HashMap;

public class HardCodedMovieRepository implements MovieRepository {
    private final HashMap<String, Movie> movies = new HashMap<>();

    public HardCodedMovieRepository() {
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
    }

    @Override
    public MovieType typeOfMovie(String id) {
        final Movie movie = movies.get(id);
        switch (movie.getCode()) {
            case "regular":
                return MovieType.regular;
            case "childrens":
                return MovieType.forChildren;
            case "new":
                return MovieType.newRelease;
            default:
                return null;
        }
    }

    @Override
    public String titleOfMovie(String id) {
        final Movie movie = movies.get(id);
        return movie.getTitle();
    }
}
