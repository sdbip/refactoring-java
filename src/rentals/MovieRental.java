package rentals;

public final class MovieRental {
    public final Movie movie;
    final int days;

    public MovieRental(Movie movie, int days) {
        this.movie = movie;
        this.days = days;
    }

    public double getPrice() {
        return movie.getRentalPrice(days);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(days);
    }
}
