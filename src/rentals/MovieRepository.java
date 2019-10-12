package rentals;

public interface MovieRepository {
    MovieType typeOfMovie(String id);

    String titleOfMovie(String id);
}
