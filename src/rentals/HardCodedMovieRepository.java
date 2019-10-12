package rentals;

import java.util.HashMap;

class HardCodedMovieRepository {
    HashMap<String, Movie> movies = new HashMap<>();

    HardCodedMovieRepository() {
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
    }
}
