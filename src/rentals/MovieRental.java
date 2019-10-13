package rentals;

import java.util.Objects;

public final class MovieRental {
    private final Movie movie;
    private final int days;

    public MovieRental(Movie movie, int days) {
        this.movie = movie;
        this.days = days;
    }

    public String getMovieTitle() {
        return movie.getTitle();
    }

    public double getPrice() {
        return movie.getRentalPrice(days);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(days);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieRental)) return false;
        MovieRental other = (MovieRental) o;
        return days == other.days &&
                movie.equals(other.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, days);
    }
}
