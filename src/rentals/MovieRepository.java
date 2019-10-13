package rentals;

public interface MovieRepository {
    MovieType typeOfMovie(String id);

    Movie getMovie(String id);

    String titleOfMovie(String id);
}
