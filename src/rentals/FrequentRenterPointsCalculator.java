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

    private Integer getPoints(MovieRental rental) {
        final MovieType typeOfMovie = movieRepository.typeOfMovie(rental.getMovieId());
        final int daysRented = rental.getDays();
        return typeOfMovie.getFrequentRenterPoints(daysRented);
    }
}
