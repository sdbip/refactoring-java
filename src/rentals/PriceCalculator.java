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
        final Movie movie = repository.getMovie(rental.getMovieId());
        return movie.getRentalPrice(rental.getDays());
    }
}
