package rentals;

import java.util.stream.Stream;

class PriceCalculator {
    private final MovieRepository repository;

    PriceCalculator(MovieRepository repository) {
        this.repository = repository;
    }

    double getTotalPrice(Stream<MovieRental> rentals) {
        return rentals.map(this::getPrice)
                .reduce(0.0, Double::sum);
    }

    double getPrice(MovieRental rental) {
        final MovieType typeOfMovie = repository.getMovie(rental.getMovieId()).getType();
        return typeOfMovie.getPrice(rental.getDays());
    }
}
