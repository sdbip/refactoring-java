package rentals;

import java.util.stream.Stream;

class FrequentRenterPointsCalculator {
    private final MovieRepository movieRepository;

    FrequentRenterPointsCalculator(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    int getPoints(Stream<MovieRental> rentals) {
        return rentals.map(this::getPoints).reduce(0, Integer::sum);
    }

    private int getPoints(MovieRental rental) {
        final Movie movie = movieRepository.get(rental.movieId);
        return movie.getFrequentRenterPoints(rental.days);
    }
}
