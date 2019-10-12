package rentals;

public class MovieRental2 {
    private final MovieType typeOfMovie;
    private final int daysRented;

    public MovieRental2(MovieType typeOfMovie, int daysRented) {
        this.typeOfMovie = typeOfMovie;
        this.daysRented = daysRented;
    }

    public MovieType getTypeOfMovie() {
        return typeOfMovie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
